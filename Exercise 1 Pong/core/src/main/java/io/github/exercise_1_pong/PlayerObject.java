package io.github.exercise_1_pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class PlayerObject {
    private ShapeRenderer playerRenderer;
    private Rectangle paddle;
    private float paddleWidth = 20;
    private float paddleHeight = 100;
    private float paddleX;
    private float paddleY;

    // Hardcoded wall bounds
    private final float upperWallY = 900;
    private final float lowerWallY = 200;

    public PlayerObject(float startX, float startY) {
        this.paddleX = startX;
        this.paddleY = startY;
        playerRenderer = new ShapeRenderer();
        paddle = new Rectangle(paddleX, paddleY, paddleWidth, paddleHeight);
    }

    public void update() {
        if (Gdx.input.isTouched()) {
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
            paddleY = touchY - paddleHeight / 2;

            if (paddleY < lowerWallY) {
                paddleY = lowerWallY;
            } else if (paddleY + paddleHeight > upperWallY) {
                paddleY = upperWallY - paddleHeight;
            }

            paddle.y = paddleY;
        }
    }

    public void render() {
        playerRenderer.begin(ShapeRenderer.ShapeType.Filled);
        playerRenderer.setColor(Color.WHITE);
        playerRenderer.rect(paddleX, paddleY, paddleWidth, paddleHeight);
        playerRenderer.end();
    }

    public boolean checkPlayerCollision(Rectangle ball) {
        return paddle.overlaps(ball);
    }

    public Rectangle getBounds() {
        return paddle;
    }

    public void dispose() {
        playerRenderer.dispose();
    }
}
