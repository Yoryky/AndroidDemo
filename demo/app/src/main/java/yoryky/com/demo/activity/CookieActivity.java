package yoryky.com.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yoryky.com.demo.R;

/**
 * Created by caicai on 2017/9/29.
 */

public class CookieActivity extends BaseActivity implements View.OnClickListener{
    private Button btnSendPost;
    private CookieManager cookieManager;
    private static CookieStore cookieStore;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitity_cookie);
        this.initViews();
    }

    private void initViews(){
        btnSendPost = (Button)findViewById(R.id.btn_send_post);
        btnSendPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_send_post:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        sendPost("http://192.168.8.130:8081/test/login","yjing","123456");
                    }
                }).start();
                break;
        }
    }

    private String sendPost(String url,String userName,String password) {
        CookieSyncManager.createInstance(CookieActivity.this);
        removeCookies();
        HttpPost httpRequest = new HttpPost(url);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.setCookieStore(cookieStore);
        String strResult = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("_tk","diet"));
        params.add(new BasicNameValuePair("name",userName));
        params.add(new BasicNameValuePair("password",password));
        httpRequest.addHeader("Accept","application/json,text/javascript,*/*;q=0.01");
        httpRequest.addHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
        httpRequest.addHeader("Origin","http://192.168.8.130:8081");
        httpRequest.addHeader("Referer","http://192.168.8.130:8081/test/login");
        httpRequest.addHeader("X-Requested-With", "XMLHttpRequest");
        try{
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            if(httpResponse.getStatusLine().getStatusCode() == 200){
                strResult = EntityUtils.toString(httpResponse.getEntity());
                cookieStore = httpClient.getCookieStore();
                List<Cookie> cookies = cookieStore.getCookies();
                if(cookies.isEmpty()){
                    System.out.println("Cookies为空");
                }else{
                    for(int i =0; i < cookies.size(); i++){
                        Cookie cookie = cookies.get(i);
                        Log.d("Cookie",cookie.getName() + "=" + cookie.getValue());
                        cookieManager = CookieManager.getInstance();
                        String cookieStr = cookie.getName() + "=" + cookie.getValue() + ";domain=" + cookie.getDomain();
                        cookieManager.setCookie("http://192.168.8.130/",cookieStr);
                    }
                }
                return strResult;
            }else{
                strResult = "错误响应:" + httpResponse.getStatusLine().toString();
            }
        }catch (ClientProtocolException e){
            strResult = "错误响应:" + e.getMessage().toString();
            e.printStackTrace();
            return strResult;
        }catch (IOException e){
            strResult = "错误响应:" + e.getMessage().toString();
            e.printStackTrace();
            return strResult;
        }catch (Exception e){
            strResult = "错误响应:" + e.getMessage().toString();
            e.printStackTrace();
            return strResult;
        }
        return strResult;
    }

    private void removeCookies(){
        cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
    }
}
