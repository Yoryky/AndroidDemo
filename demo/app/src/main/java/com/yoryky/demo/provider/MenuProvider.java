package com.yoryky.demo.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.yoryky.demo.entity.Menu;

public class MenuProvider extends ContentProvider {

    // 构造一个matcher对象
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // 定义code用来找对对应各种MIME类型的编码

    private static final int MULTIPLE_MENUS = 1;
    private static final int SINGLE_MENUS = 2;

    // 静态块,在类加载时执行一次,将uri和code绑定起来,以便解析
    static {
        sURIMatcher.addURI(Menu.AUTHORITY, Menu.PATH_MULTIPLE, MULTIPLE_MENUS);
        sURIMatcher.addURI(Menu.AUTHORITY, Menu.PATH_SINGLE, SINGLE_MENUS);
    }
    //数据库帮助类，用于创建表
    static class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        // 用db对象来创建表
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Menu.CREAT_TABLE);
        }

        @Override
        //表的更新
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + Menu.TABLENAME);
            db.execSQL(Menu.CREAT_TABLE);
        }
    }

    private SQLiteDatabase sqdb;
    private DBOpenHelper helper;

    @Override
    public boolean onCreate() {
        Context context = getContext();//得到context对象
        helper = new DBOpenHelper(context, Menu.DBNAME, null, Menu.DB_VER);//实例化数据库帮助类对象
        sqdb = helper.getWritableDatabase();//得到数据库
        if (sqdb == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 得到MIME类型
     * @param uri
     * @return
     */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        // 返回uri对应的MIME类型
        int match = sURIMatcher.match(uri);
        switch (match) {
            case MULTIPLE_MENUS:
                return Menu.MIME_TYPE_MULTIPLE;
            case SINGLE_MENUS:
                return Menu.MIME_TYPE_SINGLE;
            default:
                throw new IllegalArgumentException("Unknown uri:" + uri);
        }
    }

    /**
     * 插入操作
     * @param uri
     * @param values
     * @return
     */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long id = sqdb.insert(Menu.TABLENAME, null, values);
        if (id > 0) {
            Uri newUri = ContentUris.withAppendedId(uri, id);
            getContext().getContentResolver().notifyChange(newUri,null);
            return newUri;
        } else {
            throw new SQLException("fail to insert row into :" + uri);
        }
    }

    /**
     * 删除操作
     * @param uri
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int result = -1;
        switch (sURIMatcher.match(uri)) {
            case SINGLE_MENUS:
                String id = uri.getPathSegments().get(1);
                result = sqdb.delete(Menu.TABLENAME, Menu.KEY_ID + "=" + id, selectionArgs);
                getContext().getContentResolver().notifyChange(uri,null);
                break;
            case MULTIPLE_MENUS:
                result = sqdb.delete(Menu.TABLENAME, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri,null);
            default:
                throw new IllegalArgumentException("Unknown uri:" + uri);
        }
        return result;
    }

    /**
     * 更新操作
     * @param uri
     * @param values
     * @param selection
     * @param selectionArgs
     * @return
     */
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (sURIMatcher.match(uri)) {
            case SINGLE_MENUS:
                String id = uri.getPathSegments().get(1);// 从uri取出id字符串
                count = sqdb.update(Menu.TABLENAME, values, Menu.KEY_ID + "=" + id, selectionArgs);
                getContext().getContentResolver().notifyChange(uri,null);
                break;
            case MULTIPLE_MENUS:
                count = sqdb.update(Menu.TABLENAME, values, selection, selectionArgs);
                getContext().getContentResolver().notifyChange(uri,null);
                break;
            default:
                throw new IllegalArgumentException("Unknown uri:" + uri);
        }
        return count;
    }

    /**
     * 数据查询
     * @param uri
     * @param projection
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(Menu.TABLENAME);
        switch (sURIMatcher.match(uri)) {
            case SINGLE_MENUS:
                qb.appendWhere(Menu.KEY_ID + "=" + uri.getPathSegments().get(1));
                break;
            case MULTIPLE_MENUS:
                break;
            default:
                break;
        }
        Cursor cursor = qb.query(sqdb, projection, selection, selectionArgs,
                null, null, sortOrder);
        return cursor;
    }
}
