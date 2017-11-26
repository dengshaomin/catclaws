package com.coder.catclaws.utils;

import com.boom.service.room.netty.TCPClient;
import com.coder.catclaws.InitService;
import com.coder.catclaws.MyApplication;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;

import cn.addapp.pickers.entity.Province;

/**
 * Created by dengshaomin on 2017/11/14.
 */

public class StaticUtils {
    private static IWXAPI mWxApi;
    private static Tencent mTencent;

    public static void setmWxApi(IWXAPI mWxApi) {
        StaticUtils.mWxApi = mWxApi;
    }

    public static String getQqAppid() {
        return QQ_APPID;
    }

    public static void setProvinces(ArrayList<Province> provinces) {
        StaticUtils.provinces = provinces;
    }

    public static final String WEIXIN_APPID = "wxfef5d9bc369124d5";
    public static final String WEIXIN_SECRET = "b661d6232d8858bc962798202b3e9850";
    public static final String QQ_APPID = "101441608";
    private static ArrayList<Province> provinces = new java.util.ArrayList<>();

    public static void clear() {
        if (provinces != null) {
            provinces.clear();
        }
        if (mWxApi != null) {
            mWxApi.unregisterApp();
        }
        if (mTencent != null) {
            mTencent.logout(MyApplication.applicationContext);
        }
        TCPClient.getInstance().disConnect();
    }

    public static IWXAPI getmWxApi() {
        return mWxApi;
    }


    public static Tencent getmTencent() {
        return mTencent;
    }

    public static void setmTencent(Tencent mTencent) {
        StaticUtils.mTencent = mTencent;
    }

    public static ArrayList<Province> getProvinces() {
        if(provinces == null){
            provinces = new ArrayList<>();
        }
        return provinces;
    }

}
