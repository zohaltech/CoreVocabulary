package com.zohaltech.app.corevocabulary.classes;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

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
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        resultIntent.putExtra(VocabularyDetailsActivity.VOCAB_ID, reminder.getVocabularyId());

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
        App.notificationManager.notify((int) reminder.getTime().getTime(), notification);

        ReminderManager.setLastReminder(reminder);
        ReminderManager.setImmediateReminder(reminder.getVocabularyId(), reminder.doesTriggersNext());
    }
}
