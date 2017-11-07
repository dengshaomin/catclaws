package com.coder.catclaws.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.coder.catclaws.MyApplication;
import com.github.lazylibrary.util.DensityUtil;

/**
 * Created by dengshaomin on 2017/11/7.
 */

public class Screen {
    public static int getWidth(Context context) {
        int[] size = DensityUtil.getDeviceInfo(context);
        return size[0];
    }

    public static int getHeight(Context context) {
        int[] size = DensityUtil.getDeviceInfo(context);
        return size[1];
    }
}
