package com.coder.catclaws.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/11.
 */

public class MineDollModel {

    /**
     * msg : 获取抓中的娃娃成功！
     * code : 200
     * data : {"content":[{"version":0,"disable":false,"id":15,"userId":1,"goodId":1,"getTime":1510306148000,"playRecordId":51,"state":1,"addressId":null,"result":null,"transportCode":null,"good":{"version":0,"disable":false,"id":1,"name":"小熊","photo":"//img.alicdn.com/bao/uploaded/TB1G0CYXJbJ8KJjy1zjSuuqapXa.jpg_100x100q90.jpg_.webp"},"address":null},{"version":0,"disable":false,"id":14,"userId":1,"goodId":1,"getTime":1510306075000,"playRecordId":50,"state":1,"addressId":null,"result":null,"transportCode":null,"good":{"version":0,"disable":false,"id":1,"name":"小熊","photo":"//img.alicdn.com/bao/uploaded/TB1G0CYXJbJ8KJjy1zjSuuqapXa.jpg_100x100q90.jpg_.webp"},"address":null},{"version":0,"disable":false,"id":13,"userId":1,"goodId":1,"getTime":1510298106000,"playRecordId":46,"state":1,"addressId":null,"result":null,"transportCode":null,"good":{"version":0,"disable":false,"id":1,"name":"小熊","photo":"//img.alicdn.com/bao/uploaded/TB1G0CYXJbJ8KJjy1zjSuuqapXa.jpg_100x100q90.jpg_.webp"},"address":null}],"totalElements":13,"totalPages":2,"last":true,"number":1,"size":10,"sort":null,"first":false,"numberOfElements":3}
     */

    private String msg;
    private int code;
    /**
     * content : [{"version":0,"disable":false,"id":15,"userId":1,"goodId":1,"getTime":1510306148000,"playRecordId":51,"state":1,"addressId":null,"result":null,"transportCode":null,"good":{"version":0,"disable":false,"id":1,"name":"小熊","photo":"//img.alicdn.com/bao/uploaded/TB1G0CYXJbJ8KJjy1zjSuuqapXa.jpg_100x100q90.jpg_.webp"},"address":null},{"version":0,"disable":false,"id":14,"userId":1,"goodId":1,"getTime":1510306075000,"playRecordId":50,"state":1,"addressId":null,"result":null,"transportCode":null,"good":{"version":0,"disable":false,"id":1,"name":"小熊","photo":"//img.alicdn.com/bao/uploaded/TB1G0CYXJbJ8KJjy1zjSuuqapXa.jpg_100x100q90.jpg_.webp"},"address":null},{"version":0,"disable":false,"id":13,"userId":1,"goodId":1,"getTime":1510298106000,"playRecordId":46,"state":1,"addressId":null,"result":null,"transportCode":null,"good":{"version":0,"disable":false,"id":1,"name":"小熊","photo":"//img.alicdn.com/bao/uploaded/TB1G0CYXJbJ8KJjy1zjSuuqapXa.jpg_100x100q90.jpg_.webp"},"address":null}]
     * totalElements : 13
     * totalPages : 2
     * last : true
     * number : 1
     * size : 10
     * sort : null
     * first : false
     * numberOfElements : 3
     */

    private DataEntity data;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private int totalElements;
        private int totalPages;
        private boolean last;
        private int number;
        private int size;
        private Object sort;
        private boolean first;
        private int numberOfElements;
        /**
         * version : 0
         * disable : false
         * id : 15
         * userId : 1
         * goodId : 1
         * getTime : 1510306148000
         * playRecordId : 51
         * state : 1
         * addressId : null
         * result : null
         * transportCode : null
         * good : {"version":0,"disable":false,"id":1,"name":"小熊","photo":"//img.alicdn.com/bao/uploaded/TB1G0CYXJbJ8KJjy1zjSuuqapXa.jpg_100x100q90.jpg_.webp"}
         * address : null
         */

        private List<ContentEntity> content;

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public void setContent(List<ContentEntity> content) {
            this.content = content;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public boolean isLast() {
            return last;
        }

        public int getNumber() {
            return number;
        }

        public int getSize() {
            return size;
        }

        public Object getSort() {
            return sort;
        }

        public boolean isFirst() {
            return first;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public List<ContentEntity> getContent() {
            return content;
        }

        public static class ContentEntity extends MineSerializable {
            private int version;
            private boolean disable;
            private int id;
            private int userId;
            private int goodId;
            private long getTime;
            private int playRecordId;
            private int state;
            private String addressId;
            private int transportCode;
            private String address;
            private int result;

            public String getAddressId() {
                return addressId;
            }

            public void setAddressId(String addressId) {
                this.addressId = addressId;
            }

            public int getTransportCode() {
                return transportCode;
            }

            public void setTransportCode(int transportCode) {
                this.transportCode = transportCode;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            /**
             * version : 0
             * disable : false
             * id : 1
             * name : 小熊
             * photo : //img.alicdn.com/bao/uploaded/TB1G0CYXJbJ8KJjy1zjSuuqapXa.jpg_100x100q90.jpg_.webp
             */

            private GoodEntity good;


            public void setVersion(int version) {
                this.version = version;
            }

            public void setDisable(boolean disable) {
                this.disable = disable;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public void setGoodId(int goodId) {
                this.goodId = goodId;
            }

            public void setGetTime(long getTime) {
                this.getTime = getTime;
            }

            public void setPlayRecordId(int playRecordId) {
                this.playRecordId = playRecordId;
            }

            public void setState(int state) {
                this.state = state;
            }


            public void setResult(int result) {
                this.result = result;
            }


            public void setGood(GoodEntity good) {
                this.good = good;
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

            public int getUserId() {
                return userId;
            }

            public int getGoodId() {
                return goodId;
            }

            public long getGetTime() {
                return getTime;
            }

            public int getPlayRecordId() {
                return playRecordId;
            }

            public int getState() {
                return state;
            }


            public int getResult() {
                return result;
            }


            public GoodEntity getGood() {
                return good;
            }


            public static class GoodEntity extends MineSerializable{
                private int version;
                private boolean disable;
                private int id;
                private String name;
                private String photo;
                private int mb;
                private String nameImg;

                public String getNameImg() {
                    return nameImg;
                }

                public void setNameImg(String nameImg) {
                    this.nameImg = nameImg;
                }

                public int getMb() {
                    return mb;
                }

                public void setMb(int mb) {
                    this.mb = mb;
                }

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

                public void setPhoto(String photo) {
                    this.photo = photo;
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

                public String getPhoto() {
                    return photo;
                }
            }
        }
    }
}
