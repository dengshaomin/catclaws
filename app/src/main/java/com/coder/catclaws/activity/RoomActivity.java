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


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.coder.catclaws.widgets.RoomSecondPageView;
import com.coder.catclaws.widgets.ShareDialogView;
import com.coder.catclaws.widgets.SquareLayout;
import com.coder.catclaws.widgets.SubmitQuestionSuccessDialogView;
import com.coder.catclaws.widgets.draguplookmore.Page;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.lazylibrary.util.DensityUtil;
import com.github.lazylibrary.util.ToastUtils;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.tmall.ultraviewpager.Screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
import cn.iwgang.countdownview.CountdownView.OnCountdownEndListener;
import cn.iwgang.countdownview.CountdownView.OnCountdownIntervalListener;
import me.weyye.hipermission.PermissonItem;


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


    @BindView(R.id.player_info)
    LinearLayout mPlayerInfo;

    @BindView(R.id.icon_other_0_lay)
    SquareLayout mIconOther0Lay;

    @BindView(R.id.icon_other_1_lay)
    SquareLayout mIconOther1Lay;

    @BindView(R.id.icon_other_2_lay)
    SquareLayout mIconOther2Lay;

    @BindView(R.id.roomSecondPageView)
    RoomSecondPageView roomSecondPageView;

    @BindView(R.id.pageTwo)
    Page mPageTwo;

    @BindView(R.id.pageOne)
    Page mPageOne;

    @BindView(R.id.txCloudVideoView)
    TXCloudVideoView txCloudVideoView;

    private TXLivePlayer txLivePlayer;
//    @BindView(R.id.detail_image)
//    SimpleDraweeView mDetailImage;

