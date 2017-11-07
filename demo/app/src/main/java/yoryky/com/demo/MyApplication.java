package yoryky.com.demo;

import android.app.Application;

import yoryky.com.demo.dagger.component.AppComponent;
import yoryky.com.demo.dagger.component.DaggerAppComponent;
import yoryky.com.demo.dagger.module.AppModule;

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
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
