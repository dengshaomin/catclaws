package com.coder.catclaws.models;

/**
 * Created by dengshaomin on 2017/11/10.
 */

public class UserInfoModel {


    /**
     * msg : 登陆成功！
     * code : 200
     * data : {"user":{"version":0,"disable":false,"id":1,"phone":null,"createTime":1510279827000,"openId":"o2ICJ0cY5sUNFzTRNLMkTxCTcHhk","qqId":null,"name":"你的益达","headImg":"/0","sex":"0","inviteCode":null},"wallet":{"version":0,"disable":false,"id":1,"userId":1,"mb":8347,"money":0}}
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
         * user : {"version":0,"disable":false,"id":1,"phone":null,"createTime":1510279827000,"openId":"o2ICJ0cY5sUNFzTRNLMkTxCTcHhk","qqId":null,"name":"你的益达","headImg":"/0","sex":"0","inviteCode":null}
         * wallet : {"version":0,"disable":false,"id":1,"userId":1,"mb":8347,"money":0}
         */

        private UserBean user;
        private WalletBean wallet;
        private String ccId;

        public String getCcId() {
            return ccId;
        }

        public void setCcId(String ccId) {
            this.ccId = ccId;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public WalletBean getWallet() {
            return wallet;
        }

        public void setWallet(WalletBean wallet) {
            this.wallet = wallet;
        }

        public static class UserBean {
            /**
             * version : 0
             * disable : false
             * id : 1
             * phone : null
             * createTime : 1510279827000
             * openId : o2ICJ0cY5sUNFzTRNLMkTxCTcHhk
             * qqId : null
             * name : 你的益达
             * headImg : /0
             * sex : 0
             * inviteCode : null
             */

            private int version;
            private boolean disable;
            private int id;
            private Object phone;
            private long createTime;
            private String openId;
            private Object qqId;
            private String name;
            private String headImg;
            private String sex;
            private String inviteCode;

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

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getOpenId() {
                return openId;
            }

            public void setOpenId(String openId) {
                this.openId = openId;
            }

            public Object getQqId() {
                return qqId;
            }

            public void setQqId(Object qqId) {
                this.qqId = qqId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getInviteCode() {
                return inviteCode;
            }

            public void setInviteCode(String inviteCode) {
                this.inviteCode = inviteCode;
            }
        }

        public static class WalletBean {
            /**
             * version : 0
             * disable : false
             * id : 1
             * userId : 1
             * mb : 8347
             * money : 0.0
             */

            private int version;
            private boolean disable;
            private int id;
            private int userId;
            private int mb;
            private double money;

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

            public int getMb() {
                return mb;
            }

            public void setMb(int mb) {
                this.mb = mb;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }
        }
    }
}
