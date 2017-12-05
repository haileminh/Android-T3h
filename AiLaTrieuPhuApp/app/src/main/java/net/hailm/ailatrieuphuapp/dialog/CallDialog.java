package net.hailm.ailatrieuphuapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.hailm.ailatrieuphuapp.R;

/**
 * Created by hai_l on 22/11/2017.
 */

public class CallDialog extends Dialog implements View.OnClickListener {
    private ImageView btnHelpCall[];
    private TextView txtAnswer;
    private RelativeLayout callsLayout;

    public CallDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.call_dialog);
        getWindow().setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.MATCH_PARENT
        );
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
        btnHelpCall = new ImageView[4];
        btnHelpCall[0] = findViewById(R.id.btn_help_call_01);
        btnHelpCall[1] = findViewById(R.id.btn_help_call_02);
        btnHelpCall[2] = findViewById(R.id.btn_help_call_03);
        btnHelpCall[3] = findViewById(R.id.btn_help_call_04);

        txtAnswer = findViewById(R.id.txt_answer);
        callsLayout = findViewById(R.id.rl_calls);
    }

    private void registerListener() {
        btnHelpCall[0].setOnClickListener(this);
        btnHelpCall[1].setOnClickListener(this);
        btnHelpCall[2].setOnClickListener(this);
        btnHelpCall[3].setOnClickListener(this);
        findViewById(R.id.btn_close).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_help_call_01:
                break;

            case R.id.btn_help_call_02:
                break;

            case R.id.btn_help_call_03:
                break;

            case R.id.btn_help_call_04:
                break;

            case R.id.btn_close:
                dismiss();
                break;

            default:
                break;
        }
    }
}
