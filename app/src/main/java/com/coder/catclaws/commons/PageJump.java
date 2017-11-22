package com.coder.catclaws.commons;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.coder.catclaws.activity.AddressActivity;
import com.coder.catclaws.activity.ContactActivity;
import com.coder.catclaws.activity.DeliverGoodsActivity;
import com.coder.catclaws.activity.DetailActivity;
import com.coder.catclaws.activity.InvertFriendActivity;
import com.coder.catclaws.activity.LoginActivity;
import com.coder.catclaws.activity.MainActivity;
import com.coder.catclaws.activity.MessageActivity;
import com.coder.catclaws.activity.MineDollActivity;
import com.coder.catclaws.activity.MineInfoActivity;
import com.coder.catclaws.activity.RechargeActivity;
import com.coder.catclaws.activity.RoomActivity;
import com.coder.catclaws.activity.WeChartPayActivity;
import com.coder.catclaws.models.HomeModel;
import com.coder.catclaws.models.MineDollModel.DataEntity.ContentEntity;

/**
 * Created by dengshaomin on 2017/11/9.
 */

public class PageJump {

    public static void goContactActivity(Context context) {
        context.startActivity(new Intent(context, ContactActivity.class));
    }
    public static void goMineDollActivity(Context context) {
        context.startActivity(new Intent(context, MineDollActivity.class));
    }

    public static void goMainActivity(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    public static void goLoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public static void goMineInfoActivity(Context context) {
        context.startActivity(new Intent(context, MineInfoActivity.class));
    }

    public static void goRechargeActivity(Context context) {
        context.startActivity(new Intent(context, RechargeActivity.class));
    }

    public static void goInvertFriendActivity(Context context) {
        context.startActivity(new Intent(context, InvertFriendActivity.class));
    }
    public static void goAddressActivity(Context context) {
        context.startActivity(new Intent(context, AddressActivity.class));
    }
    public static void goMessageActivity(Context context) {
        context.startActivity(new Intent(context, MessageActivity.class));
    }
    public static void goDeliverGoodsActivity(Context context) {
        context.startActivity(new Intent(context, DeliverGoodsActivity.class));
    }

    public static void goDetailActivity(Context context, ContentEntity contentEntity) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.class.getName(), contentEntity);
        context.startActivity(intent);
    }

    public static void goWeChartPayActivity(Activity context, String url) {
        Intent intent = new Intent(context, WeChartPayActivity.class);
        intent.putExtra(WeChartPayActivity.class.getName(), url);
        context.startActivityForResult(intent, 1);
    }

    public static void goRoomActivity(Context context, HomeModel.DataBean.RoomsBean.ContentBean bean) {
        Intent intent = new Intent(context, RoomActivity.class);
        intent.putExtra(RoomActivity.class.getName(), bean);
        context.startActivity(intent);
    }
}
