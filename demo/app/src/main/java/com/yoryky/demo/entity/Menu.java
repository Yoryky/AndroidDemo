/**
 * 本文件作者为qinlang，provider部分代码都是按照该作者的代码来实现的
 *原文链接为http://blog.csdn.net/u012149399/article/details/49004843
 */
package com.yoryky.demo.entity;

import android.net.Uri;

/**
 * 定义provider需要的相关数据  MIME类型
 * @author qinlang
 */
public class Menu {
    // 以下定义provider的MIME类型常量
    public static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir";// MIME类型 多条
    public static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item";// 单条

    public static final String MIME_ITEM = "vnd.pub.menus";// 自定义MIME类型字符串

    // 将固定前缀+自定义字符串生成两个类型
    public static final String MIME_TYPE_SINGLE = MIME_ITEM_PREFIX + "/" + MIME_ITEM;
    public static final String MIME_TYPE_MULTIPLE = MIME_DIR_PREFIX + "/" + MIME_ITEM;

    // 用来定义uri常量 content://<authority>/<data path>/..../id
    public static final String AUTHORITY = "myfs.pub.menusprovider";// 授权者 表示用哪个provider，要跟清单文件中一致
    public static final String PATH_SINGLE = "menus/#"; // （menus：数据库表名）路径下单条记录 #代表数字id
    public static final String PATH_MULTIPLE = "menus";// 路径下多条记录 数据库将对应表名
    public static final String PATH_MULTIPLE_NAME = "menus/*"; // *代表文本 例如按名称访问等

    // 组合成所需要的uri字符串
    public static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/" + PATH_MULTIPLE;

    // 将uri字符串转换为uri对象
    public static final Uri CONTENT_URI = Uri.parse(CONTENT_URI_STRING);

    // 涉及sqlite底层实现的内容
    public static final String CREAT_TABLE = "create table menus(id integer primary key autoincrement ,name varchar(50),price integer)";
    public static final String DBNAME = "pub.db";//数据库名
    public static final String TABLENAME = "menus";//表名
    public static final int DB_VER = 1;//数据库版本，用于升级用

    //常量字符串，对应数据库中的字段
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PRICE = "price";

    //定义字段名的数组，ContentResolver在执行查询类型操作时会用到
    public static final String[] COLUNMS = { KEY_ID, KEY_NAME, KEY_PRICE };

    private int id;
    private String name;
    private int price;

    public Menu() {
    }

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "id:" + id + " | name:" + name + " | price:" + price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
