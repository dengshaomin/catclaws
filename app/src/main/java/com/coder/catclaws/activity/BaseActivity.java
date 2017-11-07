package com.coder.catclaws.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.coder.catclaws.commons.IBaseLayout;
import com.coder.catclaws.commons.GlobalMsg;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dengshaomin on 2017/11/7.
 */

public abstract class BaseActivity extends PermissionActivity implements IBaseLayout {
    private List<String> eventList = new ArrayList<>();
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentLayout());
        unbinder = ButterKnife.bind(this);
        List<String> events = regeistEvent();
        if (events != null) {
            eventList.addAll(events);
        }
        this.initView();
        this.initBundleData();
        this.getNetData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GlobalMsg event) {
        /* Do something */
        for (String s : eventList) {
            if (s.equals(event.getMsgId())) {
                eventComming(event);
                break;
            }
        }
    }

}
