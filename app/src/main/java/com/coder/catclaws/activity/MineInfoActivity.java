package com.coder.catclaws.activity;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.AppIndentify;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.PreferenceUtils;
import com.coder.catclaws.commons.UserManager;
import com.coder.catclaws.widgets.CoinNotEnoughDialogView;
import com.coder.catclaws.widgets.FullDialog;
import com.coder.catclaws.widgets.LoginOutDialogView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;
import me.weyye.hipermission.PermissonItem;

public class MineInfoActivity extends BaseActivity {

    @BindView(R.id.icon)
    SimpleDraweeView icon;

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.num)
    TextView num;

    @BindView(R.id.iconbg)
    ImageView iconbg;

    @BindView(R.id.recharge)
    ImageView recharge;

    @BindView(R.id.invert)
    TextView invert;

    @BindView(R.id.msg)
    TextView msg;

    @BindView(R.id.adress)
    TextView adress;

    @BindView(R.id.contact)
    TextView contact;

    @BindView(R.id.login_out)
    TextView loginOut;

    @BindView(R.id.setting_bg_music)
    ImageView mSettingBgMusic;

    @BindView(R.id.setting_yinxiao)
    ImageView mSettingYinxiao;

    @Override
    public boolean needTitle() {
        return true;
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
        return "个人中心";
    }

    @Override
    public void titleLeftClick() {
        finish();
    }

    @Override
    public void titleRightClick() {

    }

    @Override
    public int setContentLayout() {
        return R.layout.activity_mine_info;
    }

    @Override
    public void initView() {

        setUserInfo();
        boolean flag = PreferenceUtils.getInstance().getBoolean(PreferenceUtils.SETTING_BG_MUSIC, true);
        mSettingBgMusic.setBackgroundResource(flag ? R.drawable.setting_open_bg : R.drawable.setting_close_bg);

        boolean flag1 = PreferenceUtils.getInstance().getBoolean(PreferenceUtils.SETTING_YINXIAO_MUSIC, true);
        mSettingYinxiao.setBackgroundResource(flag1 ? R.drawable.setting_open_bg : R.drawable.setting_close_bg);
    }

    private void setUserInfo() {
        name.setText(UserManager.getInstance().getName());
        num.setText(UserManager.getInstance().getMb() + "");
        ImageLoader.getInstance().loadImage(icon, UserManager.getInstance().getIcon());
    }

    @Override
    public void initBundleData() {

    }

    @Override
    public void getNetData() {

    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {{
            add(AppIndentify.UPDATE_USERINFO);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        setUserInfo();
    }

    @Override
    public void setViewData(Object data) {

    }

    @Override
    public List<PermissonItem> needPermissions() {
        return null;
    }


    @OnClick({R.id.recharge, R.id.invert, R.id.msg, R.id.adress, R.id.contact, R.id.login_out, R.id.setting_bg_music, R.id.setting_yinxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.recharge:
                PageJump.goRechargeActivity(MineInfoActivity.this);
                break;
            case R.id.invert:
                PageJump.goInvertFriendActivity(MineInfoActivity.this);
                break;
            case R.id.msg:
                PageJump.goMessageActivity(this);
                break;
            case R.id.adress:
                PageJump.goAddressActivity(this);
                break;
            case R.id.contact:
                PageJump.goContactActivity(this);
                break;
            case R.id.login_out:
                LoginOutDialogView loginOutDialogView = new LoginOutDialogView(MineInfoActivity.this);
                final FullDialog fullDialog = FullDialog.create(MineInfoActivity.this).addContentView(loginOutDialogView);
                loginOutDialogView.getSure().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fullDialog.dismiss();
                        UserManager.getInstance().loginOut();
                        PageJump.goLoginActivity(MineInfoActivity.this);
                    }
                });
                fullDialog.show();
                break;
            case R.id.setting_bg_music:
                boolean flag = PreferenceUtils.getInstance().getBoolean(PreferenceUtils.SETTING_BG_MUSIC, true);
                mSettingBgMusic.setBackgroundResource(flag ? R.drawable.setting_close_bg : R.drawable.setting_open_bg);
                PreferenceUtils.getInstance().setBoolean(PreferenceUtils.SETTING_BG_MUSIC, !flag);
                break;
            case R.id.setting_yinxiao:
                boolean flag1 = PreferenceUtils.getInstance().getBoolean(PreferenceUtils.SETTING_YINXIAO_MUSIC, true);
                mSettingYinxiao.setBackgroundResource(flag1 ? R.drawable.setting_close_bg : R.drawable.setting_open_bg);
                PreferenceUtils.getInstance().setBoolean(PreferenceUtils.SETTING_YINXIAO_MUSIC, !flag1);
                break;
        }
    }

}
