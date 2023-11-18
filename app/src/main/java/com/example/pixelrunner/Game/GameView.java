package com.example.pixelrunner.Game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.pixelrunner.Activity.GameOverActivity;
import com.example.pixelrunner.Sprite.Ground;
import com.example.pixelrunner.Sprite.Player;
import com.example.pixelrunner.Sprite.Sky;
import com.example.pixelrunner.Sprite.Snail;

public class GameView extends SurfaceView implements Runnable {
    private Thread thread;
    private boolean isPlaying;
    private int screenX, screenY;
    private Ground ground;
    private Sky sky1, sky2;
    private Paint paint;
    private Player player;
    private Snail snail;

    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.screenX = screenX;
        this.screenY = screenY;

        sky1 = new Sky(this.screenX, this.screenY, getResources());
        sky2 = new Sky(this.screenX, this.screenY, getResources());
        ground = new Ground(this.screenX, this.screenY, getResources());
        player = new Player(screenY, getResources());
        snail = new Snail(screenX, screenY, getResources());

        sky2.setX(this.screenX);

        paint = new Paint();
    }

    @Override
    public void run() {
        while(isPlaying) {
            update();
            draw();
            sleep();
        }
    }

    private void update() {
        sky1.update();
        sky2.update();
        player.update();
        snail.update();
        checkCollision();
    }

    protected void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();
            sky1.draw(canvas, paint);
            sky2.draw(canvas, paint);
            ground.draw(canvas, paint);
            player.draw(canvas, paint);
            snail.draw(canvas, paint);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            player.jump();
        }

        return true;
    }

    public void checkCollision() {
        if (isColliding(player.getRect(), snail.getRect())) {
            // game over
            gameOver();
        }
    }

    private boolean isColliding(Rect playerRect, Rect snailRect) {
        return playerRect.intersect(snailRect);
    }

    private void gameOver() {
        isPlaying = false;
        Intent intent = new Intent(getContext(), GameOverActivity.class);
        getContext().startActivity(intent);
    }
}
