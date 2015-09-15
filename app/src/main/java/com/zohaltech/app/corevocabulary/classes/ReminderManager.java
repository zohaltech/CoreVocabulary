package com.zohaltech.app.corevocabulary.classes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.Calendar;

public class ReminderManager
{
    private static String REMINDER_SETTINGS = "reminder_settings";

    public static ReminderSettings getReminderSettings()
    {
        Gson gson = new Gson();
        String alarmJson = App.preferences.getString(REMINDER_SETTINGS, null);

        if (alarmJson == null || alarmJson.equals(""))
        {
            return new ReminderSettings("18:00", 45, 0, true, new boolean[]{true, true, true, true, true, true, true});
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
        alarmManager.set(AlarmManager.RTC_WAKEUP, reminder.getTime(), pendingIntent);

    }

    public static void remove(long vocabularyId)
    {
        AlarmManager alarmManager = (AlarmManager) App.context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(App.context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(App.context, ((int) vocabularyId), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);

    }

    public static void setReminder(Context context, int lastVocabularyId)
    {
        Vocabulary current = Vocabularies.select(lastVocabularyId);
        Vocabulary next = Vocabularies.next(lastVocabularyId);
        if (current != null && next != null)
        {
            ReminderSettings settings = getReminderSettings();
            settings.setVocabularyId(next.getId());
            settings.setRemindToday(current.getDay() == next.getDay());
            setReminderSettings(settings);

            setReminder(context);
        }
    }

    public static void setReminder(Context context)
    {
        ReminderSettings settings = getReminderSettings();
        if (settings == null)
        {
            return;
        }

        Vocabulary vocabulary = Vocabularies.select(settings.getVocabularyId());
        if (vocabulary == null)
        {
            return;
        }

        Calendar calendar = Calendar.getInstance();
        if (settings.isRemindToday())
        {
            calendar.add(Calendar.MINUTE, settings.getIntervals());
        }
        else
        {
            int today = Calendar.DAY_OF_WEEK;
            int nextDay = 1;

            for (int j = 1; j < 7; j++)
            {
                if (settings.getWeekdays()[(today + j) % 7])
                {
                    nextDay = j;
                    break;
                }
            }
            long extra = getLeftMinutes() + ((nextDay - 1) * 24 * 60) + getOffsetMinutes(settings.getStartTime());
            calendar.add(Calendar.MINUTE, (int) extra);
        }

        add(context, new Reminder(vocabulary.getId(), calendar.getTime().getTime(), vocabulary.getVocabulary(), vocabulary.getVocabEnglishDef()));
    }

    private static int getOffsetMinutes(String time)
    {
        // string should be in 00:00 format

        String[] segments = time.split(":");
        int hour = Integer.parseInt(segments[0]);
        int minute = Integer.parseInt(segments[1]);

        return hour * 60 + minute;
    }

    private static long getLeftMinutes()
    {
        Calendar c = Calendar.getInstance();
        long now = c.getTimeInMillis();

        c.add(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return (c.getTimeInMillis() - now) / (1000 * 60);
    }
}
