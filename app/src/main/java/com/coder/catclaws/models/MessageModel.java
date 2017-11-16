package com.coder.catclaws.models;

import java.util.List;

import android.text.TextUtils;
import android.view.TextureView;

/**
 * Created by dengshaomin on 2017/11/16.
 */

public class MessageModel {


    /**
     * msg : 获取自己的消息成功！
     * code : 200
     * data : {"content":[{"version":0,"disable":false,"id":4,"userId":1,"type":-1,"origin":0,"msg":"后台2娃娃自动兑换提醒"},{"version":0,"disable":false,"id":3,"userId":1,"type":2,"origin":1,"msg":"系统自动发送2娃娃自动兑换提醒"},{"version":0,"disable":false,"id":2,"userId":1,"type":1,"origin":1,"msg":"系统自动发送1邀请成功提醒"},{"version":0,"disable":false,"id":1,"userId":1,"type":0,"origin":1,"msg":"系统自动发送0:发货提醒"}],"totalElements":4,"totalPages":1,"last":true,"number":0,"size":20,"sort":null,"first":true,"numberOfElements":4}
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
         * content : [{"version":0,"disable":false,"id":4,"userId":1,"type":-1,"origin":0,"msg":"后台2娃娃自动兑换提醒"},{"version":0,"disable":false,"id":3,"userId":1,"type":2,"origin":1,"msg":"系统自动发送2娃娃自动兑换提醒"},{"version":0,"disable":false,"id":2,"userId":1,"type":1,"origin":1,"msg":"系统自动发送1邀请成功提醒"},{"version":0,"disable":false,"id":1,"userId":1,"type":0,"origin":1,"msg":"系统自动发送0:发货提醒"}]
         * totalElements : 4
         * totalPages : 1
         * last : true
         * number : 0
         * size : 20
         * sort : null
         * first : true
         * numberOfElements : 4
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
             * id : 4
             * userId : 1
             * type : -1
             * origin : 0
             * msg : 后台2娃娃自动兑换提醒
             */

            private long addTime;

            private String title;

            private int version;

            private boolean disable;

            private int id;

            private int userId;

            private int type;

            private int origin;

            private String msg;

            public long getAddTime() {
                return addTime;
            }

            public void setAddTime(long addTime) {
                this.addTime = addTime;
            }

            public String getTitle() {

                return TextUtils.isEmpty(title) ? "" : title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

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

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getOrigin() {
                return origin;
            }

            public void setOrigin(int origin) {
                this.origin = origin;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }
        }
    }
}
