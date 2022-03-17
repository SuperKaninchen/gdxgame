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
public class Player extends Character {

    private Inventory inventory;
    private Inventory hotbar;
    
    private static GameScreen gc;

    public Player(GameScreen pGc, Texture pTexture) {
        super(100, 250f, pTexture, new Rectangle(400, 240, 32, 32));
        inventory = new Inventory(27);
        hotbar = new Inventory(5);
        hotbar.setItem(1, new Item(0));
        super.setEquipped(new Item(0));
        gc = pGc;
    }
    
    public Inventory getHotbar() {
        return hotbar;
    }
    
    public void doAttack() {
        Array<Entity> entities = gc.getEntities();
        for(Entity e : enitities) {
            if(e.getClass() == Character.class) {
                if(getAttackArea().overlaps(e.getRectangle())) {
                    attack(e);
                }
            }
        }
    }

}
