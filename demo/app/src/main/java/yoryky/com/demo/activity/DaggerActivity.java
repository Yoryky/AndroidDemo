package yoryky.com.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import yoryky.com.demo.dagger.component.DaggerParentComponent;
import yoryky.com.demo.dagger.component.ParentComponent;
import yoryky.com.demo.dagger.module.ChildModule;
import yoryky.com.demo.dagger.module.ParentModule;
import yoryky.com.demo.entity.User;
import yoryky.com.demo.util.DaggerUtil;

public class DaggerActivity extends BaseActivity {
    //@Inject String mutliStr;
    //@Inject DaggerUtil daggerUtil;
    @Inject User user;
    @Inject DaggerUtil daggerUtil2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**  测试多Module情况下的DI
        DaggerMultiTestComponent.builder()
                .appModule(new AppModule((MyApplication)this.getApplication()))
                .multiTestModule(new MultiTestModule())
                .build()
                .inject(this);
        Log.i("ying", "string from multistr: " + mutliStr );
        */
        /**  测试组件依赖的使用
        DaggerDependTestComponent.builder()
                .appComponent(((MyApplication)getApplication()).getAppComponent())
                .dependTestModule(new DependTestModule())
                .build().inject(this);
        daggerUtil.test();
         */
        /** 测试子组件*/
        ParentComponent parentComponent = DaggerParentComponent.builder()
                .parentModule(new ParentModule(this))
                .build();
        parentComponent.newChildComponent(new ChildModule()).inject(this);
        user.test();
        daggerUtil2.test();
    }
}
