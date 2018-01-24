package com.phamhoan.navigationbar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class HandlerFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private Button btnRun, btnStop;
    private TextView txtCount;
    private int myCount;
    private Handler mDemnguocHandler;
    private CountText countText;;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_handler,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        myCount =0;
        btnRun = rootView.findViewById(R.id.btn_run);
        btnStop = rootView.findViewById(R.id.btn_stop);
        txtCount = rootView.findViewById(R.id.txt_count);
        txtCount.setText("0");
        mDemnguocHandler = new Handler();
        countText = new CountText();
        btnRun.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_run:
                mDemnguocHandler.removeCallbacks(countText);
                mDemnguocHandler.postDelayed(countText, 2000);

                break;
            case R.id.btn_stop:
                mDemnguocHandler.removeCallbacks(countText);
                break;

            default:
                break;
        }
    }
    private class CountText implements Runnable{

        @Override
        public void run() {
            if(myCount<= Integer.MAX_VALUE){
                myCount = Integer.parseInt(txtCount.getText().toString());
                myCount++;
                txtCount.setText(myCount+"");
                mDemnguocHandler.postDelayed(countText, 2000);
            }

        }
    }
}
