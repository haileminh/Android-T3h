package com.hailm.demoappmaytinh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Float fThamSoThuNhat, fThamSoThuHai;
    String sToanTu, sXuatManHinh = "";
    EditText etGiaTri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etGiaTri = (EditText) findViewById(R.id.et_ketQua);

        int[] idButton = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
                R.id.btn_cham, R.id.btn_chia, R.id.btn_cong, R.id.btn_tru, R.id.btn_nhan, R.id.btn_delete, R.id.btn_bang};

        for (int id : idButton) {
            View v = (View) findViewById(id);
            v.setOnClickListener(this);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cong:
                sToanTu = "+";
                toanTu();
                break;

            case R.id.btn_tru:
                sToanTu = "-";
                toanTu();
                break;

            case R.id.btn_nhan:
                sToanTu = "*";
                toanTu();
                break;

            case R.id.btn_chia:
                sToanTu = "/";
                toanTu();
                break;

            case R.id.btn_bang:
                tinhToan(sToanTu);
                break;
            case R.id.btn_delete:
                etGiaTri.setText("0");
                sXuatManHinh="0";
                break;

            default:
                if (sXuatManHinh.equals("0")) {
                    sXuatManHinh = "";
                }
                sXuatManHinh += ((Button) view).getText().toString();
                etGiaTri.setText(sXuatManHinh);

        }
    }

    private void toanTu() {
        fThamSoThuNhat = Float.parseFloat(etGiaTri.getText().toString());
        sXuatManHinh = "0";
        etGiaTri.setText("0");
    }

    private void tinhToan(String sToanTu) {
        Float ketQua = 0.0f;
        fThamSoThuHai = Float.parseFloat(etGiaTri.getText().toString());
        if (sToanTu == "+") {
            ketQua = fThamSoThuNhat + fThamSoThuHai;
        }

        if (sToanTu == "-") {
            ketQua = fThamSoThuNhat - fThamSoThuHai;
        }

        if (sToanTu == "*") {
            ketQua = fThamSoThuNhat * fThamSoThuHai;
        }

        if (sToanTu == "/") {
            ketQua = fThamSoThuNhat / fThamSoThuHai;
        }

        etGiaTri.setText(String.valueOf(ketQua));
        fThamSoThuNhat = 0.0f;
        fThamSoThuHai = 0.0f;
        sXuatManHinh = "0";
    }
}
