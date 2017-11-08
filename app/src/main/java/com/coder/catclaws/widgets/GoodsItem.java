package com.coder.catclaws.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.models.HomeModel;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by dengshaomin on 2017/11/8.
 */

public class GoodsItem extends BaseLayout {
    @BindView(R.id.image)
    SimpleDraweeView image;
    @BindView(R.id.desc)
    TextView desc;
    @BindView(R.id.statu)
    TextView statu;
    @BindView(R.id.num)
    TextView num;
    @BindView(R.id.name)
    TextView name;
    private HomeModel.DataBean.RoomsBean.ContentBean contentBean;

    public GoodsItem(Context context) {
        super(context);
    }

    public GoodsItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GoodsItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.goods_item;
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
    public void setViewData(Object data) {
        contentBean = (HomeModel.DataBean.RoomsBean.ContentBean) data;
        if (contentBean == null) return;
        ImageLoader.getInstance().loadImage(image, contentBean.getPhoto());
        desc.setText(contentBean.getIntroduce());
        name.setText(contentBean.getName());
        num.setText(contentBean.getPrice() + "");
    }
}
