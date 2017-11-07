package com.coder.catclaws.activity;

import android.Manifest;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.utils.ViewSize;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.weyye.hipermission.PermissonItem;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.weichart)
    TextView weichart;
    @BindView(R.id.qq)
    TextView qq;

    @Override
    public int setContentLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        ViewSize.fixedSize(this, bg, 1920f / 1080f);

    }

    @Override
    public void initBundleData() {

    }

    @Override
    protected void onStart() {
        super.onStart();
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

    }


    @Override
    public List<PermissonItem> needPermissions() {
        return new ArrayList<PermissonItem>() {{
            add(new PermissonItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取权限", R.drawable.permission_ic_memory));
            add(new PermissonItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写权限", R.drawable.permission_ic_memory));
        }};
    }
}
