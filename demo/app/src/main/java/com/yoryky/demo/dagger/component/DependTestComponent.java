package com.yoryky.demo.dagger.component;

import com.yoryky.demo.dagger.module.DependTestModule;
import com.yoryky.demo.dagger.scope.DependScope;

import dagger.Component;


@DependScope
@Component(dependencies = {AppComponent.class},modules = {DependTestModule.class})
public interface DependTestComponent {
    //void inject(DaggerActivity activity); //测试依赖时打开
}
