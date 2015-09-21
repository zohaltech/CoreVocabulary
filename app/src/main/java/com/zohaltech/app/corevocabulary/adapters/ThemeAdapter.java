package com.zohaltech.app.corevocabulary.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
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

    private static final long DURATION = 300;

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

    public static void expand(final View v) {
        v.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                                             ? ViewGroup.LayoutParams.WRAP_CONTENT
                                             : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        //        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        a.setDuration(DURATION);
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        //        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        a.setDuration(DURATION);
        v.startAnimation(a);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_theme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Theme theme = themes.get(position);
        //holder.txtTheme.setText(theme.getName());
        holder.txtTheme.setText(theme.getEncName());
        holder.txtTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.currentActivity, VocabulariesActivity.class);
                intent.putExtra("THEME", theme);
                App.currentActivity.startActivity(intent);
            }
        });

        //holder.layoutProgressDetail.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        holder.layoutProgressDetail.setVisibility(View.GONE);
        //collapse(holder.layoutProgressDetail);

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

                    ProgressDetailStatus status = progressDetailStatuses.get(position);
                    if (status.visible) {
                        collapse(holder.layoutProgressDetail);
                        App.handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                holder.layoutProgressDetail.setVisibility(View.GONE);
                            }
                        }, DURATION);
                        status.visible = false;
                    } else {
                        holder.layoutProgressDetail.setVisibility(View.VISIBLE);
                        //ViewCompat.animate(holder.layoutProgressDetail).scaleYBy(12).setDuration(1000).start();
                        expand(holder.layoutProgressDetail);
                        status.visible = true;
                    }
                }
            });
        } else {
            holder.layoutCircleProgress.setVisibility(View.GONE);
            holder.layoutProgressDetail.setVisibility(View.GONE);
            progressDetailStatuses.set(position, new ProgressDetailStatus(position, false));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
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
