package com.zohaltech.app.corevocabulary.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class VocabularyAdaptor extends ArrayAdapter<Vocabulary> {
    public VocabularyAdaptor(ArrayList<Vocabulary> vocabularyList) {
        super(App.context, R.layout.adaptor_vocabulary, vocabularyList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        Vocabulary item = getItem(position);
        if (convertView == null) {
            convertView = App.inflater.inflate(R.layout.adaptor_vocabulary, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.fill(this, item, position);
        return convertView;
    }

    private static class ViewHolder {
        LinearLayout layoutVocabulary;
        TextView     txtVocabulary;

        public ViewHolder(View view) {
            layoutVocabulary = (LinearLayout) view.findViewById(R.id.layoutVocabulary);
            txtVocabulary = (TextView) view.findViewById(R.id.txtVocabulary);
        }

        public void fill(final ArrayAdapter<Vocabulary> adapter, final Vocabulary item, final int position) {
            if (position % 2 == 1) {
                layoutVocabulary.setBackgroundResource(R.color.primary_lighter);
            } else {
                layoutVocabulary.setBackgroundResource(R.color.white);
            }
            txtVocabulary.setText(item.getVocabulary());
        }
    }
}
