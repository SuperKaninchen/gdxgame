/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    Sprite sprite;
    private Vector2 velocity;

    public Entity(String path, Vector2 pos) {
        sprite = TextureMachine.getSprite(path);
        sprite.setPosition(pos.x, pos.y);
        setBounds(sprite.getX(), sprite.getY(),
                sprite.getWidth(), sprite.getRegionHeight());
    }

    /*public Entity(Texture pTexture, Rectangle pRect) {
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
    }*/

    public Entity(Item pItem, Vector2 pos) {
        sprite = new Sprite(pItem.getTexture());
        velocity = new Vector2(0, 0);
    }
    
    public Entity() {
        velocity = new Vector2(0, 0);
    }
    
    public void setSprite(Sprite pSprite, Vector2 pos) {
        sprite = pSprite;
        sprite.setPosition(pos.x, pos.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        sprite.draw(batch);
    }
    
    @Override
    public void act(float delta) {
        move(delta);
    }
    
    public void move(float delta) {
        sprite.setPosition(sprite.getX() + velocity.x * delta, sprite.getY() + velocity.y * delta);
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }
    
    public Vector2 getPosition() {
        return new Vector2(sprite.getX(), sprite.getY());
    }
    
    /*public void draw__OLD(SpriteBatch batch, Vector2 offset) {
        rect = new Rectangle(
                position.x + offset.x - (rect.width / 2),
                position.y + offset.y - (rect.height / 2),
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
    }*/

    public void setVelocity(Vector2 pVelocity) {
        velocity = pVelocity;
    }

}
