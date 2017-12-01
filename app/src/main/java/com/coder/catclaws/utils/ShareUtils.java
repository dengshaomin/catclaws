package com.coder.catclaws.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.coder.catclaws.InitService;
import com.coder.catclaws.MyApplication;
import com.coder.catclaws.R;
import com.github.lazylibrary.util.FileUtils;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.tauth.IUiListener;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by dengshaomin on 2017/11/14.
 */

public class ShareUtils {
    private static final int THUMB_SIZE = 150;
    private static final String shareClickUrl = "http://www.baidu.com";
    private static final String shareImageUrl = "http://static.iboom.tv/static/img/wawa.png";

    private static final String shareTitle = "快来和我一起在猫爪子抓娃娃吧";
    private static final String shareContent = "正版娃娃抓的到，就在猫爪子app";
    public static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static void shareToWx(boolean isLine, String title, String description) {
        String fullPath = MyApplication.applicationContext.getCacheDir().getPath() + File.separator + "share_icon.png";
        if (!new File(fullPath).exists()) {
            FileUtils.copyAssetToSDCard(MyApplication.applicationContext.getResources().getAssets(), "share_icon" +
                    ".png", fullPath);
        }
        WXImageObject imgObj = new WXImageObject();
        imgObj.setImagePath(fullPath);

        WXWebpageObject Object = new WXWebpageObject();
        Object.webpageUrl = shareClickUrl;
        WXMediaMessage msg = new WXMediaMessage(Object);
        Bitmap bmp = BitmapFactory.decodeFile(fullPath);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
        bmp.recycle();
        msg.thumbData = getBytesFromBitmap(thumbBmp);
        msg.title = shareTitle;
        msg.description = shareContent;
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = "img/text";
        req.message = msg;
        req.scene = isLine ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        if(StaticUtils.getmWxApi() != null) {
            StaticUtils.getmWxApi().sendReq(req);
        }
    }

    //分享QQ的activity要重写onactivityresult,参照loginactivity，不然收不到回调
    public static void shareToQQ(Activity activity, String title, String description, IUiListener iUiListener) {
        final Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, shareTitle);
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, shareContent);
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, shareClickUrl);
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, shareImageUrl);
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, MyApplication.applicationContext.getResources().getString(R
                .string.app_name));
        if(StaticUtils.getmTencent() != null) {
            StaticUtils.getmTencent().shareToQQ(activity, params, iUiListener);
        }
    }

//    public static void shareToQzone(Activity activity, String title, String description, IUiListener iUiListener) {
//    //分享类型
//        final Bundle params = new Bundle();
//        params.putString(QzoneShare.SHARE_TO_QQ_KEY_TYPE, SHARE_TO_QZONE_TYPE_IMAGE_TEXT);
//        params.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);//必填
//        params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, description);//选填
//        params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, shareClickUrl);//必填
//        params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, new ArrayList<String>() {{
//            add(shareImageUrl);
//        }});
//        InitService.mTencent.shareToQzone(activity, params, iUiListener);
//    }
}
