package com.coder.catclaws.widgets.codexrefreshview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;


import com.andview.refreshview.R;
import com.coder.catclaws.widgets.BaseLayout;
import com.coder.catclaws.commons.GlobalMsg;

import java.util.List;

/**
 * Created by dengshaomin on 2017/7/25.
 */

public class CodeRecyclerViewFooter extends BaseLayout {

    private View progress, no_more_tip;

    public CodeRecyclerViewFooter(Context context) {
        super(context);
    }

    public CodeRecyclerViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CodeRecyclerViewFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private boolean needBootmTip;

    public boolean isNeedBootmTip() {
        return needBootmTip;
    }

    public void setNeedBootmTip(boolean needBootmTip) {
        this.needBootmTip = needBootmTip;
        no_more_tip.setVisibility(needBootmTip ? VISIBLE : GONE);
    }

    @Override
    public int setContentLayout() {
        return R.layout.coderecyclerview_foot_view;
    }

    @Override
    public void initView() {
        progress = findViewById(R.id.progress);
        no_more_tip = findViewById(R.id.no_more_tip);
    }

    @Override
    public void initBundleData() {

    }

    @Override
    public void getNetData() {

    }

    @Override
    public List<String> regeistEvent() {
        return null;
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {

    }


    @Override
    public void setViewData(Object data) {
        if (data instanceof Boolean && !(Boolean) data) {
            if (needBootmTip) {
                no_more_tip.setVisibility(GONE);
            }
            progress.setVisibility(VISIBLE);
        } else {
            if (needBootmTip) {
                no_more_tip.setVisibility(VISIBLE);
            }
            progress.setVisibility(GONE);
        }
    }
}
