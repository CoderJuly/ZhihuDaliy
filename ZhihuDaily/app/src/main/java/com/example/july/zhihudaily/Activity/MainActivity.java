package com.example.july.zhihudaily.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.july.zhihudaily.Adapter.Adapter;
import com.example.july.zhihudaily.R;
import com.example.july.zhihudaily.Task.LoadNewsList;
import com.example.july.zhihudaily.Utility.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {


    private SwipeRefreshLayout refreshLayout;
    private ListView listView;
    private Adapter adapter;
    private boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isConnected = Utility.checkNetworkConnection(this);
        refreshLayout=(SwipeRefreshLayout)findViewById(R.id.refreshlayout);
        refreshLayout.setOnRefreshListener(this);


        listView=(ListView)findViewById(R.id.ListView);



        adapter=new Adapter(this,R.layout.listview_item);
        listView.setAdapter(adapter);
        setTitle(getTime());
        listView.setOnItemClickListener(this);

        if(isConnected) new LoadNewsList(adapter).execute();

        else Utility.noNetworkAlert(this);






    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {

            return true;
        }
        if (id == R.id.action_favourite) {
            Intent intent = new Intent(this, MyFavoriteActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onRefresh() {
        if (isConnected) {
            new LoadNewsList(adapter, new LoadNewsList.onFinishlistener (){
                @Override
                public void afterTaskFinish() {
                    refreshLayout.setRefreshing(false);
//                Toast.makeText(MainActivity.this, "Refresh success", Toast.LENGTH_SHORT).show();
                }
            }).execute();
        } else {
            Utility.noNetworkAlert(MainActivity.this);
            refreshLayout.setRefreshing(false);
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id ){
                    NewsDetailActivity.startActivity(this,adapter.getItem(position));


    }
    public String getTime(){

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat=new SimpleDateFormat("EEE,MMM,d,yyyy");

        return dateFormat.format(calendar.getTime());

    }
}
