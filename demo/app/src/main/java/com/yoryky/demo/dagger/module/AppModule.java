package com.yoryky.demo.dagger.module;

import android.content.Context;

import com.yoryky.demo.MyApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private MyApplication myApplication;
    public AppModule(MyApplication myApplication){
        this.myApplication = myApplication;
    }

    @Singleton
    @Provides
    public Context providesContext(){
        return myApplication;
    }
}
