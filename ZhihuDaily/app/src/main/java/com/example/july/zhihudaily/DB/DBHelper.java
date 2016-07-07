package com.example.july.zhihudaily.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 11050 on 2016/6/12.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_Name = "daily_news.db";
    public static final String Table_Name = "daily_news_fav";
    public static final int DB_Version = 1;
    public static final String Column_ID = "ID";
    public static final String Column_News_Id = "news_id";
    public static final String Column_News_Title = "news_title";
    public static final String Column_News_Image = "news_image";
    public static final String Create_DB
            = "CREATE TABLE " + Table_Name
            + "(" + Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Column_News_Id + " INTEGER UNIQUE, "
            + Column_News_Title + " TEXT, "
            + Column_News_Image + " TEXT);";
    public static final String DB
            = "CREATE TABLE" + DB_Name
            + "(" + Column_ID + "INTEGER PRIMARY KEY AUTOINCREMENT"
            + Column_News_Id + "INTEGER UNIQUE"
            + Column_News_Image + "TEXT"
            + Column_News_Title + "TEXT);";

    public DBHelper(Context context) {
        super(context, DB_Name, null, DB_Version);


    }

   @Override
    public void onCreate(SQLiteDatabase db){
       db.execSQL(
               Create_DB
       );

   }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldversion,int newversion){
        db.execSQL("DROP TABLE IF EXISTS"+Table_Name);
        onCreate(db);


    }
}
