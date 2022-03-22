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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import static de.nijenhuis.gdxgame.Constants.HEIGHT;
import static de.nijenhuis.gdxgame.Constants.WIDTH;


/**
 *
 * @author max
 */
public class Character extends Entity {
    
    // Movement
    private float speed;
    private Vector2 movementInput;
    // Character
    private String name;
    private int maxHealth;
    private int health;
    private boolean dead;
    
    // Equipped
    private Item equipped;
    private float reach;
    private float cooldown;
    private float timer;
    
    private JsonValue charData;

    public Character(int pMaxHealth, float pSpeed, Item pEquipped, Texture pTexture, Rectangle pRect) {
        super(pTexture, pRect);
        
        speed = pSpeed;
        movementInput = Vector2.Zero;
        
        name = "character";
        maxHealth = pMaxHealth;
        health = maxHealth;
        dead = false;
        
        setEquipped(pEquipped);
    }

    public Character(int pId, Rectangle pRect) {
        super(pRect);
        charData = SaveMachine.loadValue("characters/"+pId);
        
        speed = charData.getFloat("speed");
        movementInput = Vector2.Zero;
        
        
        name = charData.getString("name");
        maxHealth = charData.getInt("maxHealth", 100);
        health = maxHealth;
        
        setEquipped(new Item(charData.getInt("equipped")));
        
        setTexture(new Texture(Gdx.files.internal("data/characters/"+name+".png")));
    }

    @Override
    public void draw(SpriteBatch batch, Vector2 offset) {
        if(dead) return;
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
        dead = true;
        System.out.println("Character died.");
    }

    public void attack(Character c) {
        float damage = equipped.getValue("damage").asFloat();
        c.damage(damage);
        System.out.println("attacked");
    }

    public void setHorizontalMovement(int input) {
        movementInput.x = input;
    }

    public void setVerticalMovement(int input) {
        movementInput.y = input;
    }

    public void update(float delta) {
        float m = 1f;
        if (movementInput.x != 0 && movementInput.y != 0) {
            m = 0.707106781f;
        }
        Vector2 newVelocity = new Vector2(
                movementInput.x * speed * m,
                movementInput.y * speed * m
        );
        setVelocity(newVelocity);
        
        timer -= delta;
    }
    
    public void doAttack(Player p) {
        if(timer > 0) return;
        if(getPosition().dst2(p.getPosition()) <= reach) {
            attack(p);
            timer = cooldown;
        }
    }

    public Item getEquipped() {
        return equipped;
    }

    public void setEquipped(Item pEquipped) {
        equipped = pEquipped;
        cooldown = equipped.getValue("cooldown").asFloat();
        reach = equipped.getInt("reach");
    }
    
    public float getReach() {
        return reach;
    }

}
