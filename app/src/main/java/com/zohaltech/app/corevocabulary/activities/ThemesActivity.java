package com.zohaltech.app.corevocabulary.activities;

import android.view.MenuItem;
import android.widget.ListView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.ThemeAdapter;
import com.zohaltech.app.corevocabulary.data.Themes;
import com.zohaltech.app.corevocabulary.entities.Theme;

import java.util.ArrayList;

public class ThemesActivity extends EnhancedActivity {
    ListView         lstThemes;
    ArrayList<Theme> themes;
    ThemeAdapter     adapter;

    @Override
    void onCreated() {
        setContentView(R.layout.activity_themes);
        lstThemes = (ListView) findViewById(R.id.lstThemes);
        themes = Themes.select();
        adapter = new ThemeAdapter(themes);
        lstThemes.setAdapter(adapter);

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
    void onToolbarCreated() {
        //txtToolbarTitle.setText("Themes");
        getSupportActionBar().setTitle("Themes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
