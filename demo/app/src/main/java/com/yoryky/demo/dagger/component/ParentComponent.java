package com.yoryky.demo.dagger.component;

import com.yoryky.demo.dagger.module.ChildModule;
import com.yoryky.demo.dagger.module.ParentModule;
import com.yoryky.demo.dagger.scope.ParentScope;

import dagger.Component;


@ParentScope
@Component(modules = {ParentModule.class})
public interface ParentComponent {
    ChildComponent newChildComponent(ChildModule childModule);
}
