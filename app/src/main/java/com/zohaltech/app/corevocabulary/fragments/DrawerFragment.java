package com.zohaltech.app.corevocabulary.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.activities.AboutActivity;
import com.zohaltech.app.corevocabulary.activities.BookmarksActivity;
import com.zohaltech.app.corevocabulary.activities.HelpActivity;
import com.zohaltech.app.corevocabulary.activities.MainActivity;
import com.zohaltech.app.corevocabulary.activities.SchedulerActivity;
import com.zohaltech.app.corevocabulary.data.SystemSettings;
import com.zohaltech.app.corevocabulary.entities.SystemSetting;

public class DrawerFragment extends Fragment {

    NavigationView navView;
    DrawerLayout   drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_drawer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navView = (NavigationView) view.findViewById(R.id.navView);

        updateUi();

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()) {
                    case R.id.nav_scheduler:
                        intent = new Intent(getActivity(), SchedulerActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_bookmarks:
                        intent = new Intent(getActivity(), BookmarksActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_help:
                        intent = new Intent(getActivity(), HelpActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_buy:
                        //todo : open market to buy premium version
                        ((MainActivity) getActivity()).pay();
                        //SystemSettings.register(systemSetting);
                        //buyItem.setVisible(false);
                        break;
                    case R.id.nav_about:
                        intent = new Intent(getActivity(), AboutActivity.class);
                        startActivity(intent);
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }

    public void updateUi() {
        final MenuItem buyItem = navView.getMenu().findItem(R.id.nav_buy);
        final SystemSetting systemSetting = SystemSettings.getCurrentSettings();
        if (systemSetting.isPremium()) {
            buyItem.setVisible(false);
        }
    }

    public void setUp(DrawerLayout drawerLayout, final Toolbar toolbar) {
        this.drawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                //toolbar.setAlpha(1 - slideOffset / 2);
            }
        };

        drawerLayout.setDrawerListener(mDrawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }
}