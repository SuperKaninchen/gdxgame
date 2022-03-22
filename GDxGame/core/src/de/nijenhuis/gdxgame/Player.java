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
import static de.nijenhuis.gdxgame.Constants.WIDTH;
import static de.nijenhuis.gdxgame.Constants.HEIGHT;
import de.nijenhuis.gdxgame.screens.GameScreen;

/**
 *
 * @author max
 */
public class Player extends Character {

    private Inventory inventory;
    private Inventory hotbar;
    private Vector2 aimPos;
    
    private static GameScreen gc;

    public Player(GameScreen pGc, Texture pTexture) {
        super(100, 250f, new Item(1), pTexture, new Rectangle((WIDTH/2)-32, (HEIGHT/2)-32, 64, 64));
        inventory = new Inventory(27);
        hotbar = new Inventory(5);
        hotbar.setItem(1, new Item(0));
        aimPos = Vector2.Zero;
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
                System.out.println(getPosition().dst2(e.getPosition()));
                if(getPosition().dst2(e.getPosition()) <= getReach()) {
                    attack((Character) e);
                }
            }
        }
    }
    
    public void aim(int x, int y) {
        y = HEIGHT - y;
        aimPos = new Vector2(x, y);
    }

}
