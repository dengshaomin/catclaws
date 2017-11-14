package com.coder.catclaws.models;

/**
 * Created by dengshaomin on 2017/11/13.
 */

public class ALiOrderModel  {

    /**
     * msg : 获取支付宝sign成功！
     * code : 200
     * data : charset=utf-8&biz_content=%7B%22timeout_express%22%3A%2230m%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22total_amount%22%3A%220%22%2C%22subject%22%3A%22%E7%8C%AB%E5%B8%81%E5%85%85%E5%80%BC%22%2C%22body%22%3Anull%2C%22out_trade_no%22%3A%228df8603f33bc4e8c82a2e150f5f2fb25%22%7D&method=alipay.trade.app.pay&app_id=2017110309705757&sign_type=RSA&version=1.0&timestamp=2016-07-29+16%3A55%3A53&sign=Rxdg6fEUJ0jB6mtOaYpsUgY41nqVSK%2FCjrzdtwx0LmuEpGroYbkrnbhLoVpAse06wt%2FvOFEAFIfyDqs%2F1Ns95dSD6tQzW9GRuzHkSLGCnVJWgvAah3UmCS3fE9ZOG6hV3hsqolUgX9yF6Xmkj9yjhJt75YKlVo1NdcVJsOZYWYz4na%2B9%2FE3jymifRCpB9xJ62iQ8ojv5XTRedJTyZox%2FrMdytO4J9E3TrcxzOm405IjMlv5ogjaIj5AvQKj%2Bb8palmrAOclmA%2By6jYnUa%2Bfj1Rprgbn75CSm%2FeJEyLtSkN8HmOEki4N4oypZdjVLtFAu6GHdl3TIfGAtm9MFThwQFw%3D%3D
     */

    private String msg;
    private int code;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
