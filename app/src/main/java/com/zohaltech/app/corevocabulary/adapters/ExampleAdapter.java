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
import com.zohaltech.app.corevocabulary.entities.Example;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {

    Context            context;
    ArrayList<Example> examples;

    public ExampleAdapter(Context context, ArrayList<Example> examples) {
        this.context = context;
        this.examples = examples;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_examples, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Example example = examples.get(position);
//        if (position % 6 == 0) {
//            holder.txtSection.setVisibility(View.VISIBLE);
//            holder.txtSection.setText("Day " + vocabulary.getDay());
//        } else {
//            holder.txtSection.setVisibility(View.GONE);
//        }
//        holder.txtVocabulary.setText(vocabulary.getVocabulary());
//        holder.txtVocabulary.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(App.currentActivity, VocabDescriptionActivity.class);
//                intent.putExtra("VocabularyId", vocabulary.getId());
//                App.currentActivity.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return examples.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtSection;
        public TextView txtVocabulary;

        public ViewHolder(View view) {
            super(view);
            txtSection = (TextView) view.findViewById(R.id.txtSection);
            txtVocabulary = (TextView) view.findViewById(R.id.txtVocabulary);
        }
    }
}
