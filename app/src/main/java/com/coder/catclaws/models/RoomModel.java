package com.coder.catclaws.models;

import java.util.List;

/**
 * Created by Administrator on 2017/11/11.
 */

public class RoomModel {

    /**
     * player : null
     * watcher : [{"version":0,"disable":false,"id":1,"phone":null,"createTime":1510279827000,"openId":"o2ICJ0cY5sUNFzTRNLMkTxCTcHhk","qqId":null,"name":"你的益达","headImg":"/0","sex":"0","inviteCode":null}]
     * totalWatcher : 1
     * waWaJi : {"version":0,"disable":false,"id":22,"name":"第一台娃娃机","nameImg":"http://static.iboom.tv/static/app/img/login/LOGO.png","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.1.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/StreamName","secondCamera":"rtmp://live2.iboom.tv/AppName/StreamName","price":19,"canUse":false,"goodId":1}
     * canPlay : true
     */

    private UserInfoModel.DataBean player;
    private int totalWatcher;
    /**
     * version : 0
     * disable : false
     * id : 22
     * name : 第一台娃娃机
     * nameImg : http://static.iboom.tv/static/app/img/login/LOGO.png
     * photo : http://static.iboom.tv/static/app/img/login/LOGO.png
     * ip : 192.168.1.100
     * introduce : 第一台娃娃机介绍
     * od : 0
     * activity : true
     * activityName : 限时特购
     * activityImg : http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png
     * firstCamera : rtmp://live2.iboom.tv/AppName/StreamName
     * secondCamera : rtmp://live2.iboom.tv/AppName/StreamName
     * price : 19
     * canUse : false
     * goodId : 1
     */

    private WaWaJiEntity waWaJi;
    private boolean canPlay;
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

    private List<UserInfoModel.DataBean> watcher;


    public void setTotalWatcher(int totalWatcher) {
        this.totalWatcher = totalWatcher;
    }

    public void setWaWaJi(WaWaJiEntity waWaJi) {
        this.waWaJi = waWaJi;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }


    public UserInfoModel.DataBean getPlayer() {
        return player;
    }

    public void setPlayer(UserInfoModel.DataBean player) {
        this.player = player;
    }

    public List<UserInfoModel.DataBean> getWatcher() {
        return watcher;
    }

    public void setWatcher(List<UserInfoModel.DataBean> watcher) {
        this.watcher = watcher;
    }

    public int getTotalWatcher() {
        return totalWatcher;
    }

    public WaWaJiEntity getWaWaJi() {
        return waWaJi;
    }

    public boolean isCanPlay() {
        return canPlay;
    }


    public static class WaWaJiEntity {
        private int version;
        private boolean disable;
        private int id;
        private String name;
        private String nameImg;
        private String photo;
        private String ip;
        private String introduce;
        private int od;
        private boolean activity;
        private String activityName;
        private String activityImg;
        private String firstCamera;
        private String secondCamera;
        private int price;
        private boolean canUse;
        private int goodId;

        public void setVersion(int version) {
            this.version = version;
        }

        public void setDisable(boolean disable) {
            this.disable = disable;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNameImg(String nameImg) {
            this.nameImg = nameImg;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public void setOd(int od) {
            this.od = od;
        }

        public void setActivity(boolean activity) {
            this.activity = activity;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public void setActivityImg(String activityImg) {
            this.activityImg = activityImg;
        }

        public void setFirstCamera(String firstCamera) {
            this.firstCamera = firstCamera;
        }

        public void setSecondCamera(String secondCamera) {
            this.secondCamera = secondCamera;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setCanUse(boolean canUse) {
            this.canUse = canUse;
        }

        public void setGoodId(int goodId) {
            this.goodId = goodId;
        }

        public int getVersion() {
            return version;
        }

        public boolean isDisable() {
            return disable;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getNameImg() {
            return nameImg;
        }

        public String getPhoto() {
            return photo;
        }

        public String getIp() {
            return ip;
        }

        public String getIntroduce() {
            return introduce;
        }

        public int getOd() {
            return od;
        }

        public boolean isActivity() {
            return activity;
        }

        public String getActivityName() {
            return activityName;
        }

        public String getActivityImg() {
            return activityImg;
        }

        public String getFirstCamera() {
            return firstCamera;
        }

        public String getSecondCamera() {
            return secondCamera;
        }

        public int getPrice() {
            return price;
        }

        public boolean isCanUse() {
            return canUse;
        }

        public int getGoodId() {
            return goodId;
        }
    }

    public static class WatcherEntity {
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
        private Object inviteCode;

        public void setVersion(int version) {
            this.version = version;
        }

        public void setDisable(boolean disable) {
            this.disable = disable;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public void setQqId(Object qqId) {
            this.qqId = qqId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public void setInviteCode(Object inviteCode) {
            this.inviteCode = inviteCode;
        }

        public int getVersion() {
            return version;
        }

        public boolean isDisable() {
            return disable;
        }

        public int getId() {
            return id;
        }

        public Object getPhone() {
            return phone;
        }

        public long getCreateTime() {
            return createTime;
        }

        public String getOpenId() {
            return openId;
        }

        public Object getQqId() {
            return qqId;
        }

        public String getName() {
            return name;
        }

        public String getHeadImg() {
            return headImg;
        }

        public String getSex() {
            return sex;
        }

        public Object getInviteCode() {
            return inviteCode;
        }
    }
}
