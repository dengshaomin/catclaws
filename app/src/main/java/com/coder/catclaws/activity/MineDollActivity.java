package com.coder.catclaws.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.coder.catclaws.MyApplication;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.Tools;
import com.coder.catclaws.models.HomeModel;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.widgets.HomeItemDecoration;
import com.coder.catclaws.widgets.MineDollHeader;
import com.coder.catclaws.widgets.codexrefreshview.CodeRecycleView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.weyye.hipermission.PermissonItem;

public class MineDollActivity extends BaseActivity {

    @BindView(R.id.codeRecycleView)
    CodeRecycleView codeRecycleView;
    private int page = 1;

    @Override
    public boolean needTitle() {
        return true;
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
        return "我的娃娃";
    }

    @Override
    public void titleLeftClick() {
        finish();
    }

    @Override
    public void titleRightClick() {

    }

    @Override
    public int setContentLayout() {
        return R.layout.activity_mine_doll;
    }

    @Override
    public void initView() {
        codeRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
        codeRecycleView.addItemDecoration(new HomeItemDecoration());
        codeRecycleView.setRefreshMode(CodeRecycleView.BOTH);
        codeRecycleView.addHeaderView(new MineDollHeader(this));
        codeRecycleView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                page = 1;
                getNetData();
            }

            @Override
            public void onLoadMore(boolean isSilence, int index) {
                page = index;
                getNetData();
            }
        });
        if (MyApplication.DEBUG) {
            eventComming(new GlobalMsg(true, NetIndentify.MINEDOLL, JSON.parseObject(Tools.getFromAssets(this, "home.txt")
                    , HomeModel
                            .class)));
        }
    }

    @Override
    public void initBundleData() {

    }

    @Override
    public void getNetData() {
        Net.request(NetIndentify.MINEDOLL, new HashMap<String, String>() {{
            put("page", page + "");
            put("size", CodeRecycleView.pageSize + "");
        }});
    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {{
            add(NetIndentify.MINEDOLL);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {

    }

    @Override
    public void setViewData(Object data) {

    }

    @Override
    public List<PermissonItem> needPermissions() {
        return null;
    }

}
