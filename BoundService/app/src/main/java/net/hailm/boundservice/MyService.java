package net.hailm.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by hai_l on 16/12/2017.
 */

public class MyService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }
    //Setp 2:
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyServiceBinder();
    }

    public class MyServiceBinder extends Binder {
        private MyService myService;

        public MyServiceBinder() {
            myService = MyService.this;
        }

        public MyService getMyService() {
            return myService;
        }
    }

    public long sum(int... number) {
        long sum = 0;
        for (int i = 0; i < number.length; i++) {
            sum += number[i];
        }
        return sum;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
