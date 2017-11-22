package com.yoryky.demo.entity;


import android.util.Log;

public class User {
    private Child child;
    public User(Child child){
        this.child = child;
    }

    public void test(){
        Log.i("yjing", "test: " + child.getName());
    }
}
