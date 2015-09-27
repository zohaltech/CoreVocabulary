package com.zohaltech.app.corevocabulary.data;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zohaltech.app.corevocabulary.classes.Helper;
import com.zohaltech.app.corevocabulary.classes.MyRuntimeException;
import com.zohaltech.app.corevocabulary.entities.SystemSetting;

import java.util.ArrayList;

public class SystemSettings {
    static final String TableName      = "SystemSettings";
    static final String Id             = "Id";
    static final String Installed      = "Installed";
    static final String PremiumVersion = "PremiumVersion";


    static final String CreateTable = "CREATE TABLE " + TableName + " (" +
                                      Id + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                                      Installed + " BOOLEAN NOT NULL, " +
                                      PremiumVersion + " VARCHAR(50) ); ";
    static final String DropTable   = "Drop Table If Exists " + TableName;

    private static ArrayList<SystemSetting> select(String whereClause, String[] selectionArgs) {
        ArrayList<SystemSetting> settings = new ArrayList<>();
        DataAccess da = new DataAccess();
        SQLiteDatabase db = da.getReadableDB();
        Cursor cursor = null;

        try {
            String query = "Select * From " + TableName + " " + whereClause;
            cursor = db.rawQuery(query, selectionArgs);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    SystemSetting systemSetting = new SystemSetting(cursor.getInt(cursor.getColumnIndex(Id)),
                                                                    cursor.getInt(cursor.getColumnIndex(Installed)) == 1,
                                                                    cursor.getString(cursor.getColumnIndex(PremiumVersion)));
                    settings.add(systemSetting);
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
        return settings;
    }


    public static long update(SystemSetting setting) {
        ContentValues values = new ContentValues();

        values.put(Installed, setting.getInstalled() ? 1 : 0);
        values.put(PremiumVersion, setting.getPremiumVersion());

        DataAccess da = new DataAccess();
        return da.update(TableName, values, Id + " = ? ", new String[]{String.valueOf(setting.getId())});
    }

    public static void register(SystemSetting setting){
        setting.setPremiumVersion(Helper.hashString(Helper.getDeviceId()));
        update(setting);
    }

    public static long delete(SystemSetting setting) {
        DataAccess db = new DataAccess();
        return db.delete(TableName, Id + " =? ", new String[]{String.valueOf(setting.getId())});
    }

    public static SystemSetting getCurrentSettings() {
        ArrayList<SystemSetting> settings = select("", null);
        int count = settings.size();

        return (count == 0) ? null : settings.get(0);
    }
}
