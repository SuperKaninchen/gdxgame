/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

/**
 *
 * @author max
 */
public class PlayerInputProcessor implements InputProcessor {

    Player player;

    public PlayerInputProcessor(Player pPlayer) {
        player = pPlayer;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.A:
                player.setHorizontalMovement(-1);
                break;
            case Keys.D:
                player.setHorizontalMovement(1);
                break;
            case Keys.W:
                player.setVerticalMovement(1);
                break;
            case Keys.S:
                player.setVerticalMovement(-1);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Keys.A:
                player.setHorizontalMovement(0);
                break;
            case Keys.D:
                player.setHorizontalMovement(0);
                break;
            case Keys.W:
                player.setVerticalMovement(0);
                break;
            case Keys.S:
                player.setVerticalMovement(0);
                break;
        }
        return true;
    }

    public boolean keyTyped(char character) {
        return false;
    }

    public boolean touchDown(int x, int y, int pointer, int button) {
        switch(button) {
            case Input.Buttons.LEFT:
                player.doAttack();
                break;
            default:
                return false;
        }
        return true;
    }

    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    public boolean mouseMoved(int x, int y) {
        player.aim(x, y);
        return true;
    }

    public boolean scrolled(float amountX, float amountY) {
        player.scroll((int) amountY);
        return true;
    }

}
