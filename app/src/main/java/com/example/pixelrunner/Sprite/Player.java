package com.example.pixelrunner.Sprite;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.pixelrunner.R;

public class Player {
    private int x, y, width, height, walkCounter = 0;
    private Bitmap playerWalk1, playerWalk2;
    private int gravity = 0;
    private int screenY;

    public Player(int screenY, Resources res) {
        this.screenY = screenY;

        playerWalk1 = BitmapFactory.decodeResource(res, R.drawable.player_walk_1);
        playerWalk2 = BitmapFactory.decodeResource(res, R.drawable.player_walk_2);

        width = playerWalk1.getWidth();
        height = playerWalk1.getHeight();

        playerWalk1 = Bitmap.createScaledBitmap(playerWalk1, width, height, false);
        playerWalk2 = Bitmap.createScaledBitmap(playerWalk2, width, height, false);

        x = 64;
        y = screenY / 2;
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(this.getFrames(), this.x, this.y, paint);
    }

    public void update() {
        gravity += 5;
        this.y += gravity;
        if (this.y > this.screenY / 2) {
            this.y = this.screenY / 2;
        }

        if (this.y <= 10) {
            this.y = 10;
        }
    }

    public void jump() {
        if (isOnGround()) {
            this.gravity -= 150;
        }
    }

    public Rect getRect() {
        return new Rect(this.x, this.y, (x + this.width), (y + this.height));
    }

    private boolean isOnGround() {
        return this.y == this.screenY / 2;
    }

    private Bitmap getFrames() {
        if (walkCounter == 0) {
            walkCounter++;
            return playerWalk1;
        }

        walkCounter--;
        return playerWalk2;
    }
}
