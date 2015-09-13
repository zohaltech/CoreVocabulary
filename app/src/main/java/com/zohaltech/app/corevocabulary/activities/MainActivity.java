package com.zohaltech.app.corevocabulary.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.fragments.DrawerFragment;
import com.zohaltech.app.corevocabulary.fragments.FriendsFragment;
import com.zohaltech.app.corevocabulary.fragments.ThemesFragment;

import java.util.ArrayList;

import widgets.MyToast;


public class MainActivity extends EnhancedActivity implements DrawerFragment.FragmentDrawerListener
{

    private DrawerFragment drawerFragment;
    long startTime;
    boolean searchShown;

    @Override
    protected void onCreated()
    {
        setContentView(R.layout.activity_main);
        startTime = System.currentTimeMillis() - 5000;

        //Fragment fragment = new ThemesFragment();
        //FragmentManager fragmentManager = getSupportFragmentManager();
        //FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.replace(R.id.container_body, fragment);
        //fragmentTransaction.commit();
    }

    @Override
    void onToolbarCreated()
    {
        drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        drawerFragment.setDrawerListener(this);
        drawerFragment.setMenuVisibility(true);

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setTitle("Themes");
        displayView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setLayoutParams(new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        searchView.setOnCloseListener(new SearchView.OnCloseListener()
        {
            @Override
            public boolean onClose()
            {
                MyToast.show("closed", Toast.LENGTH_SHORT);
                displayView(0);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                MyToast.show("query text submit", Toast.LENGTH_SHORT);
                // todo : get result
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MyToast.show("click", Toast.LENGTH_SHORT);
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MyToast.show("search click", Toast.LENGTH_SHORT);
                //todo : load search fragment
                displayView(1);
            }
        });


        return true;
    }

    private void displayView(int position)
    {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position)
        {
            case 0:
                fragment = new ThemesFragment();
                title = "Themes";
                break;
            case 1:
                fragment = new FriendsFragment();
                title = getString(R.string.title_friends);
                break;
            default:
                break;
        }

        if (fragment != null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        //if (id == R.id.action_search) {
        //    //App.setAppLocal(App.LANG_FARSI);
        //    //finish();
        //    //Intent intent = new Intent(MainActivity.this, MainActivity.class);
        //    //startActivity(intent);
        //
        //    //searchShown = true;
        //    //getSupportActionBar().setTitle("");
        //    //drawerFragment.setMenuVisibility(false);
        //    //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position)
    {
        if (position == 2)
        {
            Intent intent = new Intent(this, SettingsActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }

    @Override
    public void onBackPressed()
    {
        if ((System.currentTimeMillis() - startTime) > 2000)
        {
            startTime = System.currentTimeMillis();
            MyToast.show(getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT);
        }
        else
        {
            finish();
        }
    }
}
