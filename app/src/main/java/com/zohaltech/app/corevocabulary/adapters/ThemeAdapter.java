package com.zohaltech.app.corevocabulary.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.activities.VocabDescriptionActivity;
import com.zohaltech.app.corevocabulary.activities.VocabulariesActivity;
import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.entities.Theme;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    Context          context;
    ArrayList<Theme> themes;

    public ThemeAdapter(Context context, ArrayList<Theme> themes) {
        this.context = context;
        this.themes = themes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adaptor_theme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Theme theme = themes.get(position);
        holder.txtTheme.setText(theme.getName());
        holder.txtTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.currentActivity, VocabulariesActivity.class);
                intent.putExtra("THEME", theme);
                App.currentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView     txtTheme;

        public ViewHolder(View view) {
            super(view);
            txtTheme = (TextView) view.findViewById(R.id.txtTheme);
        }
    }
}
