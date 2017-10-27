package com.hailm.drawabledemo;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int MAX = 5;
    private TextView tvPin;
    private ImageView ivImage;
    private Button btnClipPhoto;

    private ClipDrawable clipDrawable;

    private ImageView ivImage2;

    private LevelListDrawable levelListDrawable;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        setContentView(R.layout.layout_three);

        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
        tvPin = (TextView) findViewById(R.id.tv_pin);

        // Start animation list drawable
        // Bước 1:
//        AnimationDrawable drawable = tvPin.getDrawableState();

        // background = ""
//        AnimationDrawable drawable = (AnimationDrawable) tvPin.getBackground();
//        drawable.start();


        ivImage = (ImageView) findViewById(R.id.iv_image);
        btnClipPhoto = (Button) findViewById(R.id.btn_open);

        clipDrawable = (ClipDrawable) ivImage.getDrawable();

        ivImage2 = (ImageView) findViewById(R.id.iv_photo_level);

        levelListDrawable = (LevelListDrawable) ivImage2.getDrawable();

    }

    private void registerListener() {
        btnClipPhoto.setOnClickListener(this);
        ivImage2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open:
                PhotoTask photoTask = new PhotoTask();
                photoTask.execute();
                break;

            case R.id.iv_photo_level:
                changePhotoLevel();
                break;
            default:
                break;
        }
    }

    private void changePhotoLevel() {
        if (i < 5) {
            i++;
        } else {
            i = 0;
        }
        levelListDrawable.setLevel(i);
    }


    private void clipPhoto() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 1000; i += 10) {
                    clipDrawable.setLevel(i);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private class PhotoTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 0; i <= 10000; i += 1000) {
                publishProgress(i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            clipDrawable.setLevel(values[0]);
        }
    }
}
