package com.coder.catclaws.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.models.MineDollModel;
import com.coder.catclaws.models.RechargeModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dengshaomin on 2017/11/8.
 */

public class Rechargetem extends BaseLayout {
    @BindView(R.id.image)
    SimpleDraweeView image;
    @BindView(R.id.value)
    TextView value;
    @BindView(R.id.recharge_item_rootview)
    SquareLayout rechargeItemRootview;
    private RechargeModel.DataEntity contentBean;

    public Rechargetem(Context context) {
        super(context);
    }

    public Rechargetem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Rechargetem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.recharge_item;
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
    public void setSelected(boolean selected) {
        super.setSelected(selected);
//        rechargeItemRootview.setBackgroundResource(selected ? R.drawable.deliver_doll_item_bg_p : R.drawable.deliver_doll_item_bg);
    }

    @Override
    public void setViewData(Object data) {
        contentBean = (RechargeModel.DataEntity) data;
        if (contentBean == null) return;
        ImageLoader.getInstance().loadImage(image, contentBean.getImg());
        value.setText("RMB " + contentBean.getPrice() + "");
    }
}
