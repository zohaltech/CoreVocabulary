package com.zohaltech.app.corevocabulary.classes;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.Calendar;

public class ReminderManager
{
    public static void add(Context context, Reminder reminder)
    {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("reminder", reminder);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, reminder.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, reminder.getTime(), pendingIntent);

    }

    public static void remove(Context context, Reminder reminder)
    {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, reminder.getId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);

    }

    public static void setNextReminder(Context context, int vocabularyId)
    {
        Vocabulary nextVocabulary = Vocabularies.selectNextVocabulary(vocabularyId);
        if (nextVocabulary != null)
        {
            AlarmSettings setting = getSettings();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, setting.getIntervals());

            int today = Calendar.DAY_OF_WEEK;
            int nextDay = 1;
            long alarmTime;

            for (int j = 0; j < 7; j++)
            {
                if (setting.getWeekdays()[(today + j) % 7])
                {
                    nextDay = j;
                    break;
                }
            }

            if (nextDay == 0)
            {
                alarmTime = calendar.getTime().getTime();
            }
            else
            {
                alarmTime = (nextDay - 1) * 24 * 60;
            }

            add(context, new Reminder(nextVocabulary.getId(), alarmTime, nextVocabulary.getVocabulary(), nextVocabulary.getVocabEnglishDef()));
        }
    }

    private static AlarmSettings getSettings()
    {
        boolean[] weekdays = new boolean[7];
        weekdays[0] = weekdays[3] = weekdays[4] = true;

        AlarmSettings settings = new AlarmSettings();

        settings.setIntervals(1);
        settings.setStartTime("18:00");
        settings.setWeekdays(weekdays);

        return settings;
    }
}
