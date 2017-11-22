package com.yoryky.demo.dagger.module;

import android.content.Context;

import com.yoryky.demo.dagger.scope.ParentScope;
import com.yoryky.demo.entity.Child;
import com.yoryky.demo.entity.User;

import dagger.Module;
import dagger.Provides;

@Module
public class ParentModule {
    private Context context;
    public ParentModule(Context context){
        this.context = context;
    }

    @ParentScope
    @Provides
    public Context providesContext(){
        return this.context;
    }

    @ParentScope
    @Provides
    public User providesUser(){
        Child child = new Child();
        return new User(child);
    }

}
