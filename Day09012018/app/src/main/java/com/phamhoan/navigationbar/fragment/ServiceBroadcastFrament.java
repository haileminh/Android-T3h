package com.phamhoan.navigationbar.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.phamhoan.navigationbar.MainActivity;
import com.phamhoan.navigationbar.MyService;
import com.phamhoan.navigationbar.R;

/**
 * Created by PHAMHOAN on 12/11/2017.
 */

public class ServiceBroadcastFrament extends Fragment implements View.OnClickListener {
    private View rootView;
    private Button btnRun, btnStop;
    private TextView txtCount;
    private IntentFilter mIntentFilter;
    private MReceiver mReceiver;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_service_broadcast, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        btnRun = rootView.findViewById(R.id.btn_run);
        btnStop = rootView.findViewById(R.id.btn_stop);
        txtCount = rootView.findViewById(R.id.txt_count);
        txtCount.setText("0");

        btnRun.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_run:
                mIntentFilter = new IntentFilter();
                mIntentFilter.addAction("ACTION");
                getContext().registerReceiver(mReceiver, mIntentFilter);
                Intent intent = new Intent(getActivity(), MyService.class);
                getContext().startService(intent);

                break;
            case R.id.btn_stop:
                Intent intent1 = new Intent(getActivity(), MyService.class);
                getActivity().stopService(intent1);
                break;

            default:
                break;
        }
    }


    @Override
    public void onPause() {
        getContext().unregisterReceiver(mReceiver);
        super.onPause();
    }

    public class MReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (intent.getAction().equals("ACTION")) {
                    txtCount.setText(intent.getStringExtra("Count").toString());
                }

            }
        }
    }


}
