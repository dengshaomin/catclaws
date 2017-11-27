package com.coder.catclaws.commons;


import java.sql.DatabaseMetaData;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.coder.catclaws.MyApplication;
import com.coder.catclaws.models.ThirdLoginModel;
import com.coder.catclaws.models.UserInfoModel;
import com.coder.catclaws.models.UserInfoModel.DataBean;
import com.coder.catclaws.models.UserInfoModel.DataBean.WalletBean;

import org.greenrobot.eventbus.EventBus;
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
            userInfoModel.setData(new DataBean());
        }
        return userInfoModel;
    }

    public String getCcId() {
        if (userInfoModel == null) {
            userInfoModel = JSON.parseObject(PreferenceUtils.getInstance().getString(PreferenceUtils.USERINFO),
                    UserInfoModel.class);
        }
        if (MyApplication.DEBUG) {
            if (userInfoModel == null) {
                userInfoModel = new UserInfoModel();
                DataBean dataBean = new DataBean();
                userInfoModel.setData(dataBean);
            }
            userInfoModel.getData().setCcId("e2887c02-ba2d-4156-9e2c-c2985ed1e2f9");
        }
        return userInfoModel == null || userInfoModel.getData() == null ? "" : userInfoModel.getData().getCcId();
    }

    public String getToken() {
        if (userInfoModel == null) {
            userInfoModel = JSON.parseObject(PreferenceUtils.getInstance().getString(PreferenceUtils.USERINFO),
                    UserInfoModel.class);
        }
        if (MyApplication.DEBUG) {
            if (userInfoModel == null) {
                userInfoModel = new UserInfoModel();
                DataBean dataBean = new DataBean();
                userInfoModel.setData(dataBean);
            }
            userInfoModel.getData().setToken("DB3500E754DD3CF7221334BE57A2DBE7");

        }
        return userInfoModel == null || userInfoModel.getData() == null ? "" : userInfoModel.getData().getToken();
    }

    public String getIcon() {
        if (userInfoModel == null || userInfoModel.getData() == null || userInfoModel.getData().getUser() == null) {
            return MyApplication.DEBUG ? "http://img.zcool.cn/community/017bdc59420a3ea8012193a344b26a.png" : "";
        }
        return userInfoModel.getData().getUser().getHeadImg();
    }

    public String getName() {
        if (userInfoModel == null || userInfoModel.getData() == null || userInfoModel.getData().getUser() == null) {
            return MyApplication.DEBUG ? "剑指天下" : "";
        }
        return userInfoModel.getData().getUser().getName();
    }

    public int getMb() {
        if (userInfoModel == null || userInfoModel.getData() == null || userInfoModel.getData().getWallet() == null) {
            return 0;
        }
        return userInfoModel.getData().getWallet().getMb();
    }

//    public void changeMb(int change) {
//        if (userInfoModel == null) {
//            userInfoModel = new UserInfoModel();
//        }
//        if (userInfoModel.getData() == null) {
//            userInfoModel.setData(new DataBean());
//        }
//        if (userInfoModel.getData().getWallet() == null) {
//            userInfoModel.getData().setWallet(new WalletBean());
//        }
//        userInfoModel.getData().getWallet().setMb(userInfoModel.getData().getWallet().getMb() + change);
//        EventBus.getDefault().post(new GlobalMsg(true, AppIndentify.UPDATE_USERINFO, null));
//    }

    public String getInvertCode() {
        if (userInfoModel == null || userInfoModel.getData() == null || userInfoModel.getData().getWallet() == null) {
            return "";
        }
        return userInfoModel.getData().getUser().getInviteCode();
    }


    public void setUserinfo(UserInfoModel userinfo) {
        this.userInfoModel = userinfo;
        PreferenceUtils.getInstance().saveString(PreferenceUtils.USERINFO, JSON.toJSONString(userInfoModel));
        EventBus.getDefault().post(new GlobalMsg(true, AppIndentify.UPDATE_USERINFO, null));
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
