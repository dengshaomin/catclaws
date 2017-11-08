package com.coder.catclaws.widgets;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.coder.catclaws.R;
import com.coder.catclaws.adapters.HomePagerAdapter;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.models.HomeModel;
import com.tmall.ultraviewpager.Screen;
import com.coder.catclaws.utils.ViewSize;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.DensityUtil;

import java.util.List;

import butterknife.BindView;

/**
 * Created by dengshaomin on 2017/11/8.
 */

public class HomeViewPager extends BaseLayout {
    @BindView(R.id.viewpager)
    UltraViewPager viewpager;
    private HomePagerAdapter homePagerAdapter;
    private List<HomeModel.DataBean.BannersBean> bannersBeans;

    public HomeViewPager(Context context) {
        super(context);
    }

    public HomeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.home_view_pager;
    }

    @Override
    public void initView() {
        ViewSize.fixedSize(viewpager, (int) (Screen.getWidth(getmContext()) * 5f / 7f), 450f / 780f);
    }

    public class ScalePageTransformer implements ViewPager.PageTransformer {

        public static final float MAX_SCALE = 1.0f;
        public static final float MIN_SCALE = 0.8f;
        public static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View page, float position) {

            if (position < -1) {
                position = -1;
            } else if (position > 1) {
                position = 1;
            }

            float tempScale = position < 0 ? 1 + position : 1 - position;

            float slope = (MAX_SCALE - MIN_SCALE) / 1;
            //一个公式
            float scaleValue = MIN_SCALE + tempScale * slope;
            page.setScaleX(scaleValue);
            page.setScaleY(scaleValue);
            if (scaleValue != 1) {
                page.setAlpha(MIN_ALPHA + (scaleValue - MIN_SCALE)
                        / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            }
        }
    }

    @Override
    public void initBundleData() {

    }

    @Override
    public void getNetData() {

    }

    @Override
    public List<String> regeistEvent() {
        return null;
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {

    }

    @Override
    public void setViewData(Object data) {
        if (data == null) return;
        bannersBeans = (List<HomeModel.DataBean.BannersBean>) data;
        if (bannersBeans == null || bannersBeans.size() == 0) return;
        if (homePagerAdapter == null) {
            homePagerAdapter = new HomePagerAdapter(getContext(), bannersBeans);
            viewpager.setAdapter(homePagerAdapter);
            viewpager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
            viewpager.setOffscreenPageLimit(5);
            viewpager.setPageMargin(DensityUtil.dip2px(getmContext(), -10));
            viewpager.setPageTransformer(true, new ScalePageTransformer());
//            viewpager.setInfiniteRatio(100);
            viewpager.setInfiniteLoop(true);
            viewpager.setAutoScroll(3000);
            viewpager.initIndicator();
            viewpager.getIndicator().setOrientation(UltraViewPager.Orientation.HORIZONTAL);
            viewpager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
            viewpager.getIndicator().setFocusColor(Color.WHITE).setNormalColor(R.color.bannerSelect)
                    .setStrokeColor(Color.GREEN)
                    .setRadius(DensityUtil.dip2px(getmContext(), 5))
                    .setiWidth(DensityUtil.dip2px(getmContext(), 20))
                    .setiHeight(DensityUtil.dip2px(getmContext(), 5))
                    .setipadding(DensityUtil.dip2px(getmContext(), 5))
                    .setiselectWidth(DensityUtil.dip2px(getmContext(), 50)).build();
        } else {
            homePagerAdapter.updateData(bannersBeans);
        }
    }
}
