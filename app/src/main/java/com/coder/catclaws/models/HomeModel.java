package com.coder.catclaws.models;

import java.util.List;

/**
 * Created by dengshaomin on 2017/11/8.
 */

public class HomeModel {

    /**
     * msg :
     * code : 200
     * data : {"banners":[{"version":0,"disable":false,"id":1,"img":"http://static.iboom.tv/static/app/img/login/LOGO.png","text":"我是一个banneer","type":"web","action":"www.baidu.com","od":1,"scene":"home"},{"version":0,"disable":false,"id":2,"img":"http://static.iboom.tv/static/img/2fb8b579a405e9265024b6201ccc27c5.jpg","text":"我是一个banneer","type":"web","action":"www.baidu.com","od":1,"scene":"home"},{"version":0,"disable":false,"id":3,"img":"http://static.iboom.tv/static/img/d76655e4e8d608983cf07bcd20cb24a9.jpg","text":"我是一个banneer","type":"web","action":"www.baidu.com","od":1,"scene":"home"}],"rooms":{"content":[{"version":0,"disable":false,"id":1,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":2,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":3,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":4,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":5,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":6,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":7,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":8,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":9,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":10,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":11,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":12,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":13,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":14,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":15,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":16,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":17,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":18,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":19,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":20,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false}],"totalElements":22,"totalPages":2,"last":false,"number":0,"size":20,"sort":null,"first":true,"numberOfElements":20}}
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
         * banners : [{"version":0,"disable":false,"id":1,"img":"http://static.iboom.tv/static/app/img/login/LOGO.png","text":"我是一个banneer","type":"web","action":"www.baidu.com","od":1,"scene":"home"},{"version":0,"disable":false,"id":2,"img":"http://static.iboom.tv/static/img/2fb8b579a405e9265024b6201ccc27c5.jpg","text":"我是一个banneer","type":"web","action":"www.baidu.com","od":1,"scene":"home"},{"version":0,"disable":false,"id":3,"img":"http://static.iboom.tv/static/img/d76655e4e8d608983cf07bcd20cb24a9.jpg","text":"我是一个banneer","type":"web","action":"www.baidu.com","od":1,"scene":"home"}]
         * rooms : {"content":[{"version":0,"disable":false,"id":1,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":2,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":3,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":4,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":5,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":6,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":7,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":8,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":9,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":10,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":11,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":12,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":13,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":14,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":15,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":16,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":17,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":18,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":19,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":20,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false}],"totalElements":22,"totalPages":2,"last":false,"number":0,"size":20,"sort":null,"first":true,"numberOfElements":20}
         */

        private RoomsBean rooms;
        private List<BannersBean> banners;

        public RoomsBean getRooms() {
            return rooms;
        }

        public void setRooms(RoomsBean rooms) {
            this.rooms = rooms;
        }

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public static class RoomsBean {
            /**
             * content : [{"version":0,"disable":false,"id":1,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":2,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":3,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":4,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":5,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":6,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":7,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":8,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":9,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":10,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":11,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":12,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":13,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":14,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":15,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":16,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":17,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":18,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":19,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false},{"version":0,"disable":false,"id":20,"name":"第一台娃娃机","photo":"http://static.iboom.tv/static/app/img/login/LOGO.png","ip":"192.168.0.100","introduce":"第一台娃娃机介绍","od":0,"activity":true,"activityName":"限时特购","activityImg":"http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png","firstCamera":"rtmp://live2.iboom.tv/AppName/Stream","secondCamera":"rtmp://live2.iboom.tv/AppName/Stream","price":19,"canUse":false}]
             * totalElements : 22
             * totalPages : 2
             * last : false
             * number : 0
             * size : 20
             * sort : null
             * first : true
             * numberOfElements : 20
             */

            private int totalElements;
            private int totalPages;
            private boolean last;
            private int number;
            private int size;
            private Object sort;
            private boolean first;
            private int numberOfElements;
            private List<ContentBean> content;

            public int getTotalElements() {
                return totalElements;
            }

            public void setTotalElements(int totalElements) {
                this.totalElements = totalElements;
            }

            public int getTotalPages() {
                return totalPages;
            }

            public void setTotalPages(int totalPages) {
                this.totalPages = totalPages;
            }

            public boolean isLast() {
                return last;
            }

            public void setLast(boolean last) {
                this.last = last;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
            }

            public boolean isFirst() {
                return first;
            }

            public void setFirst(boolean first) {
                this.first = first;
            }

            public int getNumberOfElements() {
                return numberOfElements;
            }

            public void setNumberOfElements(int numberOfElements) {
                this.numberOfElements = numberOfElements;
            }

            public List<ContentBean> getContent() {
                return content;
            }

            public void setContent(List<ContentBean> content) {
                this.content = content;
            }

            public static class ContentBean {
                /**
                 * version : 0
                 * disable : false
                 * id : 1
                 * name : 第一台娃娃机
                 * photo : http://static.iboom.tv/static/app/img/login/LOGO.png
                 * ip : 192.168.0.100
                 * introduce : 第一台娃娃机介绍
                 * od : 0
                 * activity : true
                 * activityName : 限时特购
                 * activityImg : http://gate.school.iboom.tv/app/static/images/icon/ic_weixin_00.png
                 * firstCamera : rtmp://live2.iboom.tv/AppName/Stream
                 * secondCamera : rtmp://live2.iboom.tv/AppName/Stream
                 * price : 19
                 * canUse : false
                 */

                private int version;
                private boolean disable;
                private int id;
                private String name;
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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
                }

                public String getIp() {
                    return ip;
                }

                public void setIp(String ip) {
                    this.ip = ip;
                }

                public String getIntroduce() {
                    return introduce;
                }

                public void setIntroduce(String introduce) {
                    this.introduce = introduce;
                }

                public int getOd() {
                    return od;
                }

                public void setOd(int od) {
                    this.od = od;
                }

                public boolean isActivity() {
                    return activity;
                }

                public void setActivity(boolean activity) {
                    this.activity = activity;
                }

                public String getActivityName() {
                    return activityName;
                }

                public void setActivityName(String activityName) {
                    this.activityName = activityName;
                }

                public String getActivityImg() {
                    return activityImg;
                }

                public void setActivityImg(String activityImg) {
                    this.activityImg = activityImg;
                }

                public String getFirstCamera() {
                    return firstCamera;
                }

                public void setFirstCamera(String firstCamera) {
                    this.firstCamera = firstCamera;
                }

                public String getSecondCamera() {
                    return secondCamera;
                }

                public void setSecondCamera(String secondCamera) {
                    this.secondCamera = secondCamera;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public boolean isCanUse() {
                    return canUse;
                }

                public void setCanUse(boolean canUse) {
                    this.canUse = canUse;
                }
            }
        }

        public static class BannersBean {
            /**
             * version : 0
             * disable : false
             * id : 1
             * img : http://static.iboom.tv/static/app/img/login/LOGO.png
             * text : 我是一个banneer
             * type : web
             * action : www.baidu.com
             * od : 1
             * scene : home
             */

            private int version;
            private boolean disable;
            private int id;
            private String img;
            private String text;
            private String type;
            private String action;
            private int od;
            private String scene;

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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public int getOd() {
                return od;
            }

            public void setOd(int od) {
                this.od = od;
            }

            public String getScene() {
                return scene;
            }

            public void setScene(String scene) {
                this.scene = scene;
            }
        }
    }
}
