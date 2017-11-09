package com.coder.catclaws.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.IBaseLayout;
import com.coder.catclaws.commons.ITitle;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by dengshaomin on 2017/11/7.
 */

public abstract class BaseActivity extends PermissionActivity implements IBaseLayout, ITitle {
    LinearLayout container;
    View left_image, right_image;
    TextView title_text;

    private List<String> eventList = new ArrayList<>();
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initTitle();
        unbinder = ButterKnife.bind(this);
        List<String> events = regeistEvent();
        if (events != null) {
            eventList.addAll(events);
        }
        this.initView();
        this.initBundleData();
        this.getNetData();
    }

    @SuppressLint("WrongViewCast")
    private void initTitle() {
        container = findViewById(R.id.container);
        LayoutInflater.from(this).inflate(setContentLayout(), container);
        findViewById(R.id.title_lay).setVisibility(needTitle() ? View.VISIBLE : View.GONE);
        if (needTitle()) {
            title_text = findViewById(R.id.title_text);
            left_image = findViewById(R.id.left_image);
            right_image = findViewById(R.id.right_image);
            left_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titleLeftClick();
                }
            });
            right_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titleRightClick();
                }
            });
            title_text.setText(setTitleText());
            if (setTitleLeftImage() > 0) {
                left_image.setBackgroundResource(setTitleLeftImage());
            }
            if (setTitleRightImage() > 0) {
                left_image.setBackgroundResource(setTitleRightImage());
            }
        }
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
