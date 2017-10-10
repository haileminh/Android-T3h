package com.hailm.asynctaskdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hai_l on 07/10/2017.
 */

public class MainActivity extends Activity implements OnClickListener {
    private TextView tvTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        rigisterListener();
    }


    private void initializeComponents() {
        tvTime = findViewById(R.id.tv_time);
    }

    private void rigisterListener() {
        tvTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                TimeUpperTask task= new TimeUpperTask();
                task.execute();
                break;

            default:
                break;
        }
    }

    private class TimeUpperTask extends AsyncTask<Void, Integer, Void> {
        private static final int MAX_VALUE = 10;
        private int value;

        @Override
        protected void onPreExecute() {
            value = 0;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (value < MAX_VALUE) {
                value++;
                publishProgress(value);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            tvTime.setText(String.valueOf(values[0]));

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MainActivity.this, "DONE", Toast.LENGTH_SHORT).show();
        }
    }
}
