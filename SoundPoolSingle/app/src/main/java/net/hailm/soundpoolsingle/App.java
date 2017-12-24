package net.hailm.soundpoolsingle;

import android.app.Application;

/**
 * Created by hai_l on 21/12/2017.
 */

public class App extends Application {
    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

}
