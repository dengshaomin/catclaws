package com.coder.catclaws.commons;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import com.coder.catclaws.models.MineDollModel;

/**
 * Created by dengshaomin on 2017/11/8.
 */

public class Tools {

    public static String getFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null) {
                Result += line;
            }
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTimeStr(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY.MM.dd kk:mm");
        return dateFormat.format(time);
    }

    public static String getDollState(MineDollModel.DataEntity.ContentEntity contentBean) {
        if (contentBean.getState() == 1) {
            return "寄存中";
        } else if (contentBean.getState() == 2) {
            return "配送中";
        } else if (contentBean.getState() == 3) {
            return "已兑换";
        }
        if (contentBean.getResult() == 1) {
            return "已签收";
        }
        return "寄存中";
    }
}
