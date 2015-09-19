package com.zohaltech.app.corevocabulary.activities;


import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.DescriptionPagerAdapter;
import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.data.Examples;
import com.zohaltech.app.corevocabulary.data.Notes;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Example;
import com.zohaltech.app.corevocabulary.entities.Note;

import java.util.ArrayList;

public class VocabDescriptionActivity extends EnhancedActivity {
    public static final String INIT_MODE_KEY = "INIT_MODE";
    //public static final String MODE_SEARCH_RESULT = "MODE_SEARCH_RESULT";
    public static final String MODE_VIEW     = "MODE_VIEW";
    public static final String VOCAB_ID      = "VOCAB_ID";
    //public static final String SEARCH_TEXT        = "SEARCH_TEXT";

    TextView             txtVocabulary;
    CheckBox             chkBookmark;
    PagerSlidingTabStrip tabCategories;
    ViewPager            pagerCategories;

    DescriptionPagerAdapter descriptionPagerAdapter;
    ArrayList<Example>      examples;
    ArrayList<Note>         notes;
    int tabCount = 2;

    @Override
    void onCreated() {
        setContentView(R.layout.activity_vocab_description);

        txtVocabulary = (TextView) findViewById(R.id.txtVocabulary);
        chkBookmark = (CheckBox) findViewById(R.id.chkBookmark);
        tabCategories = (PagerSlidingTabStrip) findViewById(R.id.tabDescriptions);
        pagerCategories = (ViewPager) findViewById(R.id.pagerDescItems);

        int vocabularyId = getIntent().getIntExtra(VOCAB_ID, 0);

        txtVocabulary.setText(Vocabularies.select(vocabularyId).getVocabulary());

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
        ((TextView) ((ViewGroup) tabCategories.getChildAt(0)).getChildAt(0)).setTextColor(getResources().getColor(R.color.secondary_text));
        ((TextView) ((ViewGroup) tabCategories.getChildAt(0)).getChildAt(1)).setTextColor(getResources().getColor(R.color.secondary_text));
        if (tabCount == 3) {
            ((TextView) ((ViewGroup) tabCategories.getChildAt(0)).getChildAt(2)).setTextColor(getResources().getColor(R.color.secondary_text));
        }
        ((TextView) ((ViewGroup) tabCategories.getChildAt(0)).getChildAt(position)).setTextColor(getResources().getColor(R.color.primary_text));
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
            textView.setTypeface(App.englishFont);
            textView.setTextColor(getResources().getColor(R.color.secondary_text));
            textView.setTextSize(14);
        }
    }

    private void highlightSearchText(String searchText) {

    }
}
