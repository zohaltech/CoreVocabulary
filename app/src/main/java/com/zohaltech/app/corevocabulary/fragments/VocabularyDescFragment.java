package com.zohaltech.app.corevocabulary.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.entities.Example;
import com.zohaltech.app.corevocabulary.entities.Note;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.List;

public class VocabularyDescFragment extends Fragment {
    public static final String POSITION = "POSITION";
    ListView      lstVocabDescriptions;
    Vocabulary    vocabulary;
    List<Example> examples;
    List<Note>    notes;


    public static VocabularyDescFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
        VocabularyDescFragment fragment = new VocabularyDescFragment();
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

        lstVocabDescriptions = (ListView) view.findViewById(R.id.lstVocabDescriptions);
        // dataPackages = new HashMap<>();
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
