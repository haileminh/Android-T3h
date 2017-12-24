package net.hailm.broadcastreceier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by hai_l on 16/11/2017.
 */

public class DowloaderReceiver extends BroadcastReceiver {
    private static final String TAG = "DownloaderReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Dowload
        Log.d(TAG, "Download success");
    }
}
