package net.hailm.serviceaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by hai_l on 16/12/2017.
 */

public class DownloadService extends Service {
    private static final String TAG = "DownloadService";

    private DownloadAIDL.Stub stub = new DownloadAIDL.Stub() {
        @Override
        public void download(String link) throws RemoteException {
            Log.d(TAG, "download: ...");
            //TODO
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
