package com.coder.catclaws.widgets;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;

import com.github.lazylibrary.util.DensityUtil;


/**
 * Created by dengshaomin on 2017/10/17.
 */

public class PayItemDecoration extends RecyclerView.ItemDecoration {
    private int itemDecorationWidth;

    @Deprecated
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (itemDecorationWidth == 0) {
            itemDecorationWidth = DensityUtil.dip2px(parent.getContext(), 2);
        }
        if (itemPosition == 0) {
            outRect.set(itemDecorationWidth * 4, itemDecorationWidth * 3, itemDecorationWidth * 4,
                    0);
        } else {
            itemPosition -= 1;
            outRect.set(itemPosition % 2 == 0 ? 4 * itemDecorationWidth : 2 * itemDecorationWidth, 3 *
                            itemDecorationWidth,
                    itemPosition % 2 == 0 ? 2 * itemDecorationWidth : 4 * itemDecorationWidth,
                    0);
        }
    }

}