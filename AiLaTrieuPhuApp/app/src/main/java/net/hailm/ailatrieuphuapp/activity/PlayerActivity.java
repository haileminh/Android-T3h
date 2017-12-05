package net.hailm.ailatrieuphuapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import net.hailm.ailatrieuphuapp.R;
import net.hailm.ailatrieuphuapp.dialog.AudienceDialog;
import net.hailm.ailatrieuphuapp.dialog.CallDialog;
import net.hailm.ailatrieuphuapp.dialog.NoticeDialog;
import net.hailm.ailatrieuphuapp.manager.DatabaseManager;
import net.hailm.ailatrieuphuapp.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hai_l on 22/11/2017.
 */

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseManager databaseManager;
    private List<Question> questions;

    private NoticeDialog noticeDialog;
    private CallDialog callDialog;
    private AudienceDialog audienceDialog;
    private DrawerLayout drawerLayout;
    private LinearLayout llPlayer;

    private Handler handler;
    private Runnable runnable;
    private Runnable runnableTimer;


    private Button btnHide;
    private TextView txtTimer, txtMoney, txtQuestion, txtLevel, txtCase[];
    private ProgressBar pgTimer;
    private ImageView imgPlayer;
    private ImageView btnCall, btnAudience, btnStop, btn5050, btnChange;

    private boolean isPlaying;
    private boolean isReady;
    private int level;
    private int timer;


    public PlayerActivity() {
        handler = new Handler();
        isPlaying = false;
        isReady = false;
        txtCase = new TextView[4];
        databaseManager = new DatabaseManager(this);
        questions = new ArrayList<>();
        questions.addAll(databaseManager.getQuestions());
        level = 1;

        runnableTimer = new Runnable() {
            @Override
            public void run() {
                if (isPlaying) timer--;
                txtTimer.setText(String.valueOf(timer));

                handler.postDelayed(runnableTimer, 1000);
            }
        };
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_player);

        noticeDialog = new NoticeDialog(this);
        callDialog = new CallDialog(this);
        audienceDialog = new AudienceDialog(this);
        initializeComponents();
        resgisterListener();
        loadNotie();
    }

    private void loadNotie() {

    }


    private void initializeComponents() {
        drawerLayout = findViewById(R.id.activity_player);
        drawerLayout.openDrawer(Gravity.START);

        txtCase[0] = findViewById(R.id.txt_case_a);
        txtCase[1] = findViewById(R.id.txt_case_b);
        txtCase[2] = findViewById(R.id.txt_case_c);
        txtCase[3] = findViewById(R.id.txt_case_d);
        txtTimer = findViewById(R.id.txt_timer);
        txtMoney = findViewById(R.id.txt_money);
        txtLevel = findViewById(R.id.txt_level);
        txtQuestion = findViewById(R.id.txt_question);

        llPlayer = findViewById(R.id.ll_player);


        pgTimer = findViewById(R.id.pg_timer);

        btnHide = findViewById(R.id.btn_hide);
        btnStop = findViewById(R.id.btn_stop);
        btnChange = findViewById(R.id.btn_change);
        btn5050 = findViewById(R.id.btn_5050);
        btnAudience = findViewById(R.id.btn_audience);
        btnCall = findViewById(R.id.btn_call);


        txtMoney.setText("0");
    }

    private void resgisterListener() {
        btnStop.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btn5050.setOnClickListener(this);
        btnAudience.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnHide.setOnClickListener(this);

        txtCase[0].setOnClickListener(this);
        txtCase[1].setOnClickListener(this);
        txtCase[2].setOnClickListener(this);
        txtCase[3].setOnClickListener(this);

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Toast.makeText(PlayerActivity.this, "Hello close Drawer", Toast.LENGTH_SHORT).show();
                if (!isReady) {
                    startGame();
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    private void startGame() {
        isReady = true;
        //TODO
        playGame();
    }

    private void playGame() {
        drawerLayout.closeDrawer(Gravity.START);
        llPlayer.setVisibility(View.VISIBLE);
        setQuestion();
        handler.post(runnableTimer);
    }

    private void setQuestion() {
        Question ques = questions.get(level - 1);
        txtCase[0].setEnabled(true);
        txtCase[1].setEnabled(true);
        txtCase[2].setEnabled(true);
        txtCase[3].setEnabled(true);

        txtCase[0].setBackgroundResource(R.drawable.player_answer_background_normal);
        txtCase[1].setBackgroundResource(R.drawable.player_answer_background_normal);
        txtCase[2].setBackgroundResource(R.drawable.player_answer_background_normal);
        txtCase[3].setBackgroundResource(R.drawable.player_answer_background_normal);

        txtLevel.setText("Câu: " + ques.getLevel());
        txtQuestion.setText(ques.getQuestion());
        txtCase[0].setText("A: " + ques.getCaseA());
        txtCase[1].setText("B: " + ques.getCaseB());
        txtCase[2].setText("C: " + ques.getCaseC());
        txtCase[3].setText("D: " + ques.getCaseD());

        timer = 30;
        pgTimer.setVisibility(View.VISIBLE);
        isPlaying = true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_stop:
                stopGameDialog();
                break;

            case R.id.btn_change:
                changeQuestionsDialog();
                break;

            case R.id.btn_5050:

                break;

            case R.id.btn_audience:
                audienceDialog();
                break;

            case R.id.btn_call:
                callDialog();
                break;

            case R.id.btn_hide:
                drawerLayout.closeDrawer(Gravity.START);
                break;

            default:
                break;

        }
    }

    private void audienceDialog() {
        audienceDialog.show();
    }

    private void callDialog() {

        callDialog.show();
    }

    private void changeQuestionsDialog() {
        noticeDialog.setNotification("Bạn có muốn đổi câu hỏi", "Đồng ý", "Hủy bỏ", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_change) {

                }
                noticeDialog.dismiss();
            }
        });
        noticeDialog.show();
    }

    private void stopGameDialog() {
        noticeDialog.setCancelable(true);

        noticeDialog.setNotification("Bạn có muốn dừng cuộc chơi hay không? ", "Đồng ý", "Hủy bỏ", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_stop) {
                    finish();
                }
                noticeDialog.dismiss();
            }
        });

        noticeDialog.show();
    }
}
