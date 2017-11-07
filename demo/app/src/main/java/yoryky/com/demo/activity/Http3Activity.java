package yoryky.com.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import yoryky.com.demo.R;

/**
 * Created by caicai on 2017/9/28.
 */

public class Http3Activity extends BaseActivity implements View.OnClickListener {
    private Button btnGet;
    private Button btnPost;
    private Button btnPostJson;
    private Button btnPostFile;
    private Button btnPostImg;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http3);
        this.initViews();
    }

    private void initViews() {
        mContext = this;
        btnGet = (Button) findViewById(R.id.btn_get);
        btnPost = (Button) findViewById(R.id.btn_post);
        btnPostJson = (Button) findViewById(R.id.btn_post_json);
        btnPostFile = (Button) findViewById(R.id.btn_post_file);
        btnPostImg = (Button)findViewById(R.id.btn_post_img);
        btnGet.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        btnPostJson.setOnClickListener(this);
        btnPostFile.setOnClickListener(this);
        btnPostImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                getFormData();
                break;
            case R.id.btn_post:
                postFormData();
                break;
            case R.id.btn_post_json:
                postJsonData();
                break;
            case R.id.btn_post_file:
                postFileData();
                break;
            case R.id.btn_post_img:
                postImgData();
                break;
        }
    }

    private void getFormData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.8.130:8081/test/get?name=yjing&age=12";
                OkHttpClient httpClient = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                Call call = httpClient.newCall(request);
                try {
                    Response response = call.execute();
                    System.out.println("status_code:" + response.code() + response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void postFormData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.8.130:8081/test/post";
                OkHttpClient httpClient = new OkHttpClient();
                RequestBody body = new FormBody.Builder().add("name", "yjing").add("age", "12").build();
                Request request = new Request.Builder().url(url).post(body).header("Cookie","{'name':;'yjing','age':'12'").build();
                Call call = httpClient.newCall(request);
                try {
                    Response response = call.execute();
                    System.out.println("status_code:" + response.code() + response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void postJsonData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.8.130:8081/test/postJson";
                OkHttpClient httpClient = new OkHttpClient();
                MediaType JSON = MediaType.parse("application/json;charset=utf-8");
                RequestBody body = RequestBody.create(JSON, "{'name':'yjing','age':'12'}");
                Request request = new Request.Builder().url(url).post(body).build();
                Call call = httpClient.newCall(request);
                try {
                    Response response = call.execute();
                    System.out.println("status_code:" + response.code() + response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void postFileData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is = getClass().getResourceAsStream("/assets/student.txt");
                    File file = new File(mContext.getExternalCacheDir(),"student.txt");
                    OutputStream os = new FileOutputStream(file);
                    int bytesRead = 0;
                    byte[]  buffer = new byte[2048];
                    while((bytesRead = is.read(buffer,0,2048))!= -1){
                        os.write(buffer,0,bytesRead);
                    }
                    os.close();
                    is.close();
                    String url = "http://192.168.8.130:8081/test/postText";
                    OkHttpClient httpClient = new OkHttpClient();
                    MediaType Text_Plain = MediaType.parse("text/plain;charset=utf-8");
                    RequestBody body = RequestBody.create(Text_Plain, file);
                    Request request = new Request.Builder().url(url).post(body).build();
                    Call call = httpClient.newCall(request);
                    Response response = call.execute();
                    System.out.println("status_code:" + response.code() + response.body().string());
                    file.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void postImgData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is = getClass().getResourceAsStream("/assets/default.png");
                    File file = new File(mContext.getExternalCacheDir(),"default.png");
                    OutputStream os = new FileOutputStream(file);
                    int bytesRead = 0;
                    byte[]  buffer = new byte[2048];
                    while((bytesRead = is.read(buffer,0,2048))!= -1){
                        os.write(buffer,0,bytesRead);
                    }
                    os.close();
                    is.close();
                    String url = "http://192.168.8.130:8081/test/postImg";
                    OkHttpClient httpClient = new OkHttpClient();
                    MediaType Text_Plain = MediaType.parse("image/jpeg;charset=utf-8");
                    RequestBody body = RequestBody.create(Text_Plain, file);
                    Request request = new Request.Builder().url(url).post(body).build();
                    Call call = httpClient.newCall(request);
                    Response response = call.execute();
                    System.out.println("status_code:" + response.code() + response.body().string());
                    file.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
