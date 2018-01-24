package com.phamhoan.navigationbar.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
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

public class AsynctaskFragment extends Fragment implements View.OnClickListener {
    private View rootView;
    private Button btnRun, btnStop;
    private TextView txtCount;
    TimeUpperTask timeUpperTask;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_asynctask, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {
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
                if (timeUpperTask != null && timeUpperTask.getStatus() != AsyncTask.Status.FINISHED)
                    timeUpperTask.cancel(true);
                timeUpperTask = new TimeUpperTask();
                timeUpperTask.execute();
                break;
            case R.id.btn_stop:
                if (timeUpperTask != null && timeUpperTask.getStatus() != AsyncTask.Status.FINISHED)
                    timeUpperTask.cancel(true);
                break;

            default:
                break;
        }
    }

    private class TimeUpperTask extends AsyncTask<Void, Integer, Void> {
        private int value;

        @Override
        protected void onPreExecute() {

            value = Integer.parseInt(txtCount.getText().toString());
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (value <= Integer.MAX_VALUE) {
                value++;
                publishProgress(value);
                if (isCancelled()) {
                    break;
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            txtCount.setText(String.valueOf(values[0]));
        }
    }
}
