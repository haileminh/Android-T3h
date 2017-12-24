package net.hailm.soundpoolsingle;


import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

/**
 * Created by hai_l on 21/12/2017.
 */

public class SoundPoolManager {
    private SoundPool soundPool;
    private boolean isLoaded;
    private int soundId1;
    private int playId1;

    public SoundPoolManager() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build();

        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();

        // Load
        soundPool.load(App.getInstance(), R.raw.sound_1, 1);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int id, int status) {
                isLoaded = true;
                soundId1 = id;
            }
        });
    }

    public void play() {
        if (isLoaded) {
            playId1 = soundPool.play(soundId1, 1, 1, 1, 0, 1);
        }
    }

    public void stop() {
        if (isLoaded) {
            soundPool.stop(playId1);
        }
    }

}
