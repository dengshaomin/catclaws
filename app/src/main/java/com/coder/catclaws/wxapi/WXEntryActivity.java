package com.coder.catclaws.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.utils.LogUtils;
import com.coder.catclaws.InitService;
import com.coder.catclaws.activity.LoginActivity;
import com.coder.catclaws.commons.AppIndentify;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.models.ThirdLoginModel;
import com.coder.catclaws.models.WeChartTokenModel;
import com.coder.catclaws.models.WeChartUserInfo;
import com.coder.catclaws.retrofit.GCNetCallBack;
import com.coder.catclaws.retrofit.NetInterface;
import com.coder.catclaws.retrofit.RetrofitHttpUtil;
import com.github.lazylibrary.util.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * Created by dengshaomin on 2017/11/9.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final int RETURN_MSG_TYPE_LOGIN = 1;
    private static final int RETURN_MSG_TYPE_SHARE = 2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //如果没回调onResp，八成是这句没有写
        InitService.mWxApi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // 微信发送请求到第三方应用时，会回调到该方法
    @Override
    public void onReq(BaseReq req) {
    }

    // 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
    //app发送消息给微信，处理返回消息的回调
    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                if (RETURN_MSG_TYPE_SHARE == resp.getType()) {
                    ToastUtils.showToast(this, "分享失败");
                } else {
                    ToastUtils.showToast(this, "登录失败");
                }
                break;
            case BaseResp.ErrCode.ERR_OK:
                switch (resp.getType()) {
                    case RETURN_MSG_TYPE_LOGIN:
                        //拿到了微信返回的code,立马再去请求access_token
                        final String code = ((SendAuth.Resp) resp).code;
                        //就在这个地方，用网络库什么的或者自己封的网络api，发请求去咯，注意是get请求
                        RetrofitHttpUtil.getInstance().getWxToken("https://api.weixin.qq.com/sns",
                                new HashMap<String, String>() {{
                                    put("appid", InitService.WEIXIN_APPID);
                                    put("secret", InitService.WEIXIN_SECRET);
                                    put("code", code);
                                    put("grant_type", "authorization_code");
                                }}, new GCNetCallBack<String>("0", new NetInterface() {
                                    @Override
                                    public void onSuccess(String indentify, String code, String response) {
                                        Log.e("code", response);
                                        WeChartTokenModel weChartTokenModel = JSON.parseObject(response,
                                                WeChartTokenModel.class);
                                        if (weChartTokenModel == null) {
                                            LoginError();
                                        } else {
                                            getUserInfo(weChartTokenModel);
                                        }
                                    }

                                    @Override
                                    public void onError(String indentify, String code, String msg) {
                                        LoginError();
                                    }
                                }));
                        break;

                    case RETURN_MSG_TYPE_SHARE:
                        ToastUtils.showToast(this, "微信分享成功");
                        finish();
                        break;
                }
                break;
        }
    }

    private void LoginError() {
        EventBus.getDefault().post(new GlobalMsg(false, AppIndentify.THIRDLOGIN, null));
    }

    private void getUserInfo(final WeChartTokenModel weChartTokenModel) {
        RetrofitHttpUtil.getInstance().getWxUserinfo("https://api.weixin.qq.com/sns",
                new HashMap<String, String>() {{
                    put("openid", weChartTokenModel.getOpenid());
                    put("access_token", weChartTokenModel.getAccess_token());
                    put("lang", "zh_CN");
                }}, new GCNetCallBack<String>("0", new NetInterface() {
                    @Override
                    public void onSuccess(String indentify, String code, String response) {
                        Log.e("code", response);
                        WeChartUserInfo weChartUserInfo = JSON.parseObject(response,
                                WeChartUserInfo.class);
                        if (weChartUserInfo == null) {
                            LoginError();
                        } else {
                            ThirdLoginModel thirdLoginModel = new ThirdLoginModel();
                            thirdLoginModel.setAccess_token(weChartTokenModel.getAccess_token());
                            thirdLoginModel.setOpenid(weChartTokenModel.getOpenid());
                            thirdLoginModel.setHeadimgurl(weChartUserInfo.getHeadimgurl());
                            thirdLoginModel.setNickname(weChartUserInfo.getNickname());
                            thirdLoginModel.setSex(weChartUserInfo.getSex());
                            EventBus.getDefault().post(new GlobalMsg(true, AppIndentify.THIRDLOGIN, thirdLoginModel));
                            finish();
                        }
                    }

                    @Override
                    public void onError(String indentify, String code, String msg) {
                        LoginError();
                    }
                }));
    }
}

