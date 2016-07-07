package com.example.july.zhihudaily.Task;

import android.os.AsyncTask;

import com.example.july.zhihudaily.Adapter.Adapter;
import com.example.july.zhihudaily.Http.HttpDownload;
import com.example.july.zhihudaily.Http.JSONHelper;
import com.example.july.zhihudaily.Model.News;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by 11050 on 2016/6/15.
 */
public class LoadNewsList extends AsyncTask<Void, Void, List<News>> {
    private Adapter adapter;
    private onFinishlistener onFinishlistener;

    public LoadNewsList(Adapter adapter) {

        super();
        this.adapter = adapter;

    }

    public LoadNewsList(Adapter adapter, onFinishlistener finishlistener) {

        super();
        this.adapter = adapter;
        this.onFinishlistener = finishlistener;
    }

    @Override
    protected List<News> doInBackground(Void... params) {
        List<News> newsLists = null;
        try {

            newsLists = JSONHelper.toList(HttpDownload.getLatestNews());
        } catch (IOException | JSONException e) {


        } finally {
            return newsLists;
        }


    }

    @Override
    public void onPostExecute(List<News> newsLists) {
        adapter.refreshView(newsLists);
        if (onFinishlistener != null) {
            onFinishlistener.afterTaskFinish();

        }

    }


    public interface onFinishlistener {

        public  void afterTaskFinish();

    }
}
