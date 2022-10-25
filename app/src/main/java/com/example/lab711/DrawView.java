package com.example.lab711;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    Paint p = new Paint();
    static Game game = new Game();

    public void DrawPic1(Canvas canvas, int x, int y) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.p1);
        canvas.drawBitmap(bitmap, x, y, p);
    }

    public void DrawPic2(Canvas canvas, int x, int y) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.p2);
        canvas.drawBitmap(bitmap, x, y, p);
    }

    public boolean onTouchEvent(MotionEvent event) {

        if (game.getRun() != 0) {
            MainActivity.sound.playSound();
            float X = event.getX();
            float Y = event.getY();

            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int w = displayMetrics.widthPixels;
            int h = displayMetrics.heightPixels;

            int i = 0, j = 0;
            if (X >= 0 && X <= w / 3) i = 0;
            if (X >= w / 3 + 10 && X <= 2 * w / 3 + 10) i = 1;
            if (X >= 2 * w / 3 + 20) i = 2;

            if (Y >= 0 && Y <= w / 3) j = 0;
            if (Y >= w / 3 + 10 && Y <= 2 * w / 3 + 10) j = 1;
            if (Y >= 2 * w / 3 + 20) j = 2;

            if (game.get(i, j) == 0) {
                game.set(i, j);
                game.changePlayer();
                invalidate();
            }
        }
        return true;
    }

    public DrawView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int w = displayMetrics.widthPixels;
        int h = displayMetrics.heightPixels;

        p.setColor(Color.BLUE);
        p.setStrokeMiter(30);

        canvas.drawLine(0, w / 3, w, w / 3, p);
        canvas.drawLine(0, 2 * w / 3, w, 2 * w / 3, p);
        canvas.drawLine(w / 3, 50, w / 3, w, p);
        canvas.drawLine(2 * w / 3, 50, 2 * w / 3, w, p);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (game.get(i, j) == 1) DrawPic1(canvas, 20 + i * w / 3, 20 + j * w / 3);
                else if (game.get(i, j) == 2) DrawPic2(canvas, 20 + i * w / 3, 20 + j * w / 3);

        String s = "";
        int stat = game.check();
        SharedPreferences sharedPreferences = MainActivity.sharedPreferences;

        if (stat == 0 && game.getPlayer() == 1) s = "Ход " + sharedPreferences.getString("player1", "первого");
        if (stat == 0 && game.getPlayer() == 2) s = "Ход " + sharedPreferences.getString("player2", "второго");

        if (stat != 0 && game.getPlayer() == 2) s = sharedPreferences.getString("player1", "Первый") + " победил";
        if (stat != 0 && game.getPlayer() == 1) s = sharedPreferences.getString("player2", "Второй") + " победил";

        p.setColor(Color.RED);
        p.setStrokeMiter(50);
        switch (stat){
            case 1:
                canvas.drawLine(w/6, 0, w/6, w, p);
                MainActivity.sound.playWin();
                break;
            case 2:
                canvas.drawLine(3*w/6, 0, 3*w/6, w, p);
                MainActivity.sound.playWin();
                break;
            case 3:
                canvas.drawLine(5*w/6, 0, 5*w/6, w, p);
                MainActivity.sound.playWin();
                break;
            case 4:

                canvas.drawLine(0, w/6, w, w/6, p);
                MainActivity.sound.playWin();
                break;
            case 5:

                canvas.drawLine(0, 3*w/6, w, 3*w/6, p);
                MainActivity.sound.playWin();
                break;
            case 6:

                canvas.drawLine(0, 5*w/6, w, 5*w/6, p);
                MainActivity.sound.playWin();
                break;
            case 7:
                canvas.drawLine(0, 0, w, w, p);
                MainActivity.sound.playWin();
                break;
            case 8:

                canvas.drawLine(w, 0, 0, w, p);
                MainActivity.sound.playWin();
                break;
            default:
                break;
        }

        if (stat == 9) s = "Ничья";

        p.setTextSize(100);
        canvas.drawText(s, w/2-250, w+150, p);
    }
}