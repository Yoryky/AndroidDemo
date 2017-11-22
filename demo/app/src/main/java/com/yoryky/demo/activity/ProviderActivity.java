package com.yoryky.demo.activity;


import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.yoryky.demo.R;
import com.yoryky.demo.entity.Menu;

import java.util.ArrayList;

public class ProviderActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "ql debug";
    private ContentResolver cr;
    private MyContentObserver observer;

    private Button btn_add;
    private Button btn_delAll;
    private Button btn_delById;
    private Button btn_update;
    private Button btn_queryAll;
    private Button btn_queryById;
    private ListView listView;
    private EditText txt_addName;
    private EditText txt_addPrice;
    private EditText txt_delId;
    private EditText txt_queryId;
    private EditText txt_updateId;
    private EditText txt_updataName;
    private EditText txt_updataPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        this.initViews();
    }

    private void initViews(){
        listView = (ListView) findViewById(R.id.listView1);

        txt_addName = (EditText) findViewById(R.id.txt_addName);
        txt_addPrice = (EditText) findViewById(R.id.txt_addPrice);
        txt_delId = (EditText) findViewById(R.id.txt_delId);
        txt_queryId = (EditText) findViewById(R.id.txt_queryId);
        txt_updateId = (EditText) findViewById(R.id.txt_updateId);
        txt_updataName = (EditText) findViewById(R.id.txt_updataName);
        txt_updataPrice = (EditText) findViewById(R.id.txt_updataPrice);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delAll = (Button) findViewById(R.id.btn_delAll);
        btn_delById = (Button) findViewById(R.id.btn_delById);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_queryAll = (Button) findViewById(R.id.btn_queryAll);
        btn_queryById = (Button) findViewById(R.id.btn_queryById);
        btn_add.setOnClickListener(this);
        btn_delAll.setOnClickListener(this);
        btn_delById.setOnClickListener(this);
        btn_queryAll.setOnClickListener(this);
        btn_queryById.setOnClickListener(this);
        btn_update.setOnClickListener(this);

        observer=new MyContentObserver(new Handler());
        cr = getContentResolver(); //实例化ContentResolver 对象
        cr.registerContentObserver(Menu.CONTENT_URI, true, observer);//注册内容提供器观察者对象
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cr.unregisterContentObserver(observer);//取消监听
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                String name = txt_addName.getText().toString().trim();
                String s_price = txt_addPrice.getText().toString().trim();
                if ("".equals(name) || "".equals(s_price)) {
                    Toast.makeText(this, "输入不可为空！", Toast.LENGTH_SHORT).show();
                }else {
                    int price = Integer.valueOf(s_price);
                    addData(name, price);
                }
                break;
            case R.id.btn_delAll:
                delAllData();
                break;
            case R.id.btn_delById:
                String s_del_id = txt_delId.getText().toString().trim();
                if (!"".equals(s_del_id)) {
                    int del_id = Integer.valueOf(s_del_id);
                    delById(del_id);
                }
                break;
            case R.id.btn_update:
                String s_updata_id = txt_updateId.getText().toString().trim();
                String updata_name = txt_updataName.getText().toString().trim();
                String s_updata_price = txt_updataPrice.getText().toString().trim();
                if (!"".equals(s_updata_id)) {
                    int updata_id = Integer.valueOf(s_updata_id);
                    if ("".equals(updata_name) && "".equals(s_updata_price)) {
                        Toast.makeText(this, "没有项进行修改！", Toast.LENGTH_SHORT).show();
                    }else {
                        updateData(updata_id, updata_name, s_updata_price);
                    }
                }
                break;
            case R.id.btn_queryAll:
                queryAll();
                break;
            case R.id.btn_queryById:
                String s_query_id = txt_queryId.getText().toString().trim();
                if (!"".equals(s_query_id)) {
                    int query_id = Integer.valueOf(s_query_id);
                    queryById(query_id);
                }
                break;

            default:
                break;
        }
    }


    private void updateData(int id, String name, String price) {
        Menu mm = new Menu();
        if (!"".equals(name)) {
            mm.setName(name);
        }
        if (!"".equals(price)) {
            int i_price = Integer.valueOf(price);
            mm.setPrice(i_price);
        }
        Uri uri = Uri.parse(Menu.CONTENT_URI + "/" + id);// 定义要操作的uri
        ContentValues values = new ContentValues();
        values.put(Menu.KEY_NAME, mm.getName());
        values.put(Menu.KEY_PRICE, mm.getPrice());
        int result = cr.update(uri, values, null, null);
    }

    private void queryById(int id) {
        Uri uri = Uri.parse(Menu.CONTENT_URI + "/" + id);
        Cursor cursor = cr.query(uri, Menu.COLUNMS, null, null, null);
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("ID\tNAME\tPRICE");
//      Log.e(TAG, "==单条查询==");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                String s = cursor.getString(0) + "\t\t" + cursor.getString(1)
                        + "\t\t" + cursor.getString(2);
//              Log.e(TAG, s);
                arr.add(s);
            }
        }
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr));
    }

    private void queryAll() {
        Cursor cursor = cr.query(Menu.CONTENT_URI, Menu.COLUNMS, null, null, null);
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("ID\tNAME\tPRICE");
//      Log.e(TAG, "==查询所有==");
        Log.d(TAG, "Is null:" + String.valueOf(cursor == null ? true : false));
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String s = cursor.getString(0) + "\t\t" + cursor.getString(1)
                            + "\t\t" + cursor.getString(2);
                    arr.add(s);
//                  Log.e(TAG, s);
                } while (cursor.moveToNext());
            }
        }
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr));
    }

    private void delById(int id) {
        Uri uri = Uri.parse(Menu.CONTENT_URI + "/" + id);// 定义要操作的uri
        int result = cr.delete(uri, null, null);
        Toast.makeText(this, result + "项删除完成！", Toast.LENGTH_SHORT).show();
    }

    private void delAllData() {
        int result = cr.delete(Menu.CONTENT_URI, null, null);
        Toast.makeText(this, result + "项删除完成！", Toast.LENGTH_SHORT).show();
    }

    private void addData(String name, int price) {
        Menu[] mms = { new Menu(name, price)};
        for (Menu m : mms) {
            ContentValues cv = new ContentValues();
            cv.put(Menu.KEY_NAME, m.getName());
            cv.put(Menu.KEY_PRICE, m.getPrice());
            Uri uri = cr.insert(Menu.CONTENT_URI, cv); // 最终
            // cp.insert(url,values)
            Log.d(TAG, "add data:" + uri.toString());
        }
    }


    class MyContentObserver extends ContentObserver {

        public MyContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
//          Log.e(TAG, "remote observer find content provider data change!!!");
            queryAll();
        }
    }

}
