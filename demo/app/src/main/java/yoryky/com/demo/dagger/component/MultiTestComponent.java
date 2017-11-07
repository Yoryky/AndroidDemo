package yoryky.com.demo.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import yoryky.com.demo.activity.DaggerActivity;
import yoryky.com.demo.dagger.module.AppModule;
import yoryky.com.demo.dagger.module.MultiTestModule;

@Singleton
@Component(modules = {AppModule.class, MultiTestModule.class})
public interface MultiTestComponent {
    //void inject(DaggerActivity activity);//猜测是多Module注入时，取消这里的注释
}
