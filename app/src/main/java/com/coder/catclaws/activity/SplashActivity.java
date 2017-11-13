package com.coder.catclaws.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.utils.LogUtils;
import com.coder.catclaws.InitService;
import com.coder.catclaws.MyApplication;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.AppIndentify;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.PreferenceUtils;
import com.coder.catclaws.commons.UserManager;
import com.coder.catclaws.models.ThirdLoginModel;
import com.coder.catclaws.models.UserInfoModel;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.utils.ViewSize;
import com.github.lazylibrary.util.MiscUtils;
import com.github.lazylibrary.util.ToastUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.weyye.hipermission.PermissonItem;

public class SplashActivity extends BaseActivity {


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
        return R.layout.activity_splash;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

    }


    @Override
    public List<PermissonItem> needPermissions() {
        return new ArrayList<PermissonItem>() {{
            add(new PermissonItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取权限", R.drawable.permission_ic_memory));
            add(new PermissonItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写权限", R.drawable.permission_ic_memory));
        }};
    }

    private void selfLogin() {
        Net.request(NetIndentify.SELFLOGIN, new HashMap<String, String>() {{
            put("ccId", UserManager.getInstance().getUserinfo().getData().getCcId());
            put("imie", MiscUtils.getIMSI(MyApplication.applicationContext));
        }});
    }

    @Override
    public void initView() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserInfoModel userInfoModel = UserManager.getInstance().getUserinfo();
                if (userInfoModel != null && userInfoModel.getData() != null) {
                    selfLogin();
                } else {
                    PageJump.goLoginActivity(SplashActivity.this);
                    finish();
                }
            }
        }, 500);
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
            add(NetIndentify.SELFLOGIN);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        if (globalMsg.isSuccess()) {
            UserInfoModel userInfoModel = (UserInfoModel) globalMsg.getMsg();
            UserManager.getInstance().setUserinfo(userInfoModel);
            PageJump.goMainActivity(SplashActivity.this);
        } else {
            PageJump.goLoginActivity(SplashActivity.this);
            finish();
        }
    }

    @Override
    public void setViewData(Object data) {

    }

}
