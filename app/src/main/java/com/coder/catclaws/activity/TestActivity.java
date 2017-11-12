package com.coder.catclaws.activity;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andview.refreshview.utils.LogUtils;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.FullyGridLayoutManager;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.widgets.CommonViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;
import cn.iwgang.countdownview.CountdownView.OnCountdownIntervalListener;
import me.weyye.hipermission.PermissonItem;

/**
 * Created by dengshaomin on 2017/11/10.
 */

public class TestActivity extends BaseActivity {

    @BindView(R.id.count)
    CountdownView count;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;

    @Override
    public boolean needTitle() {
        return false;
    }

    @Override
    public int setTitleLeftImage() {
        return 0;
    }

    @Override
    public int setTitleRightImage() {
        return 0;
    }

    @Override
    public String setTitleText() {
        return null;
    }

    @Override
    public void titleLeftClick() {

    }

    @Override
    public void titleRightClick() {

    }

    @Override
    public int setContentLayout() {
        return R.layout.test_activity;
    }

    @Override
    public void initView() {
        count.start(20000);
        count.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                int a = 1;
            }
        });
        count.setOnCountdownIntervalListener(1000, new OnCountdownIntervalListener() {
            @Override
            public void onInterval(CountdownView cv, long remainTime) {
                int a = 1;
                LogUtils.e(remainTime + "");
                //只用他的定时器，用这个监听去更新自己的VIEW
            }
        });

        recycleView.setLayoutManager(new GridLayoutManager(this, 3));
        recycleView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                ImageView imageView = new ImageView(TestActivity.this);
                imageView.setBackgroundResource(R.drawable.mine_info_bg);
                return new CommonViewHolder(imageView);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 6;
            }
        });
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