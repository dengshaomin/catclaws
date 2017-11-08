package com.coder.catclaws.widgets;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

import com.github.lazylibrary.util.DensityUtil;


/**
 * Created by dengshaomin on 2017/10/17.
 */

public class HomeItemDecoration extends RecyclerView.ItemDecoration {
    private int itemDecorationWidth;

    @Deprecated
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (itemDecorationWidth == 0) {
            itemDecorationWidth = DensityUtil.dip2px(parent.getContext(), 2);
        }
        if (itemPosition % 2 != 0) {
            outRect.set(itemDecorationWidth, 0, 0, 0);
        } else {
            outRect.set(0, 0, itemDecorationWidth, 0);
        }
    }

}