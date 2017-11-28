package com.boom.service.room.netty;

/**
 * Created by zhumingming on 2017/11/7.
 */
public class WaWaJiProtoUtil {

    public static WaWaJiProto.Action waitBuild() {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.wait).build();
        return build;
    }

    /**
     * 进入房间
     *
     * @return
     */
    public static WaWaJiProto.Action roomBuild(String ip) {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.room).setData(ip).build();
        return build;
    }

    public static WaWaJiProto.Action freeBuild() {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.free).build();
        return build;
    }

    public static WaWaJiProto.Action playBuild() {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.play).build();
        return build;
    }

    public static WaWaJiProto.Action toLoginBuild() {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.toLogin).build();
        return build;
    }

    public static WaWaJiProto.Action errorBuild(String data) {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.error).setData(data).build();
        return build;
    }

    public static WaWaJiProto.Action successBuild() {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.success).build();
        return build;
    }

    public static WaWaJiProto.Action failBuild() {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.fail).build();
        return build;
    }

    public static WaWaJiProto.Action startBuild(String data) {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.start).setData(data).build();
        return build;
    }

    public static WaWaJiProto.Action againBuild(String data) {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.again).setData(data).build();
        return build;
    }

    public static WaWaJiProto.Action upBuild(String data) {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.up).setData(data).build();
        return build;
    }

    public static WaWaJiProto.Action downBuild(String data) {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.down).setData(data).build();
        return build;
    }

    public static WaWaJiProto.Action leftBuild(String data) {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.left).setData(data).build();
        return build;
    }

    public static WaWaJiProto.Action rightBuild(String data) {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.right).setData(data).build();
        return build;
    }

    public static WaWaJiProto.Action pickBuild(String data) {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.pick).setData(data).build();
        return build;
    }

    public static WaWaJiProto.Action authBuild(String jsessionId) {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.auth).setData(jsessionId).build();
        return build;
    }

    public static WaWaJiProto.Action toRoomBuild() {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.toRoom).setData("请选择好房间在进行操作").build();
        return build;
    }

    public static WaWaJiProto.Action chatBuild(String data) {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.chat).setData(data).build();
        return build;
    }
    public static WaWaJiProto.Action leaveBuild(String data) {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.leave).setData(data).build();
        return build;
    }

    /**
     * 客户端跳转到充值界面
     *
     * @return
     */
    public static WaWaJiProto.Action rechargeBuild() {
        WaWaJiProto.Action build = WaWaJiProto.Action.newBuilder().setType(WaWaJiProtoType.recharge).build();
        return build;
    }

}


