package com.zohaltech.app.corevocabulary.activities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.VocabularyAdapter;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

import widgets.MyToast;

public class SearchActivity extends EnhancedActivity {
    public static final String SEARCH_TEXT = "SEARCH_TEXT";

    @Override
    void onCreated() {
        setContentView(R.layout.activity_search);

        String searchText = getIntent().getStringExtra(SEARCH_TEXT);
        search(searchText);
    }

    private void search(String searchText) {
        if (searchText.length() == 0) {
            MyToast.show("Hoyy ..., Please enter a world", Toast.LENGTH_SHORT, R.drawable.ic_warning_white);
            return;
        }
        RecyclerView recyclerSearchResults = (RecyclerView) findViewById(R.id.recyclerSearch);
        recyclerSearchResults.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerSearchResults.setLayoutManager(layoutManager);

        ArrayList<Vocabulary> vocabularies = Vocabularies.search(searchText);
        VocabularyAdapter adapter = new VocabularyAdapter(this, vocabularies, false);
        recyclerSearchResults.setAdapter(adapter);
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
