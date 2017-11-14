package com.coder.catclaws.utils;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;

import cn.addapp.pickers.entity.Province;

/**
 * Created by dengshaomin on 2017/11/14.
 */

public class StaticUtils {
    public static IWXAPI mWxApi;
    public static Tencent mTencent;
    public static final String WEIXIN_APPID = "wxfef5d9bc369124d5";
    public static final String WEIXIN_SECRET = "b661d6232d8858bc962798202b3e9850";
    public static final String QQ_APPID = "101441608";
    public static ArrayList<Province> provinces = new java.util.ArrayList<>();

}
