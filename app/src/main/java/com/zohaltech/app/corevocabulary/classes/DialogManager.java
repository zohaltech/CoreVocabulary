package com.zohaltech.app.corevocabulary.classes;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

import com.rey.material.app.TimePickerDialog;

import java.text.SimpleDateFormat;

public final class DialogManager {
    public static String timeResult;

    public static void showTimePickerDialog(Activity activity
            , String caption, int hour, int minute, final Runnable onPositiveActionClick) {

        final TimePickerDialog timePickerDialog = new TimePickerDialog(activity);
        timePickerDialog.title(caption);
        timePickerDialog.cancelable(true);
        timePickerDialog.cornerRadius(5);
        timePickerDialog.hour(hour);
        timePickerDialog.minute(minute);
        timePickerDialog.negativeAction("CANCEL");
        timePickerDialog.positiveAction("OK");
        timePickerDialog.positiveActionClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timeResult = timePickerDialog.getFormattedTime(new SimpleDateFormat("HH:mm"));
                onPositiveActionClick.run();
                timeResult = "";
                timePickerDialog.dismiss();
            }
        });
        timePickerDialog.negativeActionClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.dismiss();
            }
        });
        timePickerDialog.show();
    }
}
