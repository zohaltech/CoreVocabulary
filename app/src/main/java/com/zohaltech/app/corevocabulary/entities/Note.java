package com.zohaltech.app.corevocabulary.entities;

public class Note
{
    private int id;
    private int vocabularyId;
    private String Description;

    public Note(int id, int vocabularyId, String description)
    {
        this(vocabularyId, description);
        this.id = id;
    }

    public Note(int vocabularyId, String description)
    {
        setVocabularyId(vocabularyId);
        setDescription(description);
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

    public String getDescription()
    {
        return Description;
    }

    public void setDescription(String description)
    {
        Description = description;
    }
}
