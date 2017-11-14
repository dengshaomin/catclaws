package com.coder.catclaws.commons;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.coder.catclaws.MyApplication;
import com.coder.catclaws.models.ThirdLoginModel;
import com.coder.catclaws.models.UserInfoModel;

import org.w3c.dom.Text;

/**
 * Created by dengshaomin on 2017/11/9.
 */

public class UserManager {
    private static UserManager userManager;

    private UserInfoModel userInfoModel;

    private ThirdLoginModel thirdLoginModel;


    public static UserManager getInstance() {
        if (userManager == null) {
            synchronized (UserManager.class) {
                if (userManager == null) {
                    userManager = new UserManager();
                }
            }
        }
        return userManager;
    }

    public UserInfoModel getUserinfo() {
        if (userInfoModel == null) {
            userInfoModel = JSON.parseObject(PreferenceUtils.getInstance().getString(PreferenceUtils.USERINFO),
                    UserInfoModel.class);
        }
        if (MyApplication.DEBUG && userInfoModel == null) {
            userInfoModel = new UserInfoModel();
        }
        return userInfoModel;
    }

    public String getIcon() {
        if (userInfoModel == null || userInfoModel.getData() == null || userInfoModel.getData().getUser() == null)
            return MyApplication.DEBUG ? "http://img.zcool.cn/community/017bdc59420a3ea8012193a344b26a.png" : "";
        return userInfoModel.getData().getUser().getHeadImg();
    }

    public String getName() {
        if (userInfoModel == null || userInfoModel.getData() == null || userInfoModel.getData().getUser() == null)
            return MyApplication.DEBUG ? "剑指天下" : "";
        return userInfoModel.getData().getUser().getName();
    }

    public int getMb() {
        if (userInfoModel == null || userInfoModel.getData() == null || userInfoModel.getData().getWallet() == null)
            return 0;
        return userInfoModel.getData().getWallet().getMb();
    }

    public void setUserinfo(UserInfoModel userinfo) {
        this.userInfoModel = userinfo;
        PreferenceUtils.getInstance().saveString(PreferenceUtils.USERINFO, JSON.toJSONString(userInfoModel));
    }

    public ThirdLoginModel getThirdLoginModel() {
        return thirdLoginModel;
    }

    public void setThirdLoginModel(ThirdLoginModel thirdLoginModel) {
        this.thirdLoginModel = thirdLoginModel;
        PreferenceUtils.getInstance().saveString(PreferenceUtils.THIRDINFO, JSON.toJSONString(thirdLoginModel));
    }

    public void loginOut() {
        PreferenceUtils.getInstance().saveString(PreferenceUtils.THIRDINFO, "");
        PreferenceUtils.getInstance().saveString(PreferenceUtils.USERINFO, JSON.toJSONString(userInfoModel));
    }
}
