package com.hailm.activitydemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hai_l on 23/09/2017.
 */

public class LoginActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {
    private static final String TAG = "LoginActivity";
    private EditText edtPhoneNumber;
    private EditText edtPassword;
    private Button btnLogin;
    private TextView txtNumber;
    private int number = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_login);

        initializeComponents();
        registerListener();
    }

    private void initializeComponents() {
        edtPhoneNumber = findViewById(R.id.edt_phone_number);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        txtNumber = findViewById(R.id.txt_number);
    }

    private void registerListener() {
        btnLogin.setOnClickListener(this);
        txtNumber.setOnClickListener(this);
        btnLogin.setOnLongClickListener(this);

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                logIn();
                break;
            case R.id.txt_number:
                addNumber();
                break;
            default:
                break;
        }
    }

    private void addNumber() {

        number += 1;
        txtNumber.setText(number + "");
    }

    private void logIn() {
        String phone = edtPhoneNumber.getText().toString();
        String pass = edtPassword.getText().toString();
        if (phone.isEmpty()) {
            edtPhoneNumber.setError("Phone number can not empty");
            return;
        }

        if (pass.isEmpty()) {
            edtPassword.setError("Password can not empty");
            return;
        }

        if (phone.equals("123") && pass.equals("abc")) {
            Toast.makeText(this, "Login sucess", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Login fail", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onLongClick(View view) {
        // Giữ thật lâu vào đối tượng
        switch (view.getId()) {
            case R.id.btn_login:
                Toast.makeText(this, "Long Click", Toast.LENGTH_LONG).show();
                return false;
            default:
                return false; // neu false thì chuong trinh chay vao click
        }

    }
}
