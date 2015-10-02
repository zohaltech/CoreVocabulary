package com.zohaltech.app.corevocabulary.classes;

import com.zohaltech.app.corevocabulary.data.Examples;
import com.zohaltech.app.corevocabulary.data.Notes;
import com.zohaltech.app.corevocabulary.data.Vocabularies;
import com.zohaltech.app.corevocabulary.entities.Example;
import com.zohaltech.app.corevocabulary.entities.Note;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class SearchCache {
    private ArrayList<Vocabulary> vocabularies;
    private ArrayList<Example> examples;
    private ArrayList<Note> notes;

    private SearchCache(ArrayList<Vocabulary> vocabularies, ArrayList<Example> examples, ArrayList<Note> notes) {
        setVocabularies(vocabularies);
        setExamples(examples);
        setNotes(notes);
    }

    public static SearchCache initialCache() {
        ArrayList<Vocabulary> vocabularies = Vocabularies.selectForCache();
        ArrayList<Example> examples = Examples.selectForCache();
        ArrayList<Note> notes = Notes.select();

        return new SearchCache(vocabularies, examples, notes);
    }

    public static ArrayList<Vocabulary> searchFromCache(String searchText) {
        ArrayList<Vocabulary> searchResult = new ArrayList<>();
        SearchCache searchCache = App.searchCache;

        for (Vocabulary vocabulary : searchCache.getVocabularies()) {
            if (vocabulary.getVocabulary().toLowerCase().contains(searchText.toLowerCase())) {
                searchResult.add(Vocabularies.select(vocabulary.getId()));
            } else if (vocabulary.getVocabPersianDef().toLowerCase().contains(searchText.toLowerCase())) {
                searchResult.add(Vocabularies.select(vocabulary.getId()));
            } else if (vocabulary.getVocabEnglishDef().toLowerCase().contains(searchText.toLowerCase())) {
                searchResult.add(Vocabularies.select(vocabulary.getId()));
            }
        }
        for (Example example : searchCache.getExamples()) {
            if (example.getPersian().toLowerCase().contains(searchText.toLowerCase())) {
                if (!Exist(searchResult, example.getVocabularyId())) {
                    searchResult.add(Vocabularies.select(example.getVocabularyId()));
                } else if (example.getEnglish().toLowerCase().contains(searchText.toLowerCase())) {
                    if (!Exist(searchResult, example.getVocabularyId())) {
                        searchResult.add(Vocabularies.select(example.getVocabularyId()));
                    }
                }
            }
        }
        for (Note note : searchCache.getNotes()) {
            if (note.getDescription().toLowerCase().contains(searchText.toLowerCase())) {
                if (!Exist(searchResult, note.getVocabularyId())) {
                    searchResult.add(Vocabularies.select(note.getVocabularyId()));
                }
            }
        }
        return searchResult;
    }

    private ArrayList<Vocabulary> getVocabularies() {
        return vocabularies;
    }

    private void setVocabularies(ArrayList<Vocabulary> vocabularies) {
        this.vocabularies = vocabularies;
    }

    private ArrayList<Example> getExamples() {
        return examples;
    }

    private void setExamples(ArrayList<Example> examples) {
        this.examples = examples;
    }

    private ArrayList<Note> getNotes() {
        return notes;
    }

    private void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    private static Boolean Exist(ArrayList<Vocabulary> vocabularies, int vocabId) {
        for (Vocabulary vocabulary : vocabularies) {
            if (vocabulary.getId() == vocabId)
                return true;
        }
        return false;
    }
}
