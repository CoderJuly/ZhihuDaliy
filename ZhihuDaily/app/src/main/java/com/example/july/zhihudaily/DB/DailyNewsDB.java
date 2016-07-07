package com.example.july.zhihudaily.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.july.zhihudaily.Model.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11050 on 2016/6/13.
 */
public class DailyNewsDB {
    private static DBHelper dbHelper;
    private SQLiteDatabase db;
    private static DailyNewsDB dailyNewsDB;
    private String[] allcolumns = {DBHelper.Column_ID, DBHelper.Column_News_Id, DBHelper.Column_News_Title, DBHelper.Column_News_Image};

    private DailyNewsDB(Context context) {

        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public synchronized static DailyNewsDB getInstance(Context context) {

        if (dailyNewsDB == null) {
            dailyNewsDB = new DailyNewsDB(context);
        }
        return dailyNewsDB;
    }

    public void saveFavorite(News news) {
        if (news != null) {
            ContentValues values = new ContentValues();
            values.put(DBHelper.Column_News_Id, news.getId());
            values.put(DBHelper.Column_News_Title, news.getTitle());
            values.put(DBHelper.Column_News_Image, news.getImage());

            db.insert(DBHelper.Table_Name, null, values);

        }

    }

    public List<News> loadFavorite() {
        List<News> favnewslist = new ArrayList<News>();
        Cursor cursor = db.query(DBHelper.Table_Name, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                {

                    News news = new News();
                    news.setId(cursor.getInt(1));
                    news.setTitle(cursor.getString(2));
                    news.setImage(cursor.getString(3));
                    favnewslist.add(news);

                }
            } while (cursor.moveToNext());


        }
        return favnewslist;


    }

    public boolean isFav(News news) {

        Cursor cursor = db.query(DBHelper.Table_Name, null, DBHelper.Column_News_Id + "=?", new String[]{news.getId() + ""}, null, null, null);
        if (cursor.moveToNext()) {

            cursor.close();
            return true;

        } else {

            return false;
        }


    }

    public void deleteFav(News news) {
        if (news != null) {

            db.delete(DBHelper.Table_Name, DBHelper.Column_News_Id + "=?", new String[]{news.getId() + ""});

        }

    }
    public synchronized void closeDB(){

        if(dailyNewsDB!=null){

            dailyNewsDB.closeDB();
        }

    }
}

