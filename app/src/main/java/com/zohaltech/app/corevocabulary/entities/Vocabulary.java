package com.zohaltech.app.corevocabulary.entities;


public class Vocabulary {
    private int     id;
    private int     themeId;
    private String  vocabulary;
    private String  vocabEnglishDef;
    private String  vocabPersianDef;
    private String  example1;
    private String  example1Desc;
    private String  example2;
    private String  example2Desc;
    private String  example3;
    private String  example3Desc;
    private String  example4;
    private String  example4Desc;
    private String  note1;
    private String  note2;
    private String  note3;
    private String  note4;
    private Boolean visited;

    public Vocabulary(int themeId, String vocabulary, String vocabEnglishDef, String vocabPersianDef,
                      String example1, String example1Desc, String example2, String example2Desc, String example3,
                      String example3Desc, String example4, String example4Desc, String note1, String note2,
                      String note3, String note4, Boolean visited, Boolean learned) {
        setThemeId(themeId);
        setVocabulary(vocabulary);
        setVocabEnglishDef(vocabEnglishDef);
        setVocabPersianDef(vocabPersianDef);
        setExample1(example1);
        setExample1Desc(example1Desc);
        setExample2(example2);
        setExample2Desc(example2Desc);
        setExample3(example3);
        setExample3Desc(example3Desc);
        setExample4(example4);
        setExample4Desc(example4Desc);
        setNote1(note1);
        setNote2(note2);
        setNote3(note3);
        setNote4(note4);
        setVisited(visited);
        setLearned(learned);
    }

    public Vocabulary(int id, int themeId, String vocabulary, String vocabEnglishDef, String vocabPersianDef,
                      String example1, String example1Desc, String example2, String example2Desc, String example3,
                      String example3Desc, String example4, String example4Desc, String note1, String note2,
                      String note3, String note4, Boolean visited, Boolean learned) {
        this(themeId, vocabulary, vocabEnglishDef, vocabPersianDef,
             example1, example1Desc, example2, example2Desc, example3,
             example3Desc, example4, example4Desc, note1, note2, note3,
             note4, visited, learned);
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

    public String getExample1() {
        return example1;
    }

    public void setExample1(String example1) {
        this.example1 = example1;
    }

    public String getExample1Desc() {
        return example1Desc;
    }

    public void setExample1Desc(String example1Desc) {
        this.example1Desc = example1Desc;
    }

    public String getExample2() {
        return example2;
    }

    public void setExample2(String example2) {
        this.example2 = example2;
    }

    public String getExample2Desc() {
        return example2Desc;
    }

    public void setExample2Desc(String example2Desc) {
        this.example2Desc = example2Desc;
    }

    public String getExample3() {
        return example3;
    }

    public void setExample3(String example3) {
        this.example3 = example3;
    }

    public String getExample3Desc() {
        return example3Desc;
    }

    public void setExample3Desc(String example3Desc) {
        this.example3Desc = example3Desc;
    }

    public String getExample4() {
        return example4;
    }

    public void setExample4(String example4) {
        this.example4 = example4;
    }

    public String getExample4Desc() {
        return example4Desc;
    }

    public void setExample4Desc(String example4Desc) {
        this.example4Desc = example4Desc;
    }

    public String getNote1() {
        return note1;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return note2;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public String getNote3() {
        return note3;
    }

    public void setNote3(String note3) {
        this.note3 = note3;
    }

    public String getNote4() {
        return note4;
    }

    public void setNote4(String note4) {
        this.note4 = note4;
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
