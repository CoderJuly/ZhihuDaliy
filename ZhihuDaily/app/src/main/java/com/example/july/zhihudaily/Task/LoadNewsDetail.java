package com.example.july.zhihudaily.Task;

import android.os.AsyncTask;
import android.webkit.WebView;

import com.example.july.zhihudaily.Http.HttpDownload;
import com.example.july.zhihudaily.Http.JSONHelper;
import com.example.july.zhihudaily.Model.NewsDetail;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by 11050 on 2016/6/15.
 */
public class LoadNewsDetail extends AsyncTask<Integer,Void,NewsDetail>{

    private WebView mwebview;


public LoadNewsDetail(WebView mwebview){
    this.mwebview=mwebview;

}
    @Override
    protected NewsDetail doInBackground(Integer...params){
        NewsDetail newsDetail=null;
        try{
            newsDetail= JSONHelper.toDetail(HttpDownload.getNewsDetail(params[0]));


        }catch (IOException|JSONException e){



        }finally {
            return newsDetail;
        }

    }
    @Override
    protected void onPostExecute(NewsDetail newsDetail){
        String headerimage;
        if (newsDetail.getImage()==null||newsDetail.getImage()==""){
            headerimage = "file:///android_asset/news_detail_header_image.jpg";

        }else{

            headerimage=newsDetail.getImage();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"img-wrap\">")
                .append("<h1 class=\"headline-title\">")
                .append(newsDetail.getTitle()).append("</h1>")
                .append("<span class=\"img-source\">")
                .append(newsDetail.getImage_source()).append("</span>")
                .append("<img src=\"").append(headerimage)
                .append("\" alt=\"\">")
                .append("<div class=\"img-mask\"></div>");
        String mNewsContent = "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_content_style.css\"/>"
                + "<link rel=\"stylesheet\" type=\"text/css\" href=\"news_header_style.css\"/>"
                + newsDetail.getBody().replace("<div class=\"img-place-holder\">", sb.toString());
        mwebview.loadDataWithBaseURL("file:///android_asset/", mNewsContent, "text/html", "UTF-8", null);

    }


}
