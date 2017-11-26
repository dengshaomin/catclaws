package com.coder.catclaws.widgets;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.IHelpDialog;
import com.tmall.ultraviewpager.Screen;

import butterknife.BindView;
import butterknife.OnClick;

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

    @BindView(R.id.question3)
    TextView mQuestion3;

    @BindView(R.id.question4)
    TextView mQuestion4;

    @BindView(R.id.dialog_root_view)
    RelativeLayout mDialogRootView;

    private IHelpDialog mIHelpDialog;

    public IHelpDialog getIHelpDialog() {
        return mIHelpDialog;
    }

    public void setIHelpDialog(IHelpDialog IHelpDialog) {
        mIHelpDialog = IHelpDialog;
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
        layoutParams.width = (int) (Screen.getWidth(getmContext()) * 0.68);
//        layoutParams.height = (int) (328f / 248f * layoutParams.width);
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

    @OnClick({R.id.question1, R.id.question2, R.id.question3, R.id.question4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.question1:
                if(mIHelpDialog != null){
                    mIHelpDialog.click(mQuestion1.getText().toString());
                }
                break;
            case R.id.question2:
                if(mIHelpDialog != null){
                    mIHelpDialog.click(mQuestion2.getText().toString());
                }
                break;
            case R.id.question3:
                if(mIHelpDialog != null){
                    mIHelpDialog.click(mQuestion3.getText().toString());
                }
                break;
            case R.id.question4:
                if(mIHelpDialog != null){
                    mIHelpDialog.click(mQuestion4.getText().toString());
                }
                break;
        }
    }
}
