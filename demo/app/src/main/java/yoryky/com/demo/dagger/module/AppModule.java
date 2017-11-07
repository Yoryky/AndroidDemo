package yoryky.com.demo.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yoryky.com.demo.MyApplication;

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
