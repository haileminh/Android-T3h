package net.hailm.fragmentls14.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.hailm.fragmentls14.R;
import net.hailm.fragmentls14.model.DataBase;

/**
 * Created by hai_l on 16/11/2017.
 */

public class AddCongViecDialog extends Dialog {
    private DataBase dataBase;

    public AddCongViecDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_add_congviec);
        getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );

        initializeComponents();
    }

    private void initializeComponents() {
        dataBase = new DataBase(getContext(), "ghichu.sqlite", null, 1);
        final EditText edtName = findViewById(R.id.edt_name);
        Button btnAdd = findViewById(R.id.btn_add);
        Button btnCancel = findViewById(R.id.btn_cancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(getContext(), "Vui lòng nhập tên công việc", Toast.LENGTH_SHORT).show();
                } else {
                    dataBase.queryData("INSERT INTO CongViec VALUES(null, '" + name + "')");
                    Toast.makeText(getContext(), "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
