package com.zohaltech.app.corevocabulary.activities;


import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.VocabularyAdapter;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class BookmarksActivity extends EnhancedActivity {

    RecyclerView recyclerBookmarks;
    TextView txtNothingFound;

    @Override
    void onCreated() {
        setContentView(R.layout.activity_bookmarks);

        recyclerBookmarks = (RecyclerView) findViewById(R.id.recyclerBookmarks);
        txtNothingFound = (TextView) findViewById(R.id.txtNothingFound);
        recyclerBookmarks.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerBookmarks.setLayoutManager(layoutManager);
        
        ArrayList<Vocabulary> vocabularies = Vocabularies.selectBookmarks();
        final VocabularyAdapter adapter = new VocabularyAdapter(this, vocabularies, false);
        recyclerBookmarks.setAdapter(adapter);

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (adapter.getItemCount() > 0) {
                    txtNothingFound.setVisibility(View.GONE);
                } else {
                    txtNothingFound.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    void onToolbarCreated() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Bookmarks");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }
}
