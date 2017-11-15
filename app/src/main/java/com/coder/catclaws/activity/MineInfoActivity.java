package com.coder.catclaws.activity;

import java.util.List;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.PreferenceUtils;
import com.coder.catclaws.commons.UserManager;
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


    @OnClick({R.id.recharge, R.id.invert, R.id.msg, R.id.adress, R.id.contact, R.id.login_out, R.id.setting_bg_music, R.id.setting_yinxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.recharge:
                PageJump.goRechargeActivity(MineInfoActivity.this);
                break;
            case R.id.invert:
                PageJump.goRechargeActivity(MineInfoActivity.this);
                break;
            case R.id.msg:
                break;
            case R.id.adress:
                break;
            case R.id.contact:
                break;
            case R.id.login_out:
                UserManager.getInstance().loginOut();
                PageJump.goLoginActivity(MineInfoActivity.this);
                break;
            case R.id.setting_bg_music:
                boolean flag = PreferenceUtils.getInstance().getBoolean(PreferenceUtils.SETTING_BG_MUSIC, true);
                mSettingBgMusic.setBackgroundResource(flag ? R.drawable.setting_close_bg : R.drawable.setting_open_bg);
                PreferenceUtils.getInstance().setBoolean(PreferenceUtils.SETTING_BG_MUSIC, !flag);

                break;
            case R.id.setting_yinxiao:
                boolean flag1 = PreferenceUtils.getInstance().getBoolean(PreferenceUtils.SETTING_YINXIAO_MUSIC, true);
                mSettingBgMusic.setBackgroundResource(flag1 ? R.drawable.setting_close_bg : R.drawable.setting_open_bg);
                PreferenceUtils.getInstance().setBoolean(PreferenceUtils.SETTING_YINXIAO_MUSIC, !flag1);
                break;
        }
    }

}
