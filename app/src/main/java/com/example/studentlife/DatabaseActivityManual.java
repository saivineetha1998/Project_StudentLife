package com.example.studentlife;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseActivityManual extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "studentLifeTrack.db";
    public static final String TABLE_NAME1 = "ManualActivityTracker";
    public static final String col_curr_time = "Time_curr";
    public static final String col_act_mnl = "Activity";

    public DatabaseActivityManual(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME1 + " (Time_curr TEXT, Activity TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);


    }
    public boolean insertDataManlAct(String time,String activity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_curr_time, time);
        contentValues.put(col_act_mnl, activity);
        Long result =  db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
}
