package com.hailm.demolevellist;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView hinhPin;
    private Button btnTangLevel;
    private Handler handler;
    private Timer timer;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hinhPin = (ImageView) findViewById(R.id.imageView);
        btnTangLevel = (Button) findViewById(R.id.btn_button);
        btnTangLevel.setOnClickListener(this);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0) {
                    if (i < 5) {
                        i++;
                        hinhPin.setImageLevel(i);
                    } else {
                        i = 0;
                        hinhPin.setImageLevel(0);
                    }

                }
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 1000, 1000);


    }


    @Override
    public void onClick(View view) {

    }
}
