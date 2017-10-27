package com.hailm.labintent2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hai_l on 14/10/2017.
 */

public class ClockView extends View implements Runnable {
    private Paint paint;
    private boolean isRunning;
    private int height;
    private int width;
    private Clock clock;

    public ClockView(Context context) {
        super(context);
        setup(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context);
    }


    private void setup(Context context) {
        paint = new Paint();
        paint.setAntiAlias(true);

        width = 100;
        height = 100;

        start();
    }

    private void start() {
        isRunning = true;
        Thread thread = new Thread(this);
        clock = new Clock(width, height);
        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        clock.draw(canvas, paint);
    }

    @Override
    public void run() {
        while (isRunning) {
            clock.updateDateTime();
            postInvalidate();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
