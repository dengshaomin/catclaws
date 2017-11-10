package com.coder.catclaws;

import android.app.IntentService;
import android.content.Intent;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;

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

    public static IWXAPI mWxApi;
    public static Tencent mTencent;
    public static final String WEIXIN_APPID = "wxfef5d9bc369124d5";
    public static final String WEIXIN_SECRET = "b661d6232d8858bc962798202b3e9850";
    public static final String QQ_APPID = "101441608";

    @Override
    protected void onHandleIntent(Intent intent) {
        if (null == intent) {
            return;
        }
        registToWX();
        registToQQ();
        initFresco();
    }

    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mWxApi = WXAPIFactory.createWXAPI(this, WEIXIN_APPID, false);
        // 将该app注册到微信
        mWxApi.registerApp(WEIXIN_APPID);
    }

    private void registToQQ() {
        mTencent = Tencent.createInstance(QQ_APPID, this.getApplicationContext());
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
        Fresco.initialize(this);
    }

}