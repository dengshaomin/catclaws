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

public class DelivedSuccessDialogView extends BaseLayout {


    @BindView(R.id.success_close)
    ImageView mSuccessClose;

    @BindView(R.id.sure)
    TextView mSure;

    @BindView(R.id.share_root_view)
    RelativeLayout mShareRootView;


    public DelivedSuccessDialogView(Context context) {
        super(context);
    }

    public DelivedSuccessDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DelivedSuccessDialogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.delivedsuccess_dialogview;
    }

    @Override
    public void initView() {
        ViewGroup.LayoutParams layoutParams = mShareRootView.getLayoutParams();
        layoutParams.width = (int) (Screen.getWidth(getmContext()) * 0.68);
//        layoutParams.height = (int) (200f / 248f * layoutParams.width);
        mShareRootView.setLayoutParams(layoutParams);
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
