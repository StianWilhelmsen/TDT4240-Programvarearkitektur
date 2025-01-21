package io.github.exercise_1_pong;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

public class Main extends ApplicationAdapter {

    private PongBall pongBall;
    private Wall upperWall;
    private Wall lowerWall;

    private PlayerObject player;
    private ComputerObject computer;
    private Score playerScore;
    private Score computerScore;


    @Override
    public void create() {
        pongBall = new PongBall();
        upperWall = new Wall(650, 900, 33);
        lowerWall = new Wall(650, 200, 33);
        player = new PlayerObject(650, 550);
        computer = new ComputerObject(1680, 550);
        playerScore = new Score(450);
        computerScore = new Score(1780);
    }


    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        if (playerScore.hasWon()) {
            playerScore.renderWinMessage();
            return;
        } else if (computerScore.hasWon()) {
            computerScore.renderWinMessage();
            return;
        }

        float deltaTime = Gdx.graphics.getDeltaTime();
        pongBall.updatePosition(deltaTime, lowerWall, upperWall, player, computer, playerScore, computerScore);
        pongBall.render();
        upperWall.render();
        lowerWall.render();
        player.update();
        player.render();
        computer.update(deltaTime);
        computer.render();
        playerScore.render();
        computerScore.render();
    }

    @Override
    public void dispose() {
        pongBall.dispose();
        upperWall.dispose();
        lowerWall.dispose();
        player.dispose();
    }
}
