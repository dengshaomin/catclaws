package com.coder.catclaws.widgets;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.tmall.ultraviewpager.Screen;

import butterknife.BindView;

/**
 * Created by dengshaomin on 2017/11/16.
 */

public class LiveRoomDialogView extends BaseLayout {


    @BindView(R.id.close)
    ImageView mClose;

    @BindView(R.id.sure)
    TextView mSure;

    @BindView(R.id.cancel)
    TextView mCancel;

    @BindView(R.id.dialog_root_view)
    RelativeLayout mDialogRootView;

    public ImageView getClose() {
        return mClose;
    }

    public TextView getSure() {
        return mSure;
    }

    public TextView getCancel() {
        return mCancel;
    }

    public RelativeLayout getDialogRootView() {
        return mDialogRootView;
    }

    public LiveRoomDialogView(Context context) {
        super(context);
    }

    public LiveRoomDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LiveRoomDialogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.liveroom_dialogview;
    }

    @Override
    public void initView() {
        ViewGroup.LayoutParams layoutParams = mDialogRootView.getLayoutParams();
        layoutParams.width = (int) (Screen.getWidth(getmContext()) * 0.68);
        mDialogRootView.setLayoutParams(layoutParams);
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
