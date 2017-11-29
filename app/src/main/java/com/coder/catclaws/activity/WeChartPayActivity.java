package com.coder.catclaws.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.github.lazylibrary.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import me.weyye.hipermission.PermissonItem;

public class WeChartPayActivity extends Activity {

    public static final int RESULT_CODE = 99;

    WebView webview;

    private boolean goWechart;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chart_pay);
        initView();
    }

    public void initView() {
//        webview.setWebViewClient(new CustomWebViewClient(webview.getWebView()) {
//            @Override
//            public String onPageError(String url) {
//                return null;
//            }
//
//            @NonNull
//            @Override
//            public Map<String, String> onPageHeaders(String url) {
//
//                if (url == null) return null;
////
//                try {
//                    if (url.startsWith("weixin://") || url.startsWith("alipays://") ||
//                            url.startsWith("mailto://") || url.startsWith("tel://")
//                        //其他自定义的scheme
//                            ) {
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                        startActivity(intent);
//                    }
//                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
//                }
//                return  null;
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
////                String url = (String) getBunleData();
////                if (url == null) return true;
//////
////                try {
////                    if (url.startsWith("weixin://") || url.startsWith("alipays://") ||
////                            url.startsWith("mailto://") || url.startsWith("tel://")
////                        //其他自定义的scheme
////                            ) {
////                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
////                        startActivity(intent);
////                        return true;
////                    }
////                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
////                }
//                return super.shouldOverrideUrlLoading(view, request);
//            }
//        });
        webview = findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null) {
                    return true;
                }
                try {
                    if (url.startsWith("weixin://") || url.startsWith("alipays://") ||
                            url.startsWith("mailto://") || url.startsWith("tel://")
                        //其他自定义的scheme
                            ) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        setResult(RESULT_CODE);
                        finish();
                        return true;
                    }
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                    String description, String failingUrl) {
                // TODO Auto-generated method stub
                super.onReceivedError(view, errorCode, description, failingUrl);
                ToastUtils.showToast(WeChartPayActivity.this, "支付失败~");
//                setResult(RESULT_CODE);
                finish();
            }
        });
        webview.loadUrl(getBunleData() + "");
    }

    public Object getBunleData() {
        if (getIntent() != null) {
            return getIntent().getSerializableExtra(this.getClass().getName());
        }
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        webview.stopLoading();
        webview.removeAllViews();
        webview.destroy();
        webview = null;
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
