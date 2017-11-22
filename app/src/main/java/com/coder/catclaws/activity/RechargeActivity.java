package com.coder.catclaws.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.coder.catclaws.R;
import com.coder.catclaws.alipay.PayResult;
import com.coder.catclaws.commons.AppIndentify;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.UserManager;
import com.coder.catclaws.models.ALiOrderModel;
import com.coder.catclaws.models.RechargeModel;
import com.coder.catclaws.models.WeChartOrderModel;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.widgets.CommonViewHolder;
import com.coder.catclaws.widgets.RechargeItemDecoration;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.lazylibrary.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
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

    private int selectIndex = 0;

    private int payMode;

    private WeChartOrderModel weChartOrderModel;

    private ALiOrderModel aLiOrderModel;

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
        recycleView.setNestedScrollingEnabled(false);
        recycleView.addItemDecoration(new RechargeItemDecoration());
//        recycleView.getLayoutManager().setAutoMeasureEnabled(true);
        zhifubaodot.setSelected(true);
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
                add(NetIndentify.WX_PAY);
                add(NetIndentify.ALI_PAY);
            }
        };
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        if (NetIndentify.RECHARGE.equals(globalMsg.getMsgId())) {
            if (globalMsg.isSuccess()) {
                rechargeModel = (RechargeModel) globalMsg.getMsg();
                if(rechargeAdapter == null){
                    rechargeAdapter = new RechargeAdapter();
                    recycleView.setAdapter(new RechargeAdapter());
                }else {
                    rechargeAdapter.notifyDataSetChanged();
                }
            } else {

            }
        } else if (NetIndentify.WX_PAY.equals(globalMsg.getMsgId())) {
            if (globalMsg.isSuccess()) {
//                PageJump.goWeChartPayActivity(this,);
                weChartOrderModel = (WeChartOrderModel) globalMsg.getMsg();
                if (weChartOrderModel != null && weChartOrderModel.getData() != null) {
                    PageJump.goWeChartPayActivity(RechargeActivity.this, weChartOrderModel.getData().getPayInfo());
                }
            } else {
                ToastUtils.showToast(RechargeActivity.this, globalMsg.getMsg() + "");
            }
        } else if (NetIndentify.ALI_PAY.equals(globalMsg.getMsgId())) {
            if (globalMsg.isSuccess()) {
//                PageJump.goWeChartPayActivity(this,);
                aLiOrderModel = (ALiOrderModel) globalMsg.getMsg();
                if (aLiOrderModel != null && !TextUtils.isEmpty(aLiOrderModel.getData())) {
                    aliPay();
                }
            } else {
                ToastUtils.showToast(RechargeActivity.this, globalMsg.getMsg() + "");
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == WeChartPayActivity.RESULT_CODE) {
//            EventBus.getDefault().post(new GlobalMsg(true, AppIndentify.UPDATE_USERINFO, null));
            UserManager.getInstance().changeMb(rechargeModel.getData().get(selectIndex).getGive());
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
                payMode = 0;
                zhifubaodot.setSelected(true);
                wechartdot.setSelected(false);
                break;
            case R.id.wechartlay:
                payMode = 1;
                zhifubaodot.setSelected(false);
                wechartdot.setSelected(true);
                break;
            case R.id.pay:
                pay();
                break;
        }
    }

    private void pay() {
        if (rechargeModel == null || rechargeModel.getData() == null || rechargeModel.getData().size() == 0) {
            return;
        }
        final RechargeModel.DataEntity dataEntity = rechargeModel.getData().get(selectIndex);
        if (payMode == 1) {
            Net.request(NetIndentify.WX_PAY, new HashMap<String, String>() {{
                put("rechargeId", dataEntity.getId() + "");
            }});
        } else {
            Net.request(NetIndentify.ALI_PAY, new HashMap<String, String>() {{
                put("rechargeId", dataEntity.getId() + "");
            }});
        }
    }

    private static final int SDK_PAY_FLAG = 1;

    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        EventBus.getDefault().post(new GlobalMsg(true, AppIndentify.UPDATE_USERINFO, null));
                        UserManager.getInstance().changeMb(rechargeModel.getData().get(selectIndex).getGive());
                        Toast.makeText(RechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void aliPay() {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(RechargeActivity.this);
                Map<String, String> result = alipay.payV2(aLiOrderModel.getData(), true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
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
