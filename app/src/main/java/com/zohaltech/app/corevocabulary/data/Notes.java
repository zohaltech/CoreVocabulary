package com.zohaltech.app.corevocabulary.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zohaltech.app.corevocabulary.classes.MyRuntimeException;
import com.zohaltech.app.corevocabulary.entities.Note;

import java.util.ArrayList;

public class Notes
{
    static final String TableName = "Notes";
    static final String Id = "Id";
    static final String VocabularyId = "VocabularyId";
    static final String Description = "Description";

    static final String CreateTable = "CREATE TABLE " + TableName + " (\n" +
            Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            VocabularyId + " INTEGER , " +
            Description + " VARCHAR(50);";
    static final String DropTable = "Drop Table If Exists " + TableName;

    private static ArrayList<Note> select(String whereClause, String[] selectionArgs)
    {
        ArrayList<Note> noteList = new ArrayList<>();
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
                    Note note = new Note(cursor.getInt(cursor.getColumnIndex(Id)),
                            cursor.getInt(cursor.getColumnIndex(VocabularyId)),
                            cursor.getString(cursor.getColumnIndex(Description)));

                    noteList.add(note);
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
        return noteList;
    }

    public static ArrayList<Note> select()
    {
        return select("", null);
    }

    public static long insert(Note note)
    {
        ContentValues values = new ContentValues();

        values.put(VocabularyId, note.getVocabularyId());
        values.put(Description, note.getDescription());

        DataAccess da = new DataAccess();
        return da.insert(TableName, values);
    }

    public static long update(Note note)
    {
        ContentValues values = new ContentValues();

        values.put(VocabularyId, note.getVocabularyId());
        values.put(Description, note.getDescription());

        DataAccess da = new DataAccess();
        return da.update(TableName, values, Id + " =? ", new String[]{String.valueOf(note.getId())});
    }
}
