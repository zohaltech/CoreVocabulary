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
        //recyclerVocabularies.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        //recyclerVocabularies.addItemDecoration(new RecyclerView.ItemDecoration() {
        //    @Override
        //    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //        super.onDraw(c, parent, state);
        //        Paint paint = new Paint();
        //        paint.setStrokeWidth(1);
        //        paint.setColor(getResources().getColor(R.color.divider));
        //        c.drawLine(0, 0, App.screenWidth, App.screenHeight, paint);
        //    }
        //});

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerVocabularies.setHasFixedSize(true);

        // use a linear layout manager
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
