package com.zohaltech.app.corevocabulary.activities;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.VocabularyAdapter;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class BookmarksActivity extends EnhancedActivity {

    @Override
    void onCreated() {
        setContentView(R.layout.activity_bookmarks);

        RecyclerView recyclerBookmarks = (RecyclerView) findViewById(R.id.recyclerBookmarks);
        recyclerBookmarks.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerBookmarks.setLayoutManager(layoutManager);

        ArrayList<Vocabulary> vocabularies = Vocabularies.selectBookmarks();
        VocabularyAdapter adapter = new VocabularyAdapter(this, vocabularies, false);
        recyclerBookmarks.setAdapter(adapter);
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
