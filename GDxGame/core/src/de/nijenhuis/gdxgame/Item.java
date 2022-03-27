/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonValue;

/**
 *
 * @author max
 */
public class Item {

    private Texture texture;
    private String name;
    private String title;
    private JsonValue itemData;

    public Item(int pId) {
        if (pId == 0) {
            texture = new Texture(Gdx.files.internal("data/items/" + name + ".png"));
        } else {
            itemData = SaveMachine.loadValue("items/" + pId);
            name = itemData.getString("name");
            title = itemData.getString("title");
            texture = new Texture(Gdx.files.internal("data/items/" + name + ".png"));
        }
    }

    public static Item nullItem() {
        return new Item(0);
    }

    public JsonValue getValue(String s) {
        return itemData.get(s);
    }

    public int getInt(String s) {
        return itemData.getInt(s);
    }

    public String getString(String s) {
        return itemData.getString(s);
    }

    public Texture getTexture() {
        return texture;
    }

    public String getName() {
        return name;
    }

}
