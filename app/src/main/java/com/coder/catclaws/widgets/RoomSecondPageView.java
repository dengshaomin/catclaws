package com.coder.catclaws.widgets;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.IRoomSecondHead;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.Tools;
import com.coder.catclaws.models.DollPickLogModel;
import com.coder.catclaws.models.RoomModel;
import com.coder.catclaws.models.RoomModel.WaWaJiEntity;
import com.coder.catclaws.models.UserInfoModel;
import com.coder.catclaws.utils.Net;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tmall.ultraviewpager.transformer.DensityUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/21.
 */

public class RoomSecondPageView extends BaseLayout {


    @BindView(R.id.codeRecycleView)
    RecyclerView codeRecycleView;

    @BindView(R.id.roomSecondPageTitle)
    RoomSecondPageTitle mRoomSecondPageTitle;
    //    @BindView(R.id.imageRecycleView)
//    RecyclerView imageRecycleView;
    @BindView(R.id.photosContainer)
    LinearLayout photosContainer;

    private WaWaJiEntity mWaWaJiEntity;

    private RoomModel roomModel;
    private DollPickLogModel mDollPickLogModel;

    private LogAdapter mLogAdapter;

    private Imagedapter imagedapter;

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
                photosContainer.setVisibility(index == 0 ? VISIBLE : GONE);
                codeRecycleView.setVisibility(index == 1 ? VISIBLE : GONE);
                if (index == 1 && mDollPickLogModel == null && mWaWaJiEntity != null) {
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
        roomModel = (RoomModel) data;
        if (roomModel != null && roomModel.getWaWaJi() != null) {
            mWaWaJiEntity = roomModel.getWaWaJi();
        }
        if (roomModel != null && roomModel.getGood() != null && roomModel.getGood().getGoodPhotos() != null) {
//            if (imagedapter == null) {
//                imagedapter = new Imagedapter();
//                imageRecycleView.setAdapter(imagedapter);
//            } else {
//                imagedapter.notifyDataSetChanged();
//            }
            photosContainer.removeAllViews();
            for (RoomModel.GoodBean.GoodPhotosEntity goodPhotosEntity : roomModel.getGood().getGoodPhotos()) {
                View view = LayoutInflater.from(getmContext()).inflate(R.layout.room_imagelist_item, null);
                SimpleDraweeView image = view.findViewById(R.id.image);
                ImageLoader.getInstance().loadImage(image, goodPhotosEntity.getPhoto());
                photosContainer.addView(view,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        DensityUtil.dip2px(getmContext(),300)));
            }
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
            UserInfoModel.DataBean.UserBean contentBean = mDollPickLogModel.getData().get(position).getUser();
            View rootView = holder.itemView;
//                            ViewSize.fixedSize(rootView, (Screen.getWidth(MainActivity.this) - DensityUtil.dip2px
//                                    (MainActivity.this, 4) - DensityUtil.dip2px
//                                    (MainActivity.this, 20)) / 2, 632f / 468f);
            SimpleDraweeView image = rootView.findViewById(R.id.image);
            TextView name = rootView.findViewById(R.id.name);
            TextView date = rootView.findViewById(R.id.date);
            if (contentBean == null) {
                return;
            }
            ImageLoader.getInstance().loadImage(image, contentBean.getHeadImg());
            name.setText(contentBean.getName());
            date.setText(Tools.getTimeStr(mDollPickLogModel.getData().get(position).getPlayTime()));
        }

        @Override
        public int getItemCount() {
            return mDollPickLogModel == null || mDollPickLogModel.getData() == null ? 0 : mDollPickLogModel.getData().size();
        }
    }

    class Imagedapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.room_imagelist_item, parent, false);
            return new MineViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            RoomModel.GoodBean.GoodPhotosEntity contentBean = roomModel.getGood().getGoodPhotos().get(position);
            View rootView = holder.itemView;
//                            ViewSize.fixedSize(rootView, (Screen.getWidth(MainActivity.this) - DensityUtil.dip2px
//                                    (MainActivity.this, 4) - DensityUtil.dip2px
//                                    (MainActivity.this, 20)) / 2, 632f / 468f);
            SimpleDraweeView image = rootView.findViewById(R.id.image);
            if (contentBean == null) {
                return;
            }
            ImageLoader.getInstance().loadImage(image, contentBean.getPhoto());
        }

        @Override
        public int getItemCount() {
            return roomModel == null || roomModel.getGood() == null || roomModel.getGood().getGoodPhotos() == null ?
                    0 : roomModel.getGood().getGoodPhotos().size();
        }
    }

    class MineViewHolder extends RecyclerView.ViewHolder {

        public MineViewHolder(View itemView) {
            super(itemView);
        }
    }

}
