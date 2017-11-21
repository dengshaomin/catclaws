package com.coder.catclaws.commons;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by dengshaomin on 2017/11/7.
 */

public class ImageLoader {
    public static ImageLoader imageLoader = null;

    public static ImageLoader getInstance() {
        if (imageLoader == null) {
            synchronized (ImageLoader.class) {
                if (imageLoader == null) {
                    imageLoader = new ImageLoader();
                }
            }
        }
        return imageLoader;
    }

    public void loadImage(SimpleDraweeView simpleDraweeView, String url) {
        SimpleDraweeView mSimpleDraweeView = simpleDraweeView;
        if (mSimpleDraweeView == null) return;
        if (TextUtils.isEmpty(url)) return;
        if (url.endsWith(".gif") || url.endsWith(".webp")) {
            DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                    .setAutoPlayAnimations(true)
                    .setUri(Uri.parse(url))//设置uri
                    .build();
            mSimpleDraweeView.setController(mDraweeController);
        } else {
            mSimpleDraweeView.setImageURI(url);
        }
    }
    public void loadImage(Context context, String imageUrl, BaseBitmapDataSubscriber target) {
        if (TextUtils.isEmpty(imageUrl)) return;
        if (target == null) return;
        ImageRequest imageRequest = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(imageUrl))
                .setProgressiveRenderingEnabled(true)
                .build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>>
                dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);
        dataSource.subscribe(target, CallerThreadExecutor.getInstance());
    }
}
