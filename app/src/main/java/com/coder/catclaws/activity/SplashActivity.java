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

public class SplashActivity extends PermissionActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_splash);

    }

    @Override
    public void permissonSuccess() {
        super.permissonSuccess();
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
//        startActivity(new Intent(SplashActivity.this, LiveModeActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public List<PermissonItem> needPermissions() {
        return new ArrayList<PermissonItem>() {{
            add(new PermissonItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取权限", R.drawable.permission_ic_memory));
            add(new PermissonItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写权限", R.drawable.permission_ic_memory));
            add(new PermissonItem(Manifest.permission.READ_PHONE_STATE, "获取手机状态", R.drawable.permission_ic_memory));
        }};
    }

    private void selfLogin() {
        Net.request(NetIndentify.SELFLOGIN, new HashMap<String, String>() {{
            put("ccId", UserManager.getInstance().getCcId() + "");
            put("imie", MiscUtils.getIMEI(MyApplication.applicationContext));
        }});
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GlobalMsg globalMsg) {
        /* Do something */
        if (globalMsg != null) {
            if (NetIndentify.SELFLOGIN.equals(globalMsg.getMsgId())) {
                if (globalMsg.isSuccess()) {
                    UserInfoModel userInfoModel = (UserInfoModel) globalMsg.getMsg();
                    UserManager.getInstance().setUserinfo(userInfoModel);
                    PageJump.goMainActivity(SplashActivity.this);
                } else {
                    PageJump.goMainActivity(SplashActivity.this);
                    finish();
                }
            }
        }
    }

}
