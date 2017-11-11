package com.boom.util;

/**
 * Created by zhumingming on 2017/11/8.
 */
public class DataUtil {


    public static final String bytesToHexString(byte[] buffer) {
        StringBuffer sb = new StringBuffer(buffer.length);
        String temp;
        for (int i = 0; i < buffer.length; ++i) {
            temp = Integer.toHexString(0xff & buffer[i]);
            if (temp.length() < 2)
                sb.append(0);

            sb.append(temp);
        }
        return sb.toString();
    }

}
