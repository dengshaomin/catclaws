package com.coder.catclaws.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.models.RechargeModel;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.widgets.CommonViewHolder;
import com.coder.catclaws.widgets.PayItemDecoration;
import com.coder.catclaws.widgets.Rechargetem;
import com.coder.catclaws.widgets.ScrollViewGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.weyye.hipermission.PermissonItem;

public class RechargeActivity extends BaseActivity {

    @BindView(R.id.recycleView)
    ScrollViewGridView recycleView;
    @BindView(R.id.zhifubaodot)
    ImageView zhifubaodot;
    @BindView(R.id.zhifubaolay)
    RelativeLayout zhifubaolay;
    @BindView(R.id.wechartdot)
    ImageView wechartdot;
    @BindView(R.id.wechartlay)
    RelativeLayout wechartlay;
    @BindView(R.id.pay)
    TextView pay;
    private RechargeModel rechargeModel;
    private RechargeAdapter rechargeAdapter;
    private int selectIndex;

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
        return "充值";
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
        return R.layout.activity_recharge;
    }

    @Override
    public void initView() {
        recycleView.setAdapter(new RechargeAdapter());
//        recycleView.setNestedScrollingEnabled(false);
        recycleView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        recycleView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectIndex = position;
                rechargeAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initBundleData() {

    }

    @Override
    public void getNetData() {
        Net.request(NetIndentify.RECHARGE, null);
    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {
            {
                add(NetIndentify.RECHARGE);
            }
        };
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        if (globalMsg.isSuccess()) {
            rechargeModel = (RechargeModel) globalMsg.getMsg();
            rechargeAdapter.notifyDataSetChanged();
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


    @OnClick({R.id.zhifubaolay, R.id.wechartlay, R.id.pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhifubaolay:

                break;
            case R.id.wechartlay:
                break;
            case R.id.pay:
                break;
        }
    }

    public class RechargeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return rechargeModel == null || rechargeModel.getData() == null ? 0 : rechargeModel.getData().size();
        }

        @Override
        public Object getItem(int position) {
            return rechargeModel.getData().get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new Rechargetem(RechargeActivity.this);
            }
            ((Rechargetem) convertView).setViewData(getItem(position));
            ((Rechargetem) convertView).setSelected(position == selectIndex);
            return convertView;
        }
    }
}
