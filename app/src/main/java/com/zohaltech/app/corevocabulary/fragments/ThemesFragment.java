package com.zohaltech.app.corevocabulary.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.ThemeAdapter;
import com.zohaltech.app.corevocabulary.data.Themes;
import com.zohaltech.app.corevocabulary.entities.Theme;

import java.util.ArrayList;


public class ThemesFragment extends Fragment {

    RecyclerView     recyclerThemes;
    ArrayList<Theme> themes;
    ThemeAdapter     adapter;
    private RecyclerView.LayoutManager layoutManager;

    public ThemesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_themes, container, false);
        recyclerThemes = (RecyclerView) rootView.findViewById(R.id.recyclerThemes);
        recyclerThemes.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerThemes.setLayoutManager(layoutManager);
        themes = Themes.select();
        adapter = new ThemeAdapter(getActivity(), themes);
        recyclerThemes.setAdapter(adapter);
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