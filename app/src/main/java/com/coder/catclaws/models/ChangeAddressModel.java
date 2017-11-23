package com.coder.catclaws.models;

import com.coder.catclaws.models.AddressModel.DataBean.ContentBean;
import com.coder.catclaws.models.MineDollModel.DataEntity.ContentEntity;

/**
 * Created by dengshaomin on 2017/11/23.
 */

public class ChangeAddressModel {


    /**
     * msg : 添加或者更新收货地址成功！
     * code : 200
     * data : {"version":0,"disable":false,"id":33,"province":"北京市","city":"北京市","area":"东城区","addre":"jjjx","usually":false,"userId":1,"phone":"15618996752","name":"jdjj"}
     */

    private String msg;

    private int code;

    private AddressModel.DataBean.ContentBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ContentBean getData() {
        return data;
    }

    public void setData(ContentBean data) {
        this.data = data;
    }
}
