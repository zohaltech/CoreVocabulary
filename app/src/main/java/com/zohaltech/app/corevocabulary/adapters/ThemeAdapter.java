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
import com.zohaltech.app.corevocabulary.activities.VocabulariesActivity;
import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.classes.LearningStatus;
import com.zohaltech.app.corevocabulary.entities.Theme;

import java.util.ArrayList;

import widgets.CircleProgress;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    Context                         context;
    ArrayList<Theme>                themes;
    ArrayList<ProgressDetailStatus> progressDetailStatuses;

    public ThemeAdapter(Context context, ArrayList<Theme> themes) {
        this.context = context;
        this.themes = themes;
        this.progressDetailStatuses = new ArrayList<>();
        for (int i = 0; i < themes.size(); i++) {
            progressDetailStatuses.add(new ProgressDetailStatus(i, false));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adaptor_theme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
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

        //holder.circleProgress.setLayoutParams(new LinearLayout.LayoutParams(holder.circleProgress.getHeight(), holder.circleProgress.getHeight()));
        holder.layoutProgressDetail.setVisibility(View.GONE);

        LearningStatus status = LearningStatus.getLearningStatusByTheme(theme.getId());
        if (status != null) {
            holder.layoutCircleProgress.setVisibility(View.VISIBLE);

            holder.circleProgress.setProgress(status.getProgress());

            if (progressDetailStatuses.get(position).visible) {
                holder.layoutProgressDetail.setVisibility(View.VISIBLE);
            } else {
                holder.layoutProgressDetail.setVisibility(View.GONE);
            }

            holder.txtDayProgress.setText(String.format("Day %d/%d", status.getDayIndex(), status.getDayCount()));
            holder.txtVocabProgress.setText(String.format("Vocab %d/%d", status.getVocabIndex(), status.getVocabCount()));

            holder.layoutCircleProgress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (int i = 0; i < themes.size(); i++) {
                        if (position != i) {
                            progressDetailStatuses.get(i).visible = false;
                        }
                    }

                    notifyDataSetChanged();

                    ProgressDetailStatus status = progressDetailStatuses.get(position);
                    if (status.visible) {
                        holder.layoutProgressDetail.setVisibility(View.GONE);
                        status.visible = false;
                    } else {
                        holder.layoutProgressDetail.setVisibility(View.VISIBLE);
                        status.visible = true;
                    }
                }
            });
        } else {
            holder.layoutCircleProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView       txtTheme;
        public CircleProgress circleProgress;
        public LinearLayout   layoutCircleProgress;
        public LinearLayout   layoutProgressDetail;
        public TextView       txtDayProgress;
        public TextView       txtVocabProgress;

        public ViewHolder(View view) {
            super(view);
            txtTheme = (TextView) view.findViewById(R.id.txtTheme);
            layoutCircleProgress = (LinearLayout) view.findViewById(R.id.layoutCircleProgress);
            circleProgress = (CircleProgress) view.findViewById(R.id.circleProgress);
            layoutProgressDetail = (LinearLayout) view.findViewById(R.id.layoutProgressDetail);
            txtDayProgress = (TextView) view.findViewById(R.id.txtDayProgress);
            txtVocabProgress = (TextView) view.findViewById(R.id.txtVocabProgress);
        }
    }

    private class ProgressDetailStatus {
        public int     position;
        public boolean visible;

        public ProgressDetailStatus(int position, boolean visible) {
            this.position = position;
            this.visible = visible;
        }
    }
}
