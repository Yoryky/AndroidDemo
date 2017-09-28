package yoryky.com.demo.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by caicai on 2017/9/28.
 */

public class HelpUtil {
    public static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
