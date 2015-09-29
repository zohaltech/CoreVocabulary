package com.zohaltech.app.corevocabulary.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.App;

public abstract class EnhancedActivity extends AppCompatActivity {

    Toolbar toolbar;
    //TextView txtToolbarTitle;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        super.onCreate(savedInstanceState);

        //Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        App.currentActivity = this;

        onCreated();
        onInitialized();
        onToolbarCreated();
    }

    private void onInitialized() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.currentActivity = this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    abstract void onCreated();

    abstract void onToolbarCreated();
}
