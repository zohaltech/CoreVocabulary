package com.zohaltech.app.corevocabulary.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.classes.NavDrawerItem;

import java.util.Collections;
import java.util.List;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.ViewHolder> {
    List<NavDrawerItem> items = Collections.emptyList();
    private LayoutInflater inflater;
    private Context        context;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> items) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.items = items;
    }

    //public void delete(int position) {
    //    items.remove(position);
    //    notifyItemRemoved(position);
    //}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NavDrawerItem current = items.get(position);
        holder.title.setText(current.getTitle());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}