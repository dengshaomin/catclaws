package com.coder.catclaws.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Administrator on 2017/11/12.
 */

public class ScrollViewGridView extends GridView {
    public ScrollViewGridView(Context context) {
        super(context);
    }

    public ScrollViewGridView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewGridView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
