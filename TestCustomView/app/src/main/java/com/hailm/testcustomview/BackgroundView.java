package com.hailm.testcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hai_l on 22/09/2017.
 */

public class BackgroundView extends View implements Runnable {
    private Paint paint;
    private boolean isRunning;
    private NumberArtManager manager;

    public BackgroundView(Context context) {
        super(context);
        setUp();
    }

    public BackgroundView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    public BackgroundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    private void setUp() {
        paint = new Paint();
        paint.setAntiAlias(true); // để loại bỏ răng cưa
        start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        manager.drawNumberArt(canvas, paint);
        manager.drawOverlay(canvas, paint);

//        paint = new Paint();
//        paint.setColor(Color.RED);
//        canvas.drawOval(new RectF(20, 20, 200, 200), paint);
    }

    private void start() {
        isRunning = true;
        manager = new NumberArtManager(getContext());
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (isRunning) {
            manager.moveNumberArt();
            postInvalidate();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        isRunning = false;
        super.onDetachedFromWindow();
    }
}
