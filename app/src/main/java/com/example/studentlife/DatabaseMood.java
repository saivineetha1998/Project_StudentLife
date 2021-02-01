package com.example.studentlife;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseMood extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "studentLifeTrack.db";
    public static final String TABLE_NAME = "Moods";
    public static final String col_time = "Time";
    public static final String col_mood = "Mood";

    public static final String TABLE_NAME1 = "ManualActivityTracker";
    public static final String col_curr_time = "Time_curr";
    public static final String col_act_mnl = "Activity";

    public static final String TABLE_NAME2 = "SensorActivityTracker";
    public static final String col_time_curr = "Time_current";
    public static final String col_vehicle = "In_Vehicle";
    public static final String col_bicycle = "On_Bicycle";
    public static final String col_foot = "On_Foot";
    public static final String col_running = "Running";
    public static final String col_still = "Still";
    public static final String col_tilt = "Tilting";
    public static final String col_walk = "Walking";
    public static final String col_unknown = "Unknown";



    public DatabaseMood(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + TABLE_NAME + " (Time TEXT, Mood TEXT)");
    db.execSQL("create table " + TABLE_NAME1 + " (Time_curr TEXT, Activity TEXT)");
    db.execSQL("create table " + TABLE_NAME2 + " (Time_current TEXT, In_Vehicle TEXT, On_Bicycle TEXT, On_Foot TEXT, Running TEXT, Still TEXT, Tilting TEXT, Walking TEXT, Unknown TEXT)");

    //db.execSQL(Moods.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);

    }
    private String getDateTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(

                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        Date date = new Date();

        return dateFormat.format(date);

    }
    public boolean insertDataMood(String time,String mood) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_time, time);
        contentValues.put(col_mood, mood);
       Long result =  db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

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
    public boolean insertDataSensAct(String time,String vehicle, String bicycle, String foot, String running, String still, String tilt, String walk, String unknown) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col_time_curr, time);
        contentValues.put(col_vehicle, vehicle);
        contentValues.put(col_bicycle, bicycle);
        contentValues.put(col_foot, foot);
        contentValues.put(col_running, running);
        contentValues.put(col_still, still);
        contentValues.put(col_tilt, tilt);
        contentValues.put(col_walk, walk);
        contentValues.put(col_unknown, unknown);
        Long result =  db.insert(TABLE_NAME2, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String mood) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(col_time,);
        contentValues.put(col_mood,mood);
        //contentValues.put(COL_3,surname);
        //contentValues.put(COL_4,marks);
       // db.update(TABLE_NAME, contentValues);

        return true;
    }

    public void deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}
