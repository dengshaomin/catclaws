package com.coder.catclaws.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.AddressPickTask;
import com.coder.catclaws.commons.GlobalMsg;
import com.github.lazylibrary.util.ToastUtils;

import java.util.List;

import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import me.weyye.hipermission.PermissonItem;

public class AddressActivity extends BaseActivity {

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

    private void showPick() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {
                ToastUtils.showToast(AddressActivity.this, "数据初始化失败");
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                if (county == null) {
//                    showToast(province.getAreaName() + city.getAreaName());
                } else {
//                    showToast(province.getAreaName() + city.getAreaName() + county.getAreaName());
                }
            }
        });
        task.execute("北京市", "北京市", "东城区");
    }
}
