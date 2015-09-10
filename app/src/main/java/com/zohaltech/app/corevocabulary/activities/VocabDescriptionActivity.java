package com.zohaltech.app.corevocabulary.activities;


import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.DescriptionPagerAdapter;
import com.zohaltech.app.corevocabulary.classes.App;

public class VocabDescriptionActivity extends EnhancedActivity
{
    PagerSlidingTabStrip    tabCategories;
    ViewPager               pagerCategories;
    DescriptionPagerAdapter descriptionPagerAdapter;

    @Override
    void onCreated() {
        setContentView(R.layout.activity_vocab_description);

        // Initialize the ViewPager and set an adapter
        pagerCategories = (ViewPager) findViewById(R.id.pagerDescItems);
        descriptionPagerAdapter = new DescriptionPagerAdapter(getSupportFragmentManager());
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
                ((TextView) ((ViewGroup) tabCategories.getChildAt(0)).getChildAt(0)).setTextColor(getResources().getColor(R.color.divider));
                ((TextView) ((ViewGroup) tabCategories.getChildAt(0)).getChildAt(1)).setTextColor(getResources().getColor(R.color.divider));
                ((TextView) ((ViewGroup) tabCategories.getChildAt(0)).getChildAt(2)).setTextColor(getResources().getColor(R.color.divider));
                ((TextView) ((ViewGroup) tabCategories.getChildAt(0)).getChildAt(position)).setTextColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        changeTabsFont();
        pagerCategories.setCurrentItem(0);
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
        //txtToolbarTitle.setText("Vocabulary Description");
        getSupportActionBar().setTitle("Vocabulary Description");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void changeTabsFont()
    {
        ViewGroup vg = (ViewGroup) tabCategories.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++)
        {
            TextView textView = (TextView) vg.getChildAt(j);
            textView.setWidth(App.screenWidth / 3);
            textView.setTypeface(App.persianFont);
            textView.setTextColor(getResources().getColor(R.color.divider));
            textView.setTextSize(14);
        }
    }
}
