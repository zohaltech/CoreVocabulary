package com.zohaltech.app.corevocabulary.classes;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.activities.MainActivity;

public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        int lockScreenVisibility = android.support.v4.app.NotificationCompat.VISIBILITY_SECRET;

        Reminder reminder = (Reminder) intent.getSerializableExtra("reminder");

        android.support.v4.app.NotificationCompat.Builder builder =
                new android.support.v4.app.NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_add_white)
                        .setContentTitle(reminder.getTitle())
                        .setContentText(reminder.getMessage())
                        .setShowWhen(true)
                        .setOngoing(false)
                        .setPriority(android.support.v4.app.NotificationCompat.PRIORITY_DEFAULT)
                        .setVisibility(lockScreenVisibility)
                        .setColor(App.context.getResources().getColor(R.color.primary))
                        .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setAutoCancel(true);


        Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        App.notificationManager.notify((int) reminder.getTime().getTime(), builder.build());

        ReminderManager.resume(reminder.getId());
    }
}
