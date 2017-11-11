/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.boom.service.room.netty;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.andview.refreshview.utils.LogUtils;
import com.coder.catclaws.commons.GlobalMsg;
import com.coder.catclaws.commons.NetIndentify;
import com.coder.catclaws.models.RoomModel;

import org.greenrobot.eventbus.EventBus;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TCPResponse extends SimpleChannelInboundHandler<WaWaJiProto.Action> {

    public static volatile Channel channel;


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        TCPResponse.channel = ctx.channel();
    }


    @Override
    public void messageReceived(ChannelHandlerContext ctx, WaWaJiProto.Action times) {
        if (times != null) {
            switch (times.getType()) {
                case "roomInfo":
                    RoomModel roomModel = JSON.parseObject(times.getData(), RoomModel.class);
                    EventBus.getDefault().post(new GlobalMsg(roomModel == null ? false : true, NetIndentify.ROOM,
                            roomModel));
                    break;
                case "chat":
                    EventBus.getDefault().post(new GlobalMsg(true, NetIndentify.CHAT,
                            times.getData()));
                    break;
                case "play":
                    EventBus.getDefault().post(new GlobalMsg(true, NetIndentify.PLAY,
                            times.getData()));
                case "fail":
                    EventBus.getDefault().post(new GlobalMsg(true, NetIndentify.PLAYFAIL,
                            times.getData()));
                case "success":
                    EventBus.getDefault().post(new GlobalMsg(true, NetIndentify.PLAYSUCCESS,
                            times.getData()));
                    break;
            }
        }
        LogUtils.e("收到消息，类别是：" + times.getType() + "    数据是：" + times.getData());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
