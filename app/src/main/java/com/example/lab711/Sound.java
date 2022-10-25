package com.example.lab711;

import android.media.SoundPool;

public class Sound {

    static SoundPool soundPool;

    boolean enabled = false;

    int soundId;
    int winId;
    public static SoundPool getSoundPool() {
        return soundPool;
    }

    public static void setSoundPool(SoundPool soundPool) {
        Sound.soundPool = soundPool;
    }

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Sound(SoundPool soundPool) {
        this.soundPool = soundPool;
    }

    public int getWinId() {
        return winId;
    }

    public void setWinId(int winId) {
        this.winId = winId;
    }

    public void playSound(){
        if(enabled){
            soundPool.play(soundId, 1, 1, 0, 0, 1);
        }

    }
    public void playWin(){
        if(enabled){
            soundPool.play(winId, 1, 1, 0, 0, 1);
        }

    }
}
