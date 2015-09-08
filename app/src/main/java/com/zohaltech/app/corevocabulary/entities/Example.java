package com.zohaltech.app.corevocabulary.entities;

public class Example
{
    private int id;
    private int vocabularyId;
    private String English;
    private String Persian;

    public Example(int id, int vocabularyId, String english, String persian)
    {
        this(vocabularyId, english, persian);
        this.id = id;
    }

    public Example(int vocabularyId, String english, String persian)
    {
        setVocabularyId(vocabularyId);
        setEnglish(english);
        setPersian(persian);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getVocabularyId()
    {
        return vocabularyId;
    }

    public void setVocabularyId(int vocabularyId)
    {
        this.vocabularyId = vocabularyId;
    }

    public String getEnglish()
    {
        return English;
    }

    public void setEnglish(String english)
    {
        English = english;
    }

    public String getPersian()
    {
        return Persian;
    }

    public void setPersian(String persian)
    {
        Persian = persian;
    }
}
