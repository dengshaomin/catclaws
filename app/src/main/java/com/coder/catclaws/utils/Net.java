package com.coder.catclaws.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.models.BaseModel;
import com.coder.catclaws.models.HomeModel;
import com.coder.catclaws.retrofit.GCNetCallBack;
import com.coder.catclaws.retrofit.NetInterface;
import com.coder.catclaws.retrofit.RetrofitHttpUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

/**
 * Created by dengshaomin on 2017/11/8.
 */

public class Net {

    public static final String UKNOW_ERROR = "服务器异常~";
    public static final String HOME = "NetHome";


    public static void request(String url, Map<String, String> params, String indentiry) {
        RetrofitHttpUtil.getInstance().get(url, params, new GCNetCallBack<String>(indentiry, new NetInterface() {
            @Override
            public void onSuccess(String indentify, String code, String response) {
                BaseModel baseModel = JSON.parseObject(response, BaseModel.class);
                if (baseModel == null || baseModel.getCode() != 200) {
                    EventBus.getDefault().post(new GlobalMsg(false, indentify, baseModel == null ? UKNOW_ERROR :
                            baseModel
                                    .getMsg()));
                    return;
                }
                switch (indentify) {
                    case HOME:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                HomeModel.class)));
                        break;
                }
            }

            @Override
            public void onError(String indentify, String code, String msg) {
                EventBus.getDefault().post(new GlobalMsg(false, indentify, UKNOW_ERROR));
            }

        }));
    }
}
