package com.coder.catclaws.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.AppIndentify;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.models.AddressModel.DataBean.ContentBean;
import com.coder.catclaws.models.MineDollModel;
import com.coder.catclaws.models.MineDollModel.DataEntity.ContentEntity;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.widgets.CommonViewHolder;
import com.coder.catclaws.widgets.DelivedSuccessDialogView;
import com.coder.catclaws.widgets.FullDialog;
import com.coder.catclaws.widgets.RechargeItemDecoration;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.github.lazylibrary.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import me.weyye.hipermission.PermissonItem;

public class DeliverGoodsActivity extends BaseActivity {


    @BindView(R.id.person)
    TextView mPerson;

    @BindView(R.id.tel_phone)
    TextView mTelPhone;

    @BindView(R.id.adress)
    TextView mAdress;

    @BindView(R.id.add)
    TextView mAdd;

    @BindView(R.id.recycleView)
    RecyclerView mRecycleView;

    @BindView(R.id.value)
    TextView mValue;

    @BindView(R.id.recharge)
    TextView mRecharge;

    @BindView(R.id.commit)
    TextView mCommit;

    private ContentEntity mContentEntity;

    private MineDollModel mMineDollModel;

    private int selectIndex = 0;

    private DeliverGoodAdapter mDeliverGoodAdapter;

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
        return "申请发货";
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
        return R.layout.activity_deliver_goods;
    }

    @Override
    public void initView() {
        mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecycleView.setNestedScrollingEnabled(false);
        mRecycleView.addItemDecoration(new RechargeItemDecoration());
    }

    @Override
    public void initBundleData() {
        mContentEntity = (ContentEntity) getBunleData();
        setAddress();
    }

    private void setAddress() {
        if (mContentEntity != null && mContentEntity.getAddress() != null) {
            ContentBean contentBean = mContentEntity.getAddress();
            mPerson.setText(contentBean.getName());
            mTelPhone.setText(contentBean.getPhone());
            mAdress.setText(contentBean.getAddre());
            mAdd.setText("修改");
        } else {
            mPerson.setText("");
            mTelPhone.setText("");
            mAdress.setText("");
            mAdd.setText("添加");
        }
    }

    @Override
    public void getNetData() {
        Net.request(NetIndentify.ALL_DEPOSIT_DOLL, new HashMap<String, String>() {{
            put("page", "1");
            put("size", "1000");
        }});
    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {{
            add(NetIndentify.ALL_DEPOSIT_DOLL);
            add(AppIndentify.UPDATE_ADDRESS);
            add(NetIndentify.DELIVER_DOLL);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        if (NetIndentify.ALL_DEPOSIT_DOLL.equals(globalMsg.getMsgId())) {
            mMineDollModel = (MineDollModel) globalMsg.getMsg();
            if (mContentEntity != null && mMineDollModel != null && mMineDollModel.getData() != null
                    && mMineDollModel.getData().getContent() != null) {
                for (int i = 0; i < mMineDollModel.getData().getContent().size(); i++) {
                    ContentEntity contentEntity = mMineDollModel.getData().getContent().get(i);
                    if (mContentEntity.getGoodId() == contentEntity.getGoodId()) {
                        selectIndex = i;
                        break;
                    }
                }
            }
            if (globalMsg.isSuccess()) {
                if (mDeliverGoodAdapter == null) {
                    mDeliverGoodAdapter = new DeliverGoodAdapter();
                    mRecycleView.setAdapter(mDeliverGoodAdapter);
                } else {
                    mDeliverGoodAdapter.notifyDataSetChanged();
                }
            }
        } else if (NetIndentify.DELIVER_DOLL.equals(globalMsg.getMsgId())) {
            closeProgressDialog();
            if (globalMsg.isSuccess()) {
                FullDialog.create(DeliverGoodsActivity.this).addContentView(new DelivedSuccessDialogView(DeliverGoodsActivity.this)).show();
            } else {
                ToastUtils.showToast(this, "申请提交失败，请重新提交！");
            }
        } else if (AppIndentify.UPDATE_ADDRESS.equals(globalMsg.getMsgId())) {
            if (globalMsg.isSuccess()) {
                if (mContentEntity == null) {
                    mContentEntity = new ContentEntity();
                }
                mContentEntity.setAddress((ContentBean) globalMsg.getMsg());
                setAddress();
            } else {
            }
        }
    }

    @Override
    public void setViewData(Object data) {

    }

    @Override
    public List<PermissonItem> needPermissions() {
        return null;
    }

    private void adressAction() {
        PageJump.goAddressActivity(this);
    }

    private void commitAction() {
        if (mContentEntity != null) {
            showProgressDialog();
            Net.request(NetIndentify.DELIVER_DOLL, new HashMap<String, String>() {{
                put("giftId", mContentEntity.getGoodId() + "");
                put("addressed", mContentEntity.getAddressId() + "");
            }});
        }
    }

    private void rechargeAction() {
        PageJump.goRechargeActivity(this);
    }

    @OnClick({R.id.add, R.id.recharge, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add:
                adressAction();
                break;
            case R.id.recharge:
                rechargeAction();
                break;
            case R.id.commit:
                commitAction();
                break;
        }
    }

    public class DeliverGoodAdapter extends RecyclerView.Adapter<CommonViewHolder> {


        @Override
        public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(DeliverGoodsActivity.this).inflate(R.layout.delived_doll_item, parent, false);
            return new CommonViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CommonViewHolder holder, final int position) {
            final ContentEntity contentEntity = mMineDollModel.getData().getContent().get(position);
            SimpleDraweeView image = holder.itemView.findViewById(R.id.image);
            final SimpleDraweeView name = holder.itemView.findViewById(R.id.name);
            View deliverdoll_item_rootview = holder.itemView.findViewById(R.id.deliverdoll_item_rootview);
            deliverdoll_item_rootview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectIndex = position;
                    notifyDataSetChanged();
                    mContentEntity = contentEntity;
                    setAddress();
                }
            });
            deliverdoll_item_rootview.setSelected(position == selectIndex);
            if (contentEntity == null || contentEntity.getGood() == null) {
                return;
            }
            ImageLoader.getInstance().loadImage(DeliverGoodsActivity.this, contentEntity.getGood().getNameImg(),
                    new BaseBitmapDataSubscriber() {
                        @Override
                        protected void onNewResultImpl(Bitmap bitmap) {
                            ViewGroup.LayoutParams layoutParams = name.getLayoutParams();
                            layoutParams.width = bitmap.getWidth();
                            layoutParams.height = bitmap.getHeight();
                            name.setLayoutParams(layoutParams);
                            ImageLoader.getInstance().loadImage(name, contentEntity.getGood().getNameImg()
                            );
                        }

                        @Override
                        protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                        }
                    });
            ImageLoader.getInstance().loadImage(image, contentEntity.getGood().getPhoto());

        }

        @Override
        public int getItemCount() {
            return mMineDollModel == null || mMineDollModel.getData() == null || mMineDollModel.getData().getContent() == null ? 0 : mMineDollModel
                    .getData().getContent().size();
        }
    }
}
