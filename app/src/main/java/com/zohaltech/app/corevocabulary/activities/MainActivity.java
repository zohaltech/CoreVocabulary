package com.zohaltech.app.corevocabulary.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.fragments.DrawerFragment;
import com.zohaltech.app.corevocabulary.fragments.FriendsFragment;
import com.zohaltech.app.corevocabulary.fragments.ThemesFragment;
import com.zohaltech.app.corevocabulary.fragments.MessagesFragment;


public class MainActivity extends EnhancedActivity implements DrawerFragment.FragmentDrawerListener {

    //private Toolbar        mToolbar;
    private DrawerFragment drawerFragment;

    @Override
    protected void onCreated() {
        setContentView(R.layout.activity_main);

        //mToolbar = (Toolbar) findViewById(R.id.toolbar);
        //
        //setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    @Override
    void onToolbarCreated() {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        drawerFragment.setDrawerListener(this);
        drawerFragment.setMenuVisibility(true);

        displayView(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //App.setAppLocal(App.LANG_FARSI);
            //finish();
            //Intent intent = new Intent(MainActivity.this, MainActivity.class);
            //startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new ThemesFragment();
                title = getString(R.string.title_themes);
                break;
            case 1:
                fragment = new FriendsFragment();
                title = getString(R.string.title_friends);
                break;
            case 2:
                fragment = new MessagesFragment();
                title = getString(R.string.title_messages);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
}
