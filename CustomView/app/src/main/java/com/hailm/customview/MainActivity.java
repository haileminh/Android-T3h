package com.hailm.customview;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_one);
    }


    public void demoDateTime() {
        long current = System.currentTimeMillis();
        java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("HH:mm:ss a");

        String result = dateFormat.format(current);

    }
}
