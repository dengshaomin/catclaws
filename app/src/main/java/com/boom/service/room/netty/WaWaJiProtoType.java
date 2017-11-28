package com.boom.service.room.netty;

/**
 * Created by zhumingming on 2017/11/7.
 */
public class WaWaJiProtoType {

    //认证授权
    public static final String auth = "auth";

    //进入房间
    public static final String room = "room";

    //开始一局游戏
    public static final String start = "start";

    //重新开始
    public static final String again = "again";

    //按向上
    public static final String up = "up";

    //按向下
    public static final String down = "down";

    //按向左
    public static final String left = "left";

    //按向右
    public static final String right = "right";

    //捡娃娃
    public static final String pick = "pick";
    //离开房间
    public static final String leave = "leave";

    /**
     * 服务器发送给客户端的
     */
    //等待，当前娃娃机已经有人上机
    public static final String wait = "wait";

    //娃娃机已经准备ok，可以玩了
    public static final String play = "play";

    //去登陆
    public static final String toLogin = "toLogin";

    //娃娃机空闲了的广播消息
    public static final String free = "free";

    //抓中娃娃
    public static final String success = "success";

    //没抓中娃娃
    public static final String fail = "fail";

    //通用错误处理
    public static final String error = "error";

    //用户没进房间，就进行操作，进入一个房间
    public static final String toRoom = "toRoom";

    //房间内部信息
    public static final String roomInfo = "roomInfo";

    //充值
    public static final String recharge = "recharge";

    //聊天
    public static final String chat = "chat";


}
