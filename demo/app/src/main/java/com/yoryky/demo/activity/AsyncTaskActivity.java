package com.yoryky.demo.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yoryky.demo.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AsyncTaskActivity extends BaseActivity {
    private Button btnTest;
    private TextView tvShow;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);
        this.initViews();
    }

    private void initViews(){
        this.btnTest = (Button)findViewById(R.id.btn_task_test);
        this.tvShow = (TextView)findViewById(R.id.tv_show);
        this.progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        this.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAsyncTask task = new CustomAsyncTask();
                task.execute("http://www.baidu.com");
            }
        });
    }

    //
    class CustomAsyncTask extends AsyncTask<String,Integer,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvShow.setText("loading");
        }

        @Override
        protected String doInBackground(String[] params) {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(params[0]);
                HttpResponse response = client.execute(get);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    HttpEntity entity = response.getEntity();
                    InputStream is = entity.getContent();
                    long total = entity.getContentLength();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    int count = 0;
                    int length = -1;
                    while ((length = is.read(buf)) != -1) {
                        baos.write(buf, 0, length);
                        count += length;
                        //调用publishProgress公布进度,最后onProgressUpdate方法将被执行
                        publishProgress((int) ((count / (float) total) * 100));
                        //为了演示进度,休眠500毫秒
                        Thread.sleep(500);
                    }
                    return new String(baos.toByteArray(), "gb2312");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            tvShow.setText("loading..." + values[0] + "%");
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
