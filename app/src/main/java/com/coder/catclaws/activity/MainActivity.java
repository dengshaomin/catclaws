package com.coder.catclaws.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.UserManager;
import com.coder.catclaws.models.HomeModel;
import com.coder.catclaws.models.RoomModel;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.utils.StaticUtils;
import com.coder.catclaws.widgets.HomeItemDecoration;
import com.coder.catclaws.widgets.HomeViewPager;
import com.coder.catclaws.widgets.codexrefreshview.CodeRecycleView;
import com.coder.catclaws.widgets.codexrefreshview.CommonAdapter;
import com.coder.catclaws.widgets.codexrefreshview.MultiItemTypeAdapter;
import com.coder.catclaws.widgets.codexrefreshview.ViewHolder;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.github.lazylibrary.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import me.weyye.hipermission.PermissonItem;

public class MainActivity extends BaseActivity {


    @BindView(R.id.icon)
    SimpleDraweeView icon;

    @BindView(R.id.home_left_image)
    RelativeLayout homeLeftImage;

    @BindView(R.id.home_right_image)
    RelativeLayout homeRightImage;

    HomeViewPager homeViewPager;

    @BindView(R.id.codeRecycleView)
    CodeRecycleView codeRecycleView;

    @BindView(R.id.title_lay)
    LinearLayout titleLay;

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
        codeRecycleView.setRefreshMode(CodeRecycleView.START);
        codeRecycleView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                getNetData();
            }

            @Override
            public void onLoadMore(boolean isSilence, int index) {
            }
        });

        ImageLoader.getInstance().loadImage(icon, UserManager.getInstance().getIcon());
    }


    @Override
    public void initBundleData() {

    }

    @Override
    public void getNetData() {
        Net.request(NetIndentify.HOME, null);
    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {{
            add(NetIndentify.HOME);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        if (globalMsg.isSuccess()) {
            homeModel = (HomeModel) globalMsg.getMsg();
            if (homeModel != null && homeModel.getData() != null) {
                if (homeViewPager == null) {
                    homeViewPager = new HomeViewPager(MainActivity.this);
                }
                homeViewPager.setViewData(homeModel.getData().getBanners());
                if (commonAdapter == null) {
                    commonAdapter = new CommonAdapter<HomeModel.DataBean.RoomsBean.ContentBean>(MainActivity.this, R
                            .layout.goods_item,
                            homeModel.getData().getRooms().getContent()) {
                        @Override
                        protected void convert(ViewHolder holder, HomeModel.DataBean.RoomsBean.ContentBean contentBean, int position) {
                            View rootView = holder.itemView;
//                            ViewSize.fixedSize(rootView, (Screen.getWidth(MainActivity.this) - DensityUtil.dip2px
//                                    (MainActivity.this, 4) - DensityUtil.dip2px
//                                    (MainActivity.this, 20)) / 2, 632f / 468f);
                            final HomeModel.DataBean.RoomsBean.ContentBean finalContentBean = homeModel.getData().getRooms().getContent().get(position);
                            SimpleDraweeView image = holder.getView(R.id.image);
                            TextView desc = holder.getView(R.id.desc);
                            TextView statu = holder.getView(R.id.statu);
                            TextView num = holder.getView(R.id.num);
                            final SimpleDraweeView name = holder.getView(R.id.name);
                            if (finalContentBean == null) {
                                return;
                            }
                            ImageLoader.getInstance().loadImage(MainActivity.this, finalContentBean.getNameImg(),
                                    new BaseBitmapDataSubscriber() {
                                        @Override
                                        protected void onNewResultImpl(Bitmap bitmap) {
                                            ViewGroup.LayoutParams layoutParams = name.getLayoutParams();
                                            layoutParams.width = bitmap.getWidth();
                                            layoutParams.height = bitmap.getHeight();
                                            name.setLayoutParams(layoutParams);
                                            ImageLoader.getInstance().loadImage(name, finalContentBean.getNameImg()
                                            );
                                        }

                                        @Override
                                        protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                                        }
                                    });
                            ImageLoader.getInstance().loadImage(image, finalContentBean.getPhoto());
                            desc.setText(finalContentBean.getIntroduce());
                            statu.setText(finalContentBean.isCanUse() ? "空闲" : "游戏中");
                            statu.setCompoundDrawablesWithIntrinsicBounds(finalContentBean.isCanUse() ? R.drawable.icon_room_free : R.drawable.icon_room_busy,
                                    0, 0, 0);
                            num.setText(finalContentBean.getPrice() + "");
                        }
                    };
                    codeRecycleView.setAdapter(commonAdapter);
                    codeRecycleView.addHeaderView(homeViewPager);
                    codeRecycleView.refreshComplete(CodeRecycleView.SUCCESS);
                    codeRecycleView.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            PageJump.goRoomActivity(MainActivity.this, homeModel.getData().getRooms().getContent()
                                    .get(position));
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
            }
        } else {
            codeRecycleView.refreshComplete(CodeRecycleView.ERROR);
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
    public boolean needDanMu() {
        return false;
    }

    @Override
    public List<PermissonItem> needPermissions() {
        return null;
    }


    @OnClick({R.id.home_left_image, R.id.home_right_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_left_image:
                PageJump.goMineInfoActivity(MainActivity.this);
                break;
            case R.id.home_right_image:
                PageJump.goMineDollActivity(MainActivity.this);
//                PageJump.goDetailActivity(this, null);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        StaticUtils.clear();
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > 2000) {
                ToastUtils.showToast(MainActivity.this, "再按一次退出程序~");
                firstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @OnClick(R.id.title_lay)
    public void onViewClicked() {
    }
}
