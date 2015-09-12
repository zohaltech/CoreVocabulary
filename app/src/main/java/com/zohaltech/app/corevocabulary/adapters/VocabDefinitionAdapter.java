package com.zohaltech.app.corevocabulary.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class VocabDefinitionAdapter extends RecyclerView.Adapter<VocabDefinitionAdapter.ViewHolder> {
    Context               context;
    ArrayList<Vocabulary> vocabularies;

    public VocabDefinitionAdapter(Context context, ArrayList<Vocabulary> vocabularies) {
        this.context = context;
        this.vocabularies = vocabularies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_vocab_definition, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Vocabulary vocabulary = vocabularies.get(position);
        holder.txtVocabulary.setText(vocabulary.getVocabulary());
        holder.txtVocabEnglishDefinition.setText(vocabulary.getVocabEnglishDef());
        holder.txtVocabPersianMeaning.setText(vocabulary.getVocabPersianDef());

    }

    @Override
    public int getItemCount() {
        return vocabularies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtVocabulary;
        public TextView txtVocabEnglishDefinition;
        public TextView txtVocabPersianMeaning;

        public ViewHolder(View view) {
            super(view);
            txtVocabulary = (TextView) view.findViewById(R.id.txtVocabulary);
            txtVocabEnglishDefinition = (TextView) view.findViewById(R.id.txtVocabEnglishDefinition);
            txtVocabPersianMeaning = (TextView) view.findViewById(R.id.txtVocabPersianMeaning);
        }
    }
}
