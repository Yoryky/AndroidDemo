package yoryky.com.demo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import retrofit2.http.Url;
import yoryky.com.demo.R;

/**
 * Created by caicai on 2017/9/28.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llHttp3;
    private LinearLayout llCookie;
    private LinearLayout llDagger;
    private LinearLayout llRetrofit;
    private LinearLayout llRxJava;
    private LinearLayout llUrlConnection;
    private LinearLayout llSocket;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViews();
    }

    private void initViews() {
        llHttp3 = (LinearLayout) findViewById(R.id.ll_http3);
        llCookie = (LinearLayout) findViewById(R.id.ll_cookie);
        llDagger = (LinearLayout)findViewById(R.id.ll_dagger);
        llRetrofit = (LinearLayout)findViewById(R.id.ll_retrofit);
        llRxJava = (LinearLayout)findViewById(R.id.ll_rxjava);
        llUrlConnection = (LinearLayout)findViewById(R.id.ll_urlconnection);
        llSocket = (LinearLayout)findViewById(R.id.ll_socket);
        llHttp3.setOnClickListener(this);
        llCookie.setOnClickListener(this);
        llDagger.setOnClickListener(this);
        llRetrofit.setOnClickListener(this);
        llRxJava.setOnClickListener(this);
        llUrlConnection.setOnClickListener(this);
        llSocket.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_http3:
                Intent intent = new Intent(this, Http3Activity.class);
                startActivity(intent);
                break;
            case R.id.ll_cookie:
                Intent intent1 = new Intent(this,CookieActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_dagger:
                Intent intent2 = new Intent(this,DaggerActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_retrofit:
                Intent intent3 = new Intent(this,Retrofit2Activity.class);
                startActivity(intent3);
                break;
            case R.id.ll_rxjava:
                Intent intent4 = new Intent(this,RxJavaActivity.class);
                startActivity(intent4);
                break;
            case R.id.ll_urlconnection:
                Intent intent5 = new Intent(this, UrlConnectionActivity.class);
                startActivity(intent5);
                break;
            case R.id.ll_socket:
                Intent intent6 = new Intent(this,SocketActivity.class);
                startActivity(intent6);
                break;
        }
    }
}
