package com.coder.catclaws.retrofit;

/**
 * Created by dengshaomin on 2016/11/30.
 */
public interface NetInterface {
    void onSuccess(String indentify, String code, String response);

    void onError(String indentify, String code, String msg);
}
