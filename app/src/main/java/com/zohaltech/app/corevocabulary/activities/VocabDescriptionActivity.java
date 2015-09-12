package com.zohaltech.app.corevocabulary.activities;


import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.DescriptionPagerAdapter;
import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.data.Examples;
import com.zohaltech.app.corevocabulary.data.Notes;
import com.zohaltech.app.corevocabulary.entities.Example;
import com.zohaltech.app.corevocabulary.entities.Note;

import java.util.ArrayList;

public class VocabDescriptionActivity extends EnhancedActivity {
    PagerSlidingTabStrip    tabCategories;
    ViewPager               pagerCategories;
    DescriptionPagerAdapter descriptionPagerAdapter;
    ArrayList<Example>      examples;
    ArrayList<Note>         notes;
    int tabCount = 2;

    @Override
    void onCreated() {
        setContentView(R.layout.activity_vocab_description);

        // Initialize the ViewPager and set an adapter
        pagerCategories = (ViewPager) findViewById(R.id.pagerDescItems);

        int vocabularyId = getIntent().getIntExtra("VocabularyId", 0);
        examples = Examples.getExamples(vocabularyId);
        notes = Notes.getNotes(vocabularyId);

        ArrayList<String> tabTitles = new ArrayList<>();
        tabTitles.add("Definition");
        tabTitles.add("Examples");
        if (notes.size() > 0) {
            tabTitles.add("Notes");
            tabCount = 3;
        }

        descriptionPagerAdapter = new DescriptionPagerAdapter(getSupportFragmentManager(), tabTitles, vocabularyId);
        pagerCategories.setAdapter(descriptionPagerAdapter);

        // Bind the tabCategories to the ViewPager
        tabCategories = (PagerSlidingTabStrip) findViewById(R.id.tabDescriptions);
        tabCategories.setViewPager(pagerCategories);

        pagerCategories.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTabTitleColors(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        changeTabsFont();
        changeTabTitleColors(0);
    }

    private void changeTabTitleColors(int position) {
        ((TextView) ((ViewGroup) tabCategories.getChildAt(0)).getChildAt(0)).setTextColor(getResources().getColor(R.color.primary_light));
        ((TextView) ((ViewGroup) tabCategories.getChildAt(0)).getChildAt(1)).setTextColor(getResources().getColor(R.color.primary_light));
        if (tabCount == 3) {
            ((TextView) ((ViewGroup) tabCategories.getChildAt(0)).getChildAt(2)).setTextColor(getResources().getColor(R.color.primary_light));
        }
        ((TextView) ((ViewGroup) tabCategories.getChildAt(0)).getChildAt(position)).setTextColor(getResources().getColor(R.color.white));
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
        getSupportActionBar().setTitle("Vocabulary Description");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void changeTabsFont() {
        ViewGroup vg = (ViewGroup) tabCategories.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            TextView textView = (TextView) vg.getChildAt(j);
            textView.setWidth(App.screenWidth / tabCount);
            textView.setTypeface(App.persianFont);
            textView.setTextColor(getResources().getColor(R.color.primary_light));
            textView.setTextSize(14);
        }
    }
}
