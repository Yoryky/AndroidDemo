package yoryky.com.demo.dagger.module;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import yoryky.com.demo.dagger.scope.ChildScope;
import yoryky.com.demo.util.DaggerUtil;

@Module
public class ChildModule {
    public ChildModule(){

    }

    @ChildScope
    @Provides
    public DaggerUtil providesDaggerUtil(Context context){
        return new DaggerUtil(context);
    }
}
