/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author max
 */
public class Entity {

    private Texture texture;
    private Vector2 velocity;
    private Rectangle rect;
    private Vector2 position;

    public Entity(Texture pTexture, Rectangle pRect) {
        texture = pTexture;
        velocity = Vector2.Zero;
        rect = pRect;
        position = Vector2.Zero;
    }

    public void move(float delta) {
        position = new Vector2(
            position.x + velocity.x * delta,
            position.y + velocity.y * delta
        );
    }

    public float getRX() {
        return rect.x;
    }

    public float getRY() {
        return rect.y;
    }

    public Rectangle getRectangle() {
        return rect;
    }
    
    public void setRectangle(Rectangle pRect) {
        rect = pRect;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setVelocity(Vector2 pVelocity) {
        velocity = pVelocity;
    }

}
