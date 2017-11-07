package yoryky.com.demo.dagger.component;

import dagger.Component;
import yoryky.com.demo.dagger.module.ChildModule;
import yoryky.com.demo.dagger.module.ParentModule;
import yoryky.com.demo.dagger.scope.ParentScope;

@ParentScope
@Component(modules = {ParentModule.class})
public interface ParentComponent {
    ChildComponent newChildComponent(ChildModule childModule);
}
