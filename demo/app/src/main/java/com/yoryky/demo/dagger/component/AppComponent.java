package com.yoryky.demo.dagger.component;

import android.content.Context;

import com.yoryky.demo.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context context();
}
