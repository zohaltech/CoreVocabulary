package com.zohaltech.app.corevocabulary.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zohaltech.app.corevocabulary.classes.MyRuntimeException;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class Vocabularies {

    static final String TableName  = "Vocabularies";
    static final String Id         = "Id";
    static final String ThemeId    = "ThemeId";
    static final String Day        = "Day";
    static final String Vocabulary = "Vocabulary";
    static final String EnglishDef = "EnglishDef";
    static final String PersianDef = "PersianDef";
    static final String Visited    = "Visited";
    static final String Learned    = "Learned";

    static final String CreateTable = "CREATE TABLE " + TableName + " ( " +
                                      Id + " INTEGER PRIMARY KEY NOT NULL, " +
                                      ThemeId + " INTEGER REFERENCES " + Themes.TableName + " (" + Themes.Id + "), " +
                                      Day + " INTEGER, " +
                                      Vocabulary + " VARCHAR(250), " +
                                      EnglishDef + " VARCHAR(1024)," +
                                      PersianDef + " VARCHAR(1024), " +
                                      Visited + " Boolean, " +
                                      Learned + " Boolean );";

    static final String DropTable = "Drop Table If Exists " + TableName;

    private static ArrayList<Vocabulary> select(String whereClause, String[] selectionArgs) {
        ArrayList<Vocabulary> examples = new ArrayList<>();
        DataAccess da = new DataAccess();
        SQLiteDatabase db = da.getReadableDB();
        Cursor cursor = null;

        try {
            String query = "Select * From " + TableName + " " + whereClause;
            cursor = db.rawQuery(query, selectionArgs);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Vocabulary vocabulary = new Vocabulary(cursor.getInt(cursor.getColumnIndex(Id)),
                                                           cursor.getInt(cursor.getColumnIndex(ThemeId)),
                                                           cursor.getInt(cursor.getColumnIndex(Day)),
                                                           cursor.getString(cursor.getColumnIndex(Vocabulary)),
                                                           cursor.getString(cursor.getColumnIndex(EnglishDef)),
                                                           cursor.getString(cursor.getColumnIndex(PersianDef)),
                                                           cursor.getInt(cursor.getColumnIndex(Visited)) == 1,
                                                           cursor.getInt(cursor.getColumnIndex(Learned)) == 1);

                    examples.add(vocabulary);
                } while (cursor.moveToNext());
            }
        } catch (MyRuntimeException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
            if (db != null && db.isOpen())
                db.close();
        }
        return examples;
    }

    public static ArrayList<Vocabulary> select() {
        return select("", null);
    }

    public static Vocabulary select(long vocabularyId) {
        ArrayList<Vocabulary> vocabularies = select("Where " + Id + " = ? ", new String[]{String.valueOf(vocabularyId)});
        if (vocabularies.size() == 1) {
            return vocabularies.get(0);
        } else {
            return null;
        }
    }

    public static ArrayList<Vocabulary> selectByTheme(long themeId) {
        String whereClause = " WHERE " + themeId + " = " + themeId;
        return select(whereClause, null);
    }

    public static long insert(Vocabulary vocabulary) {
        DataAccess da = new DataAccess();
        return da.insert(TableName, getContentValues(vocabulary));
    }

    public static long update(Vocabulary vocabulary) {
        DataAccess da = new DataAccess();
        return da.update(TableName, getContentValues(vocabulary), Id + " =? ", new String[]{String.valueOf(vocabulary.getId())});
    }

    public static ContentValues getContentValues(Vocabulary vocabulary) {
        ContentValues values = new ContentValues();
        values.put(Id, vocabulary.getId());
        values.put(ThemeId, vocabulary.getThemeId());
        values.put(Day, vocabulary.getDay());
        values.put(Vocabulary, vocabulary.getVocabulary());
        values.put(EnglishDef, vocabulary.getVocabEnglishDef());
        values.put(PersianDef, vocabulary.getVocabPersianDef());
        values.put(Visited, vocabulary.getVisited());
        values.put(Learned, vocabulary.getLearned());
        return values;
    }
}


