package com.yoryky.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yoryky.demo.R;
import com.yoryky.demo.util.FileUtil;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;

public class StorageActivity extends BaseActivity {
    @ViewInject(R.id.etContent)
    private EditText etContent;

    @ViewInject(R.id.tvContent)
    private TextView tvContent;

    private final String filename = "yoryky.txt";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        x.view().inject(this);
    }

    @Event(R.id.btnWriteExt)
    private void onWriteExtClick(View view) {
        try{
            String content = etContent.getText().toString();
            FileUtil.writeExternal(this,filename,content);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Event(R.id.btnWriteInt)
    private void onWriteIntClick(View view) {
        try{
            String content = etContent.getText().toString();
            FileUtil.writeInternal(this,filename,content);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Event(R.id.btnReadExt)
    private void onReadExtClick(View view) {
        try{
            String content = FileUtil.readExternal(this,filename);
            tvContent.setText(content);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Event(R.id.btnReadInt)
    private void onReadIntClick(View view) {
        try{
            String content = FileUtil.readInternal(this,filename);
            tvContent.setText(content);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Event(R.id.btnClearExt)
    private void onClearExt(View view) {

    }

    @Event(R.id.btnClearInt)
    private void onClearInt(View view) {

    }
}
