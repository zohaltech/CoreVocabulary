package com.zohaltech.app.corevocabulary.entities;


public class Vocabulary
{
    private int id;
    private int themeId;
    private String vocabulary;
    private String vocabEnglishDef;
    private String vocabPersianDef;
    private Boolean visited;

    public Vocabulary(int themeId, String vocabulary, String vocabEnglishDef, String vocabPersianDef, Boolean visited, Boolean learned)
    {
        setThemeId(themeId);
        setVocabulary(vocabulary);
        setVocabEnglishDef(vocabEnglishDef);
        setVocabPersianDef(vocabPersianDef);
        setVisited(visited);
        setLearned(learned);
    }

    public Vocabulary(int id, int themeId, String vocabulary, String vocabEnglishDef, String vocabPersianDef, Boolean visited, Boolean learned)
    {
        this(themeId, vocabulary, vocabEnglishDef, vocabPersianDef, visited, learned);
        this.id = id;
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

    public Boolean getVisited()
    {
        return visited;
    }

    public void setVisited(Boolean visited)
    {
        this.visited = visited;
    }

    public Boolean getLearned()
    {
        return learned;
    }

    public void setLearned(Boolean learned)
    {
        this.learned = learned;
    }

    private Boolean learned;
}
