package com.coder.catclaws.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.coder.catclaws.commons.IPermissionActivity;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissonItem;

public abstract class PermissionActivity extends Activity implements IPermissionActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<PermissonItem> permissonItems = needPermissions();
        if (permissonItems != null && permissonItems.size() > 0) {
            HiPermission.create(this)
                    .permissions(permissonItems)
                    .checkMutiPermission(new PermissionCallback() {
                        @Override
                        public void onClose() {
                            finish();
                        }

                        @Override
                        public void onFinish() {
                        }

                        @Override
                        public void onDeny(String permisson, int position) {
                            finish();
                        }

                        @Override
                        public void onGuarantee(String permisson, int position) {
                        }
                    });
        }
    }

    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public void closeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
