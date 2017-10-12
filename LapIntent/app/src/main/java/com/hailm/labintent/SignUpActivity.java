package com.hailm.labintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hai_l on 10/10/2017.
 */

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtDisplayName;
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnGetStarted;
    private TextView tvSignUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initialize();
        registerListener();
    }


    private void initialize() {
        tvSignUp = (TextView) findViewById(R.id.tv_sign_up);
        edtDisplayName = (EditText) findViewById(R.id.edt_display_name);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnGetStarted = (Button) findViewById(R.id.btn_get_start);
    }

    private void registerListener() {
        tvSignUp.setOnClickListener(this);
        btnGetStarted.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_up:
                showSplashScreen();
                break;

            case R.id.btn_get_start:
                signUp();
                break;

            default:
                break;
        }
    }

    private void signUp() {
        String displayName = edtDisplayName.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (displayName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT).show();
        } else if (!displayName.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);

            // Tranfer data
            // intent.putExtra(Key.EMAIL, email);
            //intent.putExtra(Key.PASSWORD, password);

            Bundle bundle = new Bundle();
            bundle.putString(Key.EMAIL, email);
            bundle.putString(Key.PASSWORD, password);
            intent.putExtra(Key.DATA, bundle);


            startActivity(intent);
        }
    }

    private void showSplashScreen() {
        Intent intent = new Intent(SignUpActivity.this, SplashActivity.class);
        startActivity(intent);
    }
}