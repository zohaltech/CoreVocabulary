package com.zohaltech.app.corevocabulary.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.activities.VocabDescriptionActivity;
import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context               context;
    ArrayList<Vocabulary> vocabularies;

    public SearchAdapter(Context context, ArrayList<Vocabulary> vocabularies) {
        this.context = context;
        this.vocabularies = vocabularies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Vocabulary vocabulary = vocabularies.get(position);

        holder.txtSearchResult.setText(vocabulary.getVocabulary());
        holder.txtSearchResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.currentActivity, VocabDescriptionActivity.class);
                intent.putExtra(VocabDescriptionActivity.VOCAB_ID, vocabulary.getId());
                App.currentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vocabularies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtSearchResult;

        public ViewHolder(View view) {
            super(view);
            txtSearchResult = (TextView) view.findViewById(R.id.txtSearchResult);
        }
    }
}