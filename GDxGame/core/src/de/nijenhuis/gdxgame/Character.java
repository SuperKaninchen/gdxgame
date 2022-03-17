/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author max
 */
public class Character extends Entity {
    
    private int health;
    private int maxHealth;
    private float speed;
    private Vector2 movementInput;
    private Item equipped;
    private Rectangle attackArea;
    
    public Character(int pMaxHealth, float pSpeed, Texture pTexture, Rectangle pRect) {
        super(pTexture, pRect);
        maxHealth = pMaxHealth;
        health = maxHealth;
        speed = pSpeed;
        movementInput = Vector2.Zero;
        attackArea = new Rectangle();
    }
    
    public void damage(float damage) {
        health -= damage;
        if(health <= 0) {
            die();
        }
    }
    
    public void attack(Character c) {
        float damage = equipped.getValue("damage");
        c.damage(damage);
    }
    
    public Rectangle getAttackArea() {
        return attackArea;
    }
    
    private void die() {
        
    }
    
    public void setHorizontalMovement(int input) {
        movementInput.x = input;
    }

    public void setVerticalMovement(int input) {
        movementInput.y = input;
    }
    
    public void update() {
        float m = 1f;
        if(movementInput.x != 0 && movementInput.y != 0) {
            m = 0.707106781f;
        }
        Vector2 newVelocity = new Vector2(
                movementInput.x * speed * m,
                movementInput.y * speed * m
        );
        setVelocity(newVelocity);
    }
    
    public Item getEquipped() {
        return equipped;
    }
    
    public void setEquipped(Item pEquipped) {
        equipped = pEquipped;
    }
    
}
