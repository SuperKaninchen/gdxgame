/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author max
 */
public class Inventory {
    
    private int size;
    private Item[] slots;
    private Texture slotTexture;
    
    public Inventory(int pSize) {
        size = pSize;
        slots = new Item[pSize];
        slotTexture = new Texture(Gdx.files.internal("slot.png"));
    }

    public int getSize() {
        return size;
    }
    
    public Item getItem(int index) {
        return slots[index];
    }
    
    public Texture getSlotTexture() {
        return slotTexture;
    }
    
}
