package com.zohaltech.app.corevocabulary.dal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zohaltech.app.corevocabulary.classes.App;
import com.zohaltech.app.corevocabulary.classes.CsvReader;
import com.zohaltech.app.corevocabulary.classes.MyRuntimeException;

import java.io.InputStreamReader;

public class DataAccess extends SQLiteOpenHelper {

    public static final String DATABASE_NAME    = "CORE_VOCABULARY";
    public static final int    DATABASE_VERSION = 1;

    public DataAccess() {
        super(com.zohaltech.app.corevocabulary.classes.App.context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        try {

        } catch (com.zohaltech.app.corevocabulary.classes.MyRuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        try {

            onCreate(database);
        } catch (MyRuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    public SQLiteDatabase getReadableDB() {
        return this.getReadableDatabase();
    }

    public SQLiteDatabase getWritableDB() {
        return this.getWritableDatabase();
    }

    public long insert(String table, ContentValues values) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDB();
            result = db.insert(table, null, values);
            db.close();
        } catch (MyRuntimeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public long update(String table, ContentValues values, String whereClause, String[] selectionArgs) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDB();
            result = db.update(table, values, whereClause, selectionArgs);
            db.close();
        } catch (MyRuntimeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public long delete(String table, String whereClause, String[] selectionArgs) {
        long result = 0;
        try {
            SQLiteDatabase db = this.getWritableDB();
            result = db.delete(table, whereClause, selectionArgs);
            db.close();
        } catch (MyRuntimeException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void insertDataFromAsset(SQLiteDatabase db, String tableName, String filePathFromAsset, char delimiter) {

        InputStreamReader isr;
        try {
            isr = new InputStreamReader(App.context.getAssets().open(filePathFromAsset), "UTF-8");

            CsvReader csvReader = new CsvReader(isr, delimiter);
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                ContentValues values = new ContentValues();
                for (int i = 0; i < csvReader.getHeaderCount(); i++) {
                    values.put(csvReader.getHeader(i), csvReader.get(csvReader.getHeader(i)));
                }
                db.insert(tableName, null, values);
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}