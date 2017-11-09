package com.coder.catclaws.commons;


import com.coder.catclaws.models.ThirdLoginModel;

/**
 * Created by dengshaomin on 2017/11/9.
 */

public class UserManager {
    private static UserManager userManager;

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

    public ThirdLoginModel getUserinfo() {
        return thirdLoginModel;
    }

    public void setUserinfo(ThirdLoginModel thirdLoginModel) {
        this.thirdLoginModel = thirdLoginModel;
    }
}
