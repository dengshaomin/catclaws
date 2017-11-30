package com.coder.catclaws.activity;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import cn.iwgang.countdownview.CountdownView.OnCountdownEndListener;
import cn.iwgang.countdownview.CountdownView.OnCountdownIntervalListener;
import me.weyye.hipermission.PermissonItem;

/**
 * Created by dengshaomin on 2017/11/10.
 */

public class TestActivity extends BaseActivity {


    @BindView(R.id.pick)
    TextView mPick;

    private int countdownTime = 24000;

    @BindView(R.id.count_down_timer)
    CountdownView mCountDownTimer;

    @BindView(R.id.value)
    TextView mValue;

    @BindView(R.id.start)
    TextView mStart;

    @BindView(R.id.again)
    TextView mAgain;

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


    @OnClick({R.id.start, R.id.again, R.id.pick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                mCountDownTimer.start(countdownTime);
                mValue.setText((countdownTime / 1000 - 1) + "");
                mCountDownTimer.setOnCountdownEndListener(new OnCountdownEndListener() {
                    @Override
                    public void onEnd(CountdownView cv) {
//
//                    mCountDown.setText(0 + "");
//                    startPick();
                    }
                });
                mCountDownTimer.setOnCountdownIntervalListener(1000, new OnCountdownIntervalListener() {
                    @Override
                    public void onInterval(CountdownView cv, long remainTime) {
                        mValue.setText(remainTime / 1000 + "");
                    }
                });
                break;
            case R.id.again:
                mCountDownTimer.start(countdownTime);
                mValue.setText((countdownTime / 1000 - 1) + "");
                break;
            case R.id.pick:
                mCountDownTimer.start(10);
                mValue.setText(0 + "");
                break;
        }
    }


}