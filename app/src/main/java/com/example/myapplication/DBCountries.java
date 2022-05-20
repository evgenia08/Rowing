package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBCountries
{
    private static final String DATABASE_NAME="countries.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="Countries";

    private static final String COLUMN_ID="id";
    private static final String COLUMN_CLASS="class";
    private static final String COLUMN_DISTANCE="distance";
    private static final String COLUMN_TIME="time";
    private static final String COLUMN_DATE="date";
    private static final String COLUMN_AVE500M="ave500m";
    private static final String COLUMN_TEMP="myTemp";

    private static final int NUM_COLUMN_ID=0;
    private static final int NUM_COLUMN_CLASS=1;
    private static final int NUM_COLUMN_DISTANCE=2;
    private static final int NUM_COLUMN_TIME=3;
    private static final int NUM_COLUMN_DATE=4;
    private static final int NUM_COLUMN_AVE500M=5;
    private static final int NUM_COLUMN_TEMP=6;

    private SQLiteDatabase db;

    public DBCountries(Context context) {
        OpenHelper openHelper=new OpenHelper(context);
        db=openHelper.getWritableDatabase();
    }

    public long Insert(String classN, String distance,String time,String date,String ave500m, String temp)
    {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_CLASS,classN);
        cv.put(COLUMN_DISTANCE,distance);
        cv.put(COLUMN_TIME,time);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_AVE500M,ave500m);
        cv.put(COLUMN_TEMP,temp);
        long res=db.insert(TABLE_NAME,null,cv);
        return res;
    }

    public long Update(Country country)
    {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_CLASS,country.getClas());
        cv.put(COLUMN_DISTANCE,country.getDistance());
        cv.put(COLUMN_TIME,country.getTime());
        cv.put(COLUMN_DATE,country.getDate());
        cv.put(COLUMN_AVE500M,country.getAve500m());
        cv.put(COLUMN_TEMP,country.getTemp());
        return db.update(TABLE_NAME,cv,COLUMN_ID+"=?",
                new String[]{String.valueOf(country.getID())});
    }
    public void deleteAll()
    {
        db.delete(TABLE_NAME,null,null);
    }
    public void Delete(long id)
    {
        db.delete(TABLE_NAME,COLUMN_ID+"=?",
                new String[]{String.valueOf(id)});
    }
    public ArrayList<Country> selectAll()
    {
        Cursor cursor=db.query(TABLE_NAME,null,null,
                null,null,null,null);
        ArrayList<Country> list=new ArrayList<>();
        cursor.moveToFirst();
        if(!cursor.isAfterLast()) {
            do {
                long id = cursor.getLong(NUM_COLUMN_ID);
                String classN= cursor.getString(NUM_COLUMN_CLASS);
                String distance = cursor.getString(NUM_COLUMN_DISTANCE);
                String time = cursor.getString(NUM_COLUMN_TIME);
                String date = cursor.getString(NUM_COLUMN_DATE);
                String ave500m = cursor.getString(NUM_COLUMN_AVE500M);
                String temp = cursor.getString(NUM_COLUMN_TEMP);
                list.add(new Country(id,classN, distance, time,date,ave500m,temp));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public Country select(long id)
    {
        Cursor cursor=db.query(TABLE_NAME,null,COLUMN_ID+"=?",
                new String[]{String.valueOf(id)},null,null,null);
        cursor.moveToFirst();
        String distance = cursor.getString(NUM_COLUMN_DISTANCE);
        String time = cursor.getString(NUM_COLUMN_TIME);
        String date = cursor.getString(NUM_COLUMN_DATE);
        String ave500m = cursor.getString(NUM_COLUMN_AVE500M);
        String temp = cursor.getString(NUM_COLUMN_TEMP);
        String classA=cursor.getString(NUM_COLUMN_CLASS);
        return new Country(id,classA, distance, time,date,ave500m,temp);
    }


    private class OpenHelper extends SQLiteOpenHelper
    {
        public OpenHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String query="CREATE TABLE "+TABLE_NAME+ "(" +
                    COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_DISTANCE+" TEXT NOT NULL," +
                    COLUMN_TIME+" TEXT NOT NULL,"+
                    COLUMN_DATE+" TEXT NOT NULL," +
                    COLUMN_AVE500M+" TEXT NOT NULL,"+
                    COLUMN_CLASS+"TEXT NOT NULL,"+
                    COLUMN_TEMP+" TEXT NOT NULL);";
            sqLiteDatabase.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
}
