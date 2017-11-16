package com.coder.catclaws.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.coder.catclaws.MyApplication;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.UserManager;
import com.coder.catclaws.models.MineDollModel.DataEntity.ContentEntity;
import com.coder.catclaws.utils.Net;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.lazylibrary.util.MiscUtils;
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
    }

    @Override
    public void getNetData() {
        Net.request(NetIndentify.HOME, null);
    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {{
            add(NetIndentify.EXCHANGE_DOLL);
            add(NetIndentify.DELIVER_DOLL);
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
            if (globalMsg.isSuccess()) {

            } else {
                ToastUtils.showToast(DetailActivity.this, globalMsg.getMsg() + "");
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


    @OnClick({R.id.exchange, R.id.deliver_good})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exchange:
                if (mContentEntity != null) {
                    showProgressDialog();
                    Net.request(NetIndentify.LOGIN, new HashMap<String, String>() {{
                        put("gifted", mContentEntity.getGoodId() + "");
                    }});
                }
                break;
            case R.id.deliver_good:
                if (mContentEntity != null) {
                    showProgressDialog();
                    Net.request(NetIndentify.DELIVER_DOLL, new HashMap<String, String>() {{
                        put("gifted", mContentEntity.getGoodId() + "");
                        put("addressed", mContentEntity.getGoodId() + "");
                    }});
                }
                break;
        }
    }
}
