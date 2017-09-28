package com.hailm.crazymathgame;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by hai_l on 27/09/2017.
 */

public class CrazyMathActivity extends Activity implements View.OnClickListener {

    private TextView txtNumberOne;
    private TextView txtNumberTwo;
    private TextView txtAnswer;
    private TextView txtScore;
    private TextView txtTime;
    private int numberOne;
    private int numberTwo;
    private int trueAnswer;
    private int time;
    private int anwser;
    private int score;
    private Random random;
    private Button btnTrue;
    private Button btnFalse;
    private boolean isRunning;
    private CountDownTimer countDownTimer;
    private boolean close;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crazymath);

        initializeComponents();
        makeQuestion();
        registerListener();
    }

    private void makeQuestion() {
        time = 6;
        numberOne = random.nextInt(10);
        numberTwo = random.nextInt(10);
        trueAnswer = numberOne + numberTwo;

        if (numberOne % 2 == 0) {
            anwser = trueAnswer;
        } else {
            anwser = trueAnswer + random.nextInt(5);
        }

        txtNumberOne.setText(numberOne + "");
        txtNumberTwo.setText(numberTwo + "");
        txtAnswer.setText(anwser + "");

        countDownTimer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long l) {
                time -= 1;
                txtTime.setText(time + "");
            }

            @Override
            public void onFinish() {
                txtTime.setText("Het gio");
                close = true;

            }
        };
        countDownTimer.start();
    }

    private void initializeComponents() {
        score = 0;
        random = new Random();
        btnTrue = findViewById(R.id.btn_true);
        btnFalse = findViewById(R.id.btn_false);
        txtNumberOne = findViewById(R.id.txt_number_one);
        txtNumberTwo = findViewById(R.id.txt_number_two);
        txtAnswer = findViewById(R.id.txt_number_answer);
        txtScore = findViewById(R.id.txt_number_score);
        txtTime = findViewById(R.id.txt_number_time);

    }

    private void registerListener() {
        btnFalse.setOnClickListener(this);
        btnTrue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (!close) {
            switch (view.getId()) {
                case R.id.btn_true:
                    checkQuestion(true);
                    break;
                case R.id.btn_false:
                    checkQuestion(false);
                    break;
                default:
                    break;
            }
        } else {
            Toast.makeText(this, "Đã hết thời gian", Toast.LENGTH_SHORT).show();
        }

    }

    private void checkQuestion(boolean b) {
        if (b == true) {
            if (anwser == trueAnswer) {
                Toast.makeText(this, "Ban loi dung.", Toast.LENGTH_SHORT).show();
                changeScore();
                countDownTimer.cancel();
                makeQuestion();
            } else {
                Toast.makeText(this, "Ban loi sai.", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (anwser != trueAnswer) {
                Toast.makeText(this, "Ban loi dung.", Toast.LENGTH_SHORT).show();
                countDownTimer.cancel();
                makeQuestion();
                changeScore();
            } else {
                Toast.makeText(this, "Ban loi sai.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void changeScore() {
        score = Integer.parseInt(txtScore.getText().toString());
        score += 1;
        txtScore.setText(score + "");
    }

}
