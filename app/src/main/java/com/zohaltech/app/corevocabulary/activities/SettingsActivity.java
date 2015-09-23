package com.zohaltech.app.corevocabulary.activities;

import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.classes.DialogManager;
import com.zohaltech.app.corevocabulary.classes.Reminder;
import com.zohaltech.app.corevocabulary.classes.ReminderManager;
import com.zohaltech.app.corevocabulary.classes.ReminderSettings;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.Calendar;
import java.util.Date;

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
    //EditText edtStartTime;

    Button btnStart;
    Button btnStop;
    Button btnPause;
    Button btnRestart;
    Button btnStartTime;

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
        btnStartTime = (Button) findViewById(R.id.btnStartTime);

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
                String status = settings.getStatus().toString();
                if (settings.getReminder() != null)
                {
                    Date time = settings.getReminder().getTime();
                    String remindTime = (time == null ? "Not Set" : time.toString());
                    Toast.makeText(SettingsActivity.this, "Status:" + status + "\n Alarm Time: " + remindTime, Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(SettingsActivity.this, "Status:" + status + "\n No Reminder", Toast.LENGTH_LONG).show();
                }
            }
        });


        btnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                start();
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

        btnStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ReminderManager.stop();
                bind();
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                start();
            }
        });

        btnStartTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (btnStartTime.getText().length() > 0)
                {
                    int hour = Integer.valueOf(btnStartTime.getText().toString().substring(0, 2));
                    int minute = Integer.valueOf(btnStartTime.getText().toString().substring(3, 5));
                    DialogManager.showTimePickerDialog(App.currentActivity, "", hour, minute, new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            btnStartTime.setText(DialogManager.timeResult);
                        }
                    });
                }
                else
                {
                    DialogManager.showTimePickerDialog(App.currentActivity, "", 12, 0, new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            btnStartTime.setText(DialogManager.timeResult);
                        }
                    });
                }
            }
        });

    }

    private void start()
    {
        ReminderSettings settings = ReminderManager.getReminderSettings();

        boolean paused = settings.getStatus() == ReminderSettings.Status.PAUSE;

        boolean[] days = {
                chkSu.isChecked(),
                chkMo.isChecked(),
                chkTu.isChecked(),
                chkWe.isChecked(),
                chkTh.isChecked(),
                chkFr.isChecked(),
                chkSa.isChecked()};

        Vocabulary vocabulary = Vocabularies.select(Integer.parseInt(edtStartVocabularyNo.getText().toString()));
        if (vocabulary == null)
        {
            return;
        }

        Calendar calendar = Calendar.getInstance();
        settings.setReminder(new Reminder(vocabulary.getId(), calendar.getTime(), vocabulary.getVocabulary(), vocabulary.getVocabEnglishDef(), true));
        settings.setStartTime(btnStartTime.getText().toString());
        settings.setIntervals(Integer.parseInt(edtAlarmIntervals.getText().toString()));
        settings.setWeekdays(days);
        settings.setStatus(ReminderSettings.Status.RUNNING);
        ReminderManager.setReminderSettings(settings);

        ReminderManager.start(paused);

        bind();
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

        btnStartTime.setText(settings.getStartTime());
        edtAlarmIntervals.setText(String.valueOf(settings.getIntervals()));
        if (settings.getReminder() != null)
        {
            edtStartVocabularyNo.setText(String.valueOf(settings.getReminder().getVocabularyId()));
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
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setTitle(getString(R.string.title_activity_settings));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }
}
