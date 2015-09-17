package com.zohaltech.app.corevocabulary.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.SearchAdapter;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    RecyclerView recyclerSearch;
    ArrayList<Vocabulary> vocabularies = new ArrayList<>();
    SearchAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerSearch = (RecyclerView) rootView.findViewById(R.id.recyclerSearch);
        recyclerSearch.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerSearch.setLayoutManager(layoutManager);
        vocabularies.clear();
        adapter = new SearchAdapter(getActivity(), vocabularies);
        recyclerSearch.setAdapter(adapter);
        return rootView;
    }

    public void search(String text) {
        vocabularies.clear();
        if (text != null && text.length() > 0) {
            vocabularies.addAll(Vocabularies.search(text));
        }
        adapter.notifyDataSetChanged();
    }
}