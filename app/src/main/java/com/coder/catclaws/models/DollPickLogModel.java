package com.coder.catclaws.models;

import java.util.List;

import com.coder.catclaws.models.MineDollModel.DataEntity.ContentEntity.GoodEntity;

/**
 * Created by dengshaomin on 2017/11/22.
 */

public class DollPickLogModel {

    private String msg;

    private int code;

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * version : 0
         * disable : false
         * id : 248
         * userId : 1
         * authorizationCode : 2138d67051bf450592f5c08965c7eb49
         * goodId : 1
         * playTime : 1511318615000
         * result : false
         * waWaJiId : 22
         */

        private int version;

        private boolean disable;

        private int id;

        private int userId;

        private String authorizationCode;

        private int goodId;

        private long playTime;

        private boolean result;

        private int waWaJiId;

        public GoodEntity getGood() {
            return good;
        }

        public void setGood(GoodEntity good) {
            this.good = good;
        }

        private GoodEntity good;


        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public boolean isDisable() {
            return disable;
        }

        public void setDisable(boolean disable) {
            this.disable = disable;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getAuthorizationCode() {
            return authorizationCode;
        }

        public void setAuthorizationCode(String authorizationCode) {
            this.authorizationCode = authorizationCode;
        }

        public int getGoodId() {
            return goodId;
        }

        public void setGoodId(int goodId) {
            this.goodId = goodId;
        }

        public long getPlayTime() {
            return playTime;
        }

        public void setPlayTime(long playTime) {
            this.playTime = playTime;
        }

        public boolean isResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public int getWaWaJiId() {
            return waWaJiId;
        }

        public void setWaWaJiId(int waWaJiId) {
            this.waWaJiId = waWaJiId;
        }
    }
}
