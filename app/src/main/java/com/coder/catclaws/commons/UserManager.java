package com.coder.catclaws.commons;


import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
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
        return userInfoModel;
    }

    public String getIcon() {
        if (userInfoModel == null || userInfoModel.getData() == null) return "";
        return userInfoModel.getData().getHeadImg();
    }
    public String getName() {
        if (userInfoModel == null || userInfoModel.getData() == null) return "";
        return userInfoModel.getData().getName();
    }
    public void setUserinfo(UserInfoModel userinfo) {
        this.userInfoModel = userinfo;
        PreferenceUtils.getInstance().saveString(PreferenceUtils.USERINFO, JSON.toJSONString(thirdLoginModel));
    }

    public ThirdLoginModel getThirdLoginModel() {
        return thirdLoginModel;
    }

    public void setThirdLoginModel(ThirdLoginModel thirdLoginModel) {
        this.thirdLoginModel = thirdLoginModel;
        PreferenceUtils.getInstance().saveString(PreferenceUtils.THIRDINFO, JSON.toJSONString(thirdLoginModel));
    }
}
