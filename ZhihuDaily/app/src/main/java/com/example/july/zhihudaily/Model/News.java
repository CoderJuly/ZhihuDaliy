package com.example.july.zhihudaily.Model;

import java.io.Serializable;

/**
 * Created by 11050 on 2016/6/11.
 */
public class News implements Serializable {
    private int id;
    private String title;
    private String image;
    public News(){}
    public News(int id, String title, String image){
        this.id=id;
        this.title=title;
        this.image=image;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
