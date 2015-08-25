package com.zohaltech.app.corevocabulary.activities;


import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.App;

import widgets.MyToast;

public class MainActivity extends EnhancedActivity {

    Button btnThemeList;

    @Override
    void onCreated() {
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        btnThemeList = (Button) findViewById(R.id.btnThemeList);
        btnThemeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    void onToolbarCreated() {
        txtToolbarTitle.setText("Core Vocabulary");

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        if ((System.currentTimeMillis() - startTime) > 2000) {
//            startTime = System.currentTimeMillis();
//            MyToast.show(getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT);
//            //Toast.makeText(App.context, getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT).show();
//        } else {
//            super.onBackPressed();
//        }
    }
}
