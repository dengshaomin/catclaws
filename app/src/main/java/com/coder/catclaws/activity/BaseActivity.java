package com.coder.catclaws.activity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.IBaseLayout;
import com.coder.catclaws.commons.ITitle;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.danmu.BiliDanmukuParser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.BaseCacheStuffer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.model.android.SpannedCacheStuffer;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import master.flame.danmaku.danmaku.util.IOUtils;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * Created by dengshaomin on 2017/11/7.
 */

public abstract class BaseActivity extends PermissionActivity implements IBaseLayout, ITitle {

    LinearLayout container;

    View left_image, right_image;

    TextView title_text;

    public DanmakuView base_danmu_view;

    private List<String> eventList = new ArrayList<>();

    private Unbinder unbinder;

    private int maxLine = 2;

    private BaseDanmakuParser mParser;//解析器对象

    private DanmakuContext mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_base);
        initTitle();
        initDanMu();
        unbinder = ButterKnife.bind(this);
        List<String> events = regeistEvent();
        if (events != null) {
            eventList.addAll(events);
        }
        this.initView();
        this.initBundleData();
        this.getNetData();
    }

    private void initDanMu() {
        base_danmu_view = findViewById(R.id.base_danmu_view);
        base_danmu_view.setVisibility(needDanMu() ? View.VISIBLE : View.GONE);
        if (needDanMu()) {
            createDanMuView();
        }
    }

    private void createDanMuView() {
        // 设置最大显示行数
        HashMap<Integer, Integer> maxLinesPair = new HashMap<Integer, Integer>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, maxLine); // 滚动弹幕最大显示行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<Integer, Boolean>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);
        mContext = DanmakuContext.create();
        mContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 3).setDuplicateMergingEnabled(false).setScrollSpeedFactor(1.2f)
                .setScaleTextSize(1.2f)
                .setCacheStuffer(new SpannedCacheStuffer(), mCacheStufferAdapter) // 图文混排使用SpannedCacheStuffer
//        .setCacheStuffer(new BackgroundCacheStuffer())  // 绘制背景使用BackgroundCacheStuffer
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair).setDanmakuMargin(40);
        if (base_danmu_view != null) {
            mParser = createParser(null);
            base_danmu_view.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
                @Override
                public void updateTimer(DanmakuTimer timer) {
                }

                @Override
                public void drawingFinished() {

                }

                @Override
                public void danmakuShown(BaseDanmaku danmaku) {
//                    Log.d("DFM", "danmakuShown(): text=" + danmaku.text);
                }

                @Override
                public void prepared() {
                    base_danmu_view.start();
                }
            });
