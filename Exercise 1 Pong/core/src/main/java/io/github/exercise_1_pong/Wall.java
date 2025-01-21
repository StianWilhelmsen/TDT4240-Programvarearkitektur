package io.github.exercise_1_pong;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class Wall {

    private ShapeRenderer wallRenderer;
    private float wallBallRadius = 15;
    private float startX;
    private float startY;
    private float wallCount;
    private float spaceBetween = 2;
    private List<Rectangle> wallSegments;

    public Wall(float startX, float startY, float wallCount) {
        this.startX = startX;
        this.startY = startY;
        this.wallCount = wallCount;
        wallRenderer = new ShapeRenderer();
        wallSegments = new ArrayList<>();
        for (int i = 0; i < wallCount; i++) {
            float segmentX = startX + i * (2 * wallBallRadius + spaceBetween);
            float segmentY = startY;
            wallSegments.add(new Rectangle(segmentX - wallBallRadius, segmentY - wallBallRadius,
                2 * wallBallRadius, 2 * wallBallRadius));
        }
    }

    public void render() {
        wallRenderer.begin(ShapeRenderer.ShapeType.Filled);
        wallRenderer.setColor(Color.WHITE);

        for (Rectangle segment : wallSegments) {
            wallRenderer.circle(segment.x + wallBallRadius, segment.y + wallBallRadius, wallBallRadius);
        }

        wallRenderer.end();
    }

    public boolean checkCollision(Rectangle ball) {
        for (Rectangle segment : wallSegments) {
            if (segment.overlaps(ball)) {
                return true;
            }
        }
        return false;
    }

    public void dispose() {
        wallRenderer.dispose();
    }
}
