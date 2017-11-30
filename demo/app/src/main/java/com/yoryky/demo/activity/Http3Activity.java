package com.yoryky.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.yoryky.demo.R;
import com.yoryky.demo.testjros.AppVersion;
import com.yoryky.demo.testjros.BaseRequestEntity;

import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by caicai on 2017/9/28.
 */

public class Http3Activity extends BaseActivity implements View.OnClickListener {
    private Button btnGet;
    private Button btnPost;
    private Button btnPostJson;
    private Button btnPostFile;
    private Button btnPostImg;
    private Button btnAsyncTest;
    private Button btnAsyncLibrary;
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
        btnPostImg = (Button) findViewById(R.id.btn_post_img);
        btnAsyncTest = (Button) findViewById(R.id.btn_async_test);
        btnAsyncLibrary = (Button)findViewById(R.id.btn_async_library);
        btnGet.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        btnPostJson.setOnClickListener(this);
        btnPostFile.setOnClickListener(this);
        btnPostImg.setOnClickListener(this);
        btnAsyncTest.setOnClickListener(this);
        btnAsyncLibrary.setOnClickListener(this);
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
            case R.id.btn_async_test:
                syncTest();
                break;
            case R.id.btn_async_library:
                syncLibrary();
                break;
        }
    }

    private void getFormData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://192.168.8.136:8081/test/get?name=yjing&age=12";
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
                String url = "http://192.168.8.136:8081/test/post";
                OkHttpClient httpClient = new OkHttpClient();
                RequestBody body = new FormBody.Builder().add("name", "yoryky").add("age", "12").build();
                Request request = new Request.Builder().url(url).post(body).header("Cookie", "{'name':'yoryky','age':'12'").build();
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
                String url = "http://192.168.8.136:8081/test/postJson";
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
                    File file = new File(mContext.getExternalCacheDir(), "student.txt");
                    OutputStream os = new FileOutputStream(file);
                    int bytesRead = 0;
                    byte[] buffer = new byte[2048];
                    while ((bytesRead = is.read(buffer, 0, 2048)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                    os.close();
                    is.close();
                    String url = "http://192.168.8.136:8081/test/postText";
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

    private void postImgData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream is = getClass().getResourceAsStream("/assets/default.png");
                    File file = new File(mContext.getExternalCacheDir(), "default.png");
                    OutputStream os = new FileOutputStream(file);
                    int bytesRead = 0;
                    byte[] buffer = new byte[2048];
                    while ((bytesRead = is.read(buffer, 0, 2048)) != -1) {
                        os.write(buffer, 0, bytesRead);
                    }
                    os.close();
                    is.close();
                    String url = "http://192.168.8.136:8081/test/postImg";
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

    private void syncTest() {
        String url = "http://192.168.8.136:8081/test/postJson";
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        RequestBody body = RequestBody.create(JSON, "{'name':'yjing','age':'12'}");
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                System.out.println("我是异步线程,线程Id为:" + responseStr);
            }
        });
    }


    private void syncLibrary() {
        try {
            String url = "http://192.168.8.187:8001/agate/jros/qry_latestversion.do";
            OkHttpClient okHttpClient = new OkHttpClient();
            MediaType JSONTYPE = MediaType.parse("application/json;charset=utf-8");
            List<BaseRequestEntity> requestEntities = new BaseRequestEntity.Builder("qry_latestversion").addParams("native_version", "1.0.0").addParams("web_version", "1.0.0").addParams("device_platform", "android").build();
            /*RequestBody body = RequestBody.create(null, "{'name':'yjing','age':'12'}");*/
            String strBody = new Gson().toJson(requestEntities);
            RequestBody body = RequestBody.create(JSONTYPE, strBody);
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Gson gson = new Gson();
                    String responseStr = response.body().string();
                    JsonArray jsonArray = new JsonParser().parse(responseStr).getAsJsonArray();
                    JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();
                    jsonObject = jsonObject.get("data").getAsJsonObject();
                    jsonArray = jsonObject.get("record1").getAsJsonArray();
                    List<AppVersion> appVersions = gson.fromJson(jsonArray, new TypeToken<List<AppVersion>>() { }.getType());
                    System.out.println("我是异步线程,线程Id为:" + appVersions.get(0).getUpdate_type());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
