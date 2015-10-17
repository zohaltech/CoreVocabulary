package com.zohaltech.app.corevocabulary.classes;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.activities.VocabularyDetailsActivity;
import com.zohaltech.app.corevocabulary.data.SystemSettings;
import com.zohaltech.app.corevocabulary.entities.SystemSetting;
import com.zohaltech.app.corevocabulary.serializables.Reminder;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Reminder reminder = (Reminder) intent.getSerializableExtra("reminder");

        NotificationCompat.Builder builder =
                new android.support.v4.app.NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(reminder.getTitle())
                        .setContentText(reminder.getMessage())
                        .setShowWhen(true)
                        .setOngoing(false)
                        .setPriority(android.support.v4.app.NotificationCompat.PRIORITY_DEFAULT)
                        .setDefaults(Notification.DEFAULT_VIBRATE)
                        .setColor(App.context.getResources().getColor(R.color.primary))
                        .setLights(0xFFC2185B, 1000, 300)
                        .setAutoCancel(true);

        Intent resultIntent = new Intent(context, VocabularyDetailsActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        resultIntent.putExtra(VocabularyDetailsActivity.VOCAB_ID, reminder.getVocabularyId());


        //// Creates an explicit intent for an Activity in your app
        ////Intent resultIntent = new Intent(this, ResultActivity.class);
        //
        //// The stack builder object will contain an artificial back stack for the
        //// started Activity.
        //// This ensures that navigating backward from the Activity leads out of
        //// your application to the Home screen.
        //TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        //// Adds the back stack for the Intent (but not the Intent itself)
        ////stackBuilder.addParentStack(ResultActivity.class);
        //// Adds the Intent that starts the Activity to the top of the stack
        //stackBuilder.addNextIntent(resultIntent);
        //PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);


        SystemSetting setting = SystemSettings.getCurrentSettings();
        if (setting.getRingingToneUri() == null) {
            builder.setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND);
        }
        Notification notification = builder.build();
        if (setting.getRingingToneUri() != null) {
            notification.sound = Uri.parse(setting.getRingingToneUri());
        }
        //App.notificationManager.notify((int) reminder.getTime().getTime(), builder.build());
        App.notificationManager.notify(reminder.getVocabularyId(), notification);

        ReminderManager.setLastReminder(reminder);
        ReminderManager.setImmediateReminder(reminder.getVocabularyId(), reminder.doesTriggersNext());
    }
}
