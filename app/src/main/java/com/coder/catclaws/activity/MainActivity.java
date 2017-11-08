package com.coder.catclaws.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.Tools;
import com.coder.catclaws.models.HomeModel;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.utils.ViewSize;
import com.coder.catclaws.widgets.GoodsItem;
import com.coder.catclaws.widgets.HomeItemDecoration;
import com.coder.catclaws.widgets.HomeViewPager;
import com.coder.catclaws.widgets.codexrefreshview.CodeRecycleView;
import com.coder.catclaws.widgets.codexrefreshview.CommonAdapter;
import com.coder.catclaws.widgets.codexrefreshview.ViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmall.ultraviewpager.Screen;
import com.tmall.ultraviewpager.transformer.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.PermissonItem;

public class MainActivity extends BaseActivity {


    @BindView(R.id.icon)
    SimpleDraweeView icon;
    @BindView(R.id.home_left_image)
    RelativeLayout homeLeftImage;
    @BindView(R.id.home_right_image)
    RelativeLayout homeRightImage;
    @BindView(R.id.homeViewPager)
    HomeViewPager homeViewPager;
    @BindView(R.id.codeRecycleView)
    CodeRecycleView codeRecycleView;

    private CommonAdapter commonAdapter;

    private HomeModel homeModel;

    @Override
    public int setContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        codeRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
        codeRecycleView.addItemDecoration(new HomeItemDecoration());
        String s = Tools.getFromAssets(this, "home.txt");
        eventComming(new GlobalMsg(true, Net.HOME, JSON.parseObject(s, HomeModel.class)));
    }


    @Override
    public void initBundleData() {

    }

    @Override
    public void getNetData() {
        Net.request(NetIndentify.HOME, null, Net.HOME);
    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {{
            add(Net.HOME);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        if (globalMsg.isSuccess()) {
            homeModel = (HomeModel) globalMsg.getMsg();
            if (homeModel != null && homeModel.getData() != null) {
                homeViewPager.setViewData(homeModel.getData().getBanners());
                if (commonAdapter == null) {
                    commonAdapter = new CommonAdapter<HomeModel.DataBean.RoomsBean.ContentBean>(MainActivity.this, R
                            .layout.goods_item,
                            homeModel.getData().getRooms().getContent()) {
                        @Override
                        protected void convert(ViewHolder holder, HomeModel.DataBean.RoomsBean.ContentBean contentBean, int position) {
                            View rootView = holder.itemView;
                            ViewSize.fixedSize(rootView, (Screen.getWidth(MainActivity.this) - DensityUtil.dip2px
                                    (MainActivity.this, 4) - DensityUtil.dip2px
                                    (MainActivity.this, 20)) / 2, 632f / 468f);
                            SimpleDraweeView image = rootView.findViewById(R.id.image);
                            TextView desc = rootView.findViewById(R.id.desc);
                            TextView statu = rootView.findViewById(R.id.statu);
                            TextView num = rootView.findViewById(R.id.num);
                            TextView name = rootView.findViewById(R.id.name);
                            if (contentBean == null) return;
                            ImageLoader.getInstance().loadImage(image, contentBean.getPhoto());
                            desc.setText(contentBean.getIntroduce());
                            name.setText(contentBean.getName());
                            num.setText(contentBean.getPrice() + "");
                        }
                    };
                    codeRecycleView.setAdapter(commonAdapter);
                } else {
                    commonAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void setViewData(Object data) {

    }

    @Override
    public boolean needTitle() {
        return false;
    }

    @Override
    public int setTitleLeftImage() {
        return 0;
    }

    @Override
    public int setTitleRightImage() {
        return 0;
    }

    @Override
    public String setTitleText() {
        return null;
    }

    @Override
    public void titleLeftClick() {
    }

    @Override
    public void titleRightClick() {
    }


    @Override
    public List<PermissonItem> needPermissions() {
        return null;
    }


    @OnClick({R.id.home_left_image, R.id.home_right_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_left_image:
                break;
            case R.id.home_right_image:
                break;
        }
    }

}
