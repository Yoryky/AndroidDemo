/**
 * <p>文件名称: BaseRequestEntity.java</p>
 * <p>文件描述: //</p>
 * <p>版权所有: 版权所有(C)2017-2020</p>
 * <p>公    司: 深圳市金证科技股份有限公司</p>
 * <p>内容摘要: // </p>
 * <p>其他说明: // </p>
 * <p>完成日期：2016/7/15</p>
 *
 * @author gerry
 * @version 1.0.0
 */
package com.yoryky.demo.testjros;

import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;


/**
 * 网路访问请求参数格式
 * 网路访问请求参数格式
 * @author  yjing
 * @version 1.0.0
 */
public class BaseRequestEntity {

    //请求的接口号
    private String code;

    //请求的参数
    private HashMap<String, String> params;

    //请求的扩展参数，主要用于分页
    private HashMap<String, String> params_ext;


    private BaseRequestEntity() {

    }

    public BaseRequestEntity(String code) {
        this.code = code;
        params = new HashMap<String, String>();
        params_ext = new HashMap<String, String>();
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public HashMap<String, String> getParams_ext() {
        return params_ext;
    }

    public void setParams_ext(HashMap<String, String> params_ext) {
        this.params_ext = params_ext;
    }


    public static class Builder {
        List<BaseRequestEntity> baseRequestEntities;
        BaseRequestEntity baseRequestEntity;


        public Builder(String code) {
            baseRequestEntities = new ArrayList<BaseRequestEntity>();
            baseRequestEntity = new BaseRequestEntity(code);
        }


        public boolean hasParam(String key) {
            return baseRequestEntity.getParams().containsKey(key);
        }

        public Builder addParams(String key, String value) {
            if (key != null) {
                if (value == null) {
                    baseRequestEntity.getParams().put(key, "");
                } else {
                    baseRequestEntity.getParams().put(key, value);
                }
            }
            return this;
        }

        public Builder addParamsExt(String key, String value) {
            if (key != null) {
                baseRequestEntity.getParams_ext().put(key, value);
            }
            return this;
        }


        public List<BaseRequestEntity> build() {
            HashMap<String, String> params = baseRequestEntity.getParams();
            addCommonParams(params);
            return baseRequestEntities;
        }

        private void addCommonParams(HashMap<String, String> params) {
            params.put("source", "");//todo problem 公参
            params.put("deviceinfo", Build.DEVICE);
            params.put("operorg", "0000");//todo problem 公参
            //params.put("devicepversion", BuildConfig.VERSION_NAME);  //传输app的版本号过去，便于定位bug
            params.put("systime", SafeUtil.getSystemTime());
            // baseRequestEntity.getParams().put("dictversion", DictionaryUtils.getDictionaryItem("dictversion", "0"));
            // baseRequestEntity.getParams().put("token", SafeUtil.getToken());
            params.put("deviceplatform", "android");
            params.put("_key", "ouE6yWvy");
            params.put("_msgid", UUID.randomUUID().toString());
            TreeMap<String, String> treeMap = new TreeMap<String, String>(params);
            params.put("_sign", SafeUtil.getSign(treeMap));
            params.remove("_key");
            baseRequestEntities.add(baseRequestEntity);
        }

        public BaseRequestEntity buildAsObject() {
            HashMap<String, String> params = baseRequestEntity.getParams();
            addCommonParams(params);
            baseRequestEntities.add(baseRequestEntity);
            return baseRequestEntity;
        }
    }
}
