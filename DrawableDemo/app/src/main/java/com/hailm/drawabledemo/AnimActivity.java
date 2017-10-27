package com.hailm.drawabledemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * Created by hai_l on 19/10/2017.
 */

public class AnimActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgPhoto;
    private Animation scaleAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        initializeComponents();

    }

    private void initializeComponents() {
        imgPhoto = (ImageView) findViewById(R.id.img_photo);

        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.photo_exit);
        imgPhoto.startAnimation(scaleAnimation);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        imgPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_photo:
                imgPhoto.startAnimation(scaleAnimation);
                break;
            default:
                break;
        }
    }
}
