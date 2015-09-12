package com.zohaltech.app.corevocabulary.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.ThemeAdapter;
import com.zohaltech.app.corevocabulary.data.Themes;
import com.zohaltech.app.corevocabulary.entities.Theme;

import java.util.ArrayList;


public class ThemesFragment extends Fragment {

    ListView         lstPackagesHistories;
    ArrayList<Theme> themes;
    ThemeAdapter     adapter;

    public ThemesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_themes, container, false);

        lstPackagesHistories = (ListView) rootView.findViewById(R.id.lstThemes);
        themes = Themes.select();
        adapter = new ThemeAdapter(themes);
        lstPackagesHistories.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}