package com.tmall.ultraviewpager;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

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
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

}
