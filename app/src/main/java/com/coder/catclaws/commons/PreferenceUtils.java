package com.coder.catclaws.commons;

import android.content.Context;
import android.text.TextUtils;
import android.view.TextureView;

import com.alibaba.fastjson.JSON;
import com.coder.catclaws.MyApplication;
import com.coder.catclaws.models.ThirdLoginModel;

/**
 * Created by dengshaomin on 2017/11/9.
 */

public class PreferenceUtils extends BasePreference {

    private static PreferenceUtils preferenceUtils;

    public static final String THIRDINFO = "third_info";

    public static final String USERINFO = "user_info";

    public static final String SETTING_BG_MUSIC = "SETTING_BG_MUSIC";
    public static final String SETTING_YINXIAO_MUSIC = "SETTING_YINXIAO_MUSIC";

    //是否首次启动的key
    private String FIRST_LAUNCH = "first_launch";

    protected PreferenceUtils(Context context) {
        super(context);
    }


    /**
     * 这里我通过自定义的Application来获取Context对象，所以在获取preferenceUtils时不需要传入Context。
     */
    public synchronized static PreferenceUtils getInstance() {
        if (null == preferenceUtils) {
            preferenceUtils = new PreferenceUtils(MyApplication.applicationContext);
        }
        return preferenceUtils;
    }

    public void setFirstLaunch(boolean isFirst) {
        setBoolean(FIRST_LAUNCH, isFirst);
    }

    public boolean getFirstlaunch() {
        return getBoolean(FIRST_LAUNCH,false);
    }


    public void saveString(String key, String s) {
        if (!TextUtils.isEmpty(s)) {
            setString(key, s);
        }
    }

    public Object getClass(String key, Class cls) {
        if (cls != null) {
            String s = getString(key);
            if (TextUtils.isEmpty(s)) {
                return null;
            }
            return JSON.parseObject(s, cls);
        }
        return null;
    }

}
