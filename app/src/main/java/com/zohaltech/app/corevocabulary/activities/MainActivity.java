package com.zohaltech.app.corevocabulary.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.fragments.DrawerFragment;
import com.zohaltech.app.corevocabulary.fragments.FriendsFragment;
import com.zohaltech.app.corevocabulary.fragments.ThemesFragment;
import com.zohaltech.app.corevocabulary.fragments.MessagesFragment;

import widgets.MyToast;


public class MainActivity extends EnhancedActivity implements DrawerFragment.FragmentDrawerListener {

    private DrawerFragment drawerFragment;
    long startTime;

    @Override
    protected void onCreated() {
        setContentView(R.layout.activity_main);
        startTime = System.currentTimeMillis() - 5000;

        Fragment fragment = new ThemesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();
    }

    @Override
    void onToolbarCreated() {
        drawerFragment = (DrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
        drawerFragment.setDrawerListener(this);
        drawerFragment.setMenuVisibility(true);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Themes");
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
    //    // Inflate the menu; this adds items to the action bar if it is present.
    //    getMenuInflater().inflate(R.menu.menu_main, menu);
    //
    //    return true;
    //}
    //
    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
    //    // Handle action bar item clicks here. The action bar will
    //    // automatically handle clicks on the Home/Up button, so long
    //    // as you specify a parent activity in AndroidManifest.xml.
    //    int id = item.getItemId();
    //
    //    //noinspection SimplifiableIfStatement
    //    if (id == R.id.action_settings) {
    //        //App.setAppLocal(App.LANG_FARSI);
    //        //finish();
    //        //Intent intent = new Intent(MainActivity.this, MainActivity.class);
    //        //startActivity(intent);
    //        return true;
    //    }
    //
    //    return super.onOptionsItemSelected(item);
    //}

    @Override
    public void onDrawerItemSelected(View view, int position) {
        //displayView(position);
    }


    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - startTime) > 2000) {
            startTime = System.currentTimeMillis();
            MyToast.show(getString(R.string.press_back_again_to_exit), Toast.LENGTH_SHORT);

        } else {
            finish();
        }
    }
}
