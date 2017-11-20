package com.coder.catclaws.activity;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.utils.LogUtils;
import com.coder.catclaws.MyApplication;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.AppIndentify;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.UserManager;
import com.coder.catclaws.models.ThirdLoginModel;
import com.coder.catclaws.models.UserInfoModel;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.utils.StaticUtils;
import com.github.lazylibrary.util.FileUtils;
import com.github.lazylibrary.util.MiscUtils;
import com.github.lazylibrary.util.ToastUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    public static final String ThirdLoginReceiveBrord = "ThirdLoginReceiveBrord";

    private IUiListener iUiListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this);
//        ViewSize.fixedSize(this, bg, 1920f / 1080f);
//        UserInfoModel userInfoModel = UserManager.getInstance().getUserinfo();
//        if (userInfoModel != null) {
//            PageJump.goMainActivity(LoginActivity.this);
//        } else {
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    startActivity(new Intent(LoginActivity.this, TestActivity.class));
//                }
//            }, 1000);
//        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
//        InitService.mWxApi.unregisterApp();
//        InitService.mTencent.logout(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public List<PermissonItem> needPermissions() {
//        return new ArrayList<PermissonItem>() {{
//            add(new PermissonItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取权限", R.drawable.permission_ic_memory));
//            add(new PermissonItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写权限", R.drawable.permission_ic_memory));
//        }};
        return null;
    }


    @OnClick({R.id.weichart, R.id.qq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.weichart:
                wxLogin();
                break;
            case R.id.qq:
                qqLogin();
                break;
        }
    }

    public void qqLogin() {
//        if (!InitService.mTencent.isSessionValid()) {
        showProgressDialog();
        if (iUiListener == null) {
            iUiListener = new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    FileUtils.saveStrToFile(JSON.toJSONString(o), Environment.getExternalStorageDirectory() + File.separator + "111.txt");
                    ThirdLoginModel thirdLoginModel = JSON.parseObject(o + "", ThirdLoginModel.class);
                    ToastUtils.showToast(LoginActivity.this, thirdLoginModel.getOpenid());
                    UserManager.getInstance().setThirdLoginModel(thirdLoginModel);
                    login("qq");
                }

                @Override
                public void onError(UiError uiError) {
                    loginError();
                }

                @Override
                public void onCancel() {
                    loginError();
                }
            };
        }
        StaticUtils.mTencent.login(this, "all", iUiListener);
//        } else {
//        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, iUiListener);
    }

    public void wxLogin() {
        showProgressDialog();
        if (!StaticUtils.mWxApi.isWXAppInstalled()) {
            ToastUtils.showToast(this, "您还未安装微信客户端");
            return;
        }
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "diandi_wx_login";
        StaticUtils.mWxApi.sendReq(req);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GlobalMsg event) {
        /* Do something */
        if (event != null) {
            if (AppIndentify.THIRDLOGIN.equals(event.getMsgId())) {
                if (event.isSuccess()) {
                    ThirdLoginModel thirdLoginModel = (ThirdLoginModel) event.getMsg();
                    UserManager.getInstance().setThirdLoginModel(thirdLoginModel);
                    login("wx");
                } else {
                    loginError();
                }
            } else if (NetIndentify.LOGIN.equals(event.getMsgId())) {
                if (event.isSuccess()) {
                    UserInfoModel userInfoModel = (UserInfoModel) event.getMsg();
                    UserManager.getInstance().setUserinfo(userInfoModel);
                    PageJump.goMainActivity(LoginActivity.this);
//                    finish();
                } else {
                    loginError();
                }
            }
        }
    }

    private void loginError() {
        closeProgressDialog();
        ToastUtils.showToast(LoginActivity.this, "登录失败~");
    }

    private void login(final String type) {
        Net.request(NetIndentify.LOGIN, new HashMap<String, String>() {{
            put("token", UserManager.getInstance().getThirdLoginModel().getAccess_token());
            put("openid", UserManager.getInstance().getThirdLoginModel().getOpenid());
            put("imie", MiscUtils.getIMSI(MyApplication.applicationContext));
            put("type", type);
        }});
    }


}
