package com.yoryky.demo.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;

/**
 * 文件读写辅助类
 * created by yoryky
 */
public class FileUtil {
    /**
     * 将内容写入sd卡中
     * @param filename 要写入的文件名
     * @param content  待写入的内容
     * @throws IOException
     */
    public static void writeExternal(Context context, String filename, String content) throws IOException {

        //获取外部存储卡的可用状态
        String storageState = Environment.getExternalStorageState();

        //判断是否存在可用的的SD Card
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {

            //路径： /storage/emulated/0/Android/data/yoryky.com.demo/files/DCIM
            String dcim = context.getExternalFilesDir(Environment.DIRECTORY_DCIM).getAbsolutePath();
            Log.d(TAG, "writeExternal: " +  dcim);

            //路径： /storage/emulated/0/Android/data/yoryky.com.demo/files/Pictures
            String picPath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
            Log.d(TAG, "writeExternal: " + picPath);

            //路径： /storage/emulated/0/DCIM
            String en_dcim = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
            Log.d(TAG, "writeExternal: " + en_dcim);

            //路径： /storage/emulated/0/Android/data/yoryky.com.demo/cache/yoryky.txt
            filename = context.getExternalCacheDir().getAbsolutePath()  + File.separator + filename;
            //filename = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +filename;
            FileOutputStream outputStream = new FileOutputStream(filename);
            outputStream.write(content.getBytes());
            outputStream.close();
        }
    }

    /**
     * 从sd card文件中读取数据
     * @param filename 待读取的sd card
     * @return
     * @throws IOException
     */
    public static String readExternal(Context context, String filename) throws IOException {
        StringBuilder sb = new StringBuilder("");
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            filename = context.getExternalCacheDir().getAbsolutePath() + File.separator + filename;
            //打开文件输入流
            FileInputStream inputStream = new FileInputStream(filename);

            byte[] buffer = new byte[1024];
            int len = inputStream.read(buffer);
            //读取文件内容
            while(len > 0){
                sb.append(new String(buffer,0,len));

                //继续将数据放到buffer中
                len = inputStream.read(buffer);
            }
            //关闭输入流
            inputStream.close();
        }
        return sb.toString();
    }

    /**
     * 写Internal Card文件
     * @param context
     * @param filename
     * @param content
     * @throws IOException
     */
    public static void writeInternal(Context context, String filename, String content) throws IOException{
        //获取文件在内存卡中files目录下的路径
        File file = context.getFilesDir();
        filename = file.getAbsolutePath() + File.separator + filename;

        //打开文件输入流
        FileOutputStream outputStream = new FileOutputStream(filename);

        //写数据到文件中
        outputStream.write(content.getBytes());
        outputStream.close();
    }

    /**
     * 读内存卡中文件方法
     * @param context
     * @param filename 文件名
     * @return
     * @throws IOException
     */
    public static String readInternal(Context context,String filename) throws IOException{
        StringBuilder sb = new StringBuilder("");
        String cachePath = context.getFilesDir().getAbsolutePath();
        Log.d(TAG, "readInternal: " + cachePath);

        //获取文件在内存卡中files目录下的路径
        File file = context.getFilesDir();
        filename = file.getAbsolutePath() + File.separator + filename;

        //打开文件输入流
        FileInputStream inputStream = new FileInputStream(filename);

        byte[] buffer = new byte[1024];
        int len = inputStream.read(buffer);
        //读取文件内容
        while(len > 0){
            sb.append(new String(buffer,0,len));

            //继续将数据放到buffer中
            len = inputStream.read(buffer);
        }
        //关闭输入流
        inputStream.close();
        return sb.toString();
    }
}
