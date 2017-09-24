package com.hailm.magicboxgame;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by hai_l on 24/09/2017.
 */

public class MagicBoxActivity extends Activity implements View.OnClickListener {
    private int number;
    private TextView txtNumber;
    private Button button;
    private int[] id_button;
    private Random random;
    private int sum = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magicbox);

        initializeComponents();
        registerListener();
    }

    private void initializeComponents() {
        random = new Random();
        txtNumber = findViewById(R.id.txt_number);

        id_button = new int[]{R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9};

        for (int id : id_button) {
            button = findViewById(id);
            button.setOnClickListener(this);
        }
    }

    private void registerListener() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                action();
                changeNumber();
                break;
            default:
                break;
        }
    }

    private void changeNumber() {
        number = Integer.parseInt(txtNumber.getText().toString());
        number -= 1;
        txtNumber.setText(number + "");
        if (number == 0) {
            Toast.makeText(this, "YOU LOSS", Toast.LENGTH_SHORT).show();
            for(int btn:id_button){
                button = findViewById(btn);
                button.setEnabled(false);
            }

        }
    }

    private void action() {
        for (int id : id_button) {
            button = findViewById(id);
            button.setBackgroundResource(R.drawable.background);
            button.setText(random.nextInt(10) + "");

            int a = Integer.parseInt(button.getText().toString());
            sum+=a;
            if (sum > 50){
                Toast.makeText(this, "YOU WIN", Toast.LENGTH_SHORT).show();
                for (int btn : id_button) {
                    button = findViewById(btn);
                    button.setEnabled(false);
                }
            }

        }
    }
}
