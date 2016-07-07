package com.example.july.zhihudaily.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by 11050 on 2016/6/23.
 */
public class Utility {

    public static boolean checkNetworkConnection(Context context){
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getActiveNetworkInfo();
        return info!=null&&info.isConnected();


    }

    public  static void noNetworkAlert(Context context){
        Toast.makeText(context,"no network",Toast.LENGTH_SHORT).show();


    }
}
