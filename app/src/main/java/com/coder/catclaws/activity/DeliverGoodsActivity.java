package com.coder.catclaws.activity;

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

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.PermissonItem;

public class DeliverGoodsActivity extends BaseActivity {


    @BindView(R.id.person)
    EditText person;
    @BindView(R.id.adress)
    EditText adress;
    @BindView(R.id.add)
    TextView add;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.value)
    TextView value;
    @BindView(R.id.recharge)
    TextView recharge;
    @BindView(R.id.commit)
    TextView commit;

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
        Net.request(NetIndentify.LOGIN, new HashMap<String, String>() {{
            put("token", UserManager.getInstance().getThirdLoginModel().getAccess_token());
            put("opened", UserManager.getInstance().getThirdLoginModel().getOpenid());
            put("imie", MiscUtils.getIMSI(MyApplication.applicationContext));
        }});
    }

    private void commitAction() {

    }

    @OnClick({R.id.add, R.id.recharge, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add:
                adressAction();
                break;
            case R.id.recharge:
                PageJump.goRechargeActivity(this);
                break;
            case R.id.commit:
                commitAction();
                break;
        }
    }
}
