package yoryky.com.demo.dagger.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import yoryky.com.demo.activity.DaggerActivity;
import yoryky.com.demo.dagger.module.AppModule;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context context();
}
