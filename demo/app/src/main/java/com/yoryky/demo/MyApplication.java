package com.yoryky.demo;

import android.app.Application;
;import com.yoryky.demo.dagger.component.AppComponent;
import com.yoryky.demo.dagger.component.DaggerAppComponent;
import com.yoryky.demo.dagger.module.AppModule;

import org.xutils.x;

public class MyApplication extends Application {
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        this.initAppComponent();
    }

    private void initAppComponent(){
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        x.Ext.init(this);
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
