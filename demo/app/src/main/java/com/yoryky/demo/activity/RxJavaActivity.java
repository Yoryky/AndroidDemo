package com.yoryky.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.yoryky.demo.R;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class RxJavaActivity extends BaseActivity implements View.OnClickListener{
    private Button btnRxJava;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        this.initViews();
    }

    private void initViews(){
        btnRxJava = (Button)findViewById(R.id.btnRxjava);
        btnRxJava.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRxjava:
                this.subscribe();
                break;
        }
    }

    private void subscribe(){
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello world");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        myObservable.map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "-yoryky";
            }
        }).subscribe(mySubscriber);


    }
}
