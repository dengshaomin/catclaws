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
import java.util.List;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boom.service.room.netty.TCPClient;
import com.boom.service.room.netty.WaWaJiProtoType;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.IControlView;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.MusicService;
import com.coder.catclaws.commons.MusicService.MusicBinder;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.models.HomeModel.DataBean.RoomsBean.ContentBean;
import com.coder.catclaws.models.RoomModel;
import com.coder.catclaws.models.RoomModel.WaWaJiEntity;
import com.coder.catclaws.models.UserInfoModel.DataBean.UserBean;
import com.coder.catclaws.socks.MsgThread;
import com.coder.catclaws.socks.SendThread;
import com.coder.catclaws.widgets.ControlView;
import com.coder.catclaws.widgets.FullDialog;
import com.coder.catclaws.widgets.PickSuccessDialogView;
import com.daniulive.smartplayer.SmartPlayerJni;
import com.eventhandle.SmartEventCallback;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.lazylibrary.util.ToastUtils;
import com.videoengine.NTRenderer;

import butterknife.BindView;
import butterknife.OnClick;
import cn.iwgang.countdownview.CountdownView;
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

    @BindView(R.id.video_container)
    FrameLayout mVideoContainer;

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

    private SurfaceView sSurfaceView = null;

    private ContentBean contentBean;

    private long playerHandle = 0;

    private static final int PORTRAIT = 1;        //竖屏

    private static final int LANDSCAPE = 2;        //横屏

    private static final String TAG = "SmartPlayer";

    private SmartPlayerJni libPlayer = null;

    private int currentOrigentation = PORTRAIT;

    private boolean isPlaybackViewStarted = false;

    private final long countDownTimes = 24000;

    private boolean isNowPicking = false;

    //    private String playbackUrl = "rtmp://119.29.226.242:1935/hls/stream10085";//rtmp://119.29.226.242:1935/hls/stream10085//rtmp://119.29.226.242:1935/hls/stream/xuebao //rtmp://player.daniulive.com:1935/hls/stream10089
    private String playbackUrl = "rtmp://live2.iboom.tv/AppName/StreamName";
