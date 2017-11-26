package com.coder.catclaws.widgets;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.IDialogCountDown;
import com.tmall.ultraviewpager.Screen;

import butterknife.BindView;
import cn.iwgang.countdownview.CountdownView;
import cn.iwgang.countdownview.CountdownView.OnCountdownEndListener;
import cn.iwgang.countdownview.CountdownView.OnCountdownIntervalListener;

/**
 * Created by dengshaomin on 2017/11/16.
 */

public class PlayFailDialogView extends BaseLayout {


    @BindView(R.id.success_close)
    ImageView mSuccessClose;

    @BindView(R.id.cancel)
    TextView mCancel;

    @BindView(R.id.sure)
    TextView mSure;

    @BindView(R.id.count_down_timer)
    CountdownView mCountDownTimer;

    @BindView(R.id.dialog_root_view)
    RelativeLayout mDialogRootView;

    public TextView getSure() {
        return mSure;
    }

    public void setSure(TextView sure) {
        mSure = sure;
    }

    private IDialogCountDown mIDialogCountDown;

    public IDialogCountDown getIDialogCountDown() {
        return mIDialogCountDown;
    }

    public void setIDialogCountDown(IDialogCountDown IDialogCountDown) {
        mIDialogCountDown = IDialogCountDown;
    }

    public PlayFailDialogView(Context context) {
        super(context);
    }

    public PlayFailDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlayFailDialogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.playfail_dialogview;
    }

    @Override
    public void initView() {
        ViewGroup.LayoutParams layoutParams = mDialogRootView.getLayoutParams();
        layoutParams.width = (int) (Screen.getWidth(getmContext()) * 0.68);
//        layoutParams.height = (int) (200f / 248f * layoutParams.width);
        mDialogRootView.setLayoutParams(layoutParams);
        mCountDownTimer.start(10000);
        mCountDownTimer.setOnCountdownEndListener(new OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                if (getIDialogCountDown() != null) {
                    mIDialogCountDown.finish();
                }
            }
        });
        mCountDownTimer.setOnCountdownIntervalListener(1000, new OnCountdownIntervalListener() {
            @Override
            public void onInterval(CountdownView cv, long remainTime) {
                mSure.setText("再来一局（" + remainTime / 1000 + "）");
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
}
