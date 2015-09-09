package com.zohaltech.app.corevocabulary.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zohaltech.app.corevocabulary.classes.*;
import com.zohaltech.app.corevocabulary.entities.Theme;

import java.io.InputStreamReader;

public class DataAccess extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "CORE_VOCABULARY";
    public static final int DATABASE_VERSION = 2;

    public DataAccess()
    {
        super(App.context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            db.execSQL(Themes.CreateTable);
            db.execSQL(Vocabularies.CreateTable);
            db.execSQL(Examples.CreateTable);
            db.execSQL(Notes.CreateTable);

            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(1, "Education", "ic_education")));
            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(2, "Job and Employment", "ic_job")));
            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(3, "Media", "ic_media")));
            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(4, "Health", "ic_health")));
            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(5, "Environment", "ic_environment")));
            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(6, "Advertising", "ic_advertising")));
            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(7, "Foreign Language", "ic_language")));
            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(8, "Urbanisation", "ic_urbanisation")));
            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(9, "Crimes and Law", "ic_law")));
            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(11, "Sports", "ic_sports")));
            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(12, "Space", "ic_space")));
            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(13, "Science", "ic_science")));
            db.insert(Themes.TableName, null, Themes.getContentValues(new Theme(14, "Causes and Results", "ic_causes")));

            insertDataFromAsset(db, Vocabularies.TableName, "data/vocabs.csv", ';');
            insertDataFromAsset(db, Examples.TableName, "data/examples.csv", ';');
            insertDataFromAsset(db, Notes.TableName, "data/notes.csv", ';');

        }
        catch (MyRuntimeException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        try
        {
            database.execSQL(Themes.DropTable);
            database.execSQL(Vocabularies.DropTable);
            database.execSQL(Examples.DropTable);
            database.execSQL(Notes.DropTable);

            onCreate(database);
        }
        catch (MyRuntimeException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void close()
    {
        super.close();
    }

    public SQLiteDatabase getReadableDB()
    {
        return this.getReadableDatabase();
    }

    public SQLiteDatabase getWritableDB()
    {
        return this.getWritableDatabase();
    }

    public long insert(String table, ContentValues values)
    {
        long result = 0;
        try
        {
            SQLiteDatabase db = this.getWritableDB();
            result = db.insert(table, null, values);
            db.close();
        }
        catch (MyRuntimeException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long update(String table, ContentValues values, String whereClause, String[] selectionArgs)
    {
        long result = 0;
        try
        {
            SQLiteDatabase db = this.getWritableDB();
            result = db.update(table, values, whereClause, selectionArgs);
            db.close();
        }
        catch (MyRuntimeException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public long delete(String table, String whereClause, String[] selectionArgs)
    {
        long result = 0;
        try
        {
            SQLiteDatabase db = this.getWritableDB();
            result = db.delete(table, whereClause, selectionArgs);
            db.close();
        }
        catch (MyRuntimeException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    private void insertDataFromAsset(SQLiteDatabase db, String tableName, String filePathFromAsset, char delimiter)
    {
        InputStreamReader isr;
        try
        {
            isr = new InputStreamReader(App.context.getAssets().open(filePathFromAsset), "UTF-8");

            CsvReader csvReader = new CsvReader(isr, delimiter);
            csvReader.readHeaders();
            while (csvReader.readRecord())
            {
                ContentValues values = new ContentValues();
                for (int i = 0; i < csvReader.getHeaderCount(); i++)
                {
                    values.put(csvReader.getHeader(i), csvReader.get(csvReader.getHeader(i)));
                }
                db.insert(tableName, null, values);
            }
            csvReader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
