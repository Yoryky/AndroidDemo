package yoryky.com.demo.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import yoryky.com.demo.MyApplication;

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
@Module
public class MultiTestModule {
    public MultiTestModule(){

    }

    @Singleton
    @Provides
    public String providesStr(Context context){
        return  "string from myapplication" + context.getCacheDir().getPath();
    }
}
