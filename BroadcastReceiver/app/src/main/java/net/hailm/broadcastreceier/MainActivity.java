package net.hailm.broadcastreceier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import net.hailm.broadcastreceier.constant.Action;
import net.hailm.broadcastreceier.constant.Key;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNumberA, edtNumberB;
    private Button btnPlus;
    private TextView txtResult;
    private MainReceiver mainReceiver;


    // B1: Khai báo Broadcast receier
    private CalculatorReceiver calculatorReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        registerReceivers();
    }

    private void initializeComponents() {
        edtNumberA = findViewById(R.id.edt_number_a);
        edtNumberB = findViewById(R.id.edt_number_b);
        btnPlus = findViewById(R.id.btn_plus);
        txtResult = findViewById(R.id.txt_result);
        btnPlus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_plus:
                sendMessageEvent();
                break;
            default:
                break;
        }
    }

    private void sendMessageEvent() {
        String numerAStr = edtNumberA.getText().toString();
        String numerBStr = edtNumberB.getText().toString();
        if (numerAStr.isEmpty() == false && numerBStr.isEmpty() == false) {
            int a = Integer.parseInt(numerAStr);
            int b = Integer.parseInt(numerBStr);

            Intent intent = new Intent(Action.ACTION_PLUS);
            intent.putExtra(Key.NUMBER_A, a);
            intent.putExtra(Key.NUMBER_B, b);

            sendBroadcast(intent);
        }
    }

    private void registerReceivers() {

        // Tạo receiver
        calculatorReceiver = new CalculatorReceiver();
        mainReceiver = new MainReceiver();
        // Tạo bộ lọc intent
        IntentFilter filter = new IntentFilter();
        filter.addAction(Action.ACTION_PLUS);
        filter.addAction(Action.ACTION_RESULT);

        // Đăng ký
        registerReceiver(calculatorReceiver, filter);
        registerReceiver(mainReceiver, filter);

    }

    private void unregisterReceivers() {
        // Hủy đăng kí.
        unregisterReceiver(calculatorReceiver);
        unregisterReceiver(mainReceiver);
    }

    @Override
    protected void onDestroy() {
        unregisterReceivers();
        super.onDestroy();
    }

    private class MainReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //
            if (intent != null) {
                String action = intent.getAction();
                if (action == null) {
                    return;
                }

                switch (action) {
                    case Action.ACTION_RESULT:
                        int numberA = intent.getIntExtra(Key.RESULT, 0);
                        txtResult.setText(numberA + "");
                        break;

                    default:
                        break;
                }
            }
        }
    }


}
