package com.zohaltech.app.corevocabulary.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zohaltech.app.corevocabulary.classes.MyRuntimeException;
import com.zohaltech.app.corevocabulary.entities.Example;

import java.util.ArrayList;

public class Examples
{
    static final String TableName = "Examples";
    static final String Id = "Id";
    static final String VocabularyId = "VocabularyId";
    static final String English = "English";
    static final String Persian = "Persian";

    static final String CreateTable = "CREATE TABLE " + TableName + " (\n" +
            Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            VocabularyId + " INTEGER , " +
            English + " VARCHAR(1024) , " +
            Persian + " VARCHAR(1024);";

    static final String DropTable = "Drop Table If Exists " + TableName;

    private static ArrayList<Example> select(String whereClause, String[] selectionArgs)
    {
        ArrayList<Example> examples = new ArrayList<>();
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
                    Example example = new Example(cursor.getInt(cursor.getColumnIndex(Id)),
                            cursor.getInt(cursor.getColumnIndex(VocabularyId)),
                            cursor.getString(cursor.getColumnIndex(English)),
                            cursor.getString(cursor.getColumnIndex(Persian)));

                    examples.add(example);
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

    public static ArrayList<Example> select()
    {
        return select("", null);
    }

    public static long insert(Example example)
    {
        ContentValues values = new ContentValues();

        values.put(VocabularyId, example.getVocabularyId());
        values.put(English, example.getEnglish());
        values.put(Persian, example.getPersian());

        DataAccess da = new DataAccess();
        return da.insert(TableName, values);
    }

    public static long update(Example example)
    {
        ContentValues values = new ContentValues();

        values.put(VocabularyId, example.getVocabularyId());
        values.put(English, example.getEnglish());
        values.put(Persian, example.getPersian());

        DataAccess da = new DataAccess();
        return da.update(TableName, values, Id + " =? ", new String[]{String.valueOf(example.getId())});
    }
}
