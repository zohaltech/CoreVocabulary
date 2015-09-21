package com.zohaltech.app.corevocabulary.entities;

import com.zohaltech.app.corevocabulary.classes.CoreSec;

public class Example {
    private int    id;
    private int    vocabularyId;
    private int    ordinal;
    private String English;
    private String Persian;

    private String encEnglish;
    private String encPersian;


    public Example(int id, int vocabularyId, int ordinal, String english, String persian,
                   String encEnglish, String encPersian) {
        this(vocabularyId, ordinal, english, persian, encEnglish, encPersian);
        this.id = id;
    }

    public Example(int vocabularyId, int ordinal, String english, String persian,
                   String encEnglish, String encPersian) {
        setVocabularyId(vocabularyId);
        setOrdinal(ordinal);
        setEnglish(english);
        setPersian(persian);

        setEncPersian(encPersian);
        setEncEnglish(encEnglish);
    }

    public Example(int id, int vocabularyId, int ordinal, String english, String persian) {
        this(vocabularyId, ordinal, english, persian);
        this.id = id;
    }

    public Example(int vocabularyId, int ordinal, String english, String persian) {
        setVocabularyId(vocabularyId);
        setOrdinal(ordinal);
        setEnglish(english);
        setPersian(persian);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVocabularyId() {
        return vocabularyId;
    }

    public void setVocabularyId(int vocabularyId) {
        this.vocabularyId = vocabularyId;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getPersian() {
        return Persian;
    }

    public void setPersian(String persian) {
        Persian = persian;
    }

    public String getEncEnglish() {
        return CoreSec.decrypt(encEnglish);
    }

    public String getEncEnglish1() {
        return encEnglish;
    }

    public void setEncEnglish(String encEnglish) {
        this.encEnglish = encEnglish;
    }

    public String getEncPersian() {
        return CoreSec.decrypt(encPersian);
    }

    public String getEncPersian1() {
        return encPersian;
    }

    public void setEncPersian(String encPersian) {
        this.encPersian = encPersian;
    }
}
