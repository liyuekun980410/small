package com.bwei.day01.myapp;

import android.app.Application;

import com.bwei.day01.BuildConfig;

import org.xutils.x;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this); // 如果在application 中可以直接写this
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
