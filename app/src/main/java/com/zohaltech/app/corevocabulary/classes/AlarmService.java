package com.zohaltech.app.corevocabulary.classes;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


public class AlarmService extends IntentService
{
    public static final String CREATE = "Create";
    public static final String CANCEL = "Cancel";

    private IntentFilter matcher;

    public AlarmService()
    {
        super("AlarmManager");

        matcher = new IntentFilter();
        matcher.addAction(CREATE);
        matcher.addAction(CANCEL);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        String action = intent.getAction();
        Reminder reminder = (Reminder) intent.getSerializableExtra("reminder");

        if (matcher.matchAction(CREATE))
        {
            execute(action, reminder);
        }
    }

    private void execute(String action, Reminder reminder)
    {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReceiver.class);

        intent.putExtra("reminder", reminder);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (CREATE.equals(action))
        {
            alarmManager.set(AlarmManager.RTC_WAKEUP, reminder.getTime(), pendingIntent);
        }
        else if (CANCEL.equals(action))
        {
            alarmManager.cancel(pendingIntent);
        }
    }
}
