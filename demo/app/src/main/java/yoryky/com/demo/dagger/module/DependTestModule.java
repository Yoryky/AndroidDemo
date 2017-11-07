package yoryky.com.demo.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yoryky.com.demo.dagger.scope.DependScope;
import yoryky.com.demo.util.DaggerUtil;

@Module
public class DependTestModule {
    public DependTestModule(){

    }

    @DependScope
    @Provides
    public DaggerUtil providesDaggerUtil(Context context){
        return new DaggerUtil(context);
    }
}
