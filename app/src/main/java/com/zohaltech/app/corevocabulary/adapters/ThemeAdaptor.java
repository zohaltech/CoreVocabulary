package com.zohaltech.app.corevocabulary.adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.activities.VocabulariesActivity;
import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.entities.Theme;

import java.util.ArrayList;

public class ThemeAdaptor extends ArrayAdapter<Theme> {

    public ThemeAdaptor(ArrayList<Theme> themeList) {
        super(App.context, R.layout.adaptor_theme, themeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        Theme item = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(App.currentActivity);
            convertView = inflater.inflate(R.layout.adaptor_theme, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.fill(this, item, position);
        return convertView;
    }

    private static class ViewHolder {
        LinearLayout layoutTheme;
        TextView     txtThemeName;

        public ViewHolder(View view) {
            layoutTheme = (LinearLayout) view.findViewById(R.id.layoutTheme);
            txtThemeName = (TextView) view.findViewById(R.id.txtThemeName);
        }

        public void fill(final ArrayAdapter<Theme> adapter, final Theme item, final int position) {
            //            int imageId = App.context.getResources().getIdentifier("ic_launcher", "drawable", App.context.getPackageName());
            //            ImageView img = new ImageView(App.context);
            //            img.setImageResource(imageId);

            //if (position % 2 == 1) {
            //    layoutTheme.setBackgroundResource(R.color.primary_lighter);
            //} else {
            //    layoutTheme.setBackgroundResource(R.color.white);
            //}

            txtThemeName.setText(item.getName());

            layoutTheme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(App.currentActivity, VocabulariesActivity.class);
                    intent.putExtra("THEME", item);
                    App.currentActivity.startActivity(intent);
                }
            });
        }
    }
}
