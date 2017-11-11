package com.coder.catclaws.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.models.HomeModel;
import com.coder.catclaws.models.MineDollModel;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.lazylibrary.util.DateUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;

/**
 * Created by dengshaomin on 2017/11/8.
 */

public class MineDollItem extends BaseLayout {
    @BindView(R.id.image)
    SimpleDraweeView image;
    @BindView(R.id.name)
    SimpleDraweeView name;
    @BindView(R.id.statu)
    TextView statu;
    @BindView(R.id.date)
    TextView date;
    private MineDollModel.DataEntity.ContentEntity contentBean;

    public MineDollItem(Context context) {
        super(context);
    }

    public MineDollItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MineDollItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.mine_doll_item;
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
        contentBean = (MineDollModel.DataEntity.ContentEntity) data;
        if (contentBean == null) return;
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY.MM.dd kk:mm");
        date.setText(dateFormat.format(contentBean.getGetTime()));
        if(contentBean.getState() == 1){
            statu.setText("寄存中");
        }else if(contentBean.getState() == 2){
            statu.setText("配送中");
        }else if(contentBean.getState() == 3){
            statu.setText("已兑换");
        }
        if(contentBean.getResult() == 1){
            statu.setText("已签收");
        }
        if(contentBean.getGood() != null){
            MineDollModel.DataEntity.ContentEntity.GoodEntity goodEntity = contentBean.getGood();
            ImageLoader.getInstance().loadImage(image,goodEntity.getPhoto());
//            name.settext
        }
//        ImageLoader.getInstance().loadImage(image,goodEntity.getPhoto());
    }
}
