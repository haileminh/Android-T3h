package com.hailm.activityintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hai_l on 07/10/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUsername;
    private EditText edtPassWord;
    private Button btnLogin;
    private TextView btnRigister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initizliaze();
        registerListener();
    }


    private void initizliaze() {
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassWord = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRigister = (TextView) findViewById(R.id.btn_register);

    }

    private void registerListener() {
        btnLogin.setOnClickListener(this);
        btnRigister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                break;

            case R.id.btn_register:
                showRegisterScreen();
                break;

            default:
                break;
        }
    }

    private void showRegisterScreen() {
//        Intent Explicit
        Intent intent = new Intent(this, RegisterActivity.class);
        String username = edtUsername.getText().toString();
        String password = edtPassWord.getText().toString();
        if (!username.isEmpty() && !password.isEmpty()) {
            // Tranfer Data
            intent.putExtra(Key.USERNAME, username);
            intent.putExtra(Key.PASSWORD, password);

        }
        
        Bundle bundle = new Bundle();
        bundle.putBoolean("open_music", true);
        bundle.putBoolean("open_background", false);
        intent.putExtra("sound_bundle", bundle);


        ((App) getApplication()).setNumber(100);

        startActivity(intent);
    }
}