//    @BindView(R.id.pageConteiner)
//    PageContainer mPageConteiner;

    private MediaProjectionManager projectionManager;

    private MediaProjection mediaProjection;

    private RecordService recordService;


    private ContentBean contentBean;

    private IUiListener iUiListener;

    private static final int PORTRAIT = 1;        //竖屏

    private static final int LANDSCAPE = 2;        //横屏


    private final long countDownTimes = 40000;

    private boolean isNowPicking = false;

    private boolean firstVisualAngle = true;

    private RoomModel roomModel;

    private UserBean currentUser;

    private boolean isCurrentUserPlay = false;

    private boolean startBtnStatu = true; //空闲

    private String questionAuthCode = "";

    private final int STREAM_MODE = TXLivePlayer.PLAY_TYPE_LIVE_RTMP;

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

    TXLivePlayConfig mPlayConfig;


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


    @Override
    public void initView() {
//        getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);
        initVedioPlayer();
        mMineNums.setText("我的猫币:" + UserManager.getInstance().getMb());
//        mPageTwo.setScrollAble(false);
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

    private void initVedioPlayer() {
        txLivePlayer = new TXLivePlayer(this);
        mPlayConfig = new TXLivePlayConfig();
        txLivePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN);
        mPlayConfig.setAutoAdjustCacheTime(true);
        mPlayConfig.setMinAutoAdjustCacheTime(1);
        mPlayConfig.setMaxAutoAdjustCacheTime(5);
        mPlayConfig.setCacheFolderPath(Environment.getExternalStorageDirectory().getPath() + "/txcache");
        mPlayConfig.setMaxCacheItems(2);
        txLivePlayer.setPlayerView(txCloudVideoView);
        txLivePlayer.enableHardwareDecode(true);
        txLivePlayer.setAutoPlay(true);
        txLivePlayer.setConfig(mPlayConfig);
    }

    @Override
    public void initBundleData() {
        contentBean = (ContentBean) getBunleData();
        if (contentBean != null) {
            TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.auth);
//            contentBean.setFirstCamera("rtmp://live.hkstv.hk.lxdns.com/live/hks");
//            contentBean.setSecondCamera("rtmp://live.hkstv.hk.lxdns.com/live/hks");
            txLivePlayer.startPlay(contentBean.getFirstCamera(), STREAM_MODE); //推荐FLV
        }

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
            add(NetIndentify.HAS_AUTHED);

        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        if (NetIndentify.ROOMINFO.equals(globalMsg.getMsgId())) {
            if (globalMsg.isSuccess()) {
                roomModel = (RoomModel) globalMsg.getMsg();
                if (roomModel != null) {
                    currentUser = roomModel.getPlayer();
//                    roomModel.getWaWaJi().getg
                    setStartBtnStatu(roomModel.getPlayer() == null ? true : false);
                    roomSecondPageView.setViewData(roomModel);
                }
                setRoomData();
            }
        } else if (NetIndentify.PLAY.equals(globalMsg.getMsgId())) {
            Net.request(NetIndentify.GET_USERINFO, null);
//            UserManager.getInstance().changeMb(-contentBean.getPrice());
            mControlLayout.setVisibility(View.VISIBLE);
            mStartLayout.setVisibility(View.INVISIBLE);
            questionAuthCode = globalMsg.getMsg() + "";
            isCurrentUserPlay = true;
            mCountDownTimer.start(countDownTimes);
            mCountDown.setText((countDownTimes / 1000 - 1) + "");
            mCountDownTimer.setOnCountdownEndListener(new OnCountdownEndListener() {
                @Override
                public void onEnd(CountdownView cv) {
//
//                    mCountDown.setText(0 + "");
//                    startPick();
                    setStartBtnStatu(false);
                }
            });
            mCountDownTimer.setOnCountdownIntervalListener(1000, new OnCountdownIntervalListener() {
                @Override
                public void onInterval(CountdownView cv, long remainTime) {
                    mCountDown.setText(remainTime / 1000 + "");
                }
            });
        } else if (NetIndentify.PLAYFAIL.equals(globalMsg.getMsgId()))

        {
            showFailDialog();
        } else if (NetIndentify.PLAYSUCCESS.equals(globalMsg.getMsgId()))

        {
            showScuccessDialog();
        } else if (NetIndentify.CHANGE_PLAYER.equals(globalMsg.getMsgId()))

        {
            setPlayer((UserBean) globalMsg.getMsg());
            setStartBtnStatu(false);
        } else if (NetIndentify.ROOM_FREE.equals(globalMsg.getMsgId()))

        {
            isCurrentUserPlay = false;
            setStartBtnStatu(true);
            mControlLayout.setVisibility(View.INVISIBLE);
            mStartLayout.setVisibility(View.VISIBLE);
            isNowPicking = false;
            setPlayer(null);
        } else if (AppIndentify.UPDATE_USERINFO.equals(globalMsg.getMsgId())) {
            mMineNums.setText("我的猫币:" + UserManager.getInstance().getMb());
        } else if (NetIndentify.SUBMIT_QUESTION.equals(globalMsg.getMsgId())) {
            if (globalMsg.isSuccess()) {
                FullDialog.create(this).addContentView(new SubmitQuestionSuccessDialogView(this)).show();
            } else {
                ToastUtils.showToast(RoomActivity.this, "提交失败，请重新提交！");
            }
        } else if (NetIndentify.HAS_AUTHED.equals(globalMsg.getMsgId())) {
            TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.room);
        }

    }

    private void setStartBtnStatu(boolean statu) {
        startBtnStatu = statu;
        mControlLayout.setVisibility(View.INVISIBLE);
        mStartLayout.setVisibility(View.VISIBLE);
        mStart.setBackgroundResource(statu ? R.drawable.room_start_btn_bg : R.drawable.room_start_btn_disable_bg);
    }

    private void showFailDialog() {
        PlayFailDialogView playFailDialogView = new PlayFailDialogView(this);
        final FullDialog fullDialog = FullDialog.create(RoomActivity.this).addContentView(playFailDialogView);
        playFailDialogView.getSure().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fullDialog.dismiss();
                if (UserManager.getInstance().getMb() < roomModel.getWaWaJi().getPrice()) {
                    showCoinNotEnoughDialog();
                    return;
                }
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
                fullDialog.dismiss();
                if (UserManager.getInstance().getMb() < roomModel.getWaWaJi().getPrice()) {
                    showCoinNotEnoughDialog();
                    return;
                }
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.again);
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
//            mStatu.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_room_busy, 0, 0, 0);
        } else {
            mIconMine.setBackgroundResource(0);
        }
        mStart.setBackgroundResource(player == null ? R.drawable.room_start_btn_bg : R.drawable
                .room_start_btn_disable_bg);
        mPlayerInfo.setVisibility(player == null ? View.INVISIBLE : View.VISIBLE);
    }

    private void setRoomData() {
        setPlayer(roomModel == null ? null : roomModel.getPlayer());
        setStartBtnStatu(roomModel == null || roomModel.getPlayer() == null);
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
                showCoinNotEnoughDialog();
            } else {
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.start);
            }

        }
    }

    private void showCoinNotEnoughDialog() {
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
                if (txLivePlayer != null) {
                    firstVisualAngle = !firstVisualAngle;
                    txLivePlayer.startPlay(firstVisualAngle ? contentBean.getFirstCamera() : contentBean.getSecondCamera(),
                            STREAM_MODE); //推荐FLV
                }
                break;
        }
    }

    private void sendDanMuAction(String s) {
        mDanmuLay.setVisibility(View.GONE);
        TCPClient.getInstance().send(s, WaWaJiProtoType.chat);
        addDanmaku(s);
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
            TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.leave);
            finish();
            return;
        }
        LiveRoomDialogView liveRoomDialogView = new LiveRoomDialogView(this);
        final FullDialog fullDialog = FullDialog.create(this).addContentView(liveRoomDialogView);
        liveRoomDialogView.getSure().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.leave);
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
        mCountDownTimer.start(10);
        mCountDown.setText(0 + "");
        setStartBtnStatu(false);
        mMusicService.startPickSound();
        TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.pick);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mMusicConnection);
        if (txLivePlayer != null && txLivePlayer.isPlaying()) {
            txLivePlayer.stopPlay(true); // true代表清除最后一帧画面
        }
        if (txCloudVideoView != null) {
            txCloudVideoView.onDestroy();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mMusicService != null) {
            mMusicService.onPause();
        }
        if (txLivePlayer != null && txLivePlayer.isPlaying()) {
            txLivePlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMusicService != null) {
            mMusicService.onResume();
        }
        if (txLivePlayer != null && !txLivePlayer.isPlaying()) {
            txLivePlayer.resume();
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