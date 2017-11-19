package com.coder.catclaws;

import java.util.HashMap;
import java.util.Map;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.coder.catclaws.commons.PreferenceUtils;

/**
 * Created by dengshaomin on 2017/11/16.
 */

public class MusicService extends Service {

    private boolean bgMusicFlag;

    private boolean soundMusciFlag;

    private MediaPlayer musicPlay;

    private SoundPool mSoundPool;

    private Map<Integer, Integer> soundMap;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        bgMusicFlag = PreferenceUtils.getInstance().getBoolean(PreferenceUtils.SETTING_BG_MUSIC, true);
        soundMusciFlag = PreferenceUtils.getInstance().getBoolean(PreferenceUtils.SETTING_YINXIAO_MUSIC, true);
        mSoundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 100);
        soundMap = new HashMap<Integer, Integer>() {{
            if (soundMusciFlag) {
                put(R.raw.keydown_music, mSoundPool.load(getBaseContext(), R.raw.keydown_music, 1));
                put(R.raw.pick_music, mSoundPool.load(getBaseContext(), R.raw.pick_music, 1));
            }
        }};
        if (bgMusicFlag) {
            musicPlay = MediaPlayer.create(getBaseContext(), R.raw.bg_music);
            musicPlay.setLooping(true);
            startMusic();
        }
    }


    public class MusicBinder extends Binder {

        public MusicService getService() {
            return MusicService.this;
        }
    }

    public void onPause() {
        if (musicPlay != null && musicPlay.isPlaying()) {
            musicPlay.pause();
        }
    }

    public void onResume() {
        if (musicPlay != null && !musicPlay.isPlaying()) {
            musicPlay.start();
        }
    }

    public void startMusic() {
        if (bgMusicFlag && musicPlay != null && !musicPlay.isPlaying()) {
            musicPlay.start();
        }
    }

    public void stopMusic() {
        if (musicPlay != null && musicPlay.isPlaying()) {
            musicPlay.start();
        }
    }

    public void startPickSound() {
        if (mSoundPool != null) {
            mSoundPool.play(soundMap.get(R.raw.pick_music), 1, 1, 1, 0, 1);
        }
    }

    public void stopSoundPool() {
        int a = 1;
    }

    public void startPressSound() {
        if (mSoundPool != null) {
            mSoundPool.play(soundMap.get(R.raw.keydown_music), 1, 1, 1, 0, 1);
        }
    }

    public MusicService getService() {
        return MusicService.this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopMusic();
        if (musicPlay != null) {
            musicPlay.release();
        }
        if (mSoundPool != null) {
            mSoundPool.release();
        }
    }
}
