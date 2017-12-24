package net.hailm.broadcastreceier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import net.hailm.broadcastreceier.constant.Action;
import net.hailm.broadcastreceier.constant.Key;


/**
 * Created by hai_l on 16/11/2017.
 */

// Có 2 cách đăng kí sử dụng
// C1: Đki trong file điều hướng AndroidManifest
// UD tắt thì BroadcaseReceier vẫn hoạt động
// Ví dụ: Call phone, sms

// C2: Đki trong activity
// Activity phải hoạt động thì BroadcaseReceier mới HĐ

public class CalculatorReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //
        if (intent != null) {
            String action = intent.getAction();
            if (action == null) {
                return;
            }

            switch (action) {
                case Action.ACTION_PLUS:
                    int numberA = intent.getIntExtra(Key.NUMBER_A, 0);
                    int numberB = intent.getIntExtra(Key.NUMBER_B, 0);
                    int sum = numberA + numberB;

                    Toast.makeText(context, "Resume: " + sum, Toast.LENGTH_SHORT).show();

                    Intent intent1 = new Intent(Action.ACTION_RESULT);
                    intent1.putExtra(Key.RESULT, sum);
                    context.sendBroadcast(intent1);

                    break;

                default:
                    break;
            }
        }
    }
}
