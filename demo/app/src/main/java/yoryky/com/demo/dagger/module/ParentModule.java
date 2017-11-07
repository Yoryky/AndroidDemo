package yoryky.com.demo.dagger.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import yoryky.com.demo.dagger.scope.ParentScope;
import yoryky.com.demo.entity.Child;
import yoryky.com.demo.entity.User;

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
