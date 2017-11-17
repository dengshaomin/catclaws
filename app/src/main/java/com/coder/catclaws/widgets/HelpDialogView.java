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
import com.tmall.ultraviewpager.Screen;

import butterknife.BindView;

/**
 * Created by dengshaomin on 2017/11/16.
 */

public class HelpDialogView extends BaseLayout {


    @BindView(R.id.success_close)
    ImageView mSuccessClose;

    @BindView(R.id.question1)
    TextView mQuestion1;

    @BindView(R.id.question2)
    TextView mQuestion2;

    @BindView(R.id.question23)
    TextView mQuestion23;

    @BindView(R.id.question4)
    TextView mQuestion4;

    @BindView(R.id.dialog_root_view)
    RelativeLayout mDialogRootView;

    public ImageView getSuccessClose() {
        return mSuccessClose;
    }

    public TextView getQuestion1() {
        return mQuestion1;
    }

    public TextView getQuestion2() {
        return mQuestion2;
    }

    public TextView getQuestion23() {
        return mQuestion23;
    }

    public TextView getQuestion4() {
        return mQuestion4;
    }

    public RelativeLayout getDialogRootView() {
        return mDialogRootView;
    }

    public HelpDialogView(Context context) {
        super(context);
    }

    public HelpDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HelpDialogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.help_dialogview;
    }

    @Override
    public void initView() {
        ViewGroup.LayoutParams layoutParams = mDialogRootView.getLayoutParams();
        layoutParams.width = (int) (Screen.getWidth(getmContext()) * 0.8);
        layoutParams.height = (int) (328f / 248f * layoutParams.width);
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
