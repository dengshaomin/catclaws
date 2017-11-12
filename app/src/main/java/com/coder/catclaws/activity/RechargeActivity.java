package com.coder.catclaws.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.models.RechargeModel;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.widgets.CommonViewHolder;
import com.coder.catclaws.widgets.PayItemDecoration;
import com.coder.catclaws.widgets.Rechargetem;
import com.coder.catclaws.widgets.SquareLayout;
import com.coder.catclaws.widgets.codexrefreshview.CodeRecycleView;
import com.coder.catclaws.widgets.codexrefreshview.CommonAdapter;
import com.coder.catclaws.widgets.codexrefreshview.MultiItemTypeAdapter;
import com.coder.catclaws.widgets.codexrefreshview.ViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.PermissonItem;

public class RechargeActivity extends BaseActivity {

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
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
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
        recycleView.setLayoutManager(new GridLayoutManager(this, 3));
//        recycleView.setNestedScrollingEnabled(false);
        recycleView.addItemDecoration(new PayItemDecoration());
        rechargeAdapter = new RechargeAdapter();
        recycleView.setAdapter(new RechargeAdapter());
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


    public class RechargeAdapter extends RecyclerView.Adapter<CommonViewHolder> {


        @Override
        public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RechargeActivity.this).inflate(R.layout.recharge_item, parent, false);
            return new CommonViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CommonViewHolder holder, final int position) {
            RechargeModel.DataEntity contentBean = rechargeModel.getData().get(position);
            SimpleDraweeView image = holder.itemView.findViewById(R.id.image);
            TextView value = holder.itemView.findViewById(R.id.value);
            View recharge_item_rootview = holder.itemView.findViewById(R.id.recharge_item_rootview);
            recharge_item_rootview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectIndex = position;
                    notifyDataSetChanged();
                }
            });
            ImageLoader.getInstance().loadImage(image, contentBean.getImg());
            value.setText("RMB " + contentBean.getPrice() + "");
            recharge_item_rootview.setSelected(position == selectIndex);
        }

        @Override
        public int getItemCount() {
            return rechargeModel == null || rechargeModel.getData() == null ? 0 : rechargeModel.getData().size();
        }
    }
}
