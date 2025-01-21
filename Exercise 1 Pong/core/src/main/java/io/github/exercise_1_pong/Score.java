package io.github.exercise_1_pong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score {
    private int score;
    private float startX;
    private float startY = 550;
    private BitmapFont font;
    private SpriteBatch spriteBatch;
    private static final int WINNING_SCORE = 21;

    public Score(float startX) {
        this.startX = startX;
        this.score = 0;
        this.spriteBatch = new SpriteBatch();
        this.font = new BitmapFont();

        this.font.getData().setScale(2.5f);
        this.font.setColor(Color.WHITE);
    }

    public void increment() {
        score++;
    }

    public void reset() {
        score = 0;
    }

    public boolean hasWon() {
        return score >= WINNING_SCORE;
    }

    public void render() {
        spriteBatch.begin();
        font.draw(spriteBatch, "Score: " + score, startX, startY);
        spriteBatch.end();
    }

    public void renderWinMessage() {
        spriteBatch.begin();
        font.setColor(Color.YELLOW);
        font.draw(spriteBatch, "WINNER!", startX, startY + 50);
        spriteBatch.end();
    }

    public void dispose() {
        font.dispose();
        spriteBatch.dispose();
    }
}
