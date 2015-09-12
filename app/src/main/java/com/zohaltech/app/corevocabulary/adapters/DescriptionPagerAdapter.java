package com.zohaltech.app.corevocabulary.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zohaltech.app.corevocabulary.fragments.VocabularyDescFragment;

import java.util.ArrayList;

public class DescriptionPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<String> tabTitles;//= new String[]{"Meanings", "Examples", "Notes"};

    public DescriptionPagerAdapter(FragmentManager fm, ArrayList<String>  tabTitles) {
        super(fm);
        this.tabTitles = tabTitles;
    }

    @Override
    public int getCount() {
        return tabTitles.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return VocabularyDescFragment.newInstance(3);
        else if (position == 1)
            return VocabularyDescFragment.newInstance(2);
        else
            return VocabularyDescFragment.newInstance(1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
       // return tabTitles[position];
        return tabTitles.get(position);
    }
}
