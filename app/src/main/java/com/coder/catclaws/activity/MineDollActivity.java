package com.coder.catclaws.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.XRefreshView;
import com.coder.catclaws.MyApplication;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.Tools;
import com.coder.catclaws.models.HomeModel;
import com.coder.catclaws.models.MineDollModel;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.utils.ViewSize;
import com.coder.catclaws.widgets.HomeItemDecoration;
import com.coder.catclaws.widgets.MineDollHeader;
import com.coder.catclaws.widgets.codexrefreshview.CodeRecycleView;
import com.coder.catclaws.widgets.codexrefreshview.CommonAdapter;
import com.coder.catclaws.widgets.codexrefreshview.MultiItemTypeAdapter;
import com.coder.catclaws.widgets.codexrefreshview.ViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmall.ultraviewpager.Screen;
import com.tmall.ultraviewpager.transformer.DensityUtil;

import java.text.SimpleDateFormat;
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
    private MineDollModel mineDollModel;
    private CommonAdapter commonAdapter;
    private MineDollHeader mineDollHeader;

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
        if (globalMsg.isSuccess()) {
            mineDollModel = (MineDollModel) globalMsg.getMsg();
            if (mineDollModel != null && mineDollModel.getData().getContent() != null) {
                if (commonAdapter == null) {
                    commonAdapter = new CommonAdapter<MineDollModel.DataEntity.ContentEntity>(MineDollActivity.this, R
                            .layout.mine_doll_item,
                            mineDollModel.getData().getContent()) {
                        @Override
                        protected void convert(ViewHolder holder, MineDollModel.DataEntity.ContentEntity contentBean, int position) {
                            View rootView = holder.itemView;
                            SimpleDraweeView image = rootView.findViewById(R.id.image);
                            TextView statu = rootView.findViewById(R.id.statu);
                            TextView date = rootView.findViewById(R.id.date);
                            SimpleDraweeView name = rootView.findViewById(R.id.name);
                            if (contentBean == null) return;
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.mm.dd kk:mm");
                            date.setText(dateFormat.format(contentBean.getGetTime()));
                            if (contentBean.getState() == 1) {
                                statu.setText("寄存中");
                            } else if (contentBean.getState() == 2) {
                                statu.setText("配送中");
                            } else if (contentBean.getState() == 3) {
                                statu.setText("已兑换");
                            }
                            if (contentBean.getResult() == 1) {
                                statu.setText("已签收");
                            }
                            if (contentBean.getGood() != null) {
                                MineDollModel.DataEntity.ContentEntity.GoodEntity goodEntity = contentBean.getGood();
                                ImageLoader.getInstance().loadImage(image, goodEntity.getPhoto());
//            name.settext
                            }
                        }
                    };
                    codeRecycleView.setAdapter(commonAdapter);
                    if (mineDollHeader == null) {
                        mineDollHeader = new MineDollHeader(this);
                    }
                    mineDollHeader.setViewData(mineDollModel.getData().getNumber());
                    codeRecycleView.addHeaderView(mineDollHeader);
                    codeRecycleView.refreshComplete(CodeRecycleView.SUCCESS);
                    codeRecycleView.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
//                            PageJump.goRoomActivity(MineDollActivity.this, homeModel.getData().getRooms().getContent()
//                                    .get(position));
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                } else {
                    commonAdapter.notifyDataSetChanged();
                    codeRecycleView.refreshComplete(CodeRecycleView.SUCCESS);
                }
            } else {
                codeRecycleView.refreshComplete(CodeRecycleView.ERROR);
            }
        } else {
            codeRecycleView.refreshComplete(CodeRecycleView.ERROR);
        }
    }

    @Override
    public void setViewData(Object data) {

    }

    @Override
    public List<PermissonItem> needPermissions() {
        return null;
    }

}
