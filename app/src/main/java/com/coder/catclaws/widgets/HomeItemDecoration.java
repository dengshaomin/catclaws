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
        if (itemPosition == 0) return;
        itemPosition -= 1;
        outRect.set(itemPosition % 2 == 0 ? 8 * itemDecorationWidth : 2 * itemDecorationWidth, (itemPosition == 0 ||
                        itemPosition == 1) ? 6 *
                        itemDecorationWidth : 4 *
                        itemDecorationWidth,
                itemPosition % 2 == 0 ? 2 * itemDecorationWidth : 8 * itemDecorationWidth,
                0);
//        if (itemPosition % 2 != 0) {
//
//        } else {
//            outRect.set(0, itemDecorationWidth / 2, itemDecorationWidth, itemDecorationWidth / 2);
//        }
    }

}