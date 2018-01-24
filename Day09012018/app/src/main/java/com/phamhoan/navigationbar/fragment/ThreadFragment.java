package com.phamhoan.navigationbar.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.phamhoan.navigationbar.R;

/**
 * Created by PHAMHOAN on 12/11/2017.
 */

public class ThreadFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private Button btnRun, btnStop;
    private TextView txtCount;
    private Thread thread;
    private boolean frag= false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_thread, container, false);
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
                frag = false;
                startCount();
                break;
            case R.id.btn_stop:
                if (thread != null && thread.isAlive()) {
                    thread.interrupt();
                    frag = true;
                }
                break;

            default:
                break;
        }
    }

    int value;

    private void startCount() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                value = Integer.parseInt(txtCount.getText().toString());
                while (value <= Integer.MAX_VALUE && frag == false) {
                    if(thread.isInterrupted()) break;
                    try {

                        value++;
                        Log.d("valuee", "run: "+value);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtCount.setText(value + "");
                            }
                        });

                        Thread.sleep(2000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        };
        thread = new Thread(runnable);
        thread.start();
    }
}
