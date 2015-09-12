package com.zohaltech.app.corevocabulary.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.data.Examples;
import com.zohaltech.app.corevocabulary.data.Notes;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Example;
import com.zohaltech.app.corevocabulary.entities.Note;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.List;

public class VocabularyFragment extends Fragment {

    public enum Tab {VOCAB, EXAMPLE, NOTE}

    public static final String POSITION = "POSITION";
    public static final String VOCAB_ID = "VOCAB_ID";
    ListView lstVocabDescriptions;


    public static VocabularyDescFragment newInstance(int vocabId) {
        Bundle args = new Bundle();
        args.putInt(VOCAB_ID, vocabId);
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

        int vocabId = getArguments().getInt(VOCAB_ID);
        int position = getArguments().getInt(POSITION);

        if (position == Tab.VOCAB.ordinal()) {

        } else if (position == Tab.EXAMPLE.ordinal()) {

        } else if (position == Tab.NOTE.ordinal()) {

        }

        Vocabulary vocabulary = Vocabularies.select(vocabId);
        List<Example> examples = Examples.getExamples(vocabId);
        List<Note> notes = Notes.getNotes(vocabId);


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