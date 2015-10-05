package com.zohaltech.app.corevocabulary.activities;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatSpinner;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.classes.DialogManager;
import com.zohaltech.app.corevocabulary.classes.Reminder;
import com.zohaltech.app.corevocabulary.classes.ReminderManager;
import com.zohaltech.app.corevocabulary.classes.ReminderSettings;
import com.zohaltech.app.corevocabulary.data.SystemSettings;
import com.zohaltech.app.corevocabulary.data.Themes;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.SystemSetting;
import com.zohaltech.app.corevocabulary.entities.Theme;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SchedulerActivity extends EnhancedActivity
{
    CheckBox chkSa;
    CheckBox chkSu;
    CheckBox chkMo;
    CheckBox chkTu;
    CheckBox chkWe;
    CheckBox chkTh;
    CheckBox chkFr;

    //EditText edtStartVocabularyNo;
    //EditText edtAlarmIntervals;
    AppCompatSpinner spinnerIntervals;
    AppCompatSpinner spinnerStartTheme;
    TextView twAlarmSound;

    Button btnStart;
    Button btnStop;
    Button btnPause;
    Button btnRestart;
    Button btnStartTime;
    Button btnSelectTone;

    @Override
    void onCreated()
    {
        setContentView(R.layout.activity_scheduler);
        initialise();
    }

    private void initialise()
    {
        //edtStartVocabularyNo = (EditText) findViewById(R.id.edtStartVocabularyNo);
        //edtAlarmIntervals = (EditText) findViewById(R.id.edtAlarmIntervals);
        spinnerIntervals = (AppCompatSpinner) findViewById(R.id.spinnerIntervals);
        spinnerStartTheme = (AppCompatSpinner) findViewById(R.id.spinnerStartTheme);
        btnStartTime = (Button) findViewById(R.id.btnStartTime);
        twAlarmSound = (TextView) findViewById(R.id.twAlarmSound);

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
        btnSelectTone = (Button) findViewById(R.id.btnSelectTone);

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
                    Toast.makeText(SchedulerActivity.this, "Status:" + status + "\n Alarm Time: " + remindTime, Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(SchedulerActivity.this, "Status:" + status + "\n No Reminder", Toast.LENGTH_LONG).show();
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

        btnSelectTone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Tone");
                intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) null);
                startActivityForResult(intent, 5);
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

        int selectedThemeId = spinnerStartTheme.getSelectedItemPosition() + 1;
        int startVocabId = Vocabularies.selectByTheme(selectedThemeId).get(0).getId();
        Vocabulary vocabulary = Vocabularies.select(startVocabId);
        //Vocabulary vocabulary = Vocabularies.select(Integer.parseInt(edtStartVocabularyNo.getText().toString()));
        if (vocabulary == null)
        {
            return;
        }

        Date reminderTime = Calendar.getInstance().getTime();
        Reminder garbage = settings.getReminder();
        if (garbage != null && garbage.getTime() != null)
        {
            reminderTime = garbage.getTime();
        }
        settings.setReminder(new Reminder(vocabulary.getId(), reminderTime, vocabulary.getVocabulary(), vocabulary.getVocabEnglishDef(), true));
        settings.setStartTime(btnStartTime.getText().toString());
        //settings.setIntervals(Integer.parseInt(edtAlarmIntervals.getText().toString()));
        //  settings.setIntervals();
        settings.setIntervals((Integer) spinnerIntervals.getSelectedItem());
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
        //edtAlarmIntervals.setText(String.valueOf(settings.getIntervals()));

        ArrayList<Integer> intervals = new ArrayList<>();

        intervals.add(1);
        intervals.add(15);
        intervals.add(30);
        intervals.add(45);
        intervals.add(60);
        intervals.add(90);
        intervals.add(120);
        ArrayAdapter<Integer> intervalAdapter = new ArrayAdapter<>(this, R.layout.spinner_current_item, intervals);
        intervalAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerIntervals.setAdapter(intervalAdapter);
        spinnerIntervals.setSelection(intervalAdapter.getPosition(settings.getIntervals()));

        ArrayList<String> themeNames = new ArrayList<>();
        ArrayList<Theme> themes = Themes.select();

        for (Theme theme : themes)
        {
            themeNames.add(theme.getName());
        }

        ArrayAdapter<String> themesAdapter = new ArrayAdapter<>(this, R.layout.spinner_current_item, themeNames);
        themesAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerStartTheme.setAdapter(themesAdapter);
        spinnerStartTheme.setSelection(0);

        if (settings.getReminder() != null)
        {
            Vocabulary vocabulary = Vocabularies.select(settings.getReminder().getVocabularyId());
            assert vocabulary != null;
            spinnerStartTheme.setSelection(vocabulary.getThemeId() - 1);
            //edtStartVocabularyNo.setText(String.valueOf(settings.getReminder().getVocabularyId()));
        }
        boolean[] days = settings.getWeekdays();

        chkSu.setChecked(days[0]);
        chkMo.setChecked(days[1]);
        chkTu.setChecked(days[2]);
        chkWe.setChecked(days[3]);
        chkTh.setChecked(days[4]);
        chkFr.setChecked(days[5]);
        chkSa.setChecked(days[6]);

        SystemSetting setting = SystemSettings.getCurrentSettings();
        twAlarmSound.setText(setting.getAlarmRingingTone());
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

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent)
    {
        if (resultCode == Activity.RESULT_OK && requestCode == 5)
        {
            Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            if (uri != null)
            {
                Ringtone ringtone = RingtoneManager.getRingtone(this, uri);
                String title = ringtone.getTitle(this);

                SystemSetting setting = SystemSettings.getCurrentSettings();
                setting.setRingingToneUri(uri.toString());
                setting.setAlarmRingingTone(title);
                SystemSettings.update(setting);

                twAlarmSound.setText(title);
            }
            else
            {
                twAlarmSound.setText(null);
            }
        }
    }
}
