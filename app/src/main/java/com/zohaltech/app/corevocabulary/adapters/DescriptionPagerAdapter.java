package com.zohaltech.app.corevocabulary.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zohaltech.app.corevocabulary.fragments.DescriptionFragment;

public class DescriptionPagerAdapter extends FragmentPagerAdapter {
    final   int    PAGE_COUNT  = 3;
    private String tabTitles[] = new String[]{"Meanings", "Examples", "Notes"};

    public DescriptionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return DescriptionFragment.newInstance(3);
        else if (position == 1)
            return DescriptionFragment.newInstance(2);
        else
            return DescriptionFragment.newInstance(1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
