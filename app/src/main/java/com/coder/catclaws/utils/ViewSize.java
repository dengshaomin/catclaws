package com.coder.catclaws.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.tmall.ultraviewpager.Screen;

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

    public static void setSize(View view, int x, int y) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = x;
        layoutParams.height = y;
        view.setLayoutParams(layoutParams);

    }

}
