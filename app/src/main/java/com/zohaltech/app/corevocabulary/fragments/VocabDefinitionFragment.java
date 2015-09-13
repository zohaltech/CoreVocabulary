package com.zohaltech.app.corevocabulary.fragments;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zohaltech.app.corevocabulary.R;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.Locale;

public class VocabDefinitionFragment extends Fragment implements
                                                      TextToSpeech.OnInitListener {
    public static final String VOCAB_ID = "VOCAB_ID";
    TextView txtVocabulary;
    TextView txtVocabEnglishDefinition;
    TextView txtVocabPersianMeaning;
    private TextToSpeech textToSpeech;

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
        Button btnSpeechUS = (Button) view.findViewById(R.id.btnSpeechUS);
        Button btnSpeechUK = (Button) view.findViewById(R.id.btnSpeechUK);

        int vocabId = getArguments().getInt(VOCAB_ID);
        Vocabulary vocabulary = Vocabularies.select(vocabId);

        textToSpeech = new TextToSpeech(getActivity(), this);

        assert vocabulary != null;
        txtVocabulary.setText(vocabulary.getVocabulary());
        txtVocabEnglishDefinition.setText(vocabulary.getVocabEnglishDef());
        txtVocabPersianMeaning.setText(vocabulary.getVocabPersianDef());

        btnSpeechUK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.setLanguage(Locale.US);
                speakOut();
            }
        });

        btnSpeechUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.setLanguage(Locale.US);
                speakOut();
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
        if (status == TextToSpeech.SUCCESS) {

            int result = textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speakOut();
            }

        } else {
            Log.e("TTS", " Failed!");
        }

    }

    private void speakOut() {
        String text = txtVocabulary.getText().toString();
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
