package com.coder.catclaws.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.andview.refreshview.utils.LogUtils;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.IControlView;
import com.coder.catclaws.widgets.ControlView;

/**
 * Created by dengshaomin on 2017/11/10.
 */

public class TestActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ControlView control_view = findViewById(R.id.control_view);
        control_view.setiControlView(new IControlView() {
            @Override
            public void left() {
                LogUtils.e("left");
            }

            @Override
            public void up() {
                LogUtils.e("up");
            }

            @Override
            public void right() {
                LogUtils.e("rigaht");
            }

            @Override
            public void down() {
                LogUtils.e("down");
            }
        });

    }
}
