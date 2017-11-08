package yoryky.com.demo.dagger.component;

import dagger.Component;
import yoryky.com.demo.dagger.module.DependTestModule;
import yoryky.com.demo.dagger.scope.DependScope;

@DependScope
@Component(dependencies = {AppComponent.class},modules = {DependTestModule.class})
public interface DependTestComponent {
    //void inject(DaggerActivity activity); //测试依赖时打开
}
