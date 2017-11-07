package yoryky.com.demo.util;

import android.content.Context;
import android.util.Log;

public class DaggerUtil {

    private Context context;
    public DaggerUtil(Context context){
        this.context = context;
    }

    public void test(){
        Log.i("yjing", "daggerutil: success");
    }
}
