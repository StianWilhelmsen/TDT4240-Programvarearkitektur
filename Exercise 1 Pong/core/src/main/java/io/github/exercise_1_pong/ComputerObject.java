package io.github.exercise_1_pong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;

public class ComputerObject {
    private ShapeRenderer computerRenderer;
    private Rectangle paddle;
    private float paddleWidth = 20;
    private float paddleHeight = 100;
    private float paddleX;
    private float paddleY;
    private float speed = 400;
    private boolean movingUp = true;

    // Hardcoded wall bounds
    private final float upperWallY = 900;
    private final float lowerWallY = 200;

    public ComputerObject(float startX, float startY) {
        this.paddleX = startX;
        this.paddleY = startY;
        computerRenderer = new ShapeRenderer();
        paddle = new Rectangle(paddleX, paddleY, paddleWidth, paddleHeight);
    }

    public void update(float deltaTime) {

        if (movingUp) {
            paddleY += speed * deltaTime;
            if (paddleY + paddleHeight >= upperWallY) {
                paddleY = upperWallY - paddleHeight;
                movingUp = false;
            }
        } else {
            paddleY -= speed * deltaTime;
            if (paddleY <= lowerWallY) {
                paddleY = lowerWallY;
                movingUp = true;
            }
        }

        paddle.y = paddleY;
    }

    public void render() {
        computerRenderer.begin(ShapeRenderer.ShapeType.Filled);
        computerRenderer.setColor(Color.RED);
        computerRenderer.rect(paddle.x, paddle.y, paddle.width, paddle.height);
        computerRenderer.end();
    }

    public boolean checkComputerCollision(Rectangle ball) {
        return paddle.overlaps(ball);
    }

    public Rectangle getBounds() {
        return paddle;
    }

    public void dispose() {
        computerRenderer.dispose();
    }
}
