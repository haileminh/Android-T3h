package com.hailm.clockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hai_l on 23/09/2017.
 */

public class ClockView extends View implements Runnable {
    private Paint paint;
    private ClockManager clockManager;
    private boolean isRunning;

    public ClockView(Context context) {
        super(context);
        setUp();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUp();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp();
    }

    private void setUp() {
        paint = new Paint();
        paint.setAntiAlias(true);

        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "TragicMarker.otf");
        paint.setTypeface(typeface);

        start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        clockManager.drawBg(canvas, paint);
        clockManager.drawText(canvas, paint);
    }

    private void start() {
        isRunning = true;
        Thread thread = new Thread(this);
        clockManager = new ClockManager(getContext());
        thread.start();
    }

    @Override
    public void run() {
        while (isRunning) {
            clockManager.updateDateTime();
            postInvalidate();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isRunning = false;
    }
}
