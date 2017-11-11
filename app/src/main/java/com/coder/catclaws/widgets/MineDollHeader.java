package com.coder.catclaws.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.UserManager;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by dengshaomin on 2017/11/9.
 */

public class MineDollHeader extends BaseLayout {
    @BindView(R.id.icon)
    SimpleDraweeView icon;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.count)
    TextView count;

    public MineDollHeader(Context context) {
        super(context);
    }

    public MineDollHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MineDollHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.minedoll_header;
    }

    @Override
    public void initView() {

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
        count.setText("共抓中" + data + "个");
        ImageLoader.getInstance().loadImage(icon, UserManager.getInstance().getIcon());
        name.setText(UserManager.getInstance().getName());
    }
}
