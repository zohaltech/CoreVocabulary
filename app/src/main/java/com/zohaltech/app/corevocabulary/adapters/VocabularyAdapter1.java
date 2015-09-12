package com.zohaltech.app.corevocabulary.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class VocabularyAdapter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Vocabulary> vocabularies;

    public VocabularyAdapter1(ArrayList<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptor_vocabulary1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    //@Override
    //public void onBindViewHolder(ViewHolder holder, int position) {
    //    holder.txtSection.setText("Day " + Vocabularies.select(vocabularies.get(position).getId()).getDay());
    //}

    @Override
    public int getItemCount() {
        return vocabularies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout layoutVocabulary;
        public TextView     txtSection;
        public TextView     txtVocabulary;

        public ViewHolder(View view) {
            super(view);
            layoutVocabulary = (LinearLayout) view.findViewById(R.id.layoutVocabulary);
            txtSection = (TextView) view.findViewById(R.id.txtSection);
            txtVocabulary = (TextView) view.findViewById(R.id.txtVocabulary);
        }
    }
}
