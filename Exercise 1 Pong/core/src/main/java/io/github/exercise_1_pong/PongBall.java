package io.github.exercise_1_pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class PongBall {
    private ShapeRenderer ballRenderer;
    private float ballRadius = 20;
    private float ballX = 900;
    private float ballY = 500;
    private float xSpeed = 200;
    private float ySpeed = 100;

    private float wallStart = 650;
    private float wallEnd = 1675;

    public PongBall() {
        ballRenderer = new ShapeRenderer();
    }

    public void updatePosition(float deltaTime, Wall lowerWall, Wall upperWall, PlayerObject player, ComputerObject computer, Score playerScore, Score computerScore) {
        ballX += xSpeed * deltaTime;
        ballY += ySpeed * deltaTime;

        Rectangle ballBounds = new Rectangle(ballX - ballRadius, ballY - ballRadius, 2 * ballRadius, 2 * ballRadius);

        if (lowerWall.checkCollision(ballBounds) || upperWall.checkCollision(ballBounds)) {
            ySpeed = -ySpeed;
            xSpeed *= 1.2;
        }

        if (player.checkPlayerCollision(ballBounds)) {
            xSpeed = -xSpeed;
            xSpeed *= 1.2;
            ballX = player.getBounds().x + player.getBounds().width + ballRadius; // Prevent sticking
        }

        if (computer.checkComputerCollision(ballBounds)) {
            xSpeed = -xSpeed;
            xSpeed *= 1.2;
            ballX = computer.getBounds().x - ballRadius; // Prevent sticking
        }

        if (ballX < wallStart) {
            computerScore.increment();
            resetPosition();
        } else if (ballX > wallEnd) {
            playerScore.increment();
            resetPosition();
        }
    }

    private void resetPosition() {
        ballX = Gdx.graphics.getWidth() / 2;
        ballY = Gdx.graphics.getHeight() / 2;
        xSpeed = -xSpeed;
    }


    public void render() {
        ballRenderer.begin(ShapeRenderer.ShapeType.Filled);
        ballRenderer.setColor(Color.GREEN);
        ballRenderer.circle(ballX, ballY, ballRadius);
        ballRenderer.end();
    }

    public void dispose() {
        ballRenderer.dispose();
    }
}
