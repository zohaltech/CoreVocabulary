package com.zohaltech.app.corevocabulary.activities;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.SearchAdapter;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

import widgets.MyToast;

public class SearchActivity extends EnhancedActivity {

    private EditText editTextSearch;
    private Button   btnSearch;
    public static final String SEARCH_TEXT        = "SEARCH_TEXT";

    @Override
    void onCreated() {
        setContentView(R.layout.activity_search);

        String searchText = getIntent().getStringExtra(SEARCH_TEXT);
        search(searchText);

        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search(editTextSearch.getText().toString());
            }
        });
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
        SearchAdapter adapter = new SearchAdapter(this, vocabularies);
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
        //txtToolbarTitle.setText("Vocabularies");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
