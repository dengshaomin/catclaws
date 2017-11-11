package com.coder.catclaws.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.IControlView;
import com.coder.catclaws.utils.ViewSize;
import com.tmall.ultraviewpager.Screen;

import java.sql.Time;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTouch;

/**
 * Created by dengshaomin on 2017/11/10.
 */

public class ControlView extends BaseLayout implements View.OnTouchListener {
    @BindView(R.id.top)
    SquareLayout top;
    @BindView(R.id.left)
    SquareLayout left;
    @BindView(R.id.right)
    SquareLayout right;
    @BindView(R.id.down)
    SquareLayout down;
    @BindView(R.id.full_lay)
    RelativeLayout full_lay;
    private long lastTime;
    private IControlView iControlView;

    private TimerTask timerTask;
    private Timer timer;
    private int currentDirection = 0;

    public IControlView getiControlView() {
        return iControlView;
    }

    public void setiControlView(IControlView iControlView) {
        this.iControlView = iControlView;
    }

    private static final int touchSpace = 300;

    public ControlView(Context context) {
        super(context);
    }

    public ControlView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ControlView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.control_view;
    }

    private boolean start;

    @Override
    public void initView() {
        left.setOnTouchListener(this);
        top.setOnTouchListener(this);
        right.setOnTouchListener(this);
        down.setOnTouchListener(this);

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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//            case MotionEvent.ACTION_MOVE:
                if (v.getId() == R.id.left) {
                    currentDirection = 1;
                } else if (v.getId() == R.id.top) {
                    currentDirection = 2;
                } else if (v.getId() == R.id.right) {
                    currentDirection = 3;
                } else if (v.getId() == R.id.down) {
                    currentDirection = 4;
                }
                if (timer == null) {
                    timer = new Timer();
                    if (timerTask == null) {
                        timerTask = new TimerTask() {
                            @Override
                            public void run() {
                                while (true) {
                                    if (start) {
                                        if (currentDirection == 1) {
                                            if (getiControlView() != null)
                                                getiControlView().left();
                                        } else if (currentDirection == 2) {
                                            if (getiControlView() != null)
                                                getiControlView().up();
                                        } else if (currentDirection == 3) {
                                            if (getiControlView() != null)
                                                getiControlView().right();
                                        } else if (currentDirection == 4) {
                                            if (getiControlView() != null)
                                                getiControlView().down();
                                        }
                                        try {
                                            Thread.sleep(touchSpace);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        };
                    }
                    start = true;
                    timer.schedule(timerTask, 0, 50);
                } else {
                    start = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                start = !start;
                break;
        }
        return true;
    }


//    @OnClick({R.id.top, R.id.left, R.id.right, R.id.down})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.top:
//                break;
//            case R.id.left:
//                break;
//            case R.id.right:
//                break;
//            case R.id.down:
//                break;
//        }
//    }
}
