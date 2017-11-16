package com.coder.catclaws.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.Tools;
import com.coder.catclaws.models.MessageModel;
import com.coder.catclaws.models.MessageModel.DataBean.ContentBean;
import com.coder.catclaws.models.MineDollModel;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.widgets.MessageItemDecoration;
import com.coder.catclaws.widgets.MineDollHeader;
import com.coder.catclaws.widgets.MineDollItemDecoration;
import com.coder.catclaws.widgets.codexrefreshview.CodeRecycleView;
import com.coder.catclaws.widgets.codexrefreshview.CommonAdapter;
import com.coder.catclaws.widgets.codexrefreshview.MultiItemTypeAdapter;
import com.coder.catclaws.widgets.codexrefreshview.ViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.weyye.hipermission.PermissonItem;

public class MessageActivity extends BaseActivity {

    @BindView(R.id.codeRecycleView)
    CodeRecycleView mCodeRecycleView;


    private int page = 1;

    private MessageModel mMessageModel;

    private CommonAdapter commonAdapter;


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
        return "消息";
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
        return R.layout.activity_message;
    }

    @Override
    public void initView() {
        mCodeRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mCodeRecycleView.addItemDecoration(new MessageItemDecoration());
        mCodeRecycleView.setRefreshMode(CodeRecycleView.BOTH);
        mCodeRecycleView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                page = 1;
                getNetData();
            }

            @Override
            public void onLoadMore(boolean isSilence, int index) {
                page = index;
                getNetData();
            }
        });
    }

    @Override
    public void initBundleData() {

    }

    @Override
    public void getNetData() {
        Net.request(NetIndentify.MINEMESSAGE, new HashMap<String, String>() {{
            put("page", page + "");
            put("size", CodeRecycleView.pageSize + "");
        }});
    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {{
            add(NetIndentify.MINEMESSAGE);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        if (globalMsg.isSuccess()) {
            mMessageModel = (MessageModel) globalMsg.getMsg();
            if (mMessageModel != null && mMessageModel.getData().getContent() != null) {
                if (commonAdapter == null) {
                    commonAdapter = new CommonAdapter<ContentBean>(MessageActivity.this, R
                            .layout.message_item,
                            mMessageModel.getData().getContent()) {
                        @Override
                        protected void convert(ViewHolder holder, ContentBean contentBean, int position) {
                            View rootView = holder.itemView;
                            TextView title = rootView.findViewById(R.id.title);
                            TextView content = rootView.findViewById(R.id.content);
                            TextView date = rootView.findViewById(R.id.date);
                            if (contentBean == null) {
                                return;
                            }
                            date.setText(Tools.getTimeStr(contentBean.getAddTime()));
                            title.setText(contentBean.getTitle());
                            content.setText(contentBean.getMsg());
                        }

                    };
                    mCodeRecycleView.setAdapter(commonAdapter);
                    mCodeRecycleView.refreshComplete(CodeRecycleView.SUCCESS);
                } else {
                    commonAdapter.notifyDataSetChanged();
                    mCodeRecycleView.refreshComplete(CodeRecycleView.SUCCESS);
                }
            } else {
                mCodeRecycleView.refreshComplete(CodeRecycleView.ERROR);
            }
        } else {
            mCodeRecycleView.refreshComplete(CodeRecycleView.ERROR);
        }
    }
    @Override
    public void setViewData(Object data) {

    }

    @Override
    public List<PermissonItem> needPermissions() {
        return null;
    }

}
