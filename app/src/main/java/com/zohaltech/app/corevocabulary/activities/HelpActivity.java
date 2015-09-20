package com.zohaltech.app.corevocabulary.activities;

import android.support.v7.app.ActionBar;

import com.zohaltech.app.corevocabulary.R;

public class HelpActivity extends EnhancedActivity {

    @Override
    void onCreated() {
        setContentView(R.layout.activity_help);
    }

    @Override
    void onToolbarCreated() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Help");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }
}