//            base_danmu_view.setOnDanmakuClickListener(new IDanmakuView.OnDanmakuClickListener() {
//
//                @Override
//                public boolean onDanmakuClick(IDanmakus danmakus) {
//                    BaseDanmaku latest = danmakus.last();
//                    if (null != latest) {
//                        return true;
//                    }
//                    return false;
//                }
//
//                @Override
//                public boolean onDanmakuLongClick(IDanmakus danmakus) {
//                    return false;
//                }
//
//                @Override
//                public boolean onViewClick(IDanmakuView view) {
//                    return false;
//                }
//            });
            base_danmu_view.prepare(mParser, mContext);
            base_danmu_view.showFPS(false);
            base_danmu_view.enableDanmakuDrawingCache(true);
        }
    }


    private BaseDanmakuParser createParser(InputStream stream) {

        if (stream == null) {
            return new BaseDanmakuParser() {

                @Override
                protected Danmakus parse() {
                    return new Danmakus();
                }
            };
        }

        ILoader loader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);

        try {
            loader.load(stream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        BaseDanmakuParser parser = new BiliDanmukuParser();
        IDataSource<?> dataSource = loader.getDataSource();
        parser.load(dataSource);
        return parser;

    }

    private BaseCacheStuffer.Proxy mCacheStufferAdapter = new BaseCacheStuffer.Proxy() {

        private Drawable mDrawable;

        @Override
        public void prepareDrawing(final BaseDanmaku danmaku, boolean fromWorkerThread) {
            if (danmaku.text instanceof Spanned) { // 根据你的条件检查是否需要需要更新弹幕
                // FIXME 这里只是简单启个线程来加载远程url图片，请使用你自己的异步线程池，最好加上你的缓存池
                new Thread() {

                    @Override
                    public void run() {
                        String url = "http://www.bilibili.com/favicon.ico";
                        InputStream inputStream = null;
                        Drawable drawable = mDrawable;
                        if (drawable == null) {
                            try {
                                URLConnection urlConnection = new URL(url).openConnection();
                                inputStream = urlConnection.getInputStream();
                                drawable = BitmapDrawable.createFromStream(inputStream, "bitmap");
                                mDrawable = drawable;
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                IOUtils.closeQuietly(inputStream);
                            }
                        }
                        if (drawable != null) {
                            drawable.setBounds(0, 0, 100, 100);
                            SpannableStringBuilder spannable = createSpannable(drawable);
                            danmaku.text = spannable;
                            if (base_danmu_view != null) {
                                base_danmu_view.invalidateDanmaku(danmaku, false);
                            }
                            return;
                        }
                    }
                }.start();
            }
        }

        @Override
        public void releaseResource(BaseDanmaku danmaku) {
            // TODO 重要:清理含有ImageSpan的text中的一些占用内存的资源 例如drawable
        }
    };

    private SpannableStringBuilder createSpannable(Drawable drawable) {
        String text = "bitmap";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        ImageSpan span = new ImageSpan(drawable);//ImageSpan.ALIGN_BOTTOM);
        spannableStringBuilder.setSpan(span, 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append("图文混排");
        spannableStringBuilder.setSpan(new BackgroundColorSpan(Color.parseColor("#8A2233B1")), 0, spannableStringBuilder.length(),
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return spannableStringBuilder;
    }

    private void pause() {
        base_danmu_view.pause();
    }

    private void resume() {
        base_danmu_view.resume();
    }

    private void show() {
        base_danmu_view.show();
    }

    private void hide() {
        base_danmu_view.hide();
    }

    public void addDanmaku(String msg) {
        if (mContext == null || mContext.mDanmakuFactory == null) {
            return;
        }
        BaseDanmaku danmaku = mContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        if (danmaku == null || base_danmu_view == null) {
            return;
        }
        // for(int i=0;i<100;i++){
        // }
        danmaku.text = msg;
//        danmaku.padding = 5;
        danmaku.priority = 0;  // 可能会被各种过滤器过滤并隐藏显示
        danmaku.isLive = true; //是否直播弹幕
        danmaku.setTime(base_danmu_view.getCurrentTime() + 1200);
        danmaku.textSize = getResources().getDimensionPixelSize(R
                .dimen.text10);
        danmaku.textColor = Color.WHITE;
//        danmaku.textShadowColor = Color.WHITE;
        // danmaku.underlineColor = Color.GREEN;
//        danmaku.borderColor = Color.GREEN;
        base_danmu_view.addDanmaku(danmaku);

    }

    public boolean needDanMu() {
        return false;
    }

    private void initTitle() {
        container = findViewById(R.id.container);
        if (setContentLayout() != 0) {
            LayoutInflater.from(this).inflate(setContentLayout(), container);
        }
        findViewById(R.id.title_lay).setVisibility(needTitle() ? View.VISIBLE : View.GONE);
        if (needTitle()) {
            title_text = findViewById(R.id.title_text);
            left_image = findViewById(R.id.left_image);
            right_image = findViewById(R.id.right_image);
            left_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titleLeftClick();
                }
            });
            right_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    titleRightClick();
                }
            });
            title_text.setText(TextUtils.isEmpty(setTitleText()) ? "" : setTitleText());
            if (setTitleLeftImage() > 0) {
                left_image.setBackgroundResource(setTitleLeftImage());
            }
            if (setTitleRightImage() > 0) {
                left_image.setBackgroundResource(setTitleRightImage());
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GlobalMsg event) {
        /* Do something */
        if (event.getMsgId().equals(NetIndentify.CHAT)) {
            addDanmaku(event.getMsg() + "");
            return;
        }
        for (String s : eventList) {
            if (s.equals(event.getMsgId())) {
                eventComming(event);
                break;
            }
        }
    }

    public Object getBunleData() {
        if (getIntent() != null) {
            return getIntent().getSerializableExtra(this.getClass().getName());
        }
        return null;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
//            mWebView.goBack();// 返回前一个页面
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
        return super.onKeyDown(keyCode, event);
    }

}
