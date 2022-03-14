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

    public Player(Texture pTexture) {
        super(100, 250f, pTexture, new Rectangle(400, 240, 32, 32));
        inventory = new Inventory(27);
        hotbar = new Inventory(5);
        hotbar.setItem(1, new Item(0));
    }
    
    public Inventory getHotbar() {
        return hotbar;
    }

}
