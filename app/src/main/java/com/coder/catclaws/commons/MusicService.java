package com.coder.catclaws.commons;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by dengshaomin on 2017/11/16.
 */

public class MusicService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    public class MusicBinder extends Binder {

        public MusicService getService() {
            return MusicService.this;
        }
    }

    public void startMusic() {
        int a = 1;
    }

    public void stopMusic() {
        int a = 1;
    }

    public void startSoundPool() {
        int a = 1;
    }

    public void stopSoundPool() {
        int a = 1;
    }

    public void startPressKey() {
        int a = 1;
    }

    public MusicService getService() {
        return MusicService.this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
