package com.coder.catclaws.commons;

import android.content.Context;
import android.content.Intent;

import com.coder.catclaws.activity.MainActivity;
import com.coder.catclaws.activity.MineDollActivity;
import com.coder.catclaws.activity.RoomActivity;

/**
 * Created by dengshaomin on 2017/11/9.
 */

public class PageJump {
    public static void goMineDollActivity(Context context) {
        context.startActivity(new Intent(context, MineDollActivity.class));
    }

    public static void goMainActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public static void goRoomActivity(Context context) {
        context.startActivity(new Intent(context, RoomActivity.class));
    }
}
