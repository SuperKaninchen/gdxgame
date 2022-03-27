/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author max
 */
public class Entity extends Actor {
    
    TextureRegion region;

    private Texture texture;
    
    private Vector2 velocity;
    private Rectangle rect;
    private Vector2 position;

    public Entity(Texture pTexture, Rectangle pRect) {
        texture = pTexture;
        velocity = Vector2.Zero;
        rect = pRect;
        position = new Vector2(pRect.x, pRect.y);
    }
    
    public Entity(Rectangle pRect) {
        texture = null;
        velocity = Vector2.Zero;
        rect = pRect;
        position = new Vector2(pRect.x, pRect.y);
    }
    
    public Entity(Item pItem, float x, float y) {
        texture = pItem.getTexture();
        velocity = Vector2.Zero;
        rect = new Rectangle(x, y, 100, 100);
        position = new Vector2(x, y);
    }
    
    public void draw(SpriteBatch batch, Vector2 offset) {
        rect = new Rectangle(
                position.x + offset.x - (rect.width/2),
                position.y + offset.y - (rect.height/2),
                rect.width,
                rect.height
        );
        batch.draw(texture, rect.x, rect.y, rect.width, rect.height);
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
        position = new Vector2(pRect.x, pRect.y);
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
    
    public void setTexture(Texture pTexture) {
        texture = pTexture;
    }

    public void setVelocity(Vector2 pVelocity) {
        velocity = pVelocity;
    }

}
