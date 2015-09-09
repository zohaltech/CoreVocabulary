package com.zohaltech.app.corevocabulary.entities;

public class Vocabulary {
    private int     id;
    private int     themeId;
    private int     day;
    private String  vocabulary;
    private String  vocabEnglishDef;
    private String  vocabPersianDef;
    private Boolean visited;

    public Vocabulary(int themeId, int day, String vocabulary, String vocabEnglishDef, String vocabPersianDef, Boolean visited, Boolean learned) {
        setThemeId(themeId);
        setDay(day);
        setVocabulary(vocabulary);
        setVocabEnglishDef(vocabEnglishDef);
        setVocabPersianDef(vocabPersianDef);
        setVisited(visited);
        setLearned(learned);
    }

    public Vocabulary(int id, int themeId, int day, String vocabulary, String vocabEnglishDef, String vocabPersianDef, Boolean visited, Boolean learned) {
        this(themeId, day, vocabulary, vocabEnglishDef, vocabPersianDef, visited, learned);
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThemeId() {
        return themeId;
    }

    public void setThemeId(int themeId) {
        this.themeId = themeId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getVocabEnglishDef() {
        return vocabEnglishDef;
    }

    public void setVocabEnglishDef(String vocabEnglishDef) {
        this.vocabEnglishDef = vocabEnglishDef;
    }

    public String getVocabPersianDef() {
        return vocabPersianDef;
    }

    public void setVocabPersianDef(String vocabPersianDef) {
        this.vocabPersianDef = vocabPersianDef;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public Boolean getLearned() {
        return learned;
    }

    public void setLearned(Boolean learned) {
        this.learned = learned;
    }

    private Boolean learned;

}
