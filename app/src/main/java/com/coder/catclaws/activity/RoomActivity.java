package com.coder.catclaws.activity;/*
 * SmartPlayer.java
 * SmartPlayer
 * 
 * Github: https://github.com/daniulive/SmarterStreaming
 * WebSite: http://www.daniulive.com
 *
 * Created by DaniuLive on 2015/09/26.
 * Copyright © 2014~2017 DaniuLive. All rights reserved.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.MediaPlayer.MediaPlayerBufferingUpdateListener;
import com.alivc.player.MediaPlayer.MediaPlayerCompletedListener;
import com.alivc.player.MediaPlayer.MediaPlayerErrorListener;
import com.alivc.player.MediaPlayer.MediaPlayerFrameInfoListener;
import com.alivc.player.MediaPlayer.MediaPlayerPreparedListener;
import com.alivc.player.MediaPlayer.MediaPlayerSeekCompleteListener;
import com.alivc.player.MediaPlayer.MediaPlayerStoppedListener;
import com.boom.service.room.netty.TCPClient;
import com.boom.service.room.netty.WaWaJiProtoType;
import com.coder.catclaws.MusicService;
import com.coder.catclaws.MusicService.MusicBinder;
import com.coder.catclaws.R;
import com.coder.catclaws.RecordService;
import com.coder.catclaws.commons.AppIndentify;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.IControlView;
import com.coder.catclaws.commons.IDialogCountDown;
import com.coder.catclaws.commons.IHelpDialog;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.commons.PageJump;
import com.coder.catclaws.commons.UserManager;
import com.coder.catclaws.models.HomeModel.DataBean.RoomsBean.ContentBean;
import com.coder.catclaws.models.RoomModel;
import com.coder.catclaws.models.RoomModel.WaWaJiEntity;
import com.coder.catclaws.models.UserInfoModel.DataBean.UserBean;
import com.coder.catclaws.utils.Net;
import com.coder.catclaws.utils.ShareUtils;
import com.coder.catclaws.widgets.CoinNotEnoughDialogView;
import com.coder.catclaws.widgets.ControlView;
import com.coder.catclaws.widgets.FullDialog;
import com.coder.catclaws.widgets.HelpDialogView;
import com.coder.catclaws.widgets.LiveRoomDialogView;
import com.coder.catclaws.widgets.PickSuccessDialogView;
import com.coder.catclaws.widgets.PlayFailDialogView;
import com.coder.catclaws.widgets.ShareDialogView;
import com.coder.catclaws.widgets.SquareLayout;
import com.coder.catclaws.widgets.SubmitQuestionSuccessDialogView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.lazylibrary.util.DensityUtil;
import com.github.lazylibrary.util.ToastUtils;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tmall.ultraviewpager.Screen;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import cn.iwgang.countdownview.CountdownView.OnCountdownEndListener;
import cn.iwgang.countdownview.CountdownView.OnCountdownIntervalListener;
import me.weyye.hipermission.PermissonItem;

import static android.view.SurfaceHolder.SURFACE_TYPE_GPU;


public class RoomActivity extends BaseActivity {

    @BindView(R.id.icon_mine)
    SimpleDraweeView mIconMine;

    @BindView(R.id.name)
    TextView mName;

    @BindView(R.id.statu)
    TextView mStatu;

    @BindView(R.id.room_persons)
    TextView mRoomPersons;

    @BindView(R.id.icon_other_0)
    SimpleDraweeView mIconOther0;

    @BindView(R.id.icon_other_1)
    SimpleDraweeView mIconOther1;

    @BindView(R.id.icon_other_2)
    SimpleDraweeView mIconOther2;

    @BindView(R.id.icon_more)
    ImageView mIconMore;

    @BindView(R.id.room_title_lay)
    LinearLayout mRoomTitleLay;


    @BindView(R.id.out)
    TextView mOut;

    @BindView(R.id.help)
    TextView mHelp;

    @BindView(R.id.record)
    FrameLayout mRecord;

    @BindView(R.id.count_down_timer)
    CountdownView mCountDownTimer;

    @BindView(R.id.count_down)
    TextView mCountDown;

    @BindView(R.id.catch_doll)
    FrameLayout mCatchDoll;

    @BindView(R.id.control_layout)
    RelativeLayout mControlLayout;

    @BindView(R.id.num_times)
    TextView mNumTimes;

    @BindView(R.id.msg)
    TextView mMsg;

    @BindView(R.id.recharge)
    TextView mRecharge;

    @BindView(R.id.start)
    TextView mStart;

    @BindView(R.id.mine_nums)
    TextView mMineNums;

    @BindView(R.id.start_layout)
    LinearLayout mStartLayout;

    @BindView(R.id.controlView)
    ControlView mControlView;

    @BindView(R.id.danmu0)
    TextView mDanmu0;

    @BindView(R.id.danmu1)
    TextView mDanmu1;

    @BindView(R.id.danmu2)
    TextView mDanmu2;

    @BindView(R.id.danmu3)
    TextView mDanmu3;

    @BindView(R.id.danmu4)
    TextView mDanmu4;

    @BindView(R.id.danmu_lay)
    LinearLayout mDanmuLay;

    @BindView(R.id.surfaceView)
    SurfaceView mSurfaceView;

    @BindView(R.id.player_info)
    LinearLayout mPlayerInfo;

    @BindView(R.id.icon_other_0_lay)
    SquareLayout mIconOther0Lay;

    @BindView(R.id.icon_other_1_lay)
    SquareLayout mIconOther1Lay;

    @BindView(R.id.icon_other_2_lay)
    SquareLayout mIconOther2Lay;


//    @BindView(R.id.pageOne)
//    Page mPageOne;

//    @BindView(R.id.detail_image)
//    SimpleDraweeView mDetailImage;

//    @BindView(R.id.pageConteiner)
//    PageContainer mPageConteiner;

    private MediaProjectionManager projectionManager;

    private MediaProjection mediaProjection;

    private RecordService recordService;

    private AliVcMediaPlayer mPlayer;

    private ContentBean contentBean;

    private IUiListener iUiListener;

    private static final int PORTRAIT = 1;        //竖屏

    private static final int LANDSCAPE = 2;        //横屏


    private final long countDownTimes = 24000;

    private boolean isNowPicking = false;

    private boolean firstVisualAngle = true;

    private RoomModel roomModel;

    private UserBean currentUser;

    private boolean isCurrentUserPlay = false;

    private String questionAuthCode = "";

    @Override
    public boolean needTitle() {
        return false;
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
        return null;
    }

    @Override
    public void titleLeftClick() {
        finish();
    }

    @Override
    public void titleRightClick() {

    }

    @Override
    public boolean needDanMu() {
        return true;
    }

    @Override
    public int setContentLayout() {
        return R.layout.activity_room;
    }

    private Intent serviceIntent;

    private MusicConnection mMusicConnection;

    private MusicService mMusicService;



    private class MusicConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMusicService = ((MusicBinder) service).getService();
            mMusicService.startMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private void initMusicService() {
        serviceIntent = new Intent(this, MusicService.class);
        mMusicConnection = new MusicConnection();
        bindService(serviceIntent, mMusicConnection, BIND_AUTO_CREATE);
    }

    private void initAliVedio() {
        mSurfaceView.getHolder().addCallback(new Callback() {
            public void surfaceCreated(SurfaceHolder holder) {
                holder.setType(SURFACE_TYPE_GPU);
                holder.setKeepScreenOn(true);

                // Important: surfaceView changed from background to front, we need reset surface to mediaplayer.
                // 对于从后台切换到前台,需要重设surface;部分手机锁屏也会做前后台切换的处理
                if (mPlayer != null) {
                    mPlayer.setVideoSurface(mSurfaceView.getHolder().getSurface());
                }

            }

            public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
                if (mPlayer != null) {
                    mPlayer.setSurfaceChanged();
                }
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
            }
        });
        initVodPlayer();
        if (contentBean != null) {
            mPlayer.prepareAndPlay(contentBean.getFirstCamera());
        }
    }

    private void initVodPlayer() {
        mPlayer = new AliVcMediaPlayer(this, mSurfaceView);

        mPlayer.setPreparedListener(new MediaPlayerPreparedListener() {
            @Override
            public void onPrepared() {
//                Toast.makeText(LiveModeActivity.this.getApplicationContext(), R.string.toast_prepare_success, Toast.LENGTH_SHORT).show();

            }
        });
        mPlayer.setFrameInfoListener(new MediaPlayerFrameInfoListener() {
            @Override
            public void onFrameInfoListener() {
                Map<String, String> debugInfo = mPlayer.getAllDebugInfo();
                long createPts = 0;
                if (debugInfo.get("create_player") != null) {
                    String time = debugInfo.get("create_player");
                    createPts = (long) Double.parseDouble(time);
                }
                if (debugInfo.get("open-url") != null) {
                    String time = debugInfo.get("open-url");
                    long openPts = (long) Double.parseDouble(time) + createPts;
                }
                if (debugInfo.get("find-stream") != null) {
                    String time = debugInfo.get("find-stream");
                    long findPts = (long) Double.parseDouble(time) + createPts;
                }
                if (debugInfo.get("open-stream") != null) {
                    String time = debugInfo.get("open-stream");
                    long openPts = (long) Double.parseDouble(time) + createPts;
                }
            }
        });
        mPlayer.setErrorListener(new MediaPlayerErrorListener() {
            @Override
            public void onError(int i, String msg) {
//                Toast.makeText(LiveModeActivity.this.getApplicationContext(), getString(R.string.toast_fail_msg) + msg, Toast.LENGTH_SHORT).show();
            }
        });
        mPlayer.setCompletedListener(new MediaPlayerCompletedListener() {
            @Override
            public void onCompleted() {
            }
        });
        mPlayer.setSeekCompleteListener(new MediaPlayerSeekCompleteListener() {
            @Override
            public void onSeekCompleted() {
            }
        });
        mPlayer.setStoppedListener(new MediaPlayerStoppedListener() {
            @Override
            public void onStopped() {
            }
        });
        mPlayer.setBufferingUpdateListener(new MediaPlayerBufferingUpdateListener() {
            @Override
            public void onBufferingUpdateListener(int percent) {
            }
        });
        mPlayer.enableNativeLog();

    }

    @Override
    public void initView() {
        getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
        mMineNums.setText("我的猫币:" + UserManager.getInstance().getMb());
        initMusicService();
        mControlView.setiControlView(new IControlView() {
            @Override
            public void left() {
                mMusicService.startPressSound();
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.left);
            }

            @Override
            public void up() {
                mMusicService.startPressSound();
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.up);
            }

            @Override
            public void right() {
                mMusicService.startPressSound();
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.right);
            }

            @Override
            public void down() {
                mMusicService.startPressSound();
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.down);
            }
        });
//        ImageLoader.getInstance().loadImage(mDetailImage,UserManager.getInstance().getIcon());
    }

    @Override
    public void initBundleData() {
        contentBean = (ContentBean) getBunleData();
        if (contentBean != null) {
            TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.room);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initAliVedio();
            }
        }, 500);

    }

    @Override
    public void getNetData() {

    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {{
            add(NetIndentify.ROOMINFO);
            add(NetIndentify.PLAY);
            add(NetIndentify.PLAYFAIL);
            add(NetIndentify.PLAYSUCCESS);
            add(NetIndentify.CHANGE_PLAYER);
            add(NetIndentify.ROOM_FREE);
            add(NetIndentify.SUBMIT_QUESTION);
            add(AppIndentify.UPDATE_USERINFO);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        if (NetIndentify.ROOMINFO.equals(globalMsg.getMsgId())) {
            if (globalMsg.isSuccess()) {
                roomModel = (RoomModel) globalMsg.getMsg();
                if (roomModel != null) {
                    currentUser = roomModel.getPlayer();
                }
                setRoomData();
            }
        } else if (NetIndentify.PLAY.equals(globalMsg.getMsgId())) {
            UserManager.getInstance().changeMb(contentBean.getPrice());
            questionAuthCode = globalMsg.getMsg() + "";
            isCurrentUserPlay = true;
            mControlLayout.setVisibility(View.VISIBLE);
            mStartLayout.setVisibility(View.INVISIBLE);
            mCountDownTimer.start(countDownTimes);
            mCountDownTimer.setOnCountdownEndListener(new OnCountdownEndListener() {
                @Override
                public void onEnd(CountdownView cv) {
//
//                    mCountDown.setText(0 + "");
//                    startPick();
                }
            });
            mCountDownTimer.setOnCountdownIntervalListener(1000, new OnCountdownIntervalListener() {
                @Override
                public void onInterval(CountdownView cv, long remainTime) {
                    mCountDown.setText(remainTime / 1000 + "");
                }
            });

        } else if (NetIndentify.PLAYFAIL.equals(globalMsg.getMsgId())) {
            showFailDialog();
            mControlLayout.setVisibility(View.GONE);
            mStartLayout.setVisibility(View.VISIBLE);
        } else if (NetIndentify.PLAYSUCCESS.equals(globalMsg.getMsgId())) {
            showScuccessDialog();
        } else if (NetIndentify.CHANGE_PLAYER.equals(globalMsg.getMsgId())) {
            setPlayer((UserBean) globalMsg.getMsg());
        } else if (NetIndentify.ROOM_FREE.equals(globalMsg.getMsgId())) {
            isCurrentUserPlay = false;
            setPlayer((UserBean) globalMsg.getMsg());
        } else if (AppIndentify.UPDATE_USERINFO.equals(globalMsg.getMsgId())) {
            mMineNums.setText("我的猫币:" + UserManager.getInstance().getMb());
        } else if (NetIndentify.SUBMIT_QUESTION.equals(globalMsg.getMsgId())) {
            if (globalMsg.isSuccess()) {
                FullDialog.create(this).addContentView(new SubmitQuestionSuccessDialogView(this)).show();
            } else {
                ToastUtils.showToast(RoomActivity.this, "提交失败，请重新提交！");
            }
        }
    }

    private void showFailDialog() {
        PlayFailDialogView playFailDialogView = new PlayFailDialogView(this);
        final FullDialog fullDialog = FullDialog.create(RoomActivity.this).addContentView(playFailDialogView);
        playFailDialogView.getSure().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDialog.dismiss();
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.again);
            }
        });
        playFailDialogView.setIDialogCountDown(new IDialogCountDown() {
            @Override
            public void finish() {
                fullDialog.dismiss();
            }
        });
        fullDialog.show();
    }

    private void showScuccessDialog() {
        PickSuccessDialogView pickSuccessDialogView = new PickSuccessDialogView(this);
        pickSuccessDialogView.setViewData(contentBean);
        final FullDialog fullDialog = FullDialog.create(this).addContentView(pickSuccessDialogView);
        pickSuccessDialogView.getShare().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDialog.dismiss();
                shareAction();
            }
        });
        pickSuccessDialogView.getLook().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDialog.dismiss();
                PageJump.goMineDollActivity(RoomActivity.this);
            }
        });
        pickSuccessDialogView.getPlayAgain().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.again);
                fullDialog.dismiss();
            }
        });
        pickSuccessDialogView.setIDialogCountDown(new IDialogCountDown() {
            @Override
            public void finish() {
                fullDialog.dismiss();
            }
        });
        pickSuccessDialogView.getSuccessClose().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDialog.dismiss();
            }
        });
        fullDialog.show();
    }

    private void setPlayer(UserBean player) {
        if (player != null) {
            mName.setText(player.getName());
            mStatu.setText("游戏中");
            ImageLoader.getInstance().loadImage(mIconMine, player.getHeadImg());
            mStatu.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_room_busy, 0, 0, 0);
        } else {
            mIconMine.setBackgroundResource(0);
        }
        mStart.setBackgroundResource(player == null ? R.drawable.room_start_btn_bg : R.drawable
                .room_start_btn_disable_bg);
        mPlayerInfo.setVisibility(player == null ? View.INVISIBLE : View.VISIBLE);
    }

    private void setRoomData() {
        setPlayer(roomModel == null ? null : roomModel.getPlayer());
        if (roomModel.getWatcher() != null && roomModel.getWatcher().size() > 0) {

            if (roomModel.getWatcher().size() > 0) {
                ImageLoader.getInstance().loadImage(mIconOther0, roomModel.getWatcher().get(0).getHeadImg());
            }
            if (roomModel.getWatcher().size() > 1) {
                ImageLoader.getInstance().loadImage(mIconOther1, roomModel.getWatcher().get(1).getHeadImg());
            }
            if (roomModel.getWatcher().size() > 2) {
                ImageLoader.getInstance().loadImage(mIconOther2, roomModel.getWatcher().get(2).getHeadImg());
            }
            mIconOther0Lay.setVisibility(roomModel.getWatcher().size() > 0 ? View.VISIBLE : View.INVISIBLE);
            mIconOther1Lay.setVisibility(roomModel.getWatcher().size() > 1 ? View.VISIBLE : View.INVISIBLE);
            mIconOther2Lay.setVisibility(roomModel.getWatcher().size() > 2 ? View.VISIBLE : View.INVISIBLE);
        } else {
            mIconOther0Lay.setVisibility(View.INVISIBLE);
            mIconOther1Lay.setVisibility(View.INVISIBLE);
            mIconOther2Lay.setVisibility(View.INVISIBLE);
        }
        mIconMore.setVisibility(
                roomModel == null || roomModel.getWatcher() == null || roomModel.getWatcher().size() < 4 ? View.INVISIBLE : View.VISIBLE);
        if (roomModel.getWaWaJi() != null) {
            WaWaJiEntity waWaJiEntity = roomModel.getWaWaJi();
            mNumTimes.setText(waWaJiEntity.getPrice() + "/次");
        }
        mRoomPersons.setText(roomModel.getTotalWatcher() + "人");

    }

    @Override
    public void setViewData(Object data) {

    }

    @Override
    public List<PermissonItem> needPermissions() {
        return null;
    }

    private void initRecordService() {

    }

    private void startAction() {
        if (roomModel != null && roomModel.getWaWaJi() != null) {
            if (UserManager.getInstance().getMb() < roomModel.getWaWaJi().getPrice()) {
                CoinNotEnoughDialogView coinNotEnoughDialogView = new CoinNotEnoughDialogView(RoomActivity.this);
                final FullDialog fullDialog = FullDialog.create(RoomActivity.this).addContentView(coinNotEnoughDialogView);
                coinNotEnoughDialogView.getInvert().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fullDialog.dismiss();
                        PageJump.goInvertFriendActivity(RoomActivity.this);
                    }
                });
                coinNotEnoughDialogView.getRecharge().setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fullDialog.dismiss();
                        PageJump.goRechargeActivity(RoomActivity.this);
                    }
                });
                fullDialog.show();
            } else {
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.start);
            }

        }
    }

    @OnClick({R.id.icon_more, R.id.out, R.id.help, R.id.catch_doll, R.id.msg, R.id.recharge, R.id.start, R.id.danmu0, R.id.danmu1, R.id.danmu2,
            R.id.danmu3, R.id.danmu4, R.id.danmu_lay, R.id.record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_more:
                break;
            case R.id.out:
                outAction();
                break;
            case R.id.help:
                helpAction();
                break;
            case R.id.catch_doll:
                startPick();
                break;
            case R.id.msg:
                msgAction();
                break;
            case R.id.recharge:
                PageJump.goRechargeActivity(RoomActivity.this);
                break;
            case R.id.start:
                startAction();
                break;
            case R.id.danmu0:
                sendDanMuAction(mDanmu0.getText().toString());
                break;
            case R.id.danmu1:
                sendDanMuAction(mDanmu1.getText().toString());
                break;
            case R.id.danmu2:
                sendDanMuAction(mDanmu2.getText().toString());
                break;
            case R.id.danmu3:
                sendDanMuAction(mDanmu3.getText().toString());
                break;
            case R.id.danmu4:
                sendDanMuAction(mDanmu4.getText().toString());
                break;
            case R.id.danmu_lay:
                break;
            case R.id.record:
                if (mPlayer != null && contentBean != null) {
                    mPlayer.stop();
                    mPlayer.prepareAndPlay(firstVisualAngle ? contentBean.getSecondCamera() : contentBean.getFirstCamera());
                    firstVisualAngle = !firstVisualAngle;
                }

                break;
        }
    }

    private void sendDanMuAction(String s) {
        mDanmuLay.setVisibility(View.GONE);
        TCPClient.getInstance().send(s, WaWaJiProtoType.chat);
//        addDanmaku(s);
    }

    private void msgAction() {
        int[] msgLocaltion = new int[2];
        mMsg.getLocationOnScreen(msgLocaltion);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mDanmuLay.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, Screen.getHeight(this) -
                msgLocaltion[1] +
                DensityUtil.dip2px(this, 20));
//        mDanmuLay.setPadding(mDanmuLay.getPaddingLeft(), mDanmuLay.getPaddingTop(), mDanmuLay.getPaddingRight(), Screen.getHeight(this) -
//                msgLocaltion[1] +
//                DensityUtil.dip2px(this, 20));
        if (mDanmuLay.getVisibility() == View.GONE) {
            mDanmuLay.setVisibility(View.VISIBLE);
        } else {
            mDanmuLay.setVisibility(View.GONE);
        }
    }

    private void outAction() {
        if (!isCurrentUserPlay) {
            finish();
            return;
        }
        LiveRoomDialogView liveRoomDialogView = new LiveRoomDialogView(this);
        final FullDialog fullDialog = FullDialog.create(this).addContentView(liveRoomDialogView);
        liveRoomDialogView.getSure().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDialog.dismiss();
                finish();
            }
        });
        fullDialog.show();
    }

    private void helpAction() {
        final HelpDialogView helpDialogView = new HelpDialogView(this);
        final FullDialog fullDialog = FullDialog.create(this).addContentView(helpDialogView);
        helpDialogView.setIHelpDialog(new IHelpDialog() {
            @Override
            public void click(final String question) {
                fullDialog.dismiss();
                Net.request(NetIndentify.SUBMIT_QUESTION, new HashMap<String, String>() {{
                    put("authCode", questionAuthCode);
                    put("data", question);
                }});
            }
        });
        fullDialog.show();
    }

    private void startPick() {
        if (isNowPicking) {
            return;
        }
        mMusicService.startPickSound();
        TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.pick);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.destroy();
        }
        unbindService(mMusicConnection);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMusicService != null) {
            mMusicService.onPause();
        }
        if (mPlayer != null && mPlayer.isPlaying()) {
            //we pause the player for not playing on the background
            // 不可见，暂停播放器
            mPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMusicService != null) {
            mMusicService.onResume();
        }
        if (mPlayer != null) {
            //we pause the player for not playing on the background
            mPlayer.play();
        }
    }

    @Override
    public void onBackPressed() {
        outAction();
//        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, iUiListener);
    }

    private void shareAction() {
        ShareDialogView shareDialogView = new ShareDialogView(this);
        final FullDialog fullDialog = FullDialog.create(this).addContentView(shareDialogView);
        shareDialogView.getShareQq().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDialog.dismiss();
                if (iUiListener == null) {
                    iUiListener = new IUiListener() {
                        @Override
                        public void onComplete(Object o) {
                        }

                        @Override
                        public void onError(UiError uiError) {

                        }

                        @Override
                        public void onCancel() {

                        }
                    };
                }
                ShareUtils.shareToQQ(RoomActivity.this, "11111", "222222", new IUiListener() {
                    @Override
                    public void onComplete(Object o) {

                    }

                    @Override
                    public void onError(UiError uiError) {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });
        shareDialogView.getShareWx().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDialog.dismiss();
                ShareUtils.shareToWx(false, "11111", "222222");

            }
        });
        shareDialogView.getShareZone().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDialog.dismiss();
                ShareUtils.shareToWx(true, "11111", "222222");
            }
        });
        fullDialog.show();

    }
}