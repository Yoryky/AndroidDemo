package com.yoryky.demo.dagger.component;

import com.yoryky.demo.dagger.module.AppModule;
import com.yoryky.demo.dagger.module.MultiTestModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {AppModule.class, MultiTestModule.class})
public interface MultiTestComponent {
    //void inject(DaggerActivity activity);//猜测是多Module注入时，取消这里的注释
}
