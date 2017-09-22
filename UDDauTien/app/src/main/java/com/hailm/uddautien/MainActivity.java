package com.hailm.uddautien;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnXuatThongBao, btnXinChao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);

        btnXuatThongBao = (Button) findViewById(R.id.btn_thongbao);
        btnXinChao = (Button) findViewById(R.id.btn_xinchao);

        btnXuatThongBao.setOnClickListener(this);
        btnXinChao.setOnClickListener(this);

//        btnXuatThongBao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Đây là button Thông báo!!!", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        btnXinChao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "Đây là button xin chào!!!", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    public void xuatThongbao(View view) {
        switch (view.getId()) {
            case R.id.btn_thongbao:
                Toast.makeText(MainActivity.this, "Đây là button thông báo!", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_xinchao:
                Toast.makeText(MainActivity.this, "Đây là button xin chào!", Toast.LENGTH_LONG).show();
                break;
        }

//        String getId = String.valueOf(view.getId());
//        Button btnXuatThongBao = (Button) view;
//        String getText = btnXuatThongBao.getText().toString();
//        Toast.makeText(MainActivity.this, "Xin chào các bạn" + getText, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_thongbao:
                Toast.makeText(MainActivity.this, "Đây là button thông báo!", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_xinchao:
                Toast.makeText(MainActivity.this, "Đây là button xin chào!", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
