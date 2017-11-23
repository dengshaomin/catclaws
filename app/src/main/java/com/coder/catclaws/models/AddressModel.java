package com.coder.catclaws.models;

import java.util.List;

/**
 * Created by dengshaomin on 2017/11/16.
 */

public class AddressModel {


    /**
     * msg : 获取自己的收获地址成功！
     * code : 200
     * data : {"content":[{"version":0,"disable":false,"id":2,"province":"浙江省","city":"阜阳市","area":"名山区","address":"xx街道xx小区xx房间","usually":true,"userId":1,"phone":"161728761587"},{"version":0,"disable":false,"id":1,"province":"浙江省","city":"杭州市","area":"萧山区","address":"xx街道xx小区xx房间","usually":false,"userId":1,"phone":"161728761587"}],"totalElements":2,"totalPages":1,"last":true,"number":0,"size":20,"sort":null,"first":true,"numberOfElements":2}
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
         * content : [{"version":0,"disable":false,"id":2,"province":"浙江省","city":"阜阳市","area":"名山区","address":"xx街道xx小区xx房间","usually":true,"userId":1,"phone":"161728761587"},{"version":0,"disable":false,"id":1,"province":"浙江省","city":"杭州市","area":"萧山区","address":"xx街道xx小区xx房间","usually":false,"userId":1,"phone":"161728761587"}]
         * totalElements : 2
         * totalPages : 1
         * last : true
         * number : 0
         * size : 20
         * sort : null
         * first : true
         * numberOfElements : 2
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

        public static class ContentBean extends MineSerializable{

            /**
             * version : 0
             * disable : false
             * id : 2
             * province : 浙江省
             * city : 阜阳市
             * area : 名山区
             * address : xx街道xx小区xx房间
             * usually : true
             * userId : 1
             * phone : 161728761587
             */

            private int version;

            private boolean disable;

            private int id;

            private String province;

            private String city;

            private String area;

            private String addre;

            private boolean usually;

            private int userId;

            private String phone;

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getAddre() {
                return addre;
            }

            public void setAddre(String addre) {
                this.addre = addre;
            }

            public boolean isUsually() {
                return usually;
            }

            public void setUsually(boolean usually) {
                this.usually = usually;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
