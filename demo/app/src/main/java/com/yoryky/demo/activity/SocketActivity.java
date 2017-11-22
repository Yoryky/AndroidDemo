package com.yoryky.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.yoryky.demo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketActivity extends BaseActivity implements View.OnClickListener {
    private Button btnSend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        this.initViews();
    }

    private void initViews() {
        btnSend = (Button) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                sendDataToServer();
                break;
        }
    }

    private void sendDataToServer() {

        try {
            // 1、创建客户端Socket,指定服务器地址以及端口
            Socket socket = new Socket("localhost", 7779);

            // 2、获取输出流，向服务器发送数据
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("this is message from client");
            pw.flush();
            socket.shutdownOutput();

            // 3、获取输入流并读取服务器的响应数据
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String info = br.readLine();
            while (info != null) {
                System.out.println("我是客户端，服务器说：" + info);
                info = br.readLine();
            }

            // 4、关闭资源
            br.close();
            is.close();
            pw.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
