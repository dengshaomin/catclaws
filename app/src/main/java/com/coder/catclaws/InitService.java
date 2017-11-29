package com.coder.catclaws;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;

import com.alibaba.fastjson.JSON;
import com.coder.catclaws.utils.StaticUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

import java.io.IOException;

import cn.addapp.pickers.entity.Province;
import cn.addapp.pickers.util.ConvertUtils;

/**
 * Created by dengshaomin on 2017/11/7.
 */

public class InitService extends IntentService {

    private static String TAG = InitService.class.getSimpleName();

    public InitService() {
        super("InitService");
    }

    public InitService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (null == intent) {
            return;
        }
        registToWX();
        registToQQ();
//        initFresco();
        String json = null;
        try {
            json = ConvertUtils.toString(this.getAssets().open("city.json"));
            StaticUtils.getProvinces().addAll(JSON.parseArray(json, Province.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        StaticUtils.setmWxApi(WXAPIFactory.createWXAPI(this, StaticUtils.WEIXIN_APPID, false));
        // 将该app注册到微信
        if (StaticUtils.getmWxApi() != null) {
            StaticUtils.getmWxApi().registerApp(StaticUtils.WEIXIN_APPID);
        }
    }

    private void registToQQ() {
        StaticUtils.setmTencent(Tencent.createInstance(StaticUtils.QQ_APPID, this.getApplicationContext()));
    }


    private void initFresco() {
//        ProgressiveJpegConfig pjpegConfig = new ProgressiveJpegConfig() {
//            @Override
//            public int getNextScanNumberToDecode(int scanNumber) {
//                return scanNumber + 2;
//            }
//
//            public QualityInfo getQualityInfo(int scanNumber) {
//                boolean isGoodEnough = (scanNumber >= 5);
//                return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
//            }
//        };
//        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(getApplicationContext())
//                .setProgressiveJpegConfig(pjpegConfig)
//                .build();
        try {
            ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                    .setDownsampleEnabled(true)
                    .build();
            Fresco.initialize(this, config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}