/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import de.nijenhuis.gdxgame.screens.GameScreen;

/**
 *
 * @author max
 */
public class Player extends Character {

    private Inventory inventory;
    private Inventory hotbar;
    
    private static GameScreen gc;

    public Player(GameScreen pGc, Texture pTexture) {
        super(100, 250f, new Item(1), pTexture, new Rectangle(400, 240, 32, 32));
        inventory = new Inventory(27);
        hotbar = new Inventory(5);
        hotbar.setItem(1, new Item(0));
        gc = pGc;
    }
    
    public void draw(SpriteBatch batch, Vector2 center) {
        super.draw(batch, new Vector2(-getX()+center.x, -getY()+center.y));
    }
    
    public Inventory getHotbar() {
        return hotbar;
    }
    
    public void doAttack() {
        Array<Entity> entities = gc.getEntities();
        for(Entity e : entities) {
            if(e.getClass() == Character.class) {
                System.out.println("e is at " + e.getRectangle());
                System.out.println("aim is at " + getAttackArea());
                if(getAttackArea().overlaps(e.getRectangle())) {
                    System.out.println("e overlaps");
                    attack((Character) e);
                }
            }
        }
    }

}
