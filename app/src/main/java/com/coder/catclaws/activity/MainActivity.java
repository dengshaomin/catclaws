package com.coder.catclaws.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.weyye.hipermission.PermissonItem;

public class MainActivity extends BaseActivity {

    @BindView(R.id.textview)
    TextView textview;


    @Override
    public int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        textview.setText("11111");
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

    }

    @Override
    public List<PermissonItem> needPermissions() {
        return null;
    }
}
