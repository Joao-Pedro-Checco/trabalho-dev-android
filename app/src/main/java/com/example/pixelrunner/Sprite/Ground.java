package com.example.pixelrunner.Sprite;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.pixelrunner.R;

public class Ground {
    private int x = 0, y;
    private int screenX, screenY;
    private Bitmap bitmap;

    public Ground(int screenX, int screenY, Resources res) {
        this.screenX = screenX;
        this.screenY = screenY / 2;

        this.y = this.screenY + (this.screenY / 2);

        bitmap = BitmapFactory.decodeResource(res, R.drawable.ground);
        bitmap = Bitmap.createScaledBitmap(bitmap, this.screenX, this.screenY, false);
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.bitmap, this.x, this.y, paint);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
