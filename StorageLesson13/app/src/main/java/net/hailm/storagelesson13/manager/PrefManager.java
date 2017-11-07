package net.hailm.storagelesson13.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by hai_l on 04/11/2017.
 */

public class PrefManager {
    public static final String KEY_BACKGROUND_MUSIC = "key_background_music";

    public PrefManager() {

    }

    public static void setBackgroundMusicState(Context context, boolean isOpen) {
        // Tạo ra đối tượng SharedPreferences
        //  SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences pref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(KEY_BACKGROUND_MUSIC, isOpen);
        editor.apply();
    }

    public static boolean getBackgroundMusicStatic(Context context) {
        SharedPreferences pref = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        return pref.getBoolean(KEY_BACKGROUND_MUSIC, false);
    }
}
