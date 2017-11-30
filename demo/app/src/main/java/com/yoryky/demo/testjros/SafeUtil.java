/**
 * <p>文件名称: SafeUtil.java</p>
 * <p>文件描述: 签名等安全相关</p>
 * <p>版权所有: 版权所有(C)2017-2020</p>
 * <p>公    司: 深圳市金证科技股份有限公司</p>
 * <p>内容摘要: // </p>
 * <p>其他说明: // </p>
 * <p>完成日期：2016/9/5</p>
 *
 * @author shuang
 * @version 1.0
 */
package com.yoryky.demo.testjros;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;

/**
 * 签名等安全相关方法实现
 * 签名等安全相关方法实现
 * @author yjing
 * @version 1.0.0
 */
public class SafeUtil {

    public static String getSystemTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+:08:00"));
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 获取签名
     * @param paramsMap 参数键值对
     * @return md5加密串
     */
    public static String getSign(TreeMap<String, String> paramsMap) {
        StringBuilder sb = new StringBuilder();
        Set<String> sortSet = paramsMap.keySet();
        for (String key : sortSet) {
            String value = (String) paramsMap.get(key);
            if (value == null) {
                value = "";
            }
            if (!"_sign".equals(key)) {
                if (sb.length() == 0) {
                    sb.append(key).append("=").append(value);
                } else {
                    sb.append("&").append(key).append("=").append(value);
                }
            }
        }
        return CryptUtil.encodeMD5(sb.toString());
    }

}
