package yoryky.com.demo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import yoryky.com.demo.R;

/**
 * Created by caicai on 2017/9/28.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout llHttp3;
    private LinearLayout llCookie;
    private LinearLayout llDagger;

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
        llHttp3.setOnClickListener(this);
        llCookie.setOnClickListener(this);
        llDagger.setOnClickListener(this);
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
        }
    }
}
