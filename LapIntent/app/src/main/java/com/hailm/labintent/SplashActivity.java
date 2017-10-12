package com.hailm.labintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hai_l on 10/10/2017.
 */

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignIn;
    private Button btnGetStarted;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initialize();
        registerListener();
    }


    private void initialize() {
        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        btnGetStarted = (Button) findViewById(R.id.btn_get_start);
    }

    private void registerListener() {
        btnSignIn.setOnClickListener(this);
        btnGetStarted.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sign_in:
                showSignInScreen();
                break;

            case R.id.btn_get_start:
                showSignUpScreen();
                break;

            default:
                break;
        }

    }

    private void showSignUpScreen() {
        Intent intent = new Intent(SplashActivity.this, SignUpActivity.class);

        startActivity(intent);
    }

    private void showSignInScreen() {
        Intent intent = new Intent(SplashActivity.this, SignInActivity.class);

        startActivity(intent);
    }
}
