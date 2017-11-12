package com.coder.catclaws.models;

import java.util.List;

/**
 * Created by Administrator on 2017/11/12.
 */

public class RechargeModel {

    /**
     * msg : 获取商品成功！
     * code : 200
     * data : [{"version":0,"disable":false,"id":1,"name":null,"img":null,"price":1,"give":100,"info":null}]
     */

    private String msg;
    private int code;
    /**
     * version : 0
     * disable : false
     * id : 1
     * name : null
     * img : null
     * price : 1
     * give : 100
     * info : null
     */

    private List<DataEntity> data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private int version;
        private boolean disable;
        private int id;
        private Object name;
        private String img;
        private int price;
        private int give;
        private Object info;

        public void setVersion(int version) {
            this.version = version;
        }

        public void setDisable(boolean disable) {
            this.disable = disable;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setGive(int give) {
            this.give = give;
        }

        public void setInfo(Object info) {
            this.info = info;
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

        public Object getName() {
            return name;
        }

        public String getImg() {
            return img;
        }

        public int getPrice() {
            return price;
        }

        public int getGive() {
            return give;
        }

        public Object getInfo() {
            return info;
        }
    }
}
