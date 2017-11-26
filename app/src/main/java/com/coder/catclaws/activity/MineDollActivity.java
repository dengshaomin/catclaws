package com.coder.catclaws.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.Tools;
import com.coder.catclaws.models.MineDollModel;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.widgets.MineDollHeader;
import com.coder.catclaws.widgets.MineDollItemDecoration;
import com.coder.catclaws.widgets.codexrefreshview.CodeRecycleView;
import com.coder.catclaws.widgets.codexrefreshview.CommonAdapter;
import com.coder.catclaws.widgets.codexrefreshview.MultiItemTypeAdapter;
import com.coder.catclaws.widgets.codexrefreshview.ViewHolder;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

import butterknife.BindView;
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
        codeRecycleView.addItemDecoration(new MineDollItemDecoration());
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
//        if (MyApplication.DEBUG) {
//            eventComming(new GlobalMsg(true, NetIndentify.MINEDOLL, JSON.parseObject(Tools.getFromAssets(this, "home.txt")
//                    , HomeModel
//                            .class)));
//        }
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
                        protected void convert(ViewHolder holder, final MineDollModel.DataEntity.ContentEntity contentBean, int position) {
                            View rootView = holder.itemView;
                            SimpleDraweeView image = rootView.findViewById(R.id.image);
                            TextView statu = rootView.findViewById(R.id.statu);
                            TextView date = rootView.findViewById(R.id.date);
                            final SimpleDraweeView name = rootView.findViewById(R.id.name);
                            if (contentBean == null) {
                                return;
                            }
                            date.setText(Tools.getTimeStr(contentBean.getGetTime()));
                            statu.setText(Tools.getDollState(contentBean));
                            if (contentBean.getGood() != null) {
                                MineDollModel.DataEntity.ContentEntity.GoodEntity goodEntity = contentBean.getGood();
                                ImageLoader.getInstance().loadImage(image, goodEntity.getPhoto());
                                ImageLoader.getInstance().loadImage(MineDollActivity.this, contentBean.getGood().getNameImg(),
                                        new
                                                BaseBitmapDataSubscriber() {
                                                    @Override
                                                    protected void onNewResultImpl(Bitmap bitmap) {
                                                        ViewGroup.LayoutParams layoutParams = name.getLayoutParams();
                                                        layoutParams.width = bitmap.getWidth();
                                                        layoutParams.height = bitmap.getHeight();
                                                        name.setLayoutParams(layoutParams);
                                                        ImageLoader.getInstance().loadImage(name, contentBean.getGood()
                                                                .getNameImg());
                                                    }

                                                    @Override
                                                    protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                                                    }
                                                });
                            }
                        }
                    };
                    codeRecycleView.setAdapter(commonAdapter);
                    if (mineDollHeader == null) {
                        mineDollHeader = new MineDollHeader(this);
                    }
                    mineDollHeader.setViewData(mineDollModel.getData().getTotalElements());
                    codeRecycleView.addHeaderView(mineDollHeader);
                    codeRecycleView.refreshComplete(CodeRecycleView.SUCCESS);
                    codeRecycleView.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            PageJump.goDetailActivity(MineDollActivity.this, mineDollModel.getData().getContent().get(position)
                            );
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
