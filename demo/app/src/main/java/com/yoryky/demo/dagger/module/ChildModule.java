package com.yoryky.demo.dagger.module;


import android.content.Context;

import com.yoryky.demo.dagger.scope.ChildScope;
import com.yoryky.demo.util.DaggerUtil;

import dagger.Module;
import dagger.Provides;

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
