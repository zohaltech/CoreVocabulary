package com.zohaltech.app.corevocabulary.entities;

import com.zohaltech.app.corevocabulary.classes.CoreSec;

public class Vocabulary
{
    private int id;
    private int themeId;
    private int day;
    private String vocabulary;
    private String vocabEnglishDef;
    private String vocabPersianDef;
    private Boolean learned;
    private Boolean bookmarked;

    //    private String encVocab;
    //    private String encEngDef;
    //    private String encPersianDef;


    //    public Vocabulary(int themeId, int day, String vocabulary, String vocabEnglishDef, String vocabPersianDef, Boolean learned, Boolean bookmarked,
    //                      String encVocab, String encEngDef, String encPersianDef) {
    //        setThemeId(themeId);
    //        setDay(day);
    //        setVocabulary(vocabulary);
    //        setVocabEnglishDef(vocabEnglishDef);
    //        setVocabPersianDef(vocabPersianDef);
    //        setLearned(learned);
    //        setBookmarked(bookmarked);
    //
    //        setEncVocab(encVocab);
    //        setEncEngDef(encEngDef);
    //        setEncPersianDef(encPersianDef);
    //    }

    public Vocabulary(int themeId, int day, String vocabulary, String vocabEnglishDef, String vocabPersianDef, Boolean learned, Boolean bookmarked
    )
    {
        setThemeId(themeId);
        setDay(day);
        setVocabulary(vocabulary);
        setVocabEnglishDef(vocabEnglishDef);
        setVocabPersianDef(vocabPersianDef);
        setLearned(learned);
        setBookmarked(bookmarked);
    }

    //    public Vocabulary(int id, int themeId, int day, String vocabulary, String vocabEnglishDef, String vocabPersianDef, Boolean learned, Boolean bookmarked
    //            , String encVocab, String encEngDef, String encPersianDef) {
    //        this(themeId, day, vocabulary, vocabEnglishDef, vocabPersianDef, learned, bookmarked,
    //             encVocab, encEngDef, encPersianDef);
    //        this.id = id;
    //    }

    public Vocabulary(int id, int themeId, int day, String vocabulary, String vocabEnglishDef, String vocabPersianDef, Boolean learned, Boolean bookmarked)
    {
        this(themeId, day, vocabulary, vocabEnglishDef, vocabPersianDef, learned, bookmarked);
        this.id = id;
    }

    public Vocabulary(int id, String vocabulary, String vocabEnglishDef, String vocabPersianDef)
    {
        setId(id);
        setVocabulary(vocabulary);
        setVocabEnglishDef(vocabEnglishDef);
        setVocabPersianDef(vocabPersianDef);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getThemeId()
    {
        return themeId;
    }

    public void setThemeId(int themeId)
    {
        this.themeId = themeId;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public String getVocabulary()
    {
        return vocabulary;
    }

    public void setVocabulary(String vocabulary)
    {
        this.vocabulary = vocabulary;
    }

    public String getVocabEnglishDef()
    {
        return vocabEnglishDef;
    }

    public void setVocabEnglishDef(String vocabEnglishDef)
    {
        this.vocabEnglishDef = vocabEnglishDef;
    }

    public String getVocabPersianDef()
    {
        return vocabPersianDef;
    }

    public void setVocabPersianDef(String vocabPersianDef)
    {
        this.vocabPersianDef = vocabPersianDef;
    }

    public Boolean getLearned()
    {
        return learned;
    }

    public void setLearned(Boolean learned)
    {
        this.learned = learned;
    }

    public Boolean getBookmarked()
    {
        return bookmarked;
    }

    public void setBookmarked(Boolean bookmarked)
    {
        this.bookmarked = bookmarked;
    }


    //    public String getEncVocab() {
    //        return CoreSec.decrypt(encVocab);
    //    }
    //
    //    public String getEncVocab1() {
    //        return encVocab;
    //    }
    //
    //    public void setEncVocab(String encVocab) {
    //        this.encVocab = encVocab;
    //    }
    //
    //    public String getEncEngDef() {
    //        return CoreSec.decrypt(encEngDef);
    //    }
    //
    //    public String getEncEngDef1() {
    //        return encEngDef;
    //    }
    //
    //
    //    public void setEncEngDef(String encEngDef) {
    //        this.encEngDef = encEngDef;
    //    }
    //
    //    public String getEncPersianDef() {
    //        return CoreSec.decrypt(encPersianDef);
    //    }
    //
    //    public String getEncPersianDef1() {
    //        return encPersianDef;
    //    }
    //
    //    public void setEncPersianDef(String encPersianDef) {
    //        this.encPersianDef = encPersianDef;
    //    }
}
