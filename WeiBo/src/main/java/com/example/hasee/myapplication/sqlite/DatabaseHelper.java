package com.example.hasee.myapplication.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


  public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_YONGHU="create table Yonghu("
            +"id integer primary key autoincrement,"
            +"phoneNum text,"
            +"name text,"
            +"password text,"
            +"sex text,"
            +"touxiang image)";
    public static final String CREATE_INFORMATION="create table Information("
            +"id integer primary key autoincrement,"
            +"phoneNum text,"
            +"name text,"
            +"information text,"
            +"inTime text)";
    public static final String CREATE_GUANZHU="create table Guanzhu("
            +"id integer primary key autoincrement,"
            +"phoneNum text,"
            +"bPhoneNum text)";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_YONGHU);
            sqLiteDatabase.execSQL(CREATE_INFORMATION);
            sqLiteDatabase.execSQL(CREATE_GUANZHU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
