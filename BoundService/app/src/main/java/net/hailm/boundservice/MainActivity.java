package net.hailm.boundservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.hailm.serviceaidl.DownloadAIDL;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // Bound service local
    private MyService myService;
    private ServiceConnection connection;
    private boolean isServiceConnected;

    private Button btnSum, btnDownload;

    // Remote service
    private DownloadAIDL downloadAIDL;
    private ServiceConnection remoteConnection;
    private boolean isServiceRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        connectService();
        remoteDownloadService();
    }

    private void initializeComponents() {
        btnSum = findViewById(R.id.btn_sum);
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceConnected) {
                    long sum = myService.sum(5, 8);
                    Toast.makeText(myService, "Sum: " + sum, Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    downloadAIDL.download("");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        disconnectService();
        unRemoteDownloadService();
        super.onDestroy();
    }

    private void connectService() {
        connection = new ServiceConnection() {
            //Step 3:
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.MyServiceBinder myServiceBinder = (MyService.MyServiceBinder) service;
                myService = myServiceBinder.getMyService();
                Log.d(TAG, "onServiceConnected: ...");
                isServiceConnected = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isServiceConnected = false;
                Log.d(TAG, "onServiceDisconnected: ...");
            }
        };

        // Step 1:
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, connection, Service.BIND_AUTO_CREATE);
    }

    private void disconnectService() {
        unbindService(connection);
    }

    private void remoteDownloadService() {
        remoteConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
//                DownloadAIDL.Stub stub = (DownloadAIDL.Stub) service;
                downloadAIDL = DownloadAIDL.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        Intent intent = new Intent();
        intent.setClassName("net.hailm.serviceaidl",
                "net.hailm.serviceaidl.DownloadService");
        bindService(intent, remoteConnection, Service.BIND_AUTO_CREATE);
    }

    private void unRemoteDownloadService() {
        unbindService(remoteConnection);
    }
}
