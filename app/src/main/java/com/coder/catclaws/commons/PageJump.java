package com.coder.catclaws.commons;

import android.content.Context;
import android.content.Intent;

import com.coder.catclaws.activity.MineDollActivity;

/**
 * Created by dengshaomin on 2017/11/9.
 */

public class PageJump {
    public static void goMineDollActivity(Context context) {
        context.startActivity(new Intent(context, MineDollActivity.class));
    }
}
