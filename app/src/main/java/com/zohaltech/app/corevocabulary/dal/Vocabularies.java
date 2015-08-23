package com.zohaltech.app.corevocabulary.dal;

public class Vocabularies {

    static final String TableName       = "Vocabularies";
    static final String Id              = "Id";
    static final String ThemeId         = "ThemeId";
    static final String Vocabulary      = "Vocabulary";
    static final String VocabEnglishDef = "VocabEnglishDef";
    static final String VocabPersianDef = "VocabPersianDef";
    static final String Example1        = "Example1";
    static final String Example1Desc    = "Example1Desc";
    static final String Example2        = "Example2";
    static final String Example2Desc    = "Example2Desc";
    static final String Example3        = "Example3";
    static final String Example3Desc    = "Example3Desc";
    static final String Example4        = "Example4";
    static final String Example4Desc    = "Example4Desc";
    static final String Note1           = "Note1";
    static final String Note2           = "Note2";
    static final String Note3           = "Note3";
    static final String Note4           = "Note4";
    static final String Visited         = "Visited";
    static final String Learned         = "Learned";

    static final String CreateTable = "CREATE TABLE " + TableName + " (\n" +
                                      Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \n" +
                                      ThemeId + " INTEGER REFERENCES " + Themes.TableName + " (" + Themes.Id + "), \n" +
                                      Vocabulary + " VARCHAR(50), \n" +
                                      VocabEnglishDef + " VARCHAR(200),\n" +
                                      VocabPersianDef + " VARCHAR(200), \n" +
                                      Example1 + " VARCHAR(200), \n" +
                                      Example1Desc + " VARCHAR(200), \n" +
                                      Example2 + " VARCHAR(200), \n" +
                                      Example2Desc + " VARCHAR(200), \n" +
                                      Example3 + " VARCHAR(200),\n" +
                                      Example3Desc + " VARCHAR(200),\n" +
                                      Example4 + " VARCHAR(200), \n" +
                                      Example4Desc + " VARCHAR(200), \n" +
                                      Note1 + " VARCHAR(200), \n" +
                                      Note2 + " VARCHAR(200), \n" +
                                      Note3 + " VARCHAR(200), \n" +
                                      Note4 + " VARCHAR(200), \n" +
                                      Visited + " Boolean, \n" +
                                      Learned + " Boolean );";

    static final String DropTable = "Drop Table If Exists " + TableName;
}


