package com.hailm.testcustomview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.R.attr.value;

/**
 * Created by hai_l on 23/09/2017.
 */

public class NumberArtManager {
    public static final int LENGHT = 32;
    private List<NumberArt> numberArts;
    private Random rd;
    private int width;
    private int height;

    public NumberArtManager(Context context) {
        numberArts = new ArrayList<>();
        rd = new Random();
        initalize(context);

    }

    private void initalize(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

        for (int i = 0; i < LENGHT; i++) {
            int x = rd.nextInt(width - NumberArt.SIZE);
            int y = NumberArt.SIZE + rd.nextInt(height - NumberArt.SIZE);
            int orient = rd.nextInt(4);
            int color = getRamdomColor();
            int value = rd.nextInt(10);
            NumberArt numberArt = new NumberArt(x, y, orient, color, value);
            numberArts.add(numberArt);
        }
    }

    public void drawOverlay(Canvas canvas, Paint paint) {
        paint.setColor(Color.parseColor("#adFFFFFF"));
        canvas.drawRect(new RectF(0, 0, width, height), paint);
    }

    public void drawNumberArt(Canvas canvas, Paint paint) {
        for (int i = 0; i < numberArts.size(); i++) {
            numberArts.get(i).draw(canvas, paint);
        }
    }

    public void moveNumberArt() {
        for (int i = 0; i < numberArts.size(); i++) {
            numberArts.get(i).move(width, height);
        }
    }

    private int getRamdomColor() {
        return Color.rgb(rd.nextInt(256), rd.nextInt(256), rd.nextInt(256));
    }
}
