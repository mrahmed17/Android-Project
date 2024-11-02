package com.example.quizyy;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class BackgroundMusicService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null; // This service is not meant to be bound
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            // Initialize and start the MediaPlayer
            mediaPlayer = MediaPlayer.create(this, R.raw.videoplayback); // Assuming the mp3 file is in res/raw/videoplayback.mp3
            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return START_STICKY; // Service will be explicitly started and stopped as needed
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
