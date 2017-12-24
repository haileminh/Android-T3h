package net.hailm.mediademo.manager;

import android.media.MediaPlayer;
import android.net.Uri;

/**
 * Created by hai_l on 25/11/2017.
 */

public interface SongPlayer {
    void play(int soundId);

    void play(Uri uri);

    void stop();

    void pause();

    void resume();

    int getState();
}
