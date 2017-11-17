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

    public static int getStatuBarHeight(Context context) {
        int statusBarHeight1 = -1;
//获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }
}
