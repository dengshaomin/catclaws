package com.coder.catclaws;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;

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
        initFresco();
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