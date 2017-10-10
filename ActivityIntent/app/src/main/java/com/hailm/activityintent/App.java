package com.hailm.activityintent;

import android.app.Application;

/**
 * Created by hai_l on 07/10/2017.
 */

public class App extends Application {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // this
        //getApplicationContext()


    }
}
