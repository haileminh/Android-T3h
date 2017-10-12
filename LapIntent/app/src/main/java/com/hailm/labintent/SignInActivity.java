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

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtEmail;
    private EditText edtPassword;
    private Button btnSignIn;
    private TextView tvSignIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initialize();
        registerListener();
    }


    private void initialize() {
        tvSignIn = (TextView) findViewById(R.id.tv_sign_in);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnSignIn = (Button) findViewById(R.id.btn_sign_in);

        Intent intent = getIntent();
        //String email = intent.getStringExtra(Key.EMAIL);
        //String password = intent.getStringExtra(Key.PASSWORD);

        // use bundle getData
        Bundle data = intent.getBundleExtra(Key.DATA);
        if (data != null) {
            String email = data.getString(Key.EMAIL);
            String password = data.getString(Key.PASSWORD);

            if (email != null && password != null) {
                edtEmail.setText(email);
                edtPassword.setText(password);
            }
        }
    }

    private void registerListener() {
        tvSignIn.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_in:
                showSplashScreen();
                break;

            case R.id.btn_sign_in:
                signIn();
                break;
            default:
                break;
        }
    }

    private void signIn() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            Toast.makeText(this, "Login sucess", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter data", Toast.LENGTH_SHORT).show();
        }
    }

    private void showSplashScreen() {
        Intent intent = new Intent(SignInActivity.this, SplashActivity.class);
        startActivity(intent);
    }
}
