package com.example.pixelrunner.Sprite;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.pixelrunner.Game.GameView;
import com.example.pixelrunner.R;

public class Snail {
    private int x, y, width, height, animationCounter;
    private int screenX, screenY;
    private Bitmap frame1, frame2;

    public Snail(int screenX, int screenY, Resources res) {
        this.screenX = screenX;
        this.screenY = screenY;

        frame1 = BitmapFactory.decodeResource(res, R.drawable.snail_1);
        frame2 = BitmapFactory.decodeResource(res, R.drawable.snail_2);

        width = frame1.getWidth();
        height = frame1.getHeight();

        frame1 = Bitmap.createScaledBitmap(frame1, width, height, false);
        frame2 = Bitmap.createScaledBitmap(frame2, width, height, false);

        x = screenX + (screenX / 2);
        y = (screenY / 2) + 150;
    }

    public void update() {
        x -= 20;
        if (x + this.frame1.getWidth() <= 0) {
            x = this.screenX + (screenY / 2);
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.getFrames(), this.x, this.y, paint);
    }

    private Bitmap getFrames() {
        if (animationCounter == 0) {
            animationCounter++;
            return frame1;
        }

        animationCounter--;
        return frame2;
    }
}
