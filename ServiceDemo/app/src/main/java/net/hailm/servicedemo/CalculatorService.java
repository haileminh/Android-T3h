package net.hailm.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import net.hailm.servicedemo.contands.Key;

/**
 * Created by hai_l on 09/12/2017.
 */

// VD: start Activity(2 lần) -> bật 2 activity
// Nếu service đã đực bật, thì lệnh startService ko tao ra Service mới
// Bắt buộc: Đăng kí Service thì đki trong AndroidManifest

public class CalculatorService extends Service {
    private static final String TAG = "CalculatorService";
    // Phương thức onBind dùng trong bindService

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }
    // 1. START_STICKY :
    // Cho phép service tự khởi động lại sau khi bị tắt
    // Với điều kiện Intent kích hoạt là null

    // 2. START_NOT_STICKY:
    // Không cho phép service tự khởi động

    //START_REDELIVER_INTENT:
    // Giống nhứ START_STICKY: nhưng Intent kích hoạt tái tạo lần đầu.

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand:..... ");

        if (intent == null) {
            Log.d(TAG, "onStartCommand: Intent == null");
            return START_REDELIVER_INTENT;
        }

        Log.d(TAG, "onStartCommand: Intent != null");
        float numberA = intent.getFloatExtra(Key.NUMBER_A, 0);
        float numberB = intent.getFloatExtra(Key.NUMBER_B, 0);

        float result = numberA + numberB;
        Log.d(TAG, "onStartCommand: RESULT: " + result);

        return START_REDELIVER_INTENT;

    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ....");
        super.onDestroy();
    }
}
