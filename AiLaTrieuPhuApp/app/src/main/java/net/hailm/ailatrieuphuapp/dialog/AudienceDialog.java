package net.hailm.ailatrieuphuapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import net.hailm.ailatrieuphuapp.R;

/**
 * Created by hai_l on 22/11/2017.
 */

public class AudienceDialog extends Dialog implements View.OnClickListener {

    public AudienceDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(false);
        setContentView(R.layout.audience_dialog);
        getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        );
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initializeComponents();
        registerListener();
    }


    private void initializeComponents() {
    }

    private void registerListener() {
        findViewById(R.id.btn_close).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close:
                dismiss();
                break;
            default:
                break;
        }
    }
}
