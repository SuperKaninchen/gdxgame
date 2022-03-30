/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author max
 */
public class Enemy extends Character {

    public Enemy(int id, Rectangle rect) {
        super(id, rect);
    }

    public void update(float delta, Player p) {
        super.update(delta);
        trackPlayer(p);
    }

    public void doAttack(Player p) {
        if (!canAttack() ||isDead()) {
            return;
        }
        if (getPosition().dst(p.getPosition()) <= getReach()) {
            attack(p);
        }
    }

    private void trackPlayer(Player p) {
        if (p.getPosition().dst(getPosition()) > getReach()) {
            Vector2 input = p.getPosition().cpy().sub(getPosition()).nor();
            setMovement(input);
        } else {
            setMovement(Vector2.Zero);
        }
    }

}
