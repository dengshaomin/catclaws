package com.coder.catclaws.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.IBaseLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Unbinder;

/**
 * Created by dengshaomin on 2016/10/21.
 */
public abstract class BaseLayout extends LinearLayout implements IBaseLayout {
    private View rootView;
    private List<String> eventList = new ArrayList<>();
    private Unbinder unbinder;

    @Override
    public View getRootView() {
        return rootView;
    }

    public Context getmContext() {
        return mContext;
    }

    private Context mContext;

    public BaseLayout(Context context) {
        this(context, null);
    }

    public BaseLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        EventBus.getDefault().register(this);
        setEvents();
        this.mContext = context;
        int layoutId = this.setContentLayout();
        if (layoutId == 0) {
            TextView tx = new TextView(mContext);
            tx.setText("setLayout first~~");
            this.addView(tx);
            return;
        }
        rootView = LayoutInflater.from(mContext).inflate(layoutId, this);
//        unbinder = ButterKnife.bind(this, rootView);
        if (rootView != null) {
            this.initView();
            this.initBundleData();
            this.getNetData();
        }
    }


    private void setEvents() {
        List<String> events = regeistEvent();
        if (events != null) {
            eventList.addAll(events);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        //unrefeist
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(GlobalMsg event) {
        /* Do something */
        for (String s : eventList) {
            if (s.equals(event.getMsgId())) {
                eventComming(event);
                break;
            }
        }
    }

    public void hideKeyBoard() {
//        if (!getKeyBoardState()) return;
        InputMethodManager imm = (InputMethodManager) getmContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void showKeyBoard() {
//        if (getKeyBoardState()) return;
        InputMethodManager imm = (InputMethodManager) getmContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void dialogDismiss() {

    }

    public boolean getKeyBoardState() {
        InputMethodManager imm = (InputMethodManager) getmContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();
    }

    public void setPingBackParams(String rpage, String rblock) {

    }

    public void setPingBackParams(String rpage, String rblock, int index) {

    }
}
