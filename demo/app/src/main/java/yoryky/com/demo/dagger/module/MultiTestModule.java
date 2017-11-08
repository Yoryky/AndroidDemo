package yoryky.com.demo.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MultiTestModule {
    public MultiTestModule(){

    }

    @Singleton
    @Provides
    public String providesStr(Context context){
        return  "string from myapplication" + context.getCacheDir().getPath();
    }
}
