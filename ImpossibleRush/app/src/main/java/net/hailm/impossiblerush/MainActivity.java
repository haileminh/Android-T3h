package net.hailm.impossiblerush;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements Runnable {
    private static final long TIME_TRANSLATE = 2000;
    @BindView(R.id.btn_right)
    Button btnRight;
    @BindView(R.id.btn_left)
    Button btnLeft;
    @BindView(R.id.btn_retry)
    Button btnRetry;

    @BindView(R.id.img_ball)
    ImageView imgBall;
    @BindView(R.id.img_square)
    ImageView imgSquare;

    @BindView(R.id.txt_score)
    TextView txtScore;

    private int degree;
    private int mSquareColor;
    private int mBallColour;

    private Thread thread;
    private Handler handler;

    private int score;
    private boolean isPlay;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();

    }

    private void initializeComponents() {
        ButterKnife.bind(this);
        handler = new Handler();
        thread = new Thread(this);
        thread.start();
    }

    private void playAudio(int resoure) {
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(MainActivity.this, resoure);
        mediaPlayer.start();
    }

    private void initGame() {
        score = 0;
        degree = 0;
        mBallColour = 0;
        mSquareColor = 0;
        isPlay = true;

        txtScore.setText("00");
        btnRetry.setVisibility(View.INVISIBLE);
        imgSquare.clearAnimation();
    }

    @OnClick(R.id.btn_retry)
    public void playAgain() {
        Toast.makeText(this, "Retry", Toast.LENGTH_SHORT).show();
        initGame();
        btnRetry.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.btn_right)
    public void clickRigth() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                degree, degree + 90,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        rotateAnimation.setDuration(250);
        rotateAnimation.setFillAfter(true);
        imgSquare.setAnimation(rotateAnimation);

        degree = degree + 90;
        mSquareColor--;
        if (mSquareColor < 0) {
            mSquareColor = 3;
        }
    }

    @OnClick(R.id.btn_left)
    public void clickLeft() {
        RotateAnimation rotateAnimation = new RotateAnimation(
                degree, degree - 90,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        rotateAnimation.setDuration(250);
        rotateAnimation.setFillAfter(true);

        imgSquare.setAnimation(rotateAnimation);
        degree = degree - 90;
        mSquareColor++;

        if (mSquareColor > 3) {
            mSquareColor = 0;
        }
    }

    @Override
    public void run() {
        while (true) {
            if (isPlay == true) {
                startBallAnimation();
                try {
                    Thread.sleep(TIME_TRANSLATE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (mBallColour == mSquareColor) {
                    playAudio(R.raw.point);
                    score++;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            txtScore.setText(score + "");
                        }
                    });
                } else {
                    playAudio(R.raw.gameover);
                    isPlay = false;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            txtScore.setText("Game Over!!!");
                            btnRetry.setVisibility(View.VISIBLE);
                        }
                    });
                }

            }
        }
    }

    private void startBallAnimation() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                mBallColour = new Random().nextInt(4);
                imgBall.setImageResource(R.drawable.ball_0 + mBallColour);

                TranslateAnimation translateAnimation = new TranslateAnimation(
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.0f,
                        Animation.RELATIVE_TO_PARENT, 0.7f);

                translateAnimation.setDuration(TIME_TRANSLATE);
                imgBall.startAnimation(translateAnimation);
            }
        });
    }
}
