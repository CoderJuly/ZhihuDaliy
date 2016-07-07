package com.example.july.zhihudaily.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.july.zhihudaily.Adapter.Adapter;
import com.example.july.zhihudaily.DB.DailyNewsDB;
import com.example.july.zhihudaily.Model.News;
import com.example.july.zhihudaily.R;

import java.util.List;

/**
 * Created by 11050 on 2016/6/23.
 */
public class MyFavoriteActivity extends Activity implements AdapterView.OnItemClickListener {
    private Adapter adapter;

    private List<News> news;

    private ListView lv;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.favourite);
        lv = (ListView) findViewById(R.id.lv_fav);

        news = DailyNewsDB.getInstance(this).loadFavorite();

        adapter = new Adapter(this, R.layout.listview_item, news);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(this);


    }

    @Override

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsDetailActivity.startActivity(this, adapter.getItem(position));

    }


}
