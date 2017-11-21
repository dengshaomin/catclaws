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
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.models.HomeModel;
import com.coder.catclaws.models.HomeModel.DataBean.RoomsBean.ContentBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmall.ultraviewpager.Screen;

import butterknife.BindView;
import cn.iwgang.countdownview.CountdownView;
import cn.iwgang.countdownview.CountdownView.OnCountdownEndListener;
import cn.iwgang.countdownview.CountdownView.OnCountdownIntervalListener;

/**
 * Created by dengshaomin on 2017/11/16.
 */

public class PickSuccessDialogView extends BaseLayout {

    @BindView(R.id.image)
    SimpleDraweeView mImage;

    @BindView(R.id.share)
    TextView mShare;

    @BindView(R.id.look)
    TextView mLook;

    @BindView(R.id.play_again)
    TextView mPlayAgain;

    @BindView(R.id.pick_success_root_view)
    RelativeLayout mPickSuccessRootView;

    @BindView(R.id.success_close)
    ImageView mSuccessClose;

    @BindView(R.id.count_down_timer)
    CountdownView mCountDownTimer;

    private IDialogCountDown mIDialogCountDown;

    public IDialogCountDown getIDialogCountDown() {
        return mIDialogCountDown;
    }

    public void setIDialogCountDown(IDialogCountDown IDialogCountDown) {
        mIDialogCountDown = IDialogCountDown;
    }

    private ContentBean contentBean;

    public TextView getShare() {
        return mShare;
    }

    public void setShare(TextView share) {
        mShare = share;
    }

    public TextView getLook() {
        return mLook;
    }

    public void setLook(TextView look) {
        mLook = look;
    }

    public TextView getPlayAgain() {
        return mPlayAgain;
    }

    public void setPlayAgain(TextView playAgain) {
        mPlayAgain = playAgain;
    }

    public PickSuccessDialogView(Context context) {
        super(context);
    }

    public PickSuccessDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PickSuccessDialogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageView getSuccessClose() {
        return mSuccessClose;
    }

    public void setSuccessClose(ImageView successClose) {
        mSuccessClose = successClose;
    }

    @Override
    public int setContentLayout() {
        return R.layout.picksuccess_dialogview;
    }

    @Override
    public void initView() {
        ViewGroup.LayoutParams layoutParams = mPickSuccessRootView.getLayoutParams();
        layoutParams.width = (int) (Screen.getWidth(getmContext()) * 0.8);
        layoutParams.height = (int) (328f / 248f * layoutParams.width);
        mPickSuccessRootView.setLayoutParams(layoutParams);

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
                mPlayAgain.setText("再来一局（" + remainTime / 1000 + "）");
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
        contentBean = (ContentBean) data;
        if (contentBean != null) {
            ImageLoader.getInstance().loadImage(mImage, contentBean.getPhoto());
        }
    }
}
