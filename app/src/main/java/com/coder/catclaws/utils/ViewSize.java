package com.coder.catclaws.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.github.lazylibrary.util.DensityUtil;
import com.github.lazylibrary.util.SizeUtils;

/**
 * Created by dengshaomin on 2017/10/16.
 */

public class ViewSize {
    public static void fixedSize(View view, int width, float proportion) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = (int) (width * proportion);
        view.setLayoutParams(layoutParams);

    }

    public static void fixedSize(Context context, View view, float proportion) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = Screen.getWidth(context);
        layoutParams.height = (int) (Screen.getWidth(context) * proportion);
        view.setLayoutParams(layoutParams);

    }

}
