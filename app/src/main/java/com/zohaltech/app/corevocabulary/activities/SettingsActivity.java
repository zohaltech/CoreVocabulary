package com.zohaltech.app.corevocabulary.activities;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.ReminderManager;
import com.zohaltech.app.corevocabulary.classes.AlarmSettings;

public class SettingsActivity extends EnhancedActivity
{
    @Override
    void onCreated()
    {
        setContentView(R.layout.activity_settings);

        initialise();
    }

    private void initialise()
    {
        Button btnSetAlarm = (Button) findViewById(R.id.btnSetNextAlarm);
        btnSetAlarm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText intervals = (EditText) findViewById(R.id.btnStartTime);
                Button startTime = (Button) findViewById(R.id.edtAlarmIntervals);
                CheckBox chkSa = (CheckBox) findViewById(R.id.chkSa);
                CheckBox chkSu = (CheckBox) findViewById(R.id.chkSu);
                CheckBox chkMa = (CheckBox) findViewById(R.id.chkMa);
                CheckBox chkTu = (CheckBox) findViewById(R.id.chkTu);
                CheckBox chkWe = (CheckBox) findViewById(R.id.chkWe);
                CheckBox chkTh = (CheckBox) findViewById(R.id.chkTh);
                CheckBox chkFr = (CheckBox) findViewById(R.id.chkFr);

                boolean[] days = {
                        chkSa.isChecked(),
                        chkSu.isChecked(),
                        chkMa.isChecked(),
                        chkTu.isChecked(),
                        chkWe.isChecked(),
                        chkTh.isChecked(),
                        chkFr.isChecked()};


                AlarmSettings settings = new AlarmSettings();
                settings.setCurrentVocabularyId(0);
                settings.setWeekdays(days);
                settings.setStartTime(startTime.getText().toString());
                settings.setIntervals(Integer.parseInt(intervals.getText().toString()));

                ReminderManager.setNextReminder(SettingsActivity.this, settings.getCurrentVocabularyId());
            }
        });
    }

    @Override
    void onToolbarCreated()
    {
        getSupportActionBar().setTitle(getString(R.string.title_activity_settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
