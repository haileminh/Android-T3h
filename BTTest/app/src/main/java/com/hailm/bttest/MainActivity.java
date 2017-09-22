package com.hailm.bttest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvXuatThongBao;
    EditText etTenDangNhap;
    Button btnDongY;

    EditText etSoa, etSob;
    Button btnTinh;
    TextView tvKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvXuatThongBao = (TextView) findViewById(R.id.tvXuatThongBao);
        etTenDangNhap = (EditText) findViewById(R.id.et_tenDangNhap);
        btnDongY = (Button) findViewById(R.id.btnDongY);
        btnDongY.setOnClickListener(this);

        tvKetQua = (TextView) findViewById(R.id.tv_KetQua);
        etSoa = (EditText) findViewById(R.id.et_soA);
        etSob = (EditText) findViewById(R.id.et_soB);
        btnTinh = (Button) findViewById(R.id.btnTinh);
        btnTinh.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDongY:
                String sTenDangNhap = etTenDangNhap.getText().toString();
                tvXuatThongBao.setText(sTenDangNhap);
                tvXuatThongBao.setTextColor(Color.RED);
                break;
            case R.id.btnTinh:
                int isoA = Integer.parseInt(etSoa.getText().toString());
                int isoB = Integer.parseInt(etSob.getText().toString());
                int iTong = isoA + isoB;
                tvKetQua.setText(String.valueOf(iTong));
                break;

        }


    }
}
