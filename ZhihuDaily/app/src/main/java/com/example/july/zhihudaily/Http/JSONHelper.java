package com.example.july.zhihudaily.Http;

import com.example.july.zhihudaily.Model.NewsDetail;
import com.example.july.zhihudaily.Model.News;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11050 on 2016/6/12.
 */
public class JSONHelper {

    public static List<News> toList(String json) throws JSONException {

        JSONObject jsonObject = new JSONObject(json);

        JSONArray newsArray = jsonObject.getJSONArray("stories");
        List<News> newsLists = new ArrayList<News>();
        for (int i = 0; i < newsArray.length(); i++) {

            JSONObject newsinjson = newsArray.getJSONObject(i);

            int id = newsinjson.optInt("id");

            String title = newsinjson.optString("title");

            String image = null;
            if ((newsinjson.has("images"))) {

                image =(String) newsinjson.getJSONArray("images").get(0);


            }
            News news = new News(id,title,image);

            newsLists.add(news);


        }
        return newsLists;

    }

    public static NewsDetail toDetail(String json) throws  JSONException{
        Gson gson=new Gson();

        return gson.fromJson(json,NewsDetail.class);


}}
