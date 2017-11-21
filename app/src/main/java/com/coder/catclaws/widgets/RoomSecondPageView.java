package com.coder.catclaws.widgets;

import android.content.Context;
import android.util.AttributeSet;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.models.HomeModel;
import com.coder.catclaws.widgets.codexrefreshview.CodeRecycleView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/21.
 */

public class RoomSecondPageView extends BaseLayout {


    @BindView(R.id.doll_detail_img)
    SimpleDraweeView dollDetailImg;
    @BindView(R.id.codeRecycleView)
    CodeRecycleView codeRecycleView;

    private HomeModel.DataBean.RoomsBean.ContentBean contentBean;

    public RoomSecondPageView(Context context) {
        super(context);
    }

    public RoomSecondPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoomSecondPageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.room_secondpage_view;
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
        if (contentBean != null) {
            ImageLoader.getInstance().loadImage(dollDetailImg, contentBean.getPhoto());
        }
    }

}
