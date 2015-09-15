package com.zohaltech.app.corevocabulary.activities;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.Reminder;
import com.zohaltech.app.corevocabulary.classes.ReminderSettings;
import com.zohaltech.app.corevocabulary.classes.ReminderManager;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.Calendar;

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
                if (settings.getReminder() != null)
                {
                    ReminderManager.remove(settings.getReminder().getId());
                }

                EditText edtStartVocabularyNo = (EditText) findViewById(R.id.edtStartVocabularyNo);
                EditText edtAlarmIntervals = (EditText) findViewById(R.id.edtAlarmIntervals);
                EditText edtStartTime = (EditText) findViewById(R.id.edtStartTime);
                CheckBox chkSa = (CheckBox) findViewById(R.id.chkSa);
                CheckBox chkSu = (CheckBox) findViewById(R.id.chkSu);
                CheckBox chkMo = (CheckBox) findViewById(R.id.chkMo);
                CheckBox chkTu = (CheckBox) findViewById(R.id.chkTu);
                CheckBox chkWe = (CheckBox) findViewById(R.id.chkWe);
                CheckBox chkTh = (CheckBox) findViewById(R.id.chkTh);
                CheckBox chkFr = (CheckBox) findViewById(R.id.chkFr);

                boolean[] days = {
                        chkSu.isChecked(),
                        chkMo.isChecked(),
                        chkTu.isChecked(),
                        chkWe.isChecked(),
                        chkTh.isChecked(),
                        chkFr.isChecked(),
                        chkSa.isChecked()};

                Vocabulary vocabulary = Vocabularies.select(Integer.parseInt(edtStartVocabularyNo.getText().toString()));
                Calendar calendar = Calendar.getInstance();
                assert vocabulary != null;
                settings.setReminder(new Reminder(vocabulary.getId(), calendar.getTime(), vocabulary.getVocabulary(), vocabulary.getVocabEnglishDef()));
                settings.setIntervals(Integer.parseInt(edtAlarmIntervals.getText().toString()));
                settings.setStartTime(edtStartTime.getText().toString());
                settings.setWeekdays(days);

                ReminderManager.setReminderSettings(settings);
                ReminderManager.setReminder();
            }
        });

        Button btnGetLastAlarm = (Button) findViewById(R.id.btnGetLastAlarm);
        btnGetLastAlarm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ReminderSettings settings = ReminderManager.getReminderSettings();
                if (settings.getReminder() != null)
                {
                    Toast.makeText(SettingsActivity.this, "Next alarm time:" + settings.getReminder().getTime().toString(), Toast.LENGTH_LONG).show();
                }
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
