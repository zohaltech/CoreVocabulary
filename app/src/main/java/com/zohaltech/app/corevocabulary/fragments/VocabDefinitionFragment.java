package com.zohaltech.app.corevocabulary.fragments;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

public class VocabDefinitionFragment extends Fragment implements
                                                      TextToSpeech.OnInitListener {
    public static final String VOCAB_ID = "VOCAB_ID";
    private Button btnSpeechUS;
    private Button btnSpeechUK;
    TextView txtVocabulary;
    TextView txtVocabEnglishDefinition;
    TextView txtVocabPersianMeaning;

    public static VocabDefinitionFragment newInstance(int vocabId) {
        Bundle args = new Bundle();
        args.putInt(VOCAB_ID, vocabId);
        VocabDefinitionFragment fragment = new VocabDefinitionFragment();
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

        txtVocabulary = (TextView) view.findViewById(R.id.txtVocabulary);
        txtVocabEnglishDefinition = (TextView) view.findViewById(R.id.txtVocabEnglishDefinition);
        txtVocabPersianMeaning = (TextView) view.findViewById(R.id.txtVocabPersianMeaning);
        btnSpeechUS = (Button) view.findViewById(R.id.btnSpeechUS);
        btnSpeechUK = (Button) view.findViewById(R.id.btnSpeechUK);

        int vocabId = getArguments().getInt(VOCAB_ID);
        Vocabulary vocabulary = Vocabularies.select(vocabId);

        assert vocabulary != null;
        txtVocabulary.setText(vocabulary.getVocabulary());
        txtVocabEnglishDefinition.setText(vocabulary.getVocabEnglishDef());
        txtVocabPersianMeaning.setText(vocabulary.getVocabPersianDef());

        btnSpeechUK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSpeechUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


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

    @Override
    public void onInit(int status) {

    }
}
