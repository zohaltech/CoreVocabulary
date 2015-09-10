package com.zohaltech.app.corevocabulary.classes;


import android.content.Context;
import android.content.Intent;

public class ReminderManager
{
    public static void Add(Context context, Reminder reminder)
    {
        Intent intent = new Intent(context, AlarmService.class);
        intent.putExtra("reminder", reminder);

        intent.setAction(AlarmService.CREATE);
        context.startService(intent);
    }

    public static void setNextReminder(int vocabularyId)
    {

    }
}
