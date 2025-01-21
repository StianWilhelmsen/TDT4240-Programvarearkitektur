package io.github.exercise_1_helicopter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture heli1, heli2, heli3, heli4;
    private Animation<TextureRegion> helicopterAnimation;
    private BitmapFont font;

    private float stateTime;
    private float posX = 100;
    private float posY = 100;
    private boolean facingRight = true;

    private float xSpeed = 200;
    private float ySpeed = 150;

    private float posX2 = 300;
    private float posY2 = 300;
    private boolean facingRight2 = true;
    private float xSpeed2 = 250;
    private float ySpeed2 = 100;

    private float posX3 = 500;
    private float posY3 = 200;
    private boolean facingRight3 = true;
    private float xSpeed3 = 150;
    private float ySpeed3 = 200;

    private float posX4 = 700;
    private float posY4 = 400;
    private boolean facingRight4 = true;
    private float xSpeed4 = 180;
    private float ySpeed4 = 120;

    @Override
    public void create() {
        batch = new SpriteBatch();

        heli1 = new Texture("heli1.png");
        heli2 = new Texture("heli2.png");
        heli3 = new Texture("heli3.png");
        heli4 = new Texture("heli4.png");

        TextureRegion[] helicopterFrames = new TextureRegion[4];
        helicopterFrames[0] = new TextureRegion(heli1);
        helicopterFrames[1] = new TextureRegion(heli2);
        helicopterFrames[2] = new TextureRegion(heli3);
        helicopterFrames[3] = new TextureRegion(heli4);

        helicopterAnimation = new Animation<>(0.1f, helicopterFrames);
        helicopterAnimation.setPlayMode(Animation.PlayMode.LOOP);

        font = new BitmapFont(); // For drawing coordinates
        stateTime = 0f;
    }

    @Override
    public void render() {
        // Clear screen with background color
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        stateTime += Gdx.graphics.getDeltaTime();

        // Get the current frame of animation
        TextureRegion currentFrame = helicopterAnimation.getKeyFrame(stateTime);

        updatePosition(Gdx.graphics.getDeltaTime());
        updateSecondSpritePosition(Gdx.graphics.getDeltaTime());
        updateThirdSpritePosition(Gdx.graphics.getDeltaTime());
        updateFourthSpritePosition(Gdx.graphics.getDeltaTime());

        batch.begin();
        if (facingRight) {
            batch.draw(currentFrame, posX, posY);
        } else {
            batch.draw(currentFrame, posX + currentFrame.getRegionWidth(), posY, -currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        }

        if (facingRight2) {
            batch.draw(currentFrame, posX2, posY2);
        } else {
            batch.draw(currentFrame, posX2 + currentFrame.getRegionWidth(), posY2, -currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        }

        if (facingRight3) {
            batch.draw(currentFrame, posX3, posY3);
        } else {
            batch.draw(currentFrame, posX3 + currentFrame.getRegionWidth(), posY3, -currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        }

        if (facingRight4) {
            batch.draw(currentFrame, posX4, posY4);
        } else {
            batch.draw(currentFrame, posX4 + currentFrame.getRegionWidth(), posY4, -currentFrame.getRegionWidth(), currentFrame.getRegionHeight());
        }

        // Draw the coordinates in the upper-right corner
        font.draw(batch, "X1: " + (int) posX + " Y1: " + (int) posY,
            Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 30);
        font.draw(batch, "X2: " + (int) posX2 + " Y2: " + (int) posY2,
            Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 50);
        font.draw(batch, "X3: " + (int) posX3 + " Y3: " + (int) posY3,
            Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 70);
        font.draw(batch, "X4: " + (int) posX4 + " Y4: " + (int) posY4,
            Gdx.graphics.getWidth() - 150, Gdx.graphics.getHeight() - 90);

        batch.end();

        checkCollision();
    }

    private void updatePosition(float delta) {
        posX += xSpeed * delta;
        posY += ySpeed * delta;

        if (posX <= 0 || posX + 130 >= Gdx.graphics.getWidth()) {
            xSpeed = -xSpeed;
            facingRight = !facingRight;
        }

        if (posY <= 0 || posY + 52 >= Gdx.graphics.getHeight()) {
            ySpeed = -ySpeed;
        }
    }

    private void updateSecondSpritePosition(float delta) {
        posX2 += xSpeed2 * delta;
        posY2 += ySpeed2 * delta;

        if (posX2 <= 0 || posX2 + 130 >= Gdx.graphics.getWidth()) {
            xSpeed2 = -xSpeed2;
            facingRight2 = !facingRight2;
        }

        if (posY2 <= 0 || posY2 + 52 >= Gdx.graphics.getHeight()) {
            ySpeed2 = -ySpeed2;
        }
    }

    private void updateThirdSpritePosition(float delta) {
        posX3 += xSpeed3 * delta;
        posY3 += ySpeed3 * delta;

        if (posX3 <= 0 || posX3 + 130 >= Gdx.graphics.getWidth()) {
            xSpeed3 = -xSpeed3;
            facingRight3 = !facingRight3;
        }

        if (posY3 <= 0 || posY3 + 52 >= Gdx.graphics.getHeight()) {
            ySpeed3 = -ySpeed3;
        }
    }

    private void updateFourthSpritePosition(float delta) {
        posX4 += xSpeed4 * delta;
        posY4 += ySpeed4 * delta;

        if (posX4 <= 0 || posX4 + 130 >= Gdx.graphics.getWidth()) {
            xSpeed4 = -xSpeed4;
            facingRight4 = !facingRight4;
        }

        if (posY4 <= 0 || posY4 + 52 >= Gdx.graphics.getHeight()) {
            ySpeed4 = -ySpeed4;
        }
    }

    private void checkCollision() {
        Rectangle rect1 = new Rectangle(posX, posY, 130, 52);
        Rectangle rect2 = new Rectangle(posX2, posY2, 130, 52);
        Rectangle rect3 = new Rectangle(posX3, posY3, 130, 52);
        Rectangle rect4 = new Rectangle(posX4, posY4, 130, 52);

        if (rect1.overlaps(rect2)) {
            xSpeed = -xSpeed;
            ySpeed = -ySpeed;
            xSpeed2 = -xSpeed2;
            ySpeed2 = -ySpeed2;
        }

        if (rect1.overlaps(rect3)) {
            xSpeed = -xSpeed;
            ySpeed = -ySpeed;
            xSpeed3 = -xSpeed3;
            ySpeed3 = -ySpeed3;
        }

        if (rect1.overlaps(rect4)) {
            xSpeed = -xSpeed;
            ySpeed = -ySpeed;
            xSpeed4 = -xSpeed4;
            ySpeed4 = -ySpeed4;
        }

        if (rect2.overlaps(rect3)) {
            xSpeed2 = -xSpeed2;
            ySpeed2 = -ySpeed2;
            xSpeed3 = -xSpeed3;
            ySpeed3 = -ySpeed3;
        }

        if (rect2.overlaps(rect4)) {
            xSpeed2 = -xSpeed2;
            ySpeed2 = -ySpeed2;
            xSpeed4 = -xSpeed4;
            ySpeed4 = -ySpeed4;
        }

        if (rect3.overlaps(rect4)) {
            xSpeed3 = -xSpeed3;
            ySpeed3 = -ySpeed3;
            xSpeed4 = -xSpeed4;
            ySpeed4 = -ySpeed4;
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        heli1.dispose();
        heli2.dispose();
        heli3.dispose();
        heli4.dispose();
        font.dispose();
    }
}
