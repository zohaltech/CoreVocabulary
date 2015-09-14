package com.zohaltech.app.corevocabulary.activities;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.fragments.DrawerFragment;
import com.zohaltech.app.corevocabulary.fragments.FriendsFragment;
import com.zohaltech.app.corevocabulary.fragments.ThemesFragment;

import widgets.MyToast;


public class MainActivity extends EnhancedActivity {

    long startTime;
    private DrawerLayout   drawerLayout;
    private DrawerFragment drawerFragment;

    @Override
    protected void onCreated() {
        setContentView(R.layout.activity_main);
        startTime = System.currentTimeMillis() - 5000;
    }

    @Override
    void onToolbarCreated() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(drawerLayout, toolbar);
        drawerFragment.setMenuVisibility(true);
        displayView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);


        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        MenuItemCompat.setOnActionExpandListener(searchMenuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                displayView(0);
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                return true;  // Return true to expand action view
            }
        });

        searchManager.setOnCancelListener(new SearchManager.OnCancelListener() {
            @Override
            public void onCancel() {
                displayView(0);
                MyToast.show("cancel", Toast.LENGTH_SHORT);
            }
        });

        searchManager.setOnDismissListener(new SearchManager.OnDismissListener() {
            @Override
            public void onDismiss() {
                displayView(0);
                MyToast.show("dismiss", Toast.LENGTH_SHORT);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                MyToast.show("closed", Toast.LENGTH_SHORT);
                displayView(0);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MyToast.show("query text submit", Toast.LENGTH_SHORT);
                // todo : get result
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.show("click", Toast.LENGTH_SHORT);
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.show("search click", Toast.LENGTH_SHORT);
                //todo : load search fragment
                displayView(1);
            }
        });

        return true;
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
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

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            //fragmentTransaction.addToBackStack(title);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
    //    int id = item.getItemId();
    //
    //    //if (id == R.id.home) {
    //    //    MyToast.show("HOME",Toast.LENGTH_SHORT);
    //    //    //App.setAppLocal(App.LANG_FARSI);
    //    //    //finish();
    //    //    //Intent intent = new Intent(MainActivity.this, MainActivity.class);
    //    //    //startActivity(intent);
    //    //
    //    //    //searchShown = true;
    //    //    //getSupportActionBar().setTitle("");
    //    //    //drawerFragment.setMenuVisibility(false);
    //    //    //getSupportActionBar().setDisplayShowHomeEnabled(true);
    //    //
    //    //    return true;
    //    //}
    //
    //    return super.onOptionsItemSelected(item);
    //}

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if ((System.currentTimeMillis() - startTime) > 2000) {
            startTime = System.currentTimeMillis();
            MyToast.show(getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT);
        } else {
            finish();
        }
    }
}
