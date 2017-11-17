package com.coder.catclaws.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.Tools;
import com.tmall.ultraviewpager.Screen;


/**
 * Created by dengshaomin on 2016/11/9.
 */
public class FullDialog extends Dialog implements View.OnClickListener, DialogInterface.OnKeyListener {

    //    private GCDialog.DialogActionLister dialogActionLister;
    private RelativeLayout root_view;

    private Context context;

    private boolean clickClose = true;

    private boolean keyBoardBackClose = true;

    private boolean dismissPingBack = false;

    private String rpage, rblock;

    public FullDialog(Context context) {
        this(context, R.style.full_dialog_style);
        this.context = context;
    }

    public FullDialog(Context context, int themeResId) {
        super(context, themeResId);
//        int screenHeight = Screen.getHeight(getOwnerActivity());
//        int statusBarHeight = Screen.getScreenHeight((Activity) context);
//        int dialogHeight = screenHeight - statusBarHeight;
//        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHeight);

        setOnKeyListener(this);
        setContentView(LayoutInflater.from(context).inflate(R.layout.full_dialog, null), new ViewGroup
                .LayoutParams
                (Screen.getWidth(context), Screen.getHeight(context)));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        setContentView(LayoutInflater.from(context).inflate(R.layout.full_dialog, null), new ViewGroup
//                .LayoutParams
//                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        root_view = (RelativeLayout) findViewById(R.id.root_view);
        root_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickClose) {
                    dismiss();
                }
            }
        });
    }


    public FullDialog addContentView(View view) {
        if (root_view != null) {
            root_view.removeAllViews();
            root_view.addView(view);
        }
        return this;
    }

    public FullDialog setClickCloseEnable(boolean enable) {
        clickClose = enable;
        return this;
    }

    public FullDialog setKeyBoardBackCloseEnable(boolean enable) {
        keyBoardBackClose = enable;
        return this;
    }

    public FullDialog setDismissPingBackEnable(boolean enable) {
        dismissPingBack = enable;
        return this;
    }

    @Override
    public void show() {
        try {
            if (context != null && !((Activity) context).isFinishing()) {
                super.show();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void dismiss() {
        try {
            if (context != null && !((Activity) context).isFinishing()) {
                super.dismiss();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View v) {

    }

    public static FullDialog create(Context context) {
        FullDialog gcReFullDialog = new FullDialog(context);
        return gcReFullDialog;
    }


    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (keyBoardBackClose) {
                dismiss();
            }

        }
        return true;
    }

}
