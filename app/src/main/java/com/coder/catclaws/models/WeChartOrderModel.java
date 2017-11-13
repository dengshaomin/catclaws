package com.coder.catclaws.models;

/**
 * Created by dengshaomin on 2017/11/13.
 */

public class WeChartOrderModel {

    /**
     * msg : 创建订单！
     * code : 200
     * data : {"retCode":"0","cpCode":"17110601","cpTranNo":"509459e13c224e03bf3438a908f5202a","payMoney":1,"payInfo":"https://statecheck.swiftpass.cn/pay/wappay?token_id=2e2afcd692b227d8a5629712978689eb7&service=pay.weixin.wappayv2"}
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
         * retCode : 0
         * cpCode : 17110601
         * cpTranNo : 509459e13c224e03bf3438a908f5202a
         * payMoney : 1
         * payInfo : https://statecheck.swiftpass.cn/pay/wappay?token_id=2e2afcd692b227d8a5629712978689eb7&service=pay.weixin.wappayv2
         */

        private String retCode;
        private String cpCode;
        private String cpTranNo;
        private int payMoney;
        private String payInfo;

        public String getRetCode() {
            return retCode;
        }

        public void setRetCode(String retCode) {
            this.retCode = retCode;
        }

        public String getCpCode() {
            return cpCode;
        }

        public void setCpCode(String cpCode) {
            this.cpCode = cpCode;
        }

        public String getCpTranNo() {
            return cpTranNo;
        }

        public void setCpTranNo(String cpTranNo) {
            this.cpTranNo = cpTranNo;
        }

        public int getPayMoney() {
            return payMoney;
        }

        public void setPayMoney(int payMoney) {
            this.payMoney = payMoney;
        }

        public String getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }
    }
}
