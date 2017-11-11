package com.coder.catclaws.socks;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Author:      JerryChow
 * Date:        2017/5/31 15:22
 * QQ:          384114651
 * Email:       zhoumricecream@163.com
 * Description:
 */
public class SendThread extends Thread {
    private Socket socket = null;
    private int port = 80;
    private String ip = "192.168.0.4";
    private Handler handler;

    private boolean ShouldStopNow = false;

    public SendThread(Handler handler, String dip, int dport) {
        this.handler = handler;
        ip = dip;
        port = dport;
        ShouldStopNow = false;
    }

    public void StopNow() {
        Log.e("====StopNow()===", "I fire StopNow and Stop Scoket.");
        ShouldStopNow = true;
        CloseSocket();
        interrupt();
    }

    private int ReadDataUnti(byte[] datas, int expect_len, InputStream is) {
        int readCount = 0; // 已经成功读取的字节的个数
        try {
            while (readCount < expect_len) {
                int recv_len = is.read(datas, readCount, expect_len - readCount);
                if (recv_len <= 0) {
                    Log.e("====ReadDataUnti===", "read <0 ooceud");
                    break;
                } else
                    readCount += recv_len;

                Log.e("===ReadDataUnti===", "Reading...Expect:" + expect_len + " Recved:" + readCount);
            }
        } catch (IOException ioe) {
            return -1;
        }
        return readCount;
    }

    @Override
    public void run() {
        super.run();
        while (ShouldStopNow == false) {
            if (socket == null) {
                try {
                    Log.e("SendThread.run.try", "==============IP:" + ip + "Port:" + Integer.toString(port));
                    socket = new Socket(ip, port);
                    socket.setKeepAlive(true);

                    Message message = Message.obtain();
                    message.what = 0;
                    handler.sendMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();

                    Message message = Message.obtain();
                    message.what = 20;
                    message.obj = "socket连接 引发异常.3秒后重试";
                    handler.sendMessage(message);

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }

            while (socket != null) {
                try {
                    InputStream reader = socket.getInputStream();
                    byte[] bHead = new byte[7];
                    int count = ReadDataUnti(bHead, 7, reader);
                    if (count != 7) {
                        Log.e("===Readdata===", "ReadHead Failed.");
                        CloseSocket();
                        break;
                    }

                    int data_length = (bHead[6] & 0xff);
                    byte datas[] = new byte[data_length - 7];
                    int data_recved_len = ReadDataUnti(datas, data_length - 7, reader);
                    if (data_recved_len != data_length - 7) {
                        Log.e("===Readdata===", "ReadData Failed. Expect:" + data_length + "Readed" + data_recved_len);
                        CloseSocket();
                        break;
                    }

                    byte total_data[] = new byte[data_length];
                    System.arraycopy(bHead, 0, total_data, 0, 7);
                    System.arraycopy(datas, 0, total_data, 7, data_length - 7);

                    Log.e("=====recv====", bytesToHexString(total_data));

                    Message message = Message.obtain();
                    message.what = 10;
                    message.arg1 = data_length;
                    message.obj = total_data;
                    handler.sendMessage(message);

                } catch (Exception e) {
                    e.printStackTrace();
                    Message message = Message.obtain();
                    message.what = 20;
                    message.obj = "socket接收引发了异常";
                    handler.sendMessage(message);
                    //todo 还是启用这个吧。。省的不知道发生了什么错
                    CloseSocket();
                    break;
                }
            }
        }

        Message message = Message.obtain();
        message.what = 20;
        message.obj = "中止发送线程.";
        handler.sendMessage(message);
        CloseSocket();
    }

    void CloseSocket() {
        try {
            if (socket != null) {
                socket.close();
                socket = null;
            }
        } catch (IOException e) {
            Message message1 = Message.obtain();
            message1.what = 20;
            message1.obj = "socket关闭时 引发异常";
            handler.sendMessage(message1);
        }
    }

    public void sendMsg(byte[] msg) {
        if (socket == null) {
            Log.e("===sendMsg===", "socket is empty. not send.");
            Message message = Message.obtain();
            message.what = 20;
            message.obj = "socket句柄是空，不予发送";
            handler.sendMessage(message);
            return;
        }
        OutputStream outputStream = null;
        try {
            if (socket != null) {
                outputStream = socket.getOutputStream();
                outputStream.write(msg);
                outputStream.flush();
            }
        } catch (IOException e) {
            Message message = Message.obtain();
            message.what = 20;
            message.obj = "socket发送引发异常";
            handler.sendMessage(message);
            //todo 如果还有错误。请启用这行代码让它自动恢复
            CloseSocket();

            e.printStackTrace();
        }
    }

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
