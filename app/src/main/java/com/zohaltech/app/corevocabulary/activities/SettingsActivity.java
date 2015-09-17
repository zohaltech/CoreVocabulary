package com.zohaltech.app.corevocabulary.activities;

import android.view.MenuItem;
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
    CheckBox chkSa;
    CheckBox chkSu;
    CheckBox chkMo;
    CheckBox chkTu;
    CheckBox chkWe;
    CheckBox chkTh;
    CheckBox chkFr;

    EditText edtStartVocabularyNo;
    EditText edtAlarmIntervals;
    EditText edtStartTime;

    Button btnStart;
    Button btnStop;
    Button btnPause;
    Button btnRestart;

    @Override
    void onCreated()
    {
        setContentView(R.layout.activity_settings);
        initialise();
    }

    private void initialise()
    {
        edtStartVocabularyNo = (EditText) findViewById(R.id.edtStartVocabularyNo);
        edtAlarmIntervals = (EditText) findViewById(R.id.edtAlarmIntervals);
        edtStartTime = (EditText) findViewById(R.id.edtStartTime);

        chkSa = (CheckBox) findViewById(R.id.chkSa);
        chkSu = (CheckBox) findViewById(R.id.chkSu);
        chkMo = (CheckBox) findViewById(R.id.chkMo);
        chkTu = (CheckBox) findViewById(R.id.chkTu);
        chkWe = (CheckBox) findViewById(R.id.chkWe);
        chkTh = (CheckBox) findViewById(R.id.chkTh);
        chkFr = (CheckBox) findViewById(R.id.chkFr);

        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnPause = (Button) findViewById(R.id.btnPause);
        btnRestart = (Button) findViewById(R.id.btnRestart);

        bind();

        Button btnGetLastStatus = (Button) findViewById(R.id.btnGetStatus);
        btnGetLastStatus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ReminderSettings settings = ReminderManager.getReminderSettings();
                if (settings.getReminder() != null)
                {
                    Toast.makeText(SettingsActivity.this, "Status:" + settings.getStatus().toString() + "\nAlarm Time:" + settings.getReminder().getTime().toString(), Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(SettingsActivity.this, "Status:" + settings.getStatus().toString() + "\n No Reminder", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ReminderManager.pause();
                bind();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ReminderSettings settings = ReminderManager.getReminderSettings();

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

                if (vocabulary == null)
                {
                    return;
                }

                settings.setStartTime(edtStartTime.getText().toString());
                settings.setIntervals(Integer.parseInt(edtAlarmIntervals.getText().toString()));
                settings.setReminder(new Reminder(vocabulary.getId(), calendar.getTime(), vocabulary.getVocabulary(), vocabulary.getVocabEnglishDef()));
                settings.setWeekdays(days);
                settings.setStatus(ReminderSettings.Status.RUNNING);
                ReminderManager.setReminderSettings(settings);
                ReminderManager.resume();

                bind();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ReminderManager.stop();
                bind();
            }
        });

    }

    private void bind()
    {
        ReminderSettings settings = ReminderManager.getReminderSettings();

        btnRestart.setVisibility(View.GONE);
        btnStop.setVisibility(View.GONE);
        btnPause.setVisibility(View.GONE);
        btnStart.setVisibility(View.GONE);

        if (settings.getStatus() == ReminderSettings.Status.STOP)
        {
            btnStart.setVisibility(View.VISIBLE);
        }
        else if (settings.getStatus() == ReminderSettings.Status.RUNNING)
        {
            btnStop.setVisibility(View.VISIBLE);
            btnPause.setVisibility(View.VISIBLE);
        }
        else if (settings.getStatus() == ReminderSettings.Status.PAUSE)
        {
            btnStart.setVisibility(View.VISIBLE);
        }
        else if (settings.getStatus() == ReminderSettings.Status.FINISHED)
        {
            btnRestart.setVisibility(View.VISIBLE);
        }

        edtStartTime.setText(settings.getStartTime());
        edtAlarmIntervals.setText(String.valueOf(settings.getIntervals()));
        if (settings.getReminder() != null)
        {
            edtStartVocabularyNo.setText(String.valueOf(settings.getReminder().getId()));
        }
        boolean[] days = settings.getWeekdays();

        chkSu.setChecked(days[0]);
        chkMo.setChecked(days[1]);
        chkTu.setChecked(days[2]);
        chkWe.setChecked(days[3]);
        chkTh.setChecked(days[4]);
        chkFr.setChecked(days[5]);
        chkSa.setChecked(days[6]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    void onToolbarCreated()
    {
        getSupportActionBar().setTitle(getString(R.string.title_activity_settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
