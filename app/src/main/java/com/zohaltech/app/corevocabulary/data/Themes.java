package com.zohaltech.app.corevocabulary.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zohaltech.app.corevocabulary.classes.MyRuntimeException;
import com.zohaltech.app.corevocabulary.entities.Theme;

import java.util.ArrayList;

public class Themes {
    static final String TableName = "Themes";
    static final String Id        = "Id";
    static final String Name      = "Name";
    static final String Level     = "Level";
    static final String IconName  = "IconName";

    static final String CreateTable = "CREATE TABLE " + TableName + " (\n" +
                                      Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                                      Level + " INTEGER , " +
                                      Name + " VARCHAR(50) ," +
                                      IconName + " VARCHAR(50));";
    static final String DropTable   = "Drop Table If Exists " + TableName;

    private static ArrayList<Theme> select(String whereClause, String[] selectionArgs) {
        ArrayList<Theme> themeList = new ArrayList<>();
        DataAccess da = new DataAccess();
        SQLiteDatabase db = da.getReadableDB();
        Cursor cursor = null;

        try {
            String query = "Select * From " + TableName + " " + whereClause;
            cursor = db.rawQuery(query, selectionArgs);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Theme theme = new Theme(cursor.getInt(cursor.getColumnIndex(Id)),
                                            cursor.getInt(cursor.getColumnIndex(Level)),
                                            cursor.getString(cursor.getColumnIndex(Name)),
                                            cursor.getString(cursor.getColumnIndex(IconName)));

                    themeList.add(theme);
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
        return themeList;
    }

    public static ArrayList<Theme> select() {
        return select("", null);
    }

    public static long insert(Theme theme) {
        ContentValues values = new ContentValues();

        values.put(Level, theme.getLevel());
        values.put(Name, theme.getName());
        values.put(IconName, theme.getIconName());

        DataAccess da = new DataAccess();
        return da.insert(TableName, values);
    }

    public static long update(Theme theme) {
        ContentValues values = new ContentValues();

        values.put(Level, theme.getLevel());
        values.put(Name, theme.getName());
        values.put(IconName, theme.getIconName());

        DataAccess da = new DataAccess();
        return da.update(TableName, values, Id + " =? ", new String[]{String.valueOf(theme.getId())});
    }
}