//rtmp://119.29.226.242:1935/hls/stream10085//rtmp://119.29.226.242:1935/hls/stream/xuebao //rtmp://player.daniulive.com:1935/hls/stream10089

    //    private String playbackUrl2 = "rtmp://119.29.226.242:1935/hls/stream10086";
    private String playbackUrl2 = "rtmp://live2.iboom.tv/AppName/Stream";

    //    private String switchURL = "rtmp://119.29.226.242:1935/hls/stream20086";
    private String switchURL = "rtmp://live2.iboom.tv/AppName/StreamName";

    private boolean isHardwareDecoder = true;

    private int playBuffer = 100; // 默认200ms

    private boolean isFastStartup = true; // 是否秒开, 默认true

    private boolean switchUrlFlag = false;

    //Button btnPopInputText;
    private Vibrator vibrator;

    private Context myContext;

    public static SendThread sendThread;

    public static MsgThread msgThread;

    public String ServerHost = "192.168.0.4";

    public int ServerPort = 1090;

    public String ServerIP = "";

    public static String g_room_mac;//房间的mac用于发出命令

    boolean b_start_new_game = false;

    private RoomModel roomModel;

    static {
        System.loadLibrary("SmartPlayer");
    }


    public void onClickClose(View view) {
        libPlayer.SmartPlayerClose(playerHandle);
        playerHandle = 0;
        finish();
    }

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
            mMusicService = ((MusicService.MusicBinder) service).getService();
            mMusicService.startMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private void initService() {
        serviceIntent = new Intent(this, MusicService.class);
        mMusicConnection = new MusicConnection();
        bindService(serviceIntent, mMusicConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void initView() {
        initService();
        libPlayer = new SmartPlayerJni();

        myContext = this.getApplicationContext();
        boolean bViewCreated = CreateView();

        if (bViewCreated) {
            mVideoContainer.addView(sSurfaceView, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                    .LayoutParams.MATCH_PARENT));
        }
        Log.i(TAG, "Start playback stream++");

        playerHandle = libPlayer.SmartPlayerInit(myContext);

        if (playerHandle == 0) {
            Log.e(TAG, "surfaceHandle with nil..");
            return;
        }

        libPlayer.SetSmartPlayerEventCallback(playerHandle, new EventHande());

        libPlayer.SmartPlayerSetSurface(playerHandle, sSurfaceView);    //if set the second param with null, it means it will playback audio only..

        libPlayer.SmartPlayerSetAudioOutputType(playerHandle, 0);

        libPlayer.SmartPlayerSetBuffer(playerHandle, playBuffer);

        libPlayer.SmartPlayerSetFastStartup(playerHandle, isFastStartup ? 1 : 0);

        libPlayer.SmartPlayerSaveImageFlag(playerHandle, 1);

        libPlayer.SmartPlayerSetMute(playerHandle, 1);

        if (isHardwareDecoder) {
            Log.i(TAG, "check isHardwareDecoder: " + isHardwareDecoder);

            int hwChecking = libPlayer.SetSmartPlayerVideoHWDecoder(playerHandle, isHardwareDecoder ? 1 : 0);

            Log.i(TAG, "[daniulive] hwChecking: " + hwChecking);
        }

        if (playbackUrl == null) {
            Log.e(TAG, "playback URL with NULL...");
            return;
        }

        int iPlaybackRet = libPlayer.SmartPlayerStartPlayback(playerHandle, playbackUrl);

        if (iPlaybackRet != 0) {
            Log.e(TAG, "StartPlayback strem failed..");
            return;
        }

        //ButtonListener b = new ButtonListener();
        //TextView textRoomName = (TextView) findViewById(R.id.room_name);
        //textRoomName.setText(roomName);

        // 震动效果的系统服务
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

//        ImageButton btnChangeCam = (ImageButton) findViewById(R.id.btn_changeCam);
//        btnChangeCam.bringToFront();
//        btnChangeCam.setOnClickListener(b);
//        btnChangeCam.setOnTouchListener(b);

        mControlView.setiControlView(new IControlView() {
            @Override
            public void left() {
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.left);
            }

            @Override
            public void up() {
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.up);
            }

            @Override
            public void right() {
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.right);
            }

            @Override
            public void down() {
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.down);
            }
        });
    }

    @Override
    public void initBundleData() {
        contentBean = (ContentBean) getBunleData();
        if (contentBean != null) {
            TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.room);
        }
    }

    @Override
    public void getNetData() {

    }

    @Override
    public List<String> regeistEvent() {
        return new ArrayList<String>() {{
            add(NetIndentify.ROOM);
            add(NetIndentify.PLAY);
            add(NetIndentify.PLAYFAIL);
            add(NetIndentify.PLAYSUCCESS);
        }};
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {
        if (NetIndentify.ROOM.equals(globalMsg.getMsgId())) {
            if (globalMsg.isSuccess()) {
                roomModel = (RoomModel) globalMsg.getMsg();
                setRoomData();
            }
        } else if (NetIndentify.PLAY.equals(globalMsg.getMsgId())) {
            mControlLayout.setVisibility(View.VISIBLE);
            mStartLayout.setVisibility(View.INVISIBLE);
            mCountDownTimer.start(countDownTimes);
            mCountDownTimer.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                @Override
                public void onEnd(CountdownView cv) {

                    mCountDown.setText(0 + "");
                    startPick();
                }
            });
            mCountDownTimer.setOnCountdownIntervalListener(1000, new OnCountdownIntervalListener() {
                @Override
                public void onInterval(CountdownView cv, long remainTime) {
                    mCountDown.setText(remainTime / 1000 + "");
                }
            });

        } else if (NetIndentify.PLAYFAIL.equals(globalMsg.getMsgId())) {
            ToastUtils.showToast(this, "太可惜了~");
            mControlLayout.setVisibility(View.GONE);
            mStartLayout.setVisibility(View.VISIBLE);
        } else if (NetIndentify.PLAYSUCCESS.equals(globalMsg.getMsgId())) {
            showScuccessDialog();
        }
    }

    private void showScuccessDialog() {
        PickSuccessDialogView pickSuccessDialogView = new PickSuccessDialogView(this);
        final FullDialog fullDialog = FullDialog.create(this).addContentView(pickSuccessDialogView);
        pickSuccessDialogView.getShare().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        pickSuccessDialogView.getLook().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        pickSuccessDialogView.getPlayAgain().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.again);
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

    private void setRoomData() {
        if (roomModel.getPlayer() != null) {
            UserBean player = roomModel.getPlayer();
            mName.setText(player.getName());
            mStatu.setText("游戏中");
            ImageLoader.getInstance().loadImage(mIconMine, player.getHeadImg());
        } else {
            mStatu.setText("空闲中");
        }
        if (roomModel.getWatcher() != null && roomModel.getWatcher().size() > 0) {

            mIconOther0.setVisibility(View.VISIBLE);
            if (roomModel.getWatcher().size() > 0) {
                ImageLoader.getInstance().loadImage(mIconOther0, roomModel.getWatcher().get(0).getHeadImg());
            }
            if (roomModel.getWatcher().size() > 1) {
                ImageLoader.getInstance().loadImage(mIconOther1, roomModel.getWatcher().get(1).getHeadImg());
            }
            if (roomModel.getWatcher().size() > 2) {
                ImageLoader.getInstance().loadImage(mIconOther2, roomModel.getWatcher().get(2).getHeadImg());
            }
            mIconOther0.setVisibility(roomModel.getWatcher().size() > 0 ? View.INVISIBLE : View.INVISIBLE);
            mIconOther1.setVisibility(roomModel.getWatcher().size() > 1 ? View.INVISIBLE : View.INVISIBLE);
            mIconOther2.setVisibility(roomModel.getWatcher().size() > 2 ? View.INVISIBLE : View.INVISIBLE);
        } else {
            mIconOther0.setVisibility(View.INVISIBLE);
            mIconOther1.setVisibility(View.INVISIBLE);
            mIconOther2.setVisibility(View.INVISIBLE);
        }
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

    @OnClick({R.id.icon_more, R.id.out, R.id.help, R.id.catch_doll, R.id.msg, R.id.recharge, R.id.start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_more:
                break;
            case R.id.out:
                finish();
                break;
            case R.id.help:
                break;
            case R.id.catch_doll:
                startPick();
                break;
            case R.id.msg:
                break;
            case R.id.recharge:
                break;
            case R.id.start:
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.start);
                break;
        }
    }

    private void startPick() {
        if (isNowPicking) {
            return;
        }
        TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.pick);
    }

    class EventHande implements SmartEventCallback {

        @Override
        public void onCallback(int code, long param1, long param2, String param3, String param4, Object param5) {
            switch (code) {
                case EVENTID.EVENT_DANIULIVE_ERC_PLAYER_STARTED:
                    Log.i(TAG, "开始。。");
                    break;
                case EVENTID.EVENT_DANIULIVE_ERC_PLAYER_CONNECTING:
                    Log.i(TAG, "连接中。。");
                    break;
                case EVENTID.EVENT_DANIULIVE_ERC_PLAYER_CONNECTION_FAILED:
                    Log.i(TAG, "连接失败。。");
                    break;
                case EVENTID.EVENT_DANIULIVE_ERC_PLAYER_CONNECTED:
                    Log.i(TAG, "连接成功。。");
                    break;
                case EVENTID.EVENT_DANIULIVE_ERC_PLAYER_DISCONNECTED:
                    Log.i(TAG, "连接断开。。");
                    break;
                case EVENTID.EVENT_DANIULIVE_ERC_PLAYER_STOP:
                    Log.i(TAG, "关闭。。");
                    break;
                case EVENTID.EVENT_DANIULIVE_ERC_PLAYER_RESOLUTION_INFO:
                    Log.i(TAG, "分辨率信息: width: " + param1 + ", height: " + param2);
                    break;
                case EVENTID.EVENT_DANIULIVE_ERC_PLAYER_NO_MEDIADATA_RECEIVED:
                    Log.i(TAG, "收不到媒体数据，可能是url错误。。");
                    break;
                case EVENTID.EVENT_DANIULIVE_ERC_PLAYER_SWITCH_URL:
                    Log.i(TAG, "切换播放URL。。");
                    break;
                case EVENTID.EVENT_DANIULIVE_ERC_PLAYER_CAPTURE_IMAGE:
                    Log.i(TAG, "快照: " + param1 + " 路径：" + param3);

                    if (param1 == 0) {
                        Log.i(TAG, "截取快照成功。.");
                    } else {
                        Log.i(TAG, "截取快照失败。.");
                    }
                    break;
            }
        }
    }

    /* Create rendering */
    private boolean CreateView() {

        if (sSurfaceView == null) {
             /*
             *  useOpenGLES2:
             *  If with true: Check if system supports openGLES, if supported, it will choose openGLES.
             *  If with false: it will set with default surfaceView;
             */
            sSurfaceView = NTRenderer.CreateRenderer(this, true);
        }

        if (sSurfaceView == null) {
            Log.i(TAG, "Create render failed..");
            return false;
        }

        return true;
    }


    @Override
    protected void onDestroy() {
        Log.i(TAG, "Run into activity destory++");

        if (playerHandle != 0) {
            libPlayer.SmartPlayerClose(playerHandle);
            playerHandle = 0;
        }
        super.onDestroy();
        finish();
    }


}