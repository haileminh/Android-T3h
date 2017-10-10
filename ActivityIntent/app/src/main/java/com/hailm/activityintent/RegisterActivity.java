package com.hailm.activityintent;

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
 * Created by hai_l on 07/10/2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUsername;
    private EditText edtPassWord;
    private EditText edtConfirmPass;
    private TextView btnRigister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initizliaze();
        registerListener();
    }


    private void initizliaze() {
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassWord = (EditText) findViewById(R.id.edt_password);
        edtConfirmPass = (EditText) findViewById(R.id.edt_confirm_pass);
        btnRigister = (TextView) findViewById(R.id.btn_register);

        // Receive Data

        Intent intent = getIntent();
        String username = intent.getStringExtra(Key.USERNAME);
        String password = intent.getStringExtra(Key.PASSWORD);

        if (username != null && password != null) {
            edtUsername.setText(username);
            edtPassWord.setText(password);
        }


        Bundle soundBundle = intent.getBundleExtra("sound_bundle");
        soundBundle.getBoolean("open_music", false);
        soundBundle.getBoolean("open_background", false);

        int number = ((App) getApplication()).getNumber();
        Toast.makeText(this, number +"", Toast.LENGTH_SHORT).show();
    }

    private void registerListener() {
        btnRigister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                break;
            default:
                break;
        }
    }


}
