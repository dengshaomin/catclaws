package com.coder.catclaws.models;

/**
 * Created by dengshaomin on 2017/11/21.
 */

public class SubmitQuestionModel {


    /**
     * msg : 邀请处理成功！
     * code : 200
     * data : {"version":0,"disable":false,"id":1,"introduce":"画面黑屏或定个格","playCode":"","playTime":1511254641534,"userId":1}
     */

    private String msg;

    private int code;

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * version : 0
         * disable : false
         * id : 1
         * introduce : 画面黑屏或定个格
         * playCode :
         * playTime : 1511254641534
         * userId : 1
         */

        private int version;

        private boolean disable;

        private int id;

        private String introduce;

        private String playCode;

        private long playTime;

        private int userId;

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

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getPlayCode() {
            return playCode;
        }

        public void setPlayCode(String playCode) {
            this.playCode = playCode;
        }

        public long getPlayTime() {
            return playTime;
        }

        public void setPlayTime(long playTime) {
            this.playTime = playTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
