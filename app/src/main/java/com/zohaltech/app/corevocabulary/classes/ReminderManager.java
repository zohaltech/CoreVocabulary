package com.zohaltech.app.corevocabulary.classes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.google.gson.Gson;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;
import com.zohaltech.app.corevocabulary.serializables.Reminder;
import com.zohaltech.app.corevocabulary.serializables.ReminderSettings;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ReminderManager
{
    public static String REMINDER_SETTINGS = "reminder_settings";
    private static String LAST_REMINDER = "last_reminder";

    // this method is meant to be called just by AlarmReceiver class!
    public static void registerNextReminder(int currentVocabularyId, boolean doesTriggersNext)
    {
        Vocabulary current = Vocabularies.select(currentVocabularyId);
        if (current == null)
        {
            // exception occurred.
            return;
        }

        current.setLearned(true);
        Vocabularies.update(current);

        if (!doesTriggersNext)
        {
            return;
        }

        ReminderSettings settings = ReminderManager.getReminderSettings();

        Vocabulary next = Vocabularies.next(currentVocabularyId);
        if (next == null)
        {
            settings.setStatus(ReminderSettings.Status.FINISHED);
            settings.setReminder(null);
            ReminderManager.setReminderSettings(settings);
            return;
        }

        Reminder reminder = new Reminder(next.getId(), null, next.getVocabulary(), next.getVocabEnglishDef(), true);

        if (current.getDay() == next.getDay())
        {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, settings.getIntervals());

            reminder.setTime(calendar.getTime());
            settings.setReminder(reminder);
            setReminderSettings(settings);

            addAlarm(reminder);
        }
        else
        {
            settings.setReminder(reminder);
            setReminderSettings(settings);
            start(false);
        }
    }

    public static void start(boolean isResume)
    {
        ReminderSettings settings = getReminderSettings();
        if (settings == null || settings.getReminder() == null)
        {
            return;
        }

        if (settings.getStatus() != ReminderSettings.Status.RUNNING)
        {
            return;
        }

        Vocabulary vocabulary = Vocabularies.select(settings.getReminder().getVocabularyId());
        if (vocabulary == null)
        {
            return;
        }

        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        int[] timeParts = getTimeParts(settings.getStartTime());
        int nextDayOffset = 0;
        long elapsedMinutes = getElapsedMinutes();  // returns minutes passed from midnight till now
        int startTime = timeParts[0] * 60 + timeParts[1];

        Reminder nextReminder = settings.getReminder();
        Date now = new Date();
        if (nextReminder.getTime() == null)
        {
            isResume = false;
        }
        else if (nextReminder.getTime().after(now))
        {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            isResume = formatter.format(nextReminder.getTime()).equals(formatter.format(now));
        }

        // in case user manually resumes reminder
        if (isResume && settings.getWeekdays()[today - 1] && elapsedMinutes >= startTime)
        {
            Reminder lastReminder = getLastReminder();
            Vocabulary lastVocabulary = null;
            if (lastReminder != null)
            {
                lastVocabulary = Vocabularies.select(lastReminder.getVocabularyId());
            }

            // if there is no reminder at all or current vocabulary isn't in another group
            if (lastVocabulary == null || vocabulary.getDay() == lastVocabulary.getDay())
            {
                ArrayList<Vocabulary> siblings = Vocabularies.selectSiblings(vocabulary.getId());
                for (int j = 0; j < siblings.size(); j++)
                {
                    if (vocabulary.getId() > siblings.get(j).getId())
                    {
                        continue;
                    }

                    Vocabulary current = siblings.get(j);
                    calendar.add(Calendar.SECOND, 1);
                    Date time = calendar.getTime();

                    if (elapsedMinutes < startTime + (j * settings.getIntervals()))
                    {
                        time = getTime(nextDayOffset, startTime + j * settings.getIntervals());
                    }

                    Reminder reminder = new Reminder(current.getId(), time, current.getVocabulary(), current.getVocabEnglishDef(), false);

                    // we'll meet this condition for sure!
                    if (j == siblings.size() - 1 || elapsedMinutes < startTime + (j * settings.getIntervals()))
                    {
                        reminder.setTriggerNext(true);

                        settings.setReminder(reminder);
                        addAlarm(reminder);
                        ReminderManager.setReminderSettings(settings);

                        return;
                    }
                    else
                    {
                        settings.setReminder(reminder);
                        addAlarm(reminder);
                        ReminderManager.setReminderSettings(settings);
                    }
                }
            }
        }

        if (!settings.getWeekdays()[today - 1] || elapsedMinutes > startTime)
        {
            for (int j = 1; j <= 7; j++)
            {
                if (settings.getWeekdays()[(today - 1 + j) % 7])
                {
                    nextDayOffset = j;
                    break;
                }
            }
        }

        Date alarmTime = getTime(nextDayOffset, startTime);
        settings.getReminder().setTime(alarmTime);
        ReminderManager.setReminderSettings(settings);

        addAlarm(new Reminder(vocabulary.getId(), alarmTime, vocabulary.getVocabulary(), vocabulary.getVocabEnglishDef(), true));
    }

    public static void pause()
    {
        ReminderSettings settings = getReminderSettings();
        if (settings == null)
        {
            return;
        }

        if (settings.getReminder() != null)
        {
            removeAlarm(settings.getReminder().getVocabularyId());
        }

        settings.setStatus(ReminderSettings.Status.PAUSE);
        setReminderSettings(settings);
    }

    public static void stop()
    {
        setLastReminder(null);
        ReminderSettings settings = getReminderSettings();
        if (settings == null)
        {
            return;
        }

        if (settings.getReminder() != null)
        {
            removeAlarm(settings.getReminder().getVocabularyId());
        }

        settings.setStatus(ReminderSettings.Status.STOP);
        settings.setReminder(null);
        setReminderSettings(settings);

        Vocabularies.resetLearnedVocabularies();
    }

    public static ReminderSettings getReminderSettings()
    {
        Gson gson = new Gson();
        String alarmJson = App.preferences.getString(REMINDER_SETTINGS, null);

        if (alarmJson == null || alarmJson.equals(""))
        {
            return new ReminderSettings("12:00", 60, null, ReminderSettings.Status.STOP, new boolean[]{true, false, true, false, true, false, false});
        }

        return gson.fromJson(alarmJson, ReminderSettings.class);
        //return Helper.deserializeReminderSettings();
    }

    public static void setReminderSettings(ReminderSettings settings)
    {
        Gson gson = new Gson();
        App.preferences.edit().putString(REMINDER_SETTINGS, gson.toJson(settings)).apply();
        //Helper.serializeReminderSettings(settings);
    }

    public static Reminder getLastReminder()
    {
        Gson gson = new Gson();
        String lastReminder = App.preferences.getString(LAST_REMINDER, null);

        return gson.fromJson(lastReminder, Reminder.class);
    }

    public static void setLastReminder(Reminder reminder)
    {
        Gson gson = new Gson();
        App.preferences.edit().putString(LAST_REMINDER, gson.toJson(reminder)).apply();
    }

    private static void addAlarm(Reminder reminder)
    {
        Context context = App.context;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("reminder", reminder);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, reminder.getVocabularyId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
        {
            alarmManager.set(AlarmManager.RTC_WAKEUP, reminder.getTime().getTime(), pendingIntent);
        }
        else
        {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, reminder.getTime().getTime(), pendingIntent);
        }
    }

    private static void removeAlarm(long vocabularyId)
    {
        Context context = App.context;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ((int) vocabularyId), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);
    }

    private static int[] getTimeParts(String time)
    {
        // string should be in 00:00 format
        String[] segments = time.split(":");
        int hour = Integer.parseInt(segments[0]);
        int minute = Integer.parseInt(segments[1]);

        return new int[]{hour, minute};
    }

    private static Date getTime(int days, int offset)
    {
        Calendar c = Calendar.getInstance();

        c.add(Calendar.DATE, days);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        c.add(Calendar.MINUTE, offset);

        return c.getTime();
    }

    private static long getElapsedMinutes()
    {
        Calendar calendar = Calendar.getInstance();
        long now = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return Math.abs((now - (calendar.getTimeInMillis())) / (1000 * 60));
    }
}
