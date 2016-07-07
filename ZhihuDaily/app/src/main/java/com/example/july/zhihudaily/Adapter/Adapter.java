package com.example.july.zhihudaily.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.july.zhihudaily.Model.News;
import com.example.july.zhihudaily.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by 11050 on 2016/6/16.
 */
public class Adapter extends ArrayAdapter<News>{

    private LayoutInflater layoutInflater;
    private int resource;
    private ImageLoader imageLoader=ImageLoader.getInstance();
    private DisplayImageOptions dispalyImageOptions=new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.no_image)
            .showImageOnFail(R.drawable.no_image)
            .showImageForEmptyUri(R.drawable.no_image)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .build();

    public  Adapter(Context context,int resource){
        super(context,resource);
        this.layoutInflater=LayoutInflater.from(context);
        this.resource=resource;

}
    public Adapter(Context context,int resource,List<News> objects){
        super(context, resource, objects);
        layoutInflater=LayoutInflater.from(context);
        this.resource=resource;


    }
    @Override
    public View getView(int position,View convertview,ViewGroup parent){
    ViewHolder holder;
        if (convertview==null){
            convertview=layoutInflater.inflate(resource,null);
            holder=new ViewHolder();
            holder.newsImage=(ImageView)convertview.findViewById(R.id.news_image);
            holder.newTitle=(TextView)convertview.findViewById(R.id.news_title);
            convertview.setTag(holder);

        }else{
            holder=(ViewHolder)convertview.getTag();

        }
        News news=getItem(position);

        holder.newTitle.setText(news.getTitle());

        imageLoader.displayImage(news.getImage(),holder.newsImage,dispalyImageOptions);

        return convertview;


    }


    class ViewHolder{
        ImageView newsImage;
        TextView newTitle;

    }
    public void refreshView(List<News> news){

        clear();
        addAll(news);
        notifyDataSetChanged();


    }



}



