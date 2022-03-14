/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

/**
 *
 * @author max
 */
public class Inventory {
    
    private int size;
    private Item[] slots;
    private Texture slotTexture;
    private JsonValue jsonObject;
    
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
    
    public void setItem(int index, Item pItem) {
        slots[index] = pItem;
    }
    
    public Texture getItemTexture(int slotIndex) {
        if(jsonObject == null) {
            jsonObject = new JsonReader().parse(Gdx.files.internal("data/items.json"));
        }
        return new Texture( Gdx.files.internal(jsonObject.get(slots[slotIndex].getId()).getString("name") + ".png"));
    }
    
    public Texture getSlotTexture() {
        return slotTexture;
    }
    
}
