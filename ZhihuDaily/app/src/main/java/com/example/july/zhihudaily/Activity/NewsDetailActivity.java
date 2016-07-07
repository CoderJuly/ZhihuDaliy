package com.example.july.zhihudaily.Activity;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.example.july.zhihudaily.DB.DailyNewsDB;
import com.example.july.zhihudaily.Model.News;
import com.example.july.zhihudaily.R;
import com.example.july.zhihudaily.Task.LoadNewsDetail;
import com.example.july.zhihudaily.Utility.Utility;


/**
 * Created by mac on 15-2-17.
 */
public class NewsDetailActivity extends Activity {
    private WebView mWebView;
    private boolean isFavourite = false;
    private News news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        mWebView = (WebView) findViewById(R.id.WebView);
        setWebView(mWebView);

        news = (News) getIntent().getSerializableExtra("news");
        new LoadNewsDetail(mWebView).execute(news.getId());
        isFavourite = DailyNewsDB.getInstance(this).isFav(news);
    }

    private void setWebView(WebView mWebView) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
    }

    public static void startActivity(Context context, News news) {
        if (Utility.checkNetworkConnection(context)) {
            Intent i = new Intent(context, NewsDetailActivity.class);
            i.putExtra("news",news);
            context.startActivity(i);
        } else {
            Utility.noNetworkAlert(context);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if (isFavourite) menu.findItem(R.id.action_favourite).setIcon(R.drawable.fav_active);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_favourite) {
            if (!isFavourite) {
                DailyNewsDB.getInstance(this).saveFavorite(news);
                item.setIcon(R.drawable.fav_active);
                isFavourite = true;
            } else {
                DailyNewsDB.getInstance(this).deleteFav(news);
                item.setIcon(R.drawable.fav_normal);
                isFavourite = false;
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
