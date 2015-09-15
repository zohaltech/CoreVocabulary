package com.zohaltech.app.corevocabulary.activities;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.ReminderSettings;
import com.zohaltech.app.corevocabulary.classes.ReminderManager;

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

                ReminderSettings settings = ReminderManager.getReminderSettings();
                ReminderManager.remove(settings.getVocabularyId());

                EditText edtStartVocabularyNo = (EditText) findViewById(R.id.edtStartVocabularyNo);
                EditText edtAlarmIntervals = (EditText) findViewById(R.id.edtAlarmIntervals);
                Button btnStartTime = (Button) findViewById(R.id.btnStartTime);
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


                settings.setVocabularyId(Integer.parseInt(edtStartVocabularyNo.getText().toString()));
                settings.setIntervals(Integer.parseInt(edtAlarmIntervals.getText().toString()));
                settings.setStartTime(btnStartTime.getText().toString());
                settings.setWeekdays(days);

                ReminderManager.setReminderSettings(settings);
                ReminderManager.setReminder(SettingsActivity.this);
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
