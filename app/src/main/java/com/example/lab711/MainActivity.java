package com.example.lab711;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {


    static MediaPlayer musicMediaPlayer;
    static Sound sound;

    static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));

        musicMediaPlayer = MediaPlayer.create(this, R.raw.music);
        SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        int soundId = soundPool.load(this, R.raw.sound, 1);
        int winId = soundPool.load(this, R.raw.win, 1);
        sound = new Sound(soundPool);
        sound.setSoundId(soundId);
        sound.setWinId(winId);
        musicMediaPlayer.setLooping(true);

        sharedPreferences = getSharedPreferences("MUSIC_SOUND", MODE_PRIVATE);
        if (sharedPreferences.getBoolean("music", false)) {
            musicMediaPlayer.start();
        }

        sound.setEnabled(sharedPreferences.getBoolean("sounds", false));


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        menu.findItem(R.id.music).setChecked(sharedPreferences.getBoolean("music", false));
        menu.findItem(R.id.sounds).setChecked(sharedPreferences.getBoolean("sounds", false));
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (id) {
            case R.id.music:
                menuItem.setChecked(!menuItem.isChecked());
                if (!musicMediaPlayer.isPlaying()) {
                    musicMediaPlayer.start();
                    editor.putBoolean("music", true);
                } else {
                    musicMediaPlayer.pause();
                    editor.putBoolean("music", false);
                }
                editor.apply();
                return true;
            case R.id.sounds:
                menuItem.setChecked(!menuItem.isChecked());
                editor.putBoolean("sounds", menuItem.isChecked());
                sound.setEnabled(menuItem.isChecked());
                editor.apply();
                return true;
            case R.id.restart:
                DrawView.game.restart();
                setContentView(new DrawView(this));
        }
        return super.onOptionsItemSelected(menuItem);
    }
}