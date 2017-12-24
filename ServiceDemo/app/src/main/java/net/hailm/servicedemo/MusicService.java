package net.hailm.servicedemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import net.hailm.servicedemo.contands.Action;
import net.hailm.servicedemo.contands.Key;

import java.io.IOException;

/**
 * Created by hai_l on 09/12/2017.
 */

public class MusicService extends Service {
//    public static final String ACTION_STOP = "net.hailm.service.action_stop";
//    public static final String ACTION_PLAY = "net.hailm.service.action_play";
//    public static final String ACTION_PAUSE = "net.hailm.service.action_pause";

    private MediaPlayer mediaPlayer;
    private String songTitle;
    private String songUrl;
    private String songArtist;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        songTitle = intent.getStringExtra(Key.SONG_TITLE);
        songUrl = intent.getStringExtra(Key.SONG_URL);
        songArtist = intent.getStringExtra(Key.SONG_ARTIST);

        if (songTitle == null || songArtist == null || songUrl == null) {
            stopSelf();
            return START_NOT_STICKY;
        }
        playMusic();
        startForceground();

        return START_NOT_STICKY;
    }

    private void playMusic() {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(songUrl);
            mediaPlayer.prepareAsync(); // dug cho nhac internet

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                    stopSelf();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.release(); // giai phong vung nho
        mediaPlayer = null;
    }

    public void startForceground() {
        Intent intentPause = new Intent(this, MusicPlayerReceiver.class);
        intentPause.setAction(Action.ACTION_PAUSE);
        PendingIntent pendingIntentPause = PendingIntent.getBroadcast(this, 100, intentPause, 0);

        Intent intentNext = new Intent(this, MusicPlayerReceiver.class);
        intentNext.setAction(Action.ACTION_NEXT);
        PendingIntent pendingIntentNext = PendingIntent.getBroadcast(this, 101, intentNext, 0);

        Intent intentPlay = new Intent(this, MusicPlayerReceiver.class);
        intentPlay.setAction(Action.ACTION_PLAY);
        PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(this, 102, intentPlay, 0);


        Notification notification = new Notification.Builder(this)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(songTitle)
                .setContentText(songArtist)
                .setShowWhen(false)
//                .setStyle(style)
                .addAction(android.R.drawable.ic_media_previous, "Previous", pendingIntentPlay)
                .addAction(android.R.drawable.ic_media_pause, "Pause", pendingIntentPause)
                .addAction(android.R.drawable.ic_media_next, "Next", pendingIntentNext)
                .build();

        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
