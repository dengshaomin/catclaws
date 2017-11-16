package com.coder.catclaws.activity;

import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.coder.catclaws.MyApplication;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.UserManager;
import com.coder.catclaws.utils.Net;
import com.github.lazylibrary.util.MiscUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.PermissonItem;

public class DeliverGoodsActivity extends BaseActivity {


    @BindView(R.id.person)
    EditText mPerson;

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

    }

    @Override
    public void initBundleData() {

    }

    @Override
    public void getNetData() {

    }

    @Override
    public List<String> regeistEvent() {
        return null;
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

    private void adressAction() {
        PageJump.goInvertFriendActivity(this);
    }

    private void commitAction() {
        showProgressDialog();
        Net.request(NetIndentify.DELIVER_DOLL, new HashMap<String, String>() {{
            put("gifted", UserManager.getInstance().getThirdLoginModel().getAccess_token());
            put("addressed", UserManager.getInstance().getThirdLoginModel().getOpenid());
        }});
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
}
