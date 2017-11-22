package com.yoryky.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.yoryky.demo.R;
import com.yoryky.demo.entity.ZhuanLanAuthor;
import com.yoryky.demo.retrofit.ZhuanLanApi;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;


public class Retrofit2Activity extends BaseActivity implements View.OnClickListener {
    private static final String API_URL = "https://zhuanlan.zhihu.com";
    private Retrofit retrofit = null;
    private Button btnRetrofit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);
        this.initViews();
        this.initRetrofit();
    }

    private void initViews() {
        this.btnRetrofit = (Button) findViewById(R.id.btn_retrofit);
        this.btnRetrofit.setOnClickListener(this);
    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_retrofit:
                this.getDataWithRetrofit();
                break;
        }
    }

    private void getDataWithRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ZhuanLanApi api = retrofit.create(ZhuanLanApi.class);
        Call<ZhuanLanAuthor> call = api.getAuthor("qinchao");
        call.enqueue(new Callback<ZhuanLanAuthor>() {
            @Override
            public void onResponse(Call<ZhuanLanAuthor> call, Response<ZhuanLanAuthor> response) {
                System.out.println(response.body().getName());
            }

            @Override
            public void onFailure(Call<ZhuanLanAuthor> call, Throwable t) {

            }
        });
    }
}
