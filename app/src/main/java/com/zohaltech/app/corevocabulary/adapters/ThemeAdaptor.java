package com.zohaltech.app.corevocabulary.adapters;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zohaltech.app.corevocabulary.R;
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
            convertView = App.inflater.inflate(R.layout.adaptor_theme, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.fill(this, item, position);
        return convertView;
    }

    private static class ViewHolder {
        LinearLayout layoutReport;
        TextView     txtThemeName;

        public ViewHolder(View view) {
            layoutReport = (LinearLayout) view.findViewById(R.id.layoutTheme);
            txtThemeName = (TextView) view.findViewById(R.id.txtThemeName);
        }

        public void fill(final ArrayAdapter<Theme> adapter, final Theme item, final int position) {
            if (position % 2 == 1) {
                layoutReport.setBackgroundResource(R.color.primary_lighter);
            } else {
                layoutReport.setBackgroundResource(R.color.white);
            }

            txtThemeName.setText(item.getName());
        }
    }
}
