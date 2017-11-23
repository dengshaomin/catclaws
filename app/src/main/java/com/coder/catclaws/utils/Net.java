package com.coder.catclaws.utils;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.utils.LogUtils;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.models.ALiOrderModel;
import com.coder.catclaws.models.AddressModel;
import com.coder.catclaws.models.BaseModel;
import com.coder.catclaws.models.ChangeAddressModel;
import com.coder.catclaws.models.DollPickLogModel;
import com.coder.catclaws.models.HomeModel;
import com.coder.catclaws.models.MessageModel;
import com.coder.catclaws.models.MineDollModel;
import com.coder.catclaws.models.RechargeModel;
import com.coder.catclaws.models.SubmitQuestionModel;
import com.coder.catclaws.models.UserInfoModel;
import com.coder.catclaws.models.WeChartOrderModel;
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

    public static void request(String url, Map<String, String> params) {
        RetrofitHttpUtil.getInstance().get(url, params, new GCNetCallBack<String>(url, new NetInterface() {
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
                    case NetIndentify.HOME:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                HomeModel.class)));
                        break;
                    case NetIndentify.LOGIN:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                UserInfoModel.class)));
                        break;
                    case NetIndentify.MINEDOLL:
                    case NetIndentify.ALL_DEPOSIT_DOLL:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                MineDollModel.class)));
                        break;

                    case NetIndentify.RECHARGE:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                RechargeModel.class)));
                        break;
                    case NetIndentify.WX_PAY:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                WeChartOrderModel.class)));
                        break;
                    case NetIndentify.ALI_PAY:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                ALiOrderModel.class)));
                    case NetIndentify.MINEMESSAGE:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                MessageModel.class)));
                        break;
                    case NetIndentify.ADRESS:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                AddressModel.class)));
                        break;
                    case NetIndentify.ADDADRESS:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                ChangeAddressModel.class)));
                    case NetIndentify.SUBMIT_QUESTION:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                SubmitQuestionModel.class)));
                        break;
                    case NetIndentify.DOLL_PICL_LOG:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                DollPickLogModel.class)));
                        break;
                    case NetIndentify.INVITED:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, null));
                        break;
                }
            }

            @Override
            public void onError(String indentify, String code, String msg) {
                EventBus.getDefault().post(new GlobalMsg(false, indentify, UKNOW_ERROR));
            }

        }));
    }

    public static void post(String url, Map<String, String> params) {
        RetrofitHttpUtil.getInstance().postJson(url, params, new GCNetCallBack<String>(url, new NetInterface() {
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
                    case NetIndentify.ADDADRESS:
                        EventBus.getDefault().post(new GlobalMsg(true, indentify, JSON.parseObject(response,
                                AddressModel.class)));
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
