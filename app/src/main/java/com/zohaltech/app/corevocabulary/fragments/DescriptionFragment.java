package com.zohaltech.app.corevocabulary.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zohaltech.app.corevocabulary.R;

public class DescriptionFragment extends Fragment {
    public static final String POSITION = "POSITION";

    public static DescriptionFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        DescriptionFragment fragment = new DescriptionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vocab_description, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
