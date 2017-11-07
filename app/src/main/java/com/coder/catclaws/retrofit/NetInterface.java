package com.coder.catclaws.retrofit;

/**
 * Created by dengshaomin on 2016/11/30.
 */
public interface NetInterface {
    void onSuccess(int indentify, String code, String response);

    void onError(int indentify, String code, String msg);
}
