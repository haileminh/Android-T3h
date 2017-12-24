package net.hailm.broadcastreceier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by hai_l on 16/11/2017.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Intent.ACTION_POWER_CONNECTED:
                showMessage(context, "ACTION_POWER_CONNECTED");
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                showMessage(context, "ACTION_POWER_DISCONNECTED");
                break;
            default:
                break;
        }
    }

    private void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
