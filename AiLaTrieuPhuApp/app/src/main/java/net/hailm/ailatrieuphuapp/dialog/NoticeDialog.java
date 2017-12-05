package net.hailm.ailatrieuphuapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import net.hailm.ailatrieuphuapp.R;

/**
 * Created by hai_l on 22/11/2017.
 */

public class NoticeDialog extends Dialog implements View.OnClickListener {
    private Button btnCancel;
    private Button btnOk;
    private TextView txtNotice;

    public NoticeDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setCancelable(true);
        setContentView(R.layout.notice_dialog);

        getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        initializeComponents();
    }

    private void initializeComponents() {
        btnCancel = findViewById(R.id.btn_cancle);
        btnOk = findViewById(R.id.btn_ok);
        txtNotice = findViewById(R.id.txt_notice);
    }

    public void setNotification(String notification,
                                String txtOk,
                                String txtCancel,
                                View.OnClickListener onClickListener) {
        btnOk.setText(txtOk);
        btnCancel.setText(txtCancel);
        txtNotice.setText(notification);
        btnCancel.setOnClickListener(onClickListener);
        btnOk.setOnClickListener(onClickListener);

        if (txtCancel == null) {
            btnCancel.setVisibility(View.GONE);
        }

        if (onClickListener == null) {
            btnOk.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
