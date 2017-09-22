package com.hailm.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hai_l on 21/09/2017.
 */

public class NumberArtManager {
    private static final int LENGHT = 32;
    private List<NumberArt> numberArts;
    private Random random;
    private int width;
    private int heigth;

    public NumberArtManager(Context context) {
        numberArts = new ArrayList<>();
        random = new Random();
        initalize(context);
    }

    private void initalize(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        heigth = displayMetrics.heightPixels;

        for (int i = 0; i < LENGHT; i++) {
            int x = random.nextInt(width - NumberArt.SIZE);
            int y = NumberArt.SIZE + random.nextInt(heigth - NumberArt.SIZE);
            int orient = random.nextInt(4);
            int color = getRamdomColor();
            int value = random.nextInt(10);
            NumberArt art = new NumberArt(x, y, orient, color, value);
            numberArts.add(art);
        }
    }
    public void drawOverlay(Canvas canvas, Paint paint){
        paint.setColor(Color.parseColor("#aaaFFFFF"));
        canvas.drawRect(new RectF(0,0,width, heigth), paint);
    }

    public void drawNumberArt(Canvas canvas, Paint paint) {
        for (int i = 0; i < numberArts.size(); i++) {
            numberArts.get(i).draw(canvas, paint);
        }
    }

    public void moveNumberArt() {
        for (int i = 0; i < numberArts.size(); i++) {
            numberArts.get(i).move(width, heigth);
        }
    }

    private int getRamdomColor() {
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
