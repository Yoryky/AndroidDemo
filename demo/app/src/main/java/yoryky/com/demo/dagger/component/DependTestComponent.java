package yoryky.com.demo.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import yoryky.com.demo.activity.DaggerActivity;
import yoryky.com.demo.dagger.module.AppModule;
import yoryky.com.demo.dagger.module.DependTestModule;
import yoryky.com.demo.dagger.scope.DependScope;

/**
 * <p>文件名称: 题目名称</p>
 * <p>文件描述: 本类描述</p>
 * <p>版权所有: 版权所有(C)2017-2020</p>
 * <p>公    司: 深圳市金证科技股份有限公司</p>
 * <p>内容摘要: // 简要描述本文件的内容，包括主要模块、函数及能的说明</p>
 * <p>其他说明: // 其它内容的说明</p>
 * <p>完成日期：2017/11/7</p>
 *
 * @author yjing
 * @version 1.0
 */
@DependScope
@Component(dependencies = {AppComponent.class},modules = {DependTestModule.class})
public interface DependTestComponent {
    //void inject(DaggerActivity activity); //测试依赖时打开
}
