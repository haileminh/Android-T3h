package net.hailm.quickmessagedemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by hai_l on 17/11/2017.
 */

public class SmsFloatService extends Service implements View.OnClickListener, View.OnTouchListener {
    private static final String TAG = "SmsFloatService";
    private LinearLayout llRootView, llZoomIn, llZoomOut;
    private ImageView imgCloseApp, imgExitApp, imgSend;
    private EditText edtPhone, edtMessage;

    private WindowManager.LayoutParams params;
    private WindowManager mWindowManager;

    private int startX = 0, startY = 0;
    private float touchX = 0, touchY = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initView();
        initWindowManager();
        initNotifi();
    }

    private void initView() {
        llRootView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_sms_float, null);
        llZoomIn = llRootView.findViewById(R.id.ll_zoom_in_group);
        llZoomOut = llRootView.findViewById(R.id.ll_zoom_out_group);

        imgSend = llRootView.findViewById(R.id.img_send);
        imgCloseApp = llRootView.findViewById(R.id.img_close_app);
        imgExitApp = llRootView.findViewById(R.id.img_exit_full_screen);

        edtMessage = llRootView.findViewById(R.id.edt_message);
        edtPhone = llRootView.findViewById(R.id.edt_phone);

        imgSend.setOnClickListener(this);
        imgExitApp.setOnClickListener(this);
        imgCloseApp.setOnClickListener(this);
        llRootView.setOnTouchListener(this);
    }

    private void initWindowManager() {
        params = new WindowManager.LayoutParams();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;

        params.x = 10;
        params.y = 10;
        params.gravity = Gravity.LEFT;

        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mWindowManager.addView(llRootView, params);
    }

    private void initNotifi() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.quick);
        builder.setContentText("Click to open");
        builder.setContentTitle("Quick Message");

        Intent intent = new Intent(this, SmsFloatService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(10, builder.build());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_close_app:
                stopSelf();
                break;
            case R.id.img_exit_full_screen:
                if (llZoomIn.getVisibility() == View.VISIBLE) {
                    llZoomIn.setVisibility(View.GONE);
                    llZoomOut.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (llRootView != null) {
            mWindowManager.removeView(llRootView);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onTouch: 2");
                startX = params.x;
                startY = params.y;

                touchX = event.getRawX();
                touchY = event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouch: 3");
                int diffX = (int) (event.getRawX() - touchX);
                int diffY = (int) (event.getRawY() - touchY);
                if (diffX < 10 && diffY < 10) {
                    if (llRootView != null && llZoomOut.getVisibility() == View.VISIBLE) {
                        llZoomOut.setVisibility(View.GONE);
                        llZoomIn.setVisibility(View.VISIBLE);
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouch: 4");
                params.x = startX + (int) (event.getRawX() - touchX);
                params.y = startY + (int) (event.getRawY() - touchY);
                mWindowManager.updateViewLayout(llRootView, params);
                break;
        }
        return false;
    }
}
