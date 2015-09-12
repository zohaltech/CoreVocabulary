package com.zohaltech.app.corevocabulary.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.adapters.VocabDefinitionAdapter;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class VocabularyDescFragment extends Fragment {

    public enum Tab {VOCAB, EXAMPLE, NOTE}

    public static final String POSITION = "POSITION";
    public static final String VOCAB_ID = "VOCAB_ID";
    ListView lstVocabDescriptions;


    public static VocabularyDescFragment newInstance(int position, int vocabId) {
        Bundle args = new Bundle();
        args.putInt(POSITION, position);
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
        View view = inflater.inflate(R.layout.fragment_vocab_definition, container, false);
        RecyclerView recyclerDefinition = (RecyclerView) view.findViewById(R.id.recyclerDefinition);
        recyclerDefinition.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerDefinition.setLayoutManager(layoutManager);

        int vocabId = getArguments().getInt(VOCAB_ID);
        ArrayList<Vocabulary> vocabularies = new ArrayList<>();
        vocabularies.add(Vocabularies.select(vocabId));
        VocabDefinitionAdapter adapter = new VocabDefinitionAdapter(getActivity(), vocabularies);
        recyclerDefinition.setAdapter(adapter);
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
