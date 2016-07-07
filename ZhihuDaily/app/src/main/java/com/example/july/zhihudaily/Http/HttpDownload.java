package com.example.july.zhihudaily.Http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 11050 on 2016/6/11.
 */
public class HttpDownload {
    public static String LastestNews = "http://news-at.zhihu.com/api/4/news/latest";
    public static String NewsDetail= " http://news-at.zhihu.com/api/4/news/";

    public static String get(String addurl) throws IOException {
        HttpURLConnection con = null;
        try {
            URL url = new URL(addurl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String inpuline;

                StringBuilder response = new StringBuilder();

                            while((inpuline=in.readLine())!=null){

                                response.append(inpuline);
                            }
                in.close();
               return  response.toString();
            }else{
                throw new IOException("Network Error - response code: " + con.getResponseCode());
            }


        }finally {
            if (con!=null){
                con.disconnect();

            }
        }


    }
    public static String getLatestNews() throws IOException{

        return get(LastestNews);
    }
    public static String getNewsDetail(int id) throws IOException{
        return get(NewsDetail+id);


    }
}
