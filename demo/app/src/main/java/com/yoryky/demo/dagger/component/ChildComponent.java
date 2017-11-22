package com.yoryky.demo.dagger.component;

import com.yoryky.demo.activity.DaggerActivity;
import com.yoryky.demo.dagger.module.ChildModule;
import com.yoryky.demo.dagger.scope.ChildScope;

import dagger.Subcomponent;


@ChildScope
@Subcomponent(modules = {ChildModule.class})
public interface ChildComponent {
    //DaggerUtil daggerUtil();
    void inject(DaggerActivity activity);
}
