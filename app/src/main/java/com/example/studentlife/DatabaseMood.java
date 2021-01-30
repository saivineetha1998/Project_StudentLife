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


    public DatabaseMood(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + TABLE_NAME + " (Time TEXT, Mood TEXT)");
    db.execSQL("create table " + TABLE_NAME1 + " (Time_curr TEXT, Activity TEXT)");
    //db.execSQL(Moods.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
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
