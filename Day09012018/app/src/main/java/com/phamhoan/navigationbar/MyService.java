package com.phamhoan.navigationbar;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;


/**
 * Created by PHAMHOAN on 1/10/2018.
 */

public class MyService extends Service {
    private int myCount;
    private Handler mDemnguocHandler;
    private CountText countText;;

    @Override
    public void onCreate() {
        super.onCreate();
        myCount = 0;
        mDemnguocHandler = new Handler();
        countText = new CountText();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mDemnguocHandler.removeCallbacks(countText);
        mDemnguocHandler.postDelayed(countText, 2000);
        return START_REDELIVER_INTENT;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mDemnguocHandler.removeCallbacks(countText);

    }
    private class CountText implements Runnable{

        @Override
        public void run() {
            if(myCount<= Integer.MAX_VALUE){
                myCount++;
                Log.d("COUNTEE", "run: " + myCount);
                String test = myCount + "";
                Intent broadcastIntent = new Intent("ACTION");
                broadcastIntent.putExtra("Count", test);
                sendBroadcast(broadcastIntent);
                mDemnguocHandler.postDelayed(countText, 2000);
            }

        }
    }
}
