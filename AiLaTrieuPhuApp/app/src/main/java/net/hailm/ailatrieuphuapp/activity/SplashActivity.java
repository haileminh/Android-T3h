package net.hailm.ailatrieuphuapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.hailm.ailatrieuphuapp.R;

/**
 * Created by hai_l on 23/11/2017.
 */

public class SplashActivity extends AppCompatActivity {
    private ImageView imgSplash;
    private TextView txtGameCenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initializeComponents();
    }

    private void initializeComponents() {
        imgSplash = findViewById(R.id.img_splash);
        txtGameCenter = findViewById(R.id.txt_game_center);

        Animation animImg = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.alpha_bg);
        imgSplash.startAnimation(animImg);

        Animation animLogo = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.transiton_logo);
        txtGameCenter.startAnimation(animLogo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4000);
    }

}
