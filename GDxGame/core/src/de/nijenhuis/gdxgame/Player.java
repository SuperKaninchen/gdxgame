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
import com.badlogic.gdx.utils.JsonValue;
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
    private boolean inventoryVisible;

    private static Vector2 center;

    private static GameScreen gc;

    private static float maxAttackAngle = 25;

    public Player(GameScreen pGc, Texture pTexture) {
        super(100, 250f, new Item(3), pTexture, new Rectangle((WIDTH / 2) - 32, (HEIGHT / 2) - 32, 64, 64));
        inventory = new Inventory(15);
        inventory.setItem(0, new Item(6));
        hotbar = new Inventory(5);
        hotbar.setItem(1, new Item(1));
        aimPos = Vector2.Zero;
        inventoryVisible = false;
        center = new Vector2(WIDTH / 2, HEIGHT / 2);
        gc = pGc;
    }
    
    public Player(GameScreen pGc) {
        super(1, new Rectangle((WIDTH / 2) - 32, (HEIGHT / 2) - 32, 64, 64));
        JsonValue saveData = SaveMachine.loadValue("player");
        System.out.println(saveData);
        int[] pInventory = saveData.get("inventory").asIntArray();
        System.out.println(pInventory.length);
        inventory = new Inventory(pInventory);
        
        int[] pHotbar = saveData.get("hotbar").asIntArray();
        hotbar = new Inventory(pHotbar);
        
        aimPos = Vector2.Zero;
        inventoryVisible = false;
        center = new Vector2(WIDTH / 2, HEIGHT / 2);
        gc = pGc;
    }

    public void draw(SpriteBatch batch, Vector2 center) {
        super.draw(batch, new Vector2(-getX() + center.x, -getY() + center.y));
        if (inventoryVisible) {
            inventory.draw(batch, new Vector2(16, 48 + 128));
        }
    }

    public Inventory getHotbar() {
        return hotbar;
    }

    public void doAttack() {
        Array<Entity> entities = gc.getEntities();
        for (Entity e : entities) {
            if (e.getClass() == Enemy.class) {
                Enemy x = (Enemy) e;
                if (!x.isDead()) {
                    float distance = getPosition().dst(e.getPosition());
                    float angle = Math.abs(
                            aimPos.sub(center).angleDeg()
                            - (new Vector2(e.getRX(), e.getRY())).sub(center).angleDeg()
                    );
                    if (distance <= getReach() && angle <= maxAttackAngle) {
                        attack((Enemy) e);
                    }
                }
            }
        }
    }

    private void useItem(int pSlot) {
        Item item = hotbar.getItem(pSlot);
        if (item.getInt("type") != 2) {
            return;  // Not consumable
        }
        applyEffect(item.getInt("effect"), item.getInt("amount"));
    }

    public void aim(int x, int y) {
        y = HEIGHT - y;
        aimPos = new Vector2(x, y);
    }

    void toggleInventory() {
        inventoryVisible = !inventoryVisible;
    }

}
