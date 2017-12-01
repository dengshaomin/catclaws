package com.coder.catclaws.utils;


import com.coder.catclaws.models.MineDollModel;

public class StateUtil {

    public static final String Deposit = "寄存中";

    public static final String WaitSending = "待发货";

    public static final String HasSending = "已发货";

    public static final String HasReceive = "已收货";

    public static final String Finish = "兑换成功";


    public static String getState(MineDollModel.DataEntity.ContentEntity gift) {
        int state = gift.getState();
        int result = gift.getResult();

        if (state == 1) {
            return Deposit;
        }
        if (state == 2 && result == 3 && gift.getTransportCompany() == null) {
            return WaitSending;
        }

        if (state == 2 && result == 3 && gift.getTransportCompany() != null) {
            return HasSending;
        }

        if (state == 2 && result == 1 && gift.getTransportCompany() != null) {
            return HasReceive;
        }

        if (state == 3 && result == 1) {
            return Finish;
        }

        return Deposit;

    }

}
