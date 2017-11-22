package com.coder.catclaws.widgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.IRoomSecondHead;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.Tools;
import com.coder.catclaws.models.DollPickLogModel;
import com.coder.catclaws.models.DollPickLogModel.DataBean;
import com.coder.catclaws.models.MineDollModel.DataEntity;
import com.coder.catclaws.models.MineDollModel.DataEntity.ContentEntity.GoodEntity;
import com.coder.catclaws.models.RoomModel.WaWaJiEntity;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.utils.ViewSize;
import com.coder.catclaws.widgets.codexrefreshview.CodeRecycleView;
import com.coder.catclaws.widgets.codexrefreshview.CommonAdapter;
import com.coder.catclaws.widgets.codexrefreshview.MultiItemTypeAdapter;
import com.coder.catclaws.widgets.codexrefreshview.ViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.lazylibrary.util.DensityUtil;
import com.tmall.ultraviewpager.Screen;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/21.
 */

public class RoomSecondPageView extends BaseLayout {


    @BindView(R.id.doll_detail_img)
    SimpleDraweeView dollDetailImg;

    @BindView(R.id.codeRecycleView)
    RecyclerView codeRecycleView;

    @BindView(R.id.roomSecondPageTitle)
    RoomSecondPageTitle mRoomSecondPageTitle;

    private WaWaJiEntity mWaWaJiEntity;

    private DollPickLogModel mDollPickLogModel;

    private LogAdapter mLogAdapter;

    public RoomSecondPageView(Context context) {
        super(context);
    }

    public RoomSecondPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoomSecondPageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.room_secondpage_view;
    }

    @Override
    public void initView() {
        mRoomSecondPageTitle.setiRoomSecondHead(new IRoomSecondHead() {
            @Override
            public void selecdChange(int index) {
                dollDetailImg.setVisibility(index == 0 ? VISIBLE : GONE);
                codeRecycleView.setVisibility(index == 1 ? VISIBLE : GONE);
                if (index == 1 && mDollPickLogModel == null) {
                    Net.request(NetIndentify.DOLL_PICL_LOG, new HashMap<String, String>() {{
                        put("wawajiId", mWaWaJiEntity.getId() + "");
                    }});
                }
            }
        });
//        ViewSize.setSize(dollDetailImg, Screen.getWidth(getmContext()), Screen.getHeight(getContext()) - DensityUtil.dip2px(getmContext(), 52));
//        ViewSize.setSize(codeRecycleView, Screen.getWidth(getmContext()), Screen.getHeight(getContext()) - DensityUtil.dip2px(getmContext(), 52));
        codeRecycleView.setLayoutManager(new LinearLayoutManager(getmContext()));
    }

    @Override
    public void initBundleData() {

    }

    @Override
    public void getNetData() {

    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {{
            add(NetIndentify.DOLL_PICL_LOG);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        if (globalMsg.isSuccess()) {
            mDollPickLogModel = (DollPickLogModel) globalMsg.getMsg();
            if (mDollPickLogModel != null && mDollPickLogModel.getData() != null) {
                if (mLogAdapter == null) {
                    mLogAdapter = new LogAdapter();
                    codeRecycleView.setAdapter(mLogAdapter);
                } else {
                    mLogAdapter.notifyDataSetChanged();
                }
            }
        } else {

        }
    }

    @Override
    public void setViewData(Object data) {
        mWaWaJiEntity = (WaWaJiEntity) data;
        if (mWaWaJiEntity != null) {
            ImageLoader.getInstance().loadImage(dollDetailImg, mWaWaJiEntity.getPhoto());
        }
    }

    class LogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.doll_pick_log_item, parent, false);
            return new MineViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            DataBean contentBean = mDollPickLogModel.getData().get(position);
            View rootView = holder.itemView;
//                            ViewSize.fixedSize(rootView, (Screen.getWidth(MainActivity.this) - DensityUtil.dip2px
//                                    (MainActivity.this, 4) - DensityUtil.dip2px
//                                    (MainActivity.this, 20)) / 2, 632f / 468f);
            SimpleDraweeView image = rootView.findViewById(R.id.image);
            TextView name = rootView.findViewById(R.id.name);
            TextView date = rootView.findViewById(R.id.date);
            if (contentBean == null || contentBean.getGood() == null) {
                return;
            }
            ImageLoader.getInstance().loadImage(image, contentBean.getGood().getPhoto());
            name.setText(contentBean.getGood().getName());
            date.setText(Tools.getTimeStr(contentBean.getPlayTime()));
        }

        @Override
        public int getItemCount() {
            return mDollPickLogModel == null || mDollPickLogModel.getData() == null ? 0 : mDollPickLogModel.getData().size();
        }
    }

    class MineViewHolder extends RecyclerView.ViewHolder {

        public MineViewHolder(View itemView) {
            super(itemView);
        }
    }

}
