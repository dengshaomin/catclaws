package com.coder.catclaws.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.Tools;
import com.coder.catclaws.models.MineDollModel.DataEntity.ContentEntity;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.utils.StateUtil;
import com.coder.catclaws.widgets.ExchangeSureDialogView;
import com.coder.catclaws.widgets.FullDialog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.lazylibrary.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.PermissonItem;

public class DetailActivity extends BaseActivity {

    @BindView(R.id.icon)
    SimpleDraweeView mIcon;

    @BindView(R.id.name)
    TextView mName;

    @BindView(R.id.date)
    TextView mDate;

    @BindView(R.id.statu)
    TextView mStatu;

    @BindView(R.id.value)
    TextView mValue;

    @BindView(R.id.exchange)
    TextView mExchange;

    @BindView(R.id.deliver_good)
    TextView mDeliverGood;

    @BindView(R.id.deposit_lay)
    LinearLayout mDepositLay;

    @BindView(R.id.person)
    TextView mPerson;

    @BindView(R.id.tel_phone)
    TextView mTelPhone;

    @BindView(R.id.adress)
    TextView mAdress;

    @BindView(R.id.send_lay)
    LinearLayout mSendLay;

    @BindView(R.id.exchange_date)
    TextView mExchangeDate;

    @BindView(R.id.exchange_value)
    TextView mExchangeValue;

    @BindView(R.id.finish_lay)
    LinearLayout mFinishLay;

    @BindView(R.id.action_lay)
    LinearLayout mActionLay;

    @BindView(R.id.hasexchange_lay)
    LinearLayout mHasexchangeLay;

    @BindView(R.id.express_name)
    TextView mExpressName;

    @BindView(R.id.express_code)
    TextView mExpressCode;

    private ContentEntity mContentEntity;

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
        return "详情";
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
        return R.layout.activity_detail;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initBundleData() {

        mContentEntity = (ContentEntity) getBunleData();
        if (mContentEntity != null && mContentEntity.getGood() != null) {
            ImageLoader.getInstance().loadImage(mIcon, mContentEntity.getGood().getPhoto());
            mName.setText(mContentEntity.getGood().getName());
            mDate.setText(Tools.getTimeStr(mContentEntity.getGetTime()));
            mStatu.setText(Tools.getDollState(mContentEntity));
            mValue.setText(mContentEntity.getGood().getMb() + "");
            if (mContentEntity.getAddress() != null) {
                mPerson.setText(mContentEntity.getAddress().getName());
                mTelPhone.setText(mContentEntity.getAddress().getPhone());
                mAdress.setText(mContentEntity.getAddress().getAddre());
                mExchangeValue.setText(mContentEntity.getGood().getMb());
            }
            mExchangeDate.setText(Tools.getTimeStr(mContentEntity.getExchangeTime()));
            mExpressName.setText(mContentEntity.getTransportCompany());
            mExpressCode.setText(mContentEntity.getTransportCode() + "");
        }
        if (mContentEntity != null) {
            if (StateUtil.Deposit.equals(Tools.getDollState(mContentEntity))) {
                mDepositLay.setVisibility(View.VISIBLE);
                mSendLay.setVisibility(View.GONE);
                mHasexchangeLay.setVisibility(View.GONE);
                mFinishLay.setVisibility(View.GONE);
                mActionLay.setVisibility(View.VISIBLE);
            } else if (StateUtil.WaitSending.equals(Tools.getDollState(mContentEntity))) {
                mDepositLay.setVisibility(View.GONE);
                mSendLay.setVisibility(View.VISIBLE);
                mHasexchangeLay.setVisibility(View.GONE);
                mFinishLay.setVisibility(View.GONE);
                mActionLay.setVisibility(View.GONE);
            } else if (StateUtil.HasReceive.equals(Tools.getDollState(mContentEntity))) {
                mDepositLay.setVisibility(View.GONE);
                mSendLay.setVisibility(View.GONE);
                mHasexchangeLay.setVisibility(View.VISIBLE);
                mFinishLay.setVisibility(View.GONE);
                mActionLay.setVisibility(View.GONE);
            } else if (StateUtil.Finish.equals(Tools.getDollState(mContentEntity))) {
                mDepositLay.setVisibility(View.GONE);
                mSendLay.setVisibility(View.VISIBLE);
                mHasexchangeLay.setVisibility(View.GONE);
                mFinishLay.setVisibility(View.VISIBLE);
                mActionLay.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void getNetData() {
        Net.request(NetIndentify.HOME, null);
    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {{
            add(NetIndentify.EXCHANGE_DOLL);
//            add(NetIndentify.DELIVER_DOLL);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        closeProgressDialog();
        if (NetIndentify.EXCHANGE_DOLL.equals(globalMsg.getMsgId())) {
            if (globalMsg.isSuccess()) {

            } else {
                ToastUtils.showToast(DetailActivity.this, globalMsg.getMsg() + "");
            }
        } else if (NetIndentify.DELIVER_DOLL.equals(globalMsg.getMsgId())) {
//            if (globalMsg.isSuccess()) {
//
//            } else {
//                ToastUtils.showToast(DetailActivity.this, globalMsg.getMsg() + "");
//            }
        }
    }

    @Override
    public void setViewData(Object data) {

    }

    @Override
    public List<PermissonItem> needPermissions() {
        return null;
    }


    private void exchagneAction() {
        if (mContentEntity != null && mContentEntity.getGood() != null) {
            ExchangeSureDialogView exchangeSureDialogView = new ExchangeSureDialogView(this);
            final FullDialog fullDialog = FullDialog.create(this).addContentView(exchangeSureDialogView);
            exchangeSureDialogView.getTitle().setText("确认将该娃娃兑换成" + mContentEntity.getGood().getMb() + "猫币？");
            exchangeSureDialogView.getSure().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    fullDialog.dismiss();
                    showProgressDialog();
                    Net.request(NetIndentify.EXCHANGE_DOLL, new HashMap<String, String>() {{
                        put("gifted", mContentEntity.getGoodId() + "");
                    }});
                }
            });
            fullDialog.show();
        }
    }

    @OnClick({R.id.exchange, R.id.deliver_good})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exchange:
                exchagneAction();
                break;
            case R.id.deliver_good:
                if (mContentEntity != null) {
//                    showProgressDialog();
//                    Net.request(NetIndentify.DELIVER_DOLL, new HashMap<String, String>() {{
//                        put("gifted", mContentEntity.getGoodId() + "");
//                        put("addressed", mContentEntity.getAddressId() + "");
//                    }});
                    PageJump.goDeliverGoodsActivity(DetailActivity.this, mContentEntity);
                }
                break;
        }
    }

}
