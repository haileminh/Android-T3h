package com.hailm.testrelativelayout;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView vXuatThongBao;
    EditText etTenDangNhap;
    Button btnDongY;


    EditText etSoA, etSoB;
    Button btnTinh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btapdung);

        vXuatThongBao = (TextView) findViewById(R.id.vXuatThongBao);
        etTenDangNhap = (EditText) findViewById(R.id.etTenDangNhap);
        btnDongY = (Button) findViewById(R.id.btnDongY);

        btnDongY.setOnClickListener(this);


        etSoA = (EditText) findViewById(R.id.etSoa);
        etSoB = (EditText) findViewById(R.id.etSob);
        btnTinh = (Button) findViewById(R.id.btnTinh);
        btnTinh.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String sTenDangNhap = etTenDangNhap.getText().toString();
        vXuatThongBao.setText(sTenDangNhap);
        vXuatThongBao.setTextColor(Color.RED);

        int iSoa = Integer.parseInt(etSoA.getText().toString());
        int iSob = Integer.parseInt(etSoB.getText().toString());
        int iTong = iSoa + iSob;

        vXuatThongBao.setText(String.valueOf(iTong));
    }
}
