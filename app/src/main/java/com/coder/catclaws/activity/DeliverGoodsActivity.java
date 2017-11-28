package com.coder.catclaws.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.AppIndentify;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.UserManager;
import com.coder.catclaws.models.AddressModel;
import com.coder.catclaws.models.AddressModel.DataBean.ContentBean;
import com.coder.catclaws.models.MineDollModel;
import com.coder.catclaws.models.MineDollModel.DataEntity.ContentEntity;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.widgets.CommonViewHolder;
import com.coder.catclaws.widgets.DelivedSuccessDialogView;
import com.coder.catclaws.widgets.FullDialog;
import com.coder.catclaws.widgets.RechargeItemDecoration;
import com.coder.catclaws.widgets.codexrefreshview.CodeRecycleView;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.github.lazylibrary.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

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
    LinearLayout mRecharge;

    @BindView(R.id.commit)
    TextView mCommit;

    private ContentEntity mContentEntity;

    private MineDollModel mMineDollModel;

    private int selectIndex = 0;

    private DeliverGoodAdapter mDeliverGoodAdapter;

    public static final int signalFreight = 30;

    private List<ContentEntity> selectDolls;

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
        if (mContentEntity == null || mContentEntity.getAddress() == null) {
            Net.request(NetIndentify.ADRESS, new HashMap<String, String>() {{
                put("page", 1 + "");
                put("size", CodeRecycleView.pageSize + "");
            }});
        }
        setAddress();
    }

    private void setAddress() {
        if (mContentEntity != null && mContentEntity.getAddress() != null) {
            ContentBean contentBean = mContentEntity.getAddress();
            mPerson.setText(contentBean.getName());
            mTelPhone.setText(contentBean.getPhone());
            mAdress.setText(contentBean.getProvince() + " " + contentBean.getCity() + " " + contentBean.getArea() + "" +
                    " " + contentBean.getAddre());
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
            add(NetIndentify.ADRESS);
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
                        contentEntity.setSelected(true);
                        if (selectDolls == null) {
                            selectDolls = new ArrayList<>();
                        }
                        selectDolls.add(contentEntity);
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
                FullDialog fullDialog = FullDialog.create(DeliverGoodsActivity.this).addContentView(new
                        DelivedSuccessDialogView
                        (DeliverGoodsActivity.this));
                fullDialog.show();
                fullDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        EventBus.getDefault().post(new GlobalMsg(true, AppIndentify.MINE_DOLL_CHANGE, null));
                        finish();
                    }
                });
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
        } else if (globalMsg.getMsgId().equals(NetIndentify.ADRESS)) {
            if (globalMsg.isSuccess()) {
                if (mContentEntity == null) {
                    mContentEntity = new ContentEntity();
                }
                AddressModel mAddressModel = (AddressModel) globalMsg.getMsg();
                if (mAddressModel.getData() == null || mAddressModel.getData().getContent() == null
                        || mAddressModel.getData().getContent().size() == 0) {
                    return;
                }
                mContentEntity.setAddress(mAddressModel.getData().getContent().get(0));
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
            if (mContentEntity.getAddress() == null) {
                ToastUtils.showToast(this, "请填写收货地址！");
                return;
            }
            if (selectDolls == null) {
                return;
            }
//            if (selectDolls.size() < 2 && UserManager.getInstance().getMb() < signalFreight) {
//                ToastUtils.showToast(this, "猫币不足！");
//                return;
//            }
            showProgressDialog();

            Net.request(NetIndentify.DELIVER_DOLL, new HashMap<String, String>() {{
                String ids = "";
                for (ContentEntity contentEntity : selectDolls) {
                    ids += contentEntity.getId() + ",";
                }
                ids = ids.substring(0, ids.length() - 1);
                put("giftId", ids + "");
                put("addressId", mContentEntity.getAddress().getId() + "");
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

    private void setFreight() {
        if (mMineDollModel == null || mMineDollModel.getData() == null || mMineDollModel.getData().getContent() == null) {
            mValue.setText(signalFreight + "猫币");
        } else {
            selectDolls = new ArrayList<>();
            for (ContentEntity contentEntity : mMineDollModel.getData().getContent()) {
                if (contentEntity.isSelected()) {
                    selectDolls.add(contentEntity);
                }
            }
            mValue.setText(selectDolls.size() > 1 ? "免快递费" : signalFreight + "猫币");
            mRecharge.setVisibility(selectDolls.size() == 1 && UserManager.getInstance().getMb() <
                    signalFreight ? View.VISIBLE : View.INVISIBLE);
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
            final View deliverdoll_item_rootview = holder.itemView.findViewById(R.id.deliverdoll_item_rootview);
            deliverdoll_item_rootview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != selectIndex) {
                        contentEntity.setSelected(!deliverdoll_item_rootview.isSelected());
//                        deliverdoll_item_rootview.setSelected(!deliverdoll_item_rootview.isSelected());
                        setFreight();
                        notifyDataSetChanged();
//                        mContentEntity = contentEntity;
//                        setAddress();
                    }
                }
            });
            deliverdoll_item_rootview.setSelected(contentEntity.isSelected());
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
