package com.coder.catclaws.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.AddressPickTask;
import com.coder.catclaws.commons.AddressPickTask.Callback;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.UserManager;
import com.coder.catclaws.models.AddressModel;
import com.coder.catclaws.models.AddressModel.DataBean.ContentBean;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.widgets.codexrefreshview.CodeRecycleView;
import com.github.lazylibrary.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import me.weyye.hipermission.PermissonItem;

public class AddressActivity extends BaseActivity {

    @BindView(R.id.name)
    EditText mName;

    @BindView(R.id.phone)
    EditText mPhone;

    @BindView(R.id.area_lay)
    RelativeLayout mAreaLay;

    @BindView(R.id.address_detail)
    EditText mAddressDetail;

    @BindView(R.id.commit)
    TextView mCommit;

    @BindView(R.id.area_info)
    TextView mAreaInfo;

    private int page = 1;

    private AddressModel mAddressModel;

    private ContentBean mContentBean;

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
        return "地址管理";
    }

    @Override
    public void titleLeftClick() {

    }

    @Override
    public void titleRightClick() {

    }

    @Override
    public int setContentLayout() {
        return R.layout.activity_address;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initBundleData() {

    }

    @Override
    public void getNetData() {
        Net.request(NetIndentify.ADRESS, new HashMap<String, String>() {{
            put("page", page + "");
            put("size", CodeRecycleView.pageSize + "");
        }});
    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {{
            add(NetIndentify.ADRESS);
            add(NetIndentify.ADDADRESS);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        closeProgressDialog();
        if (NetIndentify.ADDADRESS.equals(globalMsg.getMsgId())) {
            if (globalMsg.isSuccess()) {
                ToastUtils.showToast(this, "保存成功~");
                finish();
            } else {
                ToastUtils.showToast(this, "保存失败,请重新提交~");
            }
        } else if (NetIndentify.ADRESS.equals(globalMsg.getMsgId())) {
            if (globalMsg.isSuccess()) {
                mAddressModel = (AddressModel) globalMsg.getMsg();
                if (mAddressModel.getData() == null || mAddressModel.getData().getContent() == null
                        || mAddressModel.getData().getContent().size() == 0) {
                    return;
                }
                mContentBean = mAddressModel.getData().getContent().get(0);
                mAreaInfo.setText(mContentBean.getProvince() + " " + mContentBean.getCity() + " " + mContentBean.getArea());
                mName.setText(mContentBean.getName());
                mPhone.setText(mContentBean.getPhone());

            } else {
                ToastUtils.showToast(this, "获取地址失败~");
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

    private void showPick() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new Callback() {
            @Override
            public void onAddressInitFailed() {
                ToastUtils.showToast(AddressActivity.this, "数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (mContentBean == null) {
                    mContentBean = new ContentBean();
                }
                if (county == null) {
                    mAreaInfo.setText(province.getAreaName() + " " + city.getAreaName());
                    mContentBean.setProvince(province.getAreaName());
                    mContentBean.setCity(city.getAreaName());
                    mContentBean.setArea("");
                } else {
                    mAreaInfo.setText(province.getAreaName() + " " + city.getAreaName() + " " + county.getAreaName());
                    mContentBean.setProvince(province.getAreaName());
                    mContentBean.setCity(city.getAreaName());
                    mContentBean.setArea(county.getAreaName());
                }
            }
        });
        task.execute("北京市", "北京市", "东城区");
    }


    @OnClick({R.id.area_lay, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.area_lay:
                showPick();
                break;
            case R.id.commit:
                showProgressDialog();
                if (mContentBean == null) {
                    mContentBean = new ContentBean();
                }
                mContentBean.setName(mName.getText().toString());
                mContentBean.setPhone(mPhone.getText().toString());
                mContentBean.setAddre(mAddressDetail.getText().toString());
                Net.request(NetIndentify.ADDADRESS, new HashMap<String, String>() {{
                    put("token", UserManager.getInstance().getToken());
                    put("provice", mContentBean.getProvince());
                    put("city", mContentBean.getCity());
                    put("area", mContentBean.getArea());
                    put("name", mContentBean.getName());
                    put("provice", mContentBean.getProvince());
                    put("phone", mContentBean.getPhone());
                    put("addre", mContentBean.getAddre());
                }});
                break;
        }
    }

}
