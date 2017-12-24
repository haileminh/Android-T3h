package net.hailm.eznote.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import net.hailm.eznote.R;

/**
 * Created by hai_l on 07/12/2017.
 */

public class NoteDialog extends Dialog {
    private TextView txtDate;
    private TextView txtContent;
    private Button btnOk, btnCancel;

    public NoteDialog(@NonNull Context context) {
        super(context);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_note);

        getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        initializeComponents();
    }

    private void initializeComponents() {
        txtContent = findViewById(R.id.txt_dialog_content);
        txtDate = findViewById(R.id.txt_dialog_date);
        btnCancel = findViewById(R.id.btn_cancel);
        btnOk = findViewById(R.id.btn_ok);
    }

    public void setNotification(String date,
                                String content,
                                String txtOk, String txtCancel,
                                View.OnClickListener onClickListener) {
        btnOk.setText(txtOk);
        btnCancel.setText(txtCancel);
        txtDate.setText(date);
        txtContent.setText(content);
        btnCancel.setOnClickListener(onClickListener);
        btnOk.setOnClickListener(onClickListener);
    }

}
