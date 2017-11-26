package com.coder.catclaws;

import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;

import com.alibaba.fastjson.JSON;
import com.alivc.player.AliVcMediaPlayer;
import com.aliyun.vodplayer.downloader.AliyunDownloadConfig;
import com.aliyun.vodplayer.downloader.AliyunDownloadManager;
import com.coder.catclaws.utils.StaticUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.tencent.mm.opensdk.openapi.IWXAPI;
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
        initFresco();
        initAliPlayer();
        String json = null;
        try {
            json = ConvertUtils.toString(this.getAssets().open("city.json"));
            StaticUtils.provinces.addAll(JSON.parseArray(json, Province.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        StaticUtils.mWxApi = WXAPIFactory.createWXAPI(this, StaticUtils.WEIXIN_APPID, false);
        // 将该app注册到微信
        StaticUtils.mWxApi.registerApp(StaticUtils.WEIXIN_APPID);
    }

    private void registToQQ() {
        StaticUtils.mTencent = Tencent.createInstance(StaticUtils.QQ_APPID, this.getApplicationContext());
    }

    private void initAliPlayer() {
        //初始化播放器
        final String businessId = "";
        AliVcMediaPlayer.init(getApplicationContext(), businessId);

        //设置保存密码。此密码如果更换，则之前保存的视频无法播放
        AliyunDownloadConfig config = new AliyunDownloadConfig();
        config.setSecretImagePath(Environment.getExternalStorageDirectory().getAbsolutePath() + "/aliyun/encryptedApp.dat");
//        config.setDownloadPassword("123456789");
        //设置保存路径。请确保有SD卡访问权限。
        config.setDownloadDir(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test_save/");
        //设置同时下载个数
        config.setMaxNums(2);

        AliyunDownloadManager.getInstance(this).setDownloadConfig(config);
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