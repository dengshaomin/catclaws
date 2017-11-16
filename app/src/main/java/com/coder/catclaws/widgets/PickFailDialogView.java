package com.coder.catclaws.widgets;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmall.ultraviewpager.Screen;

import butterknife.BindView;

/**
 * Created by dengshaomin on 2017/11/16.
 */

public class PickFailDialogView extends BaseLayout {

    @BindView(R.id.image)
    SimpleDraweeView mImage;

    @BindView(R.id.share)
    TextView mShare;

    @BindView(R.id.look)
    TextView mLook;

    @BindView(R.id.play_again)
    TextView mPlayAgain;

    @BindView(R.id.full_lay)
    LinearLayout mFullLay;

    @BindView(R.id.pick_success_root_view)
    LinearLayout mPickSuccessRootView;

    @BindView(R.id.success_close)
    ImageView mSuccessClose;

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

    public PickFailDialogView(Context context) {
        super(context);
    }

    public PickFailDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PickFailDialogView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        return R.layout.pickfail_dialogview;
    }

    @Override
    public void initView() {
        ViewGroup.LayoutParams layoutParams = mPickSuccessRootView.getLayoutParams();
        layoutParams.width = (int) (Screen.getWidth(getmContext()) * 0.8);
        layoutParams.height = (int) (328f / 248f * layoutParams.width);
        mPickSuccessRootView.setLayoutParams(layoutParams);
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
