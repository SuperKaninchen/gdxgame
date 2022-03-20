/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonValue;

/**
 *
 * @author max
 */
public class Character extends Entity {

    private String name;
    private int health;
    private int maxHealth;
    private float speed;
    private Vector2 movementInput;
    private Item equipped;
    private Rectangle attackArea;
    
    private float reach = 250;
    
    private JsonValue charData;

    public Character(int pMaxHealth, float pSpeed, Item pEquipped, Texture pTexture, Rectangle pRect) {
        super(pTexture, pRect);
        maxHealth = pMaxHealth;
        health = maxHealth;
        speed = pSpeed;
        equipped = pEquipped;
        movementInput = Vector2.Zero;
        attackArea = new Rectangle(getRX(), getRX(), 128, 128);
    }

    public Character(int pId, Rectangle pRect) {
        super(pRect);
        charData = SaveMachine.loadValue("characters/"+pId);
        name = charData.getString("name");
        maxHealth = charData.getInt("maxHealth", 100);
        health = maxHealth;
        equipped = new Item(charData.getInt("equipped"));
        movementInput = Vector2.Zero;
        attackArea = new Rectangle(getRX(), getRX(), 128, 128);
        setTexture(new Texture(Gdx.files.internal("data/characters/"+name+".png")));
    }

    @Override
    public void draw(SpriteBatch batch, Vector2 offset) {
        super.draw(batch, offset);
        if (equipped != null) {
            batch.draw(equipped.getTexture(), getRX() + 24, getRY());
        }
    }

    public void damage(float damage) {
        System.out.println("got damaged");
        health -= damage;
        if (health <= 0) {
            die();
        }
    }
    
    private void die() {
        System.out.println("Character died.");
    }
    
    public void aim(int x, int y) {
        Vector2 newPos = new Vector2(x-getRX(), y-getRY());
        newPos = newPos.clamp(0, reach);
        newPos = newPos.add(new Vector2(getRX(), getRY()));
        attackArea = new Rectangle(newPos.x-(attackArea.width/2), newPos.y-(attackArea.height/2), attackArea.width, attackArea.height);
    }

    public void attack(Character c) {
        float damage = equipped.getValue("damage").asFloat();
        c.damage(damage);
    }

    public Rectangle getAttackArea() {
        return attackArea;
    }

    public void setHorizontalMovement(int input) {
        movementInput.x = input;
    }

    public void setVerticalMovement(int input) {
        movementInput.y = input;
    }

    public void update() {
        float m = 1f;
        if (movementInput.x != 0 && movementInput.y != 0) {
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
