/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

/**
 *
 * @author max
 */
public class Inventory {

    private int size;
    private Item[] slots;
    private static Texture slotTexture;
    //private JsonValue jsonObject;

    public Inventory(int pSize) {
        size = pSize;
        slots = new Item[pSize];
        if (slotTexture == null) {
            slotTexture = new Texture(Gdx.files.internal("slot.png"));
        }
    }

    public void draw(SpriteBatch batch, Vector2 startPos) {
        int i = 3;
        if (size < 8) {
            i = 1;
        }
        int rowSize = size / i;
        for (int y = 0; y < i; y++) {
            for (int x = 0; x < rowSize; x++) {
                Item item = slots[x + (y * rowSize)];
                if (item == null) {
                    item = Item.nullItem();
                }
                int width = slotTexture.getWidth();
                int height = slotTexture.getHeight();
                Vector2 pos = new Vector2(startPos.x + (x * width), startPos.y + (y * height));
                drawSlot(batch, item, pos);
            }
        }
        if (size % i != 0) {
            rowSize = size % i;
            for (int x = 0; x < rowSize; x++) {

            }
        }

    }

    private void drawSlot(SpriteBatch batch, Item slot, Vector2 pos) {
        batch.draw(slotTexture, pos.x, pos.y);
        batch.draw(slot.getTexture(), pos.x + 32, pos.y + 32, 64, 64);
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
        return slots[slotIndex].getTexture();
    }

    public Texture getSlotTexture() {
        return slotTexture;
    }

}
