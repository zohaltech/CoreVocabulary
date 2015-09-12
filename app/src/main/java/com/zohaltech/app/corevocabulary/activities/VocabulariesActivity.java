package com.zohaltech.app.corevocabulary.activities;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.VocabularyAdapter;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Theme;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class VocabulariesActivity extends EnhancedActivity {

    private RecyclerView               recyclerVocabularies;
    private ArrayList<Vocabulary>      vocabularies;
    private VocabularyAdapter          adapter;
    private Theme                      theme;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    void onCreated() {
        setContentView(R.layout.activity_vocabularies);
        recyclerVocabularies = (RecyclerView) findViewById(R.id.recyclerVocabularies);
        recyclerVocabularies.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerVocabularies.setLayoutManager(layoutManager);
        theme = (Theme) getIntent().getSerializableExtra("THEME");
        vocabularies = Vocabularies.selectByTheme(theme.getId());
        adapter = new VocabularyAdapter(this, vocabularies);
        recyclerVocabularies.setAdapter(adapter);
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
        //txtToolbarTitle.setText("Vocabularies");
        getSupportActionBar().setTitle(theme.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
