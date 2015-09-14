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
            Gson gson = new Gson();
            String alarmJson = App.preferences.getString("alarm_settings", null);

            if (alarmJson == null || alarmJson.equals(""))
            {
                return;
            }

            AlarmSettings alarmSettings = gson.fromJson(alarmJson, AlarmSettings.class);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, alarmSettings.getIntervals());

            int today = Calendar.DAY_OF_WEEK;
            int nextDay = 1;
            long alarmTime;

            for (int j = 0; j < 7; j++)
            {
                if (alarmSettings.getWeekdays()[(today + j) % 7])
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

}
