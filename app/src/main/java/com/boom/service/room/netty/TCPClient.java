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

import com.coder.catclaws.MyApplication;
import com.coder.catclaws.commons.UserManager;
import com.github.lazylibrary.util.ToastUtils;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Sends a list of continent/city pairs to a {@link WaWaJiProto} to
 * get the local times of the specified cities.
 */
public class TCPClient {

    private static TCPClient worldClockClient;

    static final String HOST = System.getProperty("host", "115.236.11.98");

    static final int PORT = Integer.parseInt(System.getProperty("port", "1020"));

    public static final String INIT = "init";

    public Channel channel;

    public EventLoopGroup eventLoopGroup;

    public Bootstrap bootstrap;

    public static TCPClient getInstance() {
//        mContext = context.getApplicationContext();
        if (worldClockClient == null) {
            synchronized (TCPClient.class) {
                if (worldClockClient == null) {
                    worldClockClient = new TCPClient();
                    // Make a new connection.

                }
            }
        }
        return worldClockClient;
    }

    public void connectToServerNetty() {
        eventLoopGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new WorldClockClientInitializer());
        try {
            channel = bootstrap.connect(HOST, PORT).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (channel == null) {
            return;
        }
    }

    public void disConnect() {
        if (channel != null) {
            channel.close();
        }
        if (eventLoopGroup != null) {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public void send(final String ip, final String action) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(ip) || TextUtils.isEmpty(action)) {
                    return;
                }
                if (eventLoopGroup == null) {
                    connectToServerNetty();
                }
                if (!channel.isActive()) {
                    connectToServerNetty();
                    send(ip, action);
                    return;
                }
                try {
                    switch (action) {
//                case WaWaJiProtoType.auth:
//                    channel.writeAndFlush(WaWaJiProtoUtil.authBuild(ip));
//                    break;
                        case WaWaJiProtoType.up:
                            channel.writeAndFlush(WaWaJiProtoUtil.upBuild(ip));
                            break;
                        case WaWaJiProtoType.down:
                            channel.writeAndFlush(WaWaJiProtoUtil.downBuild(ip));
                            break;
                        case WaWaJiProtoType.left:
                            channel.writeAndFlush(WaWaJiProtoUtil.leftBuild(ip));
                            break;
                        case WaWaJiProtoType.right:
                            channel.writeAndFlush(WaWaJiProtoUtil.rightBuild(ip));
                            break;
                        case WaWaJiProtoType.pick:
                            channel.writeAndFlush(WaWaJiProtoUtil.pickBuild(ip));
                            break;
                        case WaWaJiProtoType.auth:
                            channel.writeAndFlush(WaWaJiProtoUtil.authBuild(UserManager.getInstance().getToken()));
                            break;
                        case WaWaJiProtoType.room:
                            channel.writeAndFlush(WaWaJiProtoUtil.roomBuild(ip));
                            break;
                        case WaWaJiProtoType.wait:
                            channel.writeAndFlush(WaWaJiProtoUtil.waitBuild());
                            break;
                        case WaWaJiProtoType.again:
                            channel.writeAndFlush(WaWaJiProtoUtil.againBuild(ip));
                            break;
                        case WaWaJiProtoType.start:
                            channel.writeAndFlush(WaWaJiProtoUtil.startBuild(ip));
                            break;
                        case WaWaJiProtoType.chat:
                            channel.writeAndFlush(WaWaJiProtoUtil.chatBuild(ip));
                            break;
                        case WaWaJiProtoType.leave:
                            channel.writeAndFlush(WaWaJiProtoUtil.leaveBuild(ip));
                            break;
                    }
                } finally {
//            eventLoopGroup.shutdownGracefully();
                }
            }
        }).start();
    }
}
