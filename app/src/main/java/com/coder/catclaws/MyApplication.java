package com.coder.catclaws;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by dengshaomin on 2017/11/7.
 */

public class MyApplication extends Application {
    public static Context applicationContext;

    public static final boolean DEBUG = false;

    @Override
    public void onCreate() {
        super.onCreate();
        this.applicationContext = getApplicationContext();
        startService(new Intent(this, InitService.class));
    }
}
