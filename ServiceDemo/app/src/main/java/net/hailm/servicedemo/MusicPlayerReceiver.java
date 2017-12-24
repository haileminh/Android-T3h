package net.hailm.servicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import net.hailm.servicedemo.contands.Action;

/**
 * Created by hai_l on 14/12/2017.
 */

public class MusicPlayerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }

            switch (action) {
                case Action.ACTION_PLAY:
                    Toast.makeText(context, "Play", Toast.LENGTH_SHORT).show();

                    break;
                case Action.ACTION_PAUSE:
                    Toast.makeText(context, "Pause", Toast.LENGTH_SHORT).show();
                    new MusicService().stop();
                    break;
                case Action.ACTION_NEXT:
                    Toast.makeText(context, "Next", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }
}
