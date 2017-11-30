/**
 * <p>文件名称: AppVersion.java</p>
 * <p>文件描述: 存储App版本相关的数据</p>
 * <p>版权所有: 版权所有(C)2017-2020</p>
 * <p>公    司: 深圳市金证科技股份有限公司</p>
 * <p>内容摘要: // </p>
 * <p>其他说明: // </p>
 * <p>完成日期：2017/5/25/p>
 *
 * @author chenpp
 * @version 1.0
 */
package com.yoryky.demo.testjros;

/**
 * App更新辅助类
 * //
 * @author  yjing
 * @version 1.0.0
 */
public class AppVersion {
    private String latest_version;
    private String latest_version_time;
    private String update_type;
    private String update_url;
    private String update_need;
    private int update_size;
    private String update_md5;
    private String update_desc;//更新描述

    public String getUpdate_desc() {
        return update_desc;
    }

    public void setUpdate_desc(String update_desc) {
        this.update_desc = update_desc;
    }

    public String getLatest_version() {
        return latest_version;
    }

    public void setLatest_version(String latest_version) {
        this.latest_version = latest_version;
    }

    public String getLatest_version_time() {
        return latest_version_time;
    }

    public void setLatest_version_time(String latest_version_time) {
        this.latest_version_time = latest_version_time;
    }

    public String getUpdate_type() {
        return update_type;
    }

    public void setUpdate_type(String update_type) {
        this.update_type = update_type;
    }

    public String getUpdate_url() {
        return update_url;
    }

    public void setUpdate_url(String update_url) {
        this.update_url = update_url;
    }

    public String getUpdate_need() {
        return update_need;
    }

    public void setUpdate_need(String update_need) {
        this.update_need = update_need;
    }

    public int getUpdate_size() {
        return update_size;
    }

    public void setUpdate_size(int update_size) {
        this.update_size = update_size;
    }

    public String getUpdate_md5() {
        return update_md5;
    }

    public void setUpdate_md5(String update_md5) {
        this.update_md5 = update_md5;
    }
}
