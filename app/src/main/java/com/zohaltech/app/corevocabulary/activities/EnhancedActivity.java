package com.zohaltech.app.corevocabulary.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.classes.MyUncaughtExceptionHandler;

public abstract class EnhancedActivity extends AppCompatActivity {

    Toolbar toolbar;
    //TextView txtToolbarTitle;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        super.onCreate(savedInstanceState);

        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        App.currentActivity = this;

        onCreated();
        onInitialized();
        onToolbarCreated();
    }

    private void onInitialized() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_back_white);
        //toolbar.setTitle("");

        //txtToolbarTitle = new MyTextView(this);
        //txtToolbarTitle.setTextAppearance(this, android.R.style.TextAppearance_Medium);
        //txtToolbarTitle.setTextColor(getResources().getColor(R.color.white));
        //txtToolbarTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
        //txtToolbarTitle.setGravity(Gravity.CENTER);
        //toolbar.addView(txtToolbarTitle, 0);

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.currentActivity = this;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);

    }

    abstract void onCreated();

    abstract void onToolbarCreated();
}
