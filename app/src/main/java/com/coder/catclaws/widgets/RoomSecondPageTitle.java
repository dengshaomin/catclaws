package com.coder.catclaws.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.IRoomSecondHead;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/21.
 */

public class RoomSecondPageTitle extends BaseLayout {
    @BindView(R.id.doll_detail)
    TextView dollDetail;
    @BindView(R.id.doll_log)
    TextView dollLog;
    @BindView(R.id.indication_0)
    View indication0;
    @BindView(R.id.indication_1)
    View indication1;
    private IRoomSecondHead iRoomSecondHead;

    public IRoomSecondHead getiRoomSecondHead() {
        return iRoomSecondHead;
    }

    public void setiRoomSecondHead(IRoomSecondHead iRoomSecondHead) {
        this.iRoomSecondHead = iRoomSecondHead;
    }

    public RoomSecondPageTitle(Context context) {
        super(context);
    }

    public RoomSecondPageTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoomSecondPageTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.room_secondpage_title_view;
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

    }

    @OnClick({R.id.doll_detail, R.id.doll_log})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.doll_detail:
                if (iRoomSecondHead != null) {
                    iRoomSecondHead.selecdChange(0);
                }
                dollDetail.setTextColor(getResources().getColor(R.color.white));
                dollLog.setTextColor(getResources().getColor(R.color.room_second_title));
                indication0.setVisibility(VISIBLE);
                indication1.setVisibility(INVISIBLE);
                break;
            case R.id.doll_log:
                if (iRoomSecondHead != null) {
                    iRoomSecondHead.selecdChange(1);
                }
                dollDetail.setTextColor(getResources().getColor(R.color.room_second_title));
                dollLog.setTextColor(getResources().getColor(R.color.white));
                indication1.setVisibility(VISIBLE);
                indication0.setVisibility(INVISIBLE);
                break;
        }
    }
}
