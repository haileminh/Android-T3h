package net.hailm.mediademo.manager;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by hai_l on 25/11/2017.
 */

public class SongPlayerImpl implements SongPlayer {
    public static final int STATE_IDLE = 1;
    public static final int STATE_PLAYING = 2;
    public static final int STATE_PAUSED = 3;

    private MediaPlayer mediaPlayer;
    private int state;
    private Context context;


    public SongPlayerImpl(Context context) {
        this.context = context;
        state = STATE_IDLE;
    }

    @Override
    public void play(int soundId) {

    }

    @Override
    public void play(Uri uri) {
        if (state == STATE_IDLE) {
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(context, uri);
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.prepare(); // chuan bi

//                mediaPlayer.setDataSource("http:/...");
//                mediaPlayer.prepareAsync(); // dung prepare tu stream

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                        state = STATE_PLAYING;
                    }
                });

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
//                        mediaPlayer.stop();
//                        mediaPlayer.release();
//                        mediaPlayer = null;
//                        state = STATE_IDLE;

                        stop();

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {
        if (state != STATE_IDLE) {
            mediaPlayer.stop();

            mediaPlayer.release(); // giai phong vung nho
            mediaPlayer = null;

            state = STATE_IDLE;
        }
    }

    @Override
    public void pause() {
        if (state == STATE_PLAYING) {
            mediaPlayer.pause();

            state = STATE_PAUSED;
        }
    }

    @Override
    public void resume() {
        if (state == STATE_PAUSED) {
            mediaPlayer.start();

            state = STATE_PLAYING;
        }
    }

    @Override
    public int getState() {
        return state;
    }
}
