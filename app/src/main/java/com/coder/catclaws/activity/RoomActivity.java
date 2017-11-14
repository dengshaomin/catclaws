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


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boom.service.room.netty.WaWaJiProtoType;
import com.boom.service.room.netty.TCPClient;
import com.coder.catclaws.R;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.IControlView;
import com.coder.catclaws.commons.ImageLoader;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.models.HomeModel;
import com.coder.catclaws.models.RoomModel;
import com.coder.catclaws.models.UserInfoModel;
import com.coder.catclaws.socks.MsgThread;
import com.coder.catclaws.socks.SendThread;
import com.coder.catclaws.widgets.ControlView;
import com.daniulive.smartplayer.SmartPlayerJni;
import com.eventhandle.SmartEventCallback;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.lazylibrary.util.ToastUtils;
import com.videoengine.NTRenderer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.hipermission.PermissonItem;


public class RoomActivity extends BaseActivity {

    @BindView(R.id.icon_mine)
    SimpleDraweeView iconMine;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.statu)
    TextView statu;
    @BindView(R.id.room_persons)
    TextView roomPersons;
    @BindView(R.id.icon_other_0)
    SimpleDraweeView iconOther0;
    @BindView(R.id.icon_other_1)
    SimpleDraweeView iconOther1;
    @BindView(R.id.icon_other_2)
    SimpleDraweeView iconOther2;
    @BindView(R.id.icon_more)
    ImageView iconMore;
    @BindView(R.id.video_container)
    FrameLayout videoContainer;
    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.onetime_coin)
    TextView onetimeCoin;
    @BindView(R.id.my_coin)
    TextView myCoin;
    @BindView(R.id.recharge)
    ImageView recharge;
    @BindView(R.id.normal_lay)
    RelativeLayout normalLay;
    @BindView(R.id.times)
    TextView times;
    @BindView(R.id.get)
    TextView get;
    @BindView(R.id.bottom_position_lay)
    View bottomPositionLay;
    @BindView(R.id.control_view)
    ControlView controlView;
    @BindView(R.id.catch_lay)
    RelativeLayout catchLay;
    @BindView(R.id.room_title_lay)
    LinearLayout roomTitleLay;
    private SurfaceView sSurfaceView = null;
    private HomeModel.DataBean.RoomsBean.ContentBean contentBean;
    private long playerHandle = 0;

    private static final int PORTRAIT = 1;        //竖屏
    private static final int LANDSCAPE = 2;        //横屏
    private static final String TAG = "SmartPlayer";

    private SmartPlayerJni libPlayer = null;

    private int currentOrigentation = PORTRAIT;
    private boolean isPlaybackViewStarted = false;

    //    private String playbackUrl = "rtmp://119.29.226.242:1935/hls/stream10085";//rtmp://119.29.226.242:1935/hls/stream10085//rtmp://119.29.226.242:1935/hls/stream/xuebao //rtmp://player.daniulive.com:1935/hls/stream10089
    private String playbackUrl = "rtmp://live2.iboom.tv/AppName/StreamName";//rtmp://119.29.226.242:1935/hls/stream10085//rtmp://119.29.226.242:1935/hls/stream/xuebao //rtmp://player.daniulive.com:1935/hls/stream10089
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
    public int setContentLayout() {
        return R.layout.activity_room;
    }

    @Override
    public void initView() {

        libPlayer = new SmartPlayerJni();

        myContext = this.getApplicationContext();
        boolean bViewCreated = CreateView();

        if (bViewCreated) {
            videoContainer.addView(sSurfaceView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
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

        controlView.setiControlView(new IControlView() {
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
        contentBean = (HomeModel.DataBean.RoomsBean.ContentBean) getBunleData();
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
            catchLay.setVisibility(View.VISIBLE);
            normalLay.setVisibility(View.INVISIBLE);
        }else if (NetIndentify.PLAYFAIL.equals(globalMsg.getMsgId())) {
            ToastUtils.showToast(this,"太可惜了~");
        }else if (NetIndentify.PLAYSUCCESS.equals(globalMsg.getMsgId())) {
            ToastUtils.showToast(this,"恭喜~");
        }
    }

    private void setRoomData() {
        if (roomModel.getPlayer() != null) {
            UserInfoModel.DataBean.UserBean player = roomModel.getPlayer();
            name.setText(player.getName());
            statu.setText("游戏中");
            ImageLoader.getInstance().loadImage(iconMine, player.getHeadImg());
        } else {
            statu.setText("空闲中");
        }
        if (roomModel.getWatcher() != null && roomModel.getWatcher().size() > 0) {
            iconMore.setVisibility(View.VISIBLE);

            iconOther0.setVisibility(View.VISIBLE);
            if (roomModel.getWatcher().size() > 0) {
                ImageLoader.getInstance().loadImage(iconOther0, roomModel.getWatcher().get(0).getHeadImg());
            }
            if (roomModel.getWatcher().size() > 1) {
                ImageLoader.getInstance().loadImage(iconOther1, roomModel.getWatcher().get(1).getHeadImg());
            }
            if (roomModel.getWatcher().size() > 2) {
                ImageLoader.getInstance().loadImage(iconOther2, roomModel.getWatcher().get(2).getHeadImg());
            }
            iconOther0.setVisibility(roomModel.getWatcher().size() > 0 ? View.INVISIBLE : View.INVISIBLE);
            iconOther1.setVisibility(roomModel.getWatcher().size() > 1 ? View.INVISIBLE : View.INVISIBLE);
            iconOther2.setVisibility(roomModel.getWatcher().size() > 2 ? View.INVISIBLE : View.INVISIBLE);
        } else {
            iconOther0.setVisibility(View.INVISIBLE);
            iconOther1.setVisibility(View.INVISIBLE);
            iconOther2.setVisibility(View.INVISIBLE);
            iconMore.setVisibility(View.INVISIBLE);
        }
        if (roomModel.getWaWaJi() != null) {
            RoomModel.WaWaJiEntity waWaJiEntity = roomModel.getWaWaJi();
            onetimeCoin.setText(waWaJiEntity.getPrice() + "/次");
        }
        roomPersons.setText(roomModel.getTotalWatcher() + "人");

    }

    @Override
    public void setViewData(Object data) {

    }

    @Override
    public List<PermissonItem> needPermissions() {
        return null;
    }


    @OnClick({R.id.start, R.id.get})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                TCPClient.getInstance().send(contentBean.getIp(), WaWaJiProtoType.start);
                break;
            case R.id.get:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "Run into onConfigurationChanged++");

//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Log.i(TAG, "onConfigurationChanged, with LANDSCAPE。。");
//            inflateLayout(LinearLayout.HORIZONTAL);
//            currentOrigentation = LANDSCAPE;
//        } else {
//            Log.i(TAG, "onConfigurationChanged, with PORTRAIT。。");
//            inflateLayout(LinearLayout.VERTICAL);
//            currentOrigentation = PORTRAIT;
//        }
//
//        if (!isPlaybackViewStarted)
//            return;

        //libPlayer.SmartPlayerSetOrientation(playerHandle, currentOrigentation);
        Log.i(TAG, "Run out of onConfigurationChanged--");
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


    int g_packget_id = 0;

    byte[] user_uart_sendcom(int... params) {
        byte send_buf[] = new byte[8 + params.length];
        send_buf[0] = (byte) 0xfe;
        send_buf[1] = (byte) (g_packget_id);
        send_buf[2] = (byte) (g_packget_id >> 8);
        send_buf[3] = (byte) ~send_buf[0];
        send_buf[4] = (byte) ~send_buf[1];
        send_buf[5] = (byte) ~send_buf[2];
        send_buf[6] = (byte) (8 + params.length);
        for (int i = 0; i < params.length; i++) {
            send_buf[7 + i] = (byte) (params[i]);
        }

        int sum = 0;
        for (int i = 6; i < (8 + params.length - 1); i++) {
            sum += (send_buf[i] & 0xff);
        }

        send_buf[8 + params.length - 1] = (byte) (sum % 100);

        g_packget_id++;
        return send_buf;
    }

    boolean check_com_data(byte[] data, int len) {
        /*if (len < 6) return false;

		//计算校验和
		int check_total = 0;
		for (int i = 0; i < len; i++) {
			if ((i >= 6) && (i < len - 1))
				check_total += (data[i] & 0xff);
		}

		//取反校验
		if (	data[0] != (byte)((~data[3])&0xff)
				&& data[1] != (byte)((~data[4])&0xff)
				&& data[2] != (byte)((~data[5])&0xff))
			return false;

		//检查校验和
		if (check_total % 100 != data[len - 1] ) {
			return false;
		}
*/
        if (len < 6) return false;
        int check_total = 0;

        //计算校验和
        for (int i = 0; i < len; i++) {
            if ((i >= 6) && (i < len - 1))
                check_total += (data[i] & 0xff);
        }

        //取反校验
        if (data[0] != (byte) (~data[3] & 0xff) && data[1] != (byte) (~data[4] & 0xff) && data[2] != (byte) (~data[5] & 0xff))
            return false;

        //检查校验和

        Log.e("==check==", Integer.toString(check_total) + "is " + Integer.toString(data[len - 1]));

        if (check_total % 100 != data[len - 1]) {
            return false;
        }

        return true;
    }


    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    if (b_start_new_game == true) {
                        b_start_new_game = false;

                        int cmd = 0x31;//表示开局-开始抓娃娃
                        int param_timeout = 60;//自动下抓时间 单位 秒
                        int catch_result = 0;//表示本次抓取娃娃的结果。0没抓到 1抓到。客户端发过去永远是0.服务器端自己决定本次是否抓中
                        int power_catch = 0;//抓起抓力 服务器填写。客户端发过去永远是0
                        int power_ontop = 0;//到顶抓力 服务器填写。客户端发过去永远是0
                        int power_move = 0;//移动抓力 服务器填写。客户端发过去永远是0
                        int power_max = 0;//大抓力 服务器填写。客户端发过去永远是0
                        int hold_height = 0;//抓起高度 服务器填写。客户端发过去永远是0 具体详询文档及微信公众号
                        //注:catch_result = 1时， 后面设置的参数全部无效。 catch_result= 0时，如果后面参数全部最大，有可能会抓中.所以最好不要这么乱来
                        //要么你设置catch_result. 要么你自己心里清楚这些参数的意义
                        byte com_cmd[] = user_uart_sendcom(0x31, param_timeout, catch_result, power_catch, power_ontop, power_move, power_max, hold_height);
                        Log.e("==sending==", sendThread.bytesToHexString(com_cmd));
                        if (msgThread != null) {
                            msgThread.putMsg(com_cmd);
                        }
                    }
                }
                break;
                case 10: {
                    int msg_len = msg.arg1;
                    byte test_data[] = (byte[]) (msg.obj);
                    if (check_com_data(test_data, msg_len) == false) {
                        Log.e("=====data recv===", "-----com check error-----");
                        break;
                    }
                    int cmd = (test_data[7] & 0xff);
                    Log.e("==onhandle==", Integer.toString(cmd));
                    switch (cmd) {
                        case 0x31://new game 游戏可以开始了。显示移动按钮
                        {
//                            TextView zhuaText = (TextView) findViewById(R.id.room_name);
//                            zhuaText.setText("");
//
//                            ImageButton mKaijuButton = (ImageButton) findViewById(R.id.btn_kaiju);
//                            mKaijuButton.setVisibility(View.INVISIBLE);
//
//                            ImageButton mUpButton = (ImageButton) findViewById(R.id.btn_up);
//                            mUpButton.setVisibility(View.VISIBLE);
//
//                            ImageButton mDownButton = (ImageButton) findViewById(R.id.btn_down);
//                            mDownButton.setVisibility(View.VISIBLE);
//
//                            ImageButton mLeftButton = (ImageButton) findViewById(R.id.btn_left);
//                            mLeftButton.setVisibility(View.VISIBLE);
//
//                            ImageButton mRightButton = (ImageButton) findViewById(R.id.btn_right);
//                            mRightButton.setVisibility(View.VISIBLE);
//
//                            ImageButton mEnterButton = (ImageButton) findViewById(R.id.btn_enter);
//                            mEnterButton.setVisibility(View.VISIBLE);
                        }
                        break;
                        case 0x33://game end 游戏结束 重新设置界面
                        {
//                            int zhuawawaret = (test_data[8] & 0xff);
//                            if (zhuawawaret == 1)//抓中
//                            {
//                                TextView zhuaText = (TextView) findViewById(R.id.room_name);
//                                zhuaText.setText("恭喜.抓中了!");
//                            } else if (zhuawawaret == 0)//没抓中
//                            {
//                                TextView zhuaText = (TextView) findViewById(R.id.room_name);
//                                zhuaText.setText("没抓到!再接再厉.");
//                            } else {
//                                TextView zhuaText = (TextView) findViewById(R.id.room_name);
//                                zhuaText.setText(zhuawawaret);
//                            }
//
//                            //重新设置界面
//                            ImageButton mKaijuButton = (ImageButton) findViewById(R.id.btn_kaiju);
//                            mKaijuButton.setVisibility(View.VISIBLE);
//
//                            ImageButton mUpButton = (ImageButton) findViewById(R.id.btn_up);
//                            mUpButton.setVisibility(View.INVISIBLE);
//
//                            ImageButton mDownButton = (ImageButton) findViewById(R.id.btn_down);
//                            mDownButton.setVisibility(View.INVISIBLE);
//
//                            ImageButton mLeftButton = (ImageButton) findViewById(R.id.btn_left);
//                            mLeftButton.setVisibility(View.INVISIBLE);
//
//                            ImageButton mRightButton = (ImageButton) findViewById(R.id.btn_right);
//                            mRightButton.setVisibility(View.INVISIBLE);
//
//                            ImageButton mEnterButton = (ImageButton) findViewById(R.id.btn_enter);
//                            mEnterButton.setVisibility(View.INVISIBLE);
//                            Log.e("=====NotifyGameEnd===", "-----------------");
                        }
                        break;
                    }
                }
                break;
            }
            super.handleMessage(msg);
        }
    };


    public static final String bytesToHexString(byte[] buffer) {
        StringBuffer sb = new StringBuffer(buffer.length);
        String temp;

        for (int i = 0; i < buffer.length; ++i) {
            temp = Integer.toHexString(0xff & buffer[i]);
            if (temp.length() < 2)
                sb.append(0);

            sb.append(temp);
        }

        return sb.toString();
    }
}