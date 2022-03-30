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
    
    public static final float swingSpeed = 250;
    private float curSwingAngle = 0;
    private float swingAngle = 90;
    private boolean weaponFlipped = false;
    
    private JsonValue charData;

    public Character(int pMaxHealth, float pSpeed, Item pEquipped, Texture pTexture, Rectangle pRect) {
        super(pTexture, pRect);
        
        speed = pSpeed;
        movementInput = new Vector2(0, 0);
        
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
        movementInput = new Vector2(0, 0);
        
        
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
            drawEquipped(batch);
        }
    }
    
    private void drawEquipped(SpriteBatch batch) {
        if(movementInput.x < 0) weaponFlipped = true;
        if(movementInput.x > 0) weaponFlipped = false;
        float x = getRX();
        if(!weaponFlipped) {
            x += 39;
        } else {
            x -= 7;
        }
        float y = getRY() + 9;
        float oX = weaponFlipped ? 22 : 10;
        float oY = 10;
        float rotation = weaponFlipped ? -curSwingAngle : curSwingAngle;
        batch.draw(
                equipped.getTexture(),
                x,
                y,
                oX,
                oY,
                equipped.getTexture().getWidth(),
                equipped.getTexture().getHeight(),
                1,
                1,
                rotation,
                0,
                0,
                equipped.getTexture().getWidth(),
                equipped.getTexture().getHeight(),
                weaponFlipped,
                false
        );
    }

    public void damage(float damage) {
        if(dead) return;
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
        if(timer > 0 || dead) return;
        float damage = equipped.getValue("damage").asFloat();
        c.damage(damage);
        curSwingAngle = swingAngle;
        timer = cooldown;
    }
    
    public void applyEffect(int effectId, int effectAmount) {
        switch(effectId) {
            case 0:
                health += effectAmount;
                break;
        }
    }

    public void setHorizontalMovement(int input) {
        movementInput.x = input;
    }

    public void setVerticalMovement(int input) {
        movementInput.y = input;
    }
    
    public void setMovement(Vector2 input) {
        movementInput = input;
    }

    public void update(float delta) {
        if(dead) return;
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
        if(curSwingAngle > -swingAngle) {
            curSwingAngle = curSwingAngle-(delta*swingSpeed);
        }
    }
    
    public boolean canAttack() {
        return !(timer > 0);
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
    
    public boolean isDead() {
        return dead;
    }

}
