package com.coder.catclaws.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.utils.ViewSize;
import com.github.lazylibrary.util.ToastUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

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

public class LoginActivity extends PermissionActivity {


    @BindView(R.id.bg)
    ImageView bg;
    @BindView(R.id.weichart)
    TextView weichart;
    @BindView(R.id.qq)
    TextView qq;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this);
        ViewSize.fixedSize(this, bg, 1920f / 1080f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        InitService.mWxApi.unregisterApp();
    }

    @Override
    public List<PermissonItem> needPermissions() {
        return new ArrayList<PermissonItem>() {{
            add(new PermissonItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取权限", R.drawable.permission_ic_memory));
            add(new PermissonItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写权限", R.drawable.permission_ic_memory));
        }};
    }


    @OnClick({R.id.weichart, R.id.qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.weichart:
                wxLogin();
                break;
            case R.id.qq:
                break;
        }
    }

    public void wxLogin() {
        if (!InitService.mWxApi.isWXAppInstalled()) {
            ToastUtils.showToast(this, "您还未安装微信客户端");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        InitService.mWxApi.sendReq(req);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GlobalMsg event) {
        /* Do something */
        if (event != null) {
            if (NetIndentify.LOGIN.equals(event.getMsgId())) {
                保存用户信息

                PageJump.goMainActivity(LoginActivity.this);
                finish();
            }
        }
    }

    private static void login() {
        Net.request(NetIndentify.LOGIN, new HashMap<String, String>() {{
            put("token", UserManager.getInstance().getUserinfo().getAccess_token());
            put("opened", UserManager.getInstance().getUserinfo().getOpenid());
        }});
    }


    public static void LoginResult(boolean success, final ThirdLoginModel thirdLoginModel) {
        if (success) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    UserManager.getInstance().setUserinfo(thirdLoginModel);
                    PreferenceUtils.getInstance().saveString(PreferenceUtils.THIRDINFO, JSON.toJSONString(thirdLoginModel));
                    login();
                }
            });
        } else {

            ToastUtils.showToast(MyApplication.applicationContext, "登录失败~");
        }
    }
}
