package com.zohaltech.app.corevocabulary.classes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.Calendar;
import java.util.Date;

public class ReminderManager
{
    private static String REMINDER_SETTINGS = "reminder_settings";

    public static ReminderSettings getReminderSettings()
    {
        Gson gson = new Gson();
        String alarmJson = App.preferences.getString(REMINDER_SETTINGS, null);

        if (alarmJson == null || alarmJson.equals(""))
        {
            Calendar calendar = Calendar.getInstance();
            Vocabulary vocabulary = Vocabularies.select(1);
            assert vocabulary != null;
            Reminder reminder = new Reminder(vocabulary.getId(), calendar.getTime(), vocabulary.getVocabulary(), vocabulary.getVocabEnglishDef());
            return new ReminderSettings("18:00", 45, reminder, new boolean[]{true, true, true, true, true, true, true});
        }

        return gson.fromJson(alarmJson, ReminderSettings.class);
    }

    public static void setReminderSettings(ReminderSettings settings)
    {
        Gson gson = new Gson();
        App.preferences.edit().putString(REMINDER_SETTINGS, gson.toJson(settings)).apply();
    }

    private static void add(Context context, Reminder reminder)
    {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("reminder", reminder);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, reminder.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, reminder.getTime().getTime(), pendingIntent);

    }

    public static void remove(long vocabularyId)
    {
        AlarmManager alarmManager = (AlarmManager) App.context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(App.context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(App.context, ((int) vocabularyId), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);
    }

    // this method is meant to be called just by AlarmReceiver class!
    public static void setReminder(int currentVocabularyId)
    {
        Vocabulary current = Vocabularies.select(currentVocabularyId);
        if (current == null)
        {
            // exception occurred.
            return;
        }

        Vocabulary next = Vocabularies.next(currentVocabularyId);
        if (next == null)
        {
            // The end! provide subscriber with a proper message.
            return;
        }

        if (current.getDay() == next.getDay())
        {
            Calendar calendar = Calendar.getInstance();
            ReminderSettings settings = getReminderSettings();
            calendar.add(Calendar.MINUTE, settings.getIntervals());

            Reminder reminder = new Reminder(next.getId(), calendar.getTime(), next.getVocabulary(), next.getVocabEnglishDef());
            settings.setReminder(reminder);
            setReminderSettings(settings);

            add(App.context, new Reminder(next.getId(), calendar.getTime(), next.getVocabulary(), next.getVocabEnglishDef()));

        }
        else
        {
            setReminder();
        }
    }

    public static void setReminder()
    {
        ReminderSettings settings = getReminderSettings();
        if (settings == null)
        {
            return;
        }

        Vocabulary vocabulary = Vocabularies.select(settings.getReminder().getId());
        if (vocabulary == null)
        {
            return;
        }

        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        int nextDayOffset = 0;
        int[] startTime = getStartTimeSegments(settings.getStartTime());
        if (!settings.getWeekdays()[today - 1] || getElapsedMinutes() > startTime[0] * 60 + startTime[1])
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

        Date alarmTime = getAlarmTime(nextDayOffset, startTime[0], startTime[1]);
        settings.getReminder().setTime(alarmTime);
        ReminderManager.setReminderSettings(settings);

        add(App.context, new Reminder(vocabulary.getId(), alarmTime, vocabulary.getVocabulary(), vocabulary.getVocabEnglishDef()));
    }

    private static int[] getStartTimeSegments(String time)
    {
        // string should be in 00:00 format
        String[] segments = time.split(":");
        int hour = Integer.parseInt(segments[0]);
        int minute = Integer.parseInt(segments[1]);

        return new int[]{hour, minute};
    }

    private static Date getAlarmTime(int days, int hours, int minutes)
    {
        Calendar c = Calendar.getInstance();

        c.add(Calendar.DATE, days);
        c.set(Calendar.HOUR_OF_DAY, hours);
        c.set(Calendar.MINUTE, minutes);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

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
