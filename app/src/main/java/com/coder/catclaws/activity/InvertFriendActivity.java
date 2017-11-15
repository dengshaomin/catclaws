package com.coder.catclaws.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.coder.catclaws.MyApplication;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.UserManager;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.utils.ShareUtils;
import com.coder.catclaws.utils.StaticUtils;
import com.github.lazylibrary.util.MiscUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.PermissonItem;

public class InvertFriendActivity extends BaseActivity {

    @BindView(R.id.invert_code)
    EditText invertCode;

    @BindView(R.id.exchange)
    TextView exchange;

    @BindView(R.id.mine_code)
    TextView mineCode;

    @BindView(R.id.invert_friend)
    TextView invertFriend;

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
        return "邀请好友";
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
        return R.layout.activity_invert_friend;
    }

    @Override
    public void initView() {
        invertCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
            add(NetIndentify.INVITED);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        closeProgressDialog();
        if (globalMsg.isSuccess()) {

        } else {

        }
    }

    @Override
    public void setViewData(Object data) {

    }

    @Override
    public List<PermissonItem> needPermissions() {
        return null;
    }


    @OnClick({R.id.exchange, R.id.invert_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.exchange:
                showProgressDialog();
                if (!TextUtils.isEmpty(invertCode.getText().toString())) {
                    Net.request(NetIndentify.INVITED, new HashMap<String, String>() {{
                        put("inviteCode", UserManager.getInstance().getThirdLoginModel().getAccess_token());
                    }});
                }
                break;
            case R.id.invert_friend:
                break;
        }
    }
}
