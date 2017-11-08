package yoryky.com.demo.dagger.component;

import dagger.Subcomponent;
import yoryky.com.demo.activity.DaggerActivity;
import yoryky.com.demo.dagger.module.ChildModule;
import yoryky.com.demo.dagger.scope.ChildScope;
import yoryky.com.demo.entity.User;
import yoryky.com.demo.util.DaggerUtil;

@ChildScope
@Subcomponent(modules = {ChildModule.class})
public interface ChildComponent {
    //DaggerUtil daggerUtil();
    void inject(DaggerActivity activity);
}
