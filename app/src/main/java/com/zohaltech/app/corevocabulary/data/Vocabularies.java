package com.zohaltech.app.corevocabulary.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zohaltech.app.corevocabulary.classes.MyRuntimeException;
import com.zohaltech.app.corevocabulary.entities.Vocabulary;

import java.util.ArrayList;

public class Vocabularies
{

    static final String TableName = "Vocabularies";
    static final String Id = "Id";
    static final String ThemeId = "ThemeId";
    static final String Vocabulary = "Vocabulary";
    static final String VocabEnglishDef = "VocabEnglishDef";
    static final String VocabPersianDef = "VocabPersianDef";
    static final String Visited = "Visited";
    static final String Learned = "Learned";

    static final String CreateTable = "CREATE TABLE " + TableName + " ( " +
            Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            ThemeId + " INTEGER REFERENCES " + Themes.TableName + " (" + Themes.Id + "), " +
            Vocabulary + " VARCHAR(50), " +
            VocabEnglishDef + " VARCHAR(200)," +
            VocabPersianDef + " VARCHAR(200), " +
            Visited + " Boolean, " +
            Learned + " Boolean );";

    static final String DropTable = "Drop Table If Exists " + TableName;

    private static ArrayList<Vocabulary> select(String whereClause, String[] selectionArgs)
    {
        ArrayList<Vocabulary> examples = new ArrayList<>();
        DataAccess da = new DataAccess();
        SQLiteDatabase db = da.getReadableDB();
        Cursor cursor = null;

        try
        {
            String query = "Select * From " + TableName + " " + whereClause;
            cursor = db.rawQuery(query, selectionArgs);
            if (cursor != null && cursor.moveToFirst())
            {
                do
                {
                    Vocabulary vocabulary = new Vocabulary(cursor.getInt(cursor.getColumnIndex(Id)),
                            cursor.getInt(cursor.getColumnIndex(ThemeId)),
                            cursor.getString(cursor.getColumnIndex(Vocabulary)),
                            cursor.getString(cursor.getColumnIndex(VocabEnglishDef)),
                            cursor.getString(cursor.getColumnIndex(VocabPersianDef)),
                            cursor.getInt(cursor.getColumnIndex(Visited)) == 1,
                            cursor.getInt(cursor.getColumnIndex(Learned)) == 1);

                    examples.add(vocabulary);
                } while (cursor.moveToNext());
            }
        }
        catch (MyRuntimeException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
            if (db != null && db.isOpen())
                db.close();
        }
        return examples;
    }

    public static ArrayList<Vocabulary> select()
    {
        return select("", null);
    }

    public static long insert(Vocabulary vocabulary)
    {
        ContentValues values = new ContentValues();

        values.put(ThemeId, vocabulary.getThemeId());
        values.put(Vocabulary, vocabulary.getVocabulary());
        values.put(VocabEnglishDef, vocabulary.getVocabEnglishDef());
        values.put(VocabPersianDef, vocabulary.getVocabPersianDef());
        values.put(Visited, vocabulary.getVisited());
        values.put(Learned, vocabulary.getLearned());

        DataAccess da = new DataAccess();
        return da.insert(TableName, values);
    }

    public static long update(Vocabulary vocabulary)
    {
        ContentValues values = new ContentValues();

        values.put(ThemeId, vocabulary.getThemeId());
        values.put(Vocabulary, vocabulary.getVocabulary());
        values.put(VocabEnglishDef, vocabulary.getVocabEnglishDef());
        values.put(VocabPersianDef, vocabulary.getVocabPersianDef());
        values.put(Visited, vocabulary.getVisited());
        values.put(Learned, vocabulary.getLearned());

        DataAccess da = new DataAccess();
        return da.update(TableName, values, Id + " =? ", new String[]{String.valueOf(vocabulary.getId())});
    }
}


