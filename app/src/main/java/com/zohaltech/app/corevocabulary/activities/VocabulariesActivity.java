package com.zohaltech.app.corevocabulary.activities;


import android.view.MenuItem;
import android.widget.ListView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.VocabularyAdaptor;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Theme;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class VocabulariesActivity extends EnhancedActivity {

    ListView          lstVocabularies;
    ArrayList<Vocabulary>  themeVocabs;
    VocabularyAdaptor adapter;

    @Override
    void onCreated() {
        setContentView(R.layout.activity_vocabularies);
        int themeId = getIntent().getIntExtra("ThemeId", 0);
        themeVocabs = Vocabularies.getVocabsOfTheme(themeId);

        lstVocabularies = (ListView) findViewById(R.id.lstVocabularies);
        adapter = new VocabularyAdaptor(themeVocabs);
        lstVocabularies.setAdapter(adapter);
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
        txtToolbarTitle.setText("Vocabularies");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
