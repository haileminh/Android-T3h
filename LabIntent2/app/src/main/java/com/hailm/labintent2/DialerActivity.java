package com.hailm.labintent2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hai_l on 14/10/2017.
 */

public class DialerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnSao, btnThang;
    private ImageView btnCall, btnBackSpace, btnBack;
    private TextView txtNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);
        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
        btn0 = (Button) findViewById(R.id.btn_0);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn6 = (Button) findViewById(R.id.btn_6);
        btn7 = (Button) findViewById(R.id.btn_7);
        btn8 = (Button) findViewById(R.id.btn_8);
        btn9 = (Button) findViewById(R.id.btn_9);
        btnSao = (Button) findViewById(R.id.btn_sao);
        btnThang = (Button) findViewById(R.id.btn_thang);
        btnCall = (ImageView) findViewById(R.id.btn_call);
        btnBackSpace = (ImageView) findViewById(R.id.btn_backspace);
        btnBack = (ImageView) findViewById(R.id.btn_back);
        txtNumber = (TextView) findViewById(R.id.txt_number);
    }

    private void registerListener() {
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnSao.setOnClickListener(this);
        btnThang.setOnClickListener(this);
        btnBackSpace.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        txtNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_0:
                addText(btn0.getText());
                break;
            case R.id.btn_1:
                addText(btn1.getText());
                break;
            case R.id.btn_2:
                addText(btn2.getText());
                break;
            case R.id.btn_3:
                addText(btn3.getText());
                break;
            case R.id.btn_4:
                addText(btn4.getText());
                break;
            case R.id.btn_5:
                addText(btn5.getText());
                break;
            case R.id.btn_6:
                addText(btn6.getText());
                break;
            case R.id.btn_7:
                addText(btn7.getText());
                break;
            case R.id.btn_8:
                addText(btn8.getText());
                break;
            case R.id.btn_9:
                addText(btn9.getText());
                break;
            case R.id.btn_sao:
                addText(btnSao.getText());
                break;
            case R.id.btn_thang:
                addText(btnThang.getText());
                break;
            case R.id.btn_backspace:
                backSpace();
                break;
            case R.id.btn_call:
                showCall();
                break;
            default:
                break;
        }
    }

    private void showCall() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String call = "tel:" + txtNumber.getText().toString();
        intent.setData(Uri.parse(call));
        startActivity(intent);
    }

    private void backSpace() {
        String backspace = null;
        if (txtNumber.getText().length() > 0) {
            StringBuilder str = new StringBuilder(txtNumber.getText());
            str.deleteCharAt(txtNumber.getText().length() - 1);
            backspace = str.toString();
            txtNumber.setText(backspace);
        }
    }

    private void addText(CharSequence text) {
        String str = txtNumber.getText().toString() + text.toString();
        txtNumber.setText(str);
    }
}
