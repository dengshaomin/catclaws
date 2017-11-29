package com.coder.catclaws;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.tencent.bugly.crashreport.CrashReport;

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
        CrashReport.initCrashReport(getApplicationContext(), "dc243a1ffb", false);
        Fresco.initialize(this);
        startService(new Intent(this, InitService.class));
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
////        MultiDex.install(this);
//    }
}
