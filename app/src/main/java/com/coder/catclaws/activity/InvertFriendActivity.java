package com.coder.catclaws.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.coder.catclaws.MyApplication;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.UserManager;
import com.coder.catclaws.models.ThirdLoginModel;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.utils.ShareUtils;
import com.coder.catclaws.utils.StaticUtils;
import com.coder.catclaws.widgets.FullDialog;
import com.coder.catclaws.widgets.ShareDialogView;
import com.github.lazylibrary.util.MiscUtils;
import com.github.lazylibrary.util.ToastUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

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

    private IUiListener iUiListener;

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
        mineCode.setText(UserManager.getInstance().getInvertCode());
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
            ToastUtils.showToast(InvertFriendActivity.this, "兑换成功");
        } else {
            ToastUtils.showToast(InvertFriendActivity.this, "邀请码无效");
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
                if (TextUtils.isEmpty(invertCode.getText().toString())) {
                    ToastUtils.showToast(InvertFriendActivity.this, "请输入邀请码");
                    return;
                }
                showProgressDialog();
                if (!TextUtils.isEmpty(invertCode.getText().toString())) {
                    Net.request(NetIndentify.INVITED, new HashMap<String, String>() {{
                        put("inviteCode", invertCode.getText().toString());
                    }});
                }
                break;
            case R.id.invert_friend:
                shareAction();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, iUiListener);
    }

    private void shareAction() {
        ShareDialogView shareDialogView = new ShareDialogView(this);
        final FullDialog fullDialog = FullDialog.create(this).addContentView(shareDialogView);
        shareDialogView.getShareQq().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDialog.dismiss();
                if (iUiListener == null) {
                    iUiListener = new IUiListener() {
                        @Override
                        public void onComplete(Object o) {
                        }

                        @Override
                        public void onError(UiError uiError) {

                        }

                        @Override
                        public void onCancel() {

                        }
                    };
                }
                ShareUtils.shareToQQ(InvertFriendActivity.this, "11111", "222222", new IUiListener() {
                    @Override
                    public void onComplete(Object o) {

                    }

                    @Override
                    public void onError(UiError uiError) {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });
        shareDialogView.getShareWx().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDialog.dismiss();
                ShareUtils.shareToWx(false, "11111", "222222");

            }
        });
        shareDialogView.getShareZone().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDialog.dismiss();
                ShareUtils.shareToWx(true, "11111", "222222");
            }
        });
        fullDialog.show();

    }
}
