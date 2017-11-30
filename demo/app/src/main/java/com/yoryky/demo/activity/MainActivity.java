package com.yoryky.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ViewUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.yoryky.demo.R;

import org.xutils.view.annotation.Event;
import org.xutils.x;

/**
 * Created by caicai on 2017/9/28.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
    }

    @Event(value = R.id.ll_http3)
    private void onHttp3Click(View view){
        startActivity("com.yoryky.demo.activity.Http3Activity");
    }

    @Event(value = R.id.ll_dagger)
    private void onDaggerClick(View view){
        startActivity("com.yoryky.demo.activity.DaggerActivity");
    }

    @Event(value = R.id.ll_cookie)
    private  void onCookieClick(View view){
        startActivity("com.yoryky.demo.activity.CookieActivity");
    }

    @Event(value = R.id.ll_retrofit)
    private void onRetrofitClick(View view){
        startActivity("com.yoryky.demo.activity.Retrofit2Activity");
    }

    @Event(value = R.id.ll_rxjava)
    private void onRxJavaClick(View view){
        startActivity("com.yoryky.demo.activity.RxJavaActivity");
    }

    @Event(value = R.id.ll_urlconnection)
    private void onUrlConnectClick(View view){
        startActivity("com.yoryky.demo.activity.UrlConnectionActivity");
    }

    @Event(value = R.id.ll_socket)
    private void onSocketClick(View view){
        startActivity("com.yoryky.demo.activity.SocketActivity");
    }

    @Event(value = R.id.ll_asynctask)
    private void onAsyncTaskClick(View view){
        startActivity("com.yoryky.demo.activity.AsyncTaskActivity");
    }

    @Event(value = R.id.ll_provider)
    private void onProviderClick(View view){
        startActivity("com.yoryky.demo.activity.ProviderActivity");
    }

    @Event(value = R.id.ll_storage)
    private void onStorageClick(View view){
        startActivity("com.yoryky.demo.activity.StorageActivity");
    }

    private void startActivity(String activityName){
        try{
            Intent intent = new Intent(MainActivity.this,Class.forName(activityName));
            startActivity(intent);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
