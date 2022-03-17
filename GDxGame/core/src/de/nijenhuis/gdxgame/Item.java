/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author max
 */
public class Item {
    
    private Texture texture;
    private int id;
    private String name;
    private String title;
    private JsonValue itemData;
    
    public Item(int pId) {
        itemData = SaveMachine.loadValue("items/"+pId);
        id = pId;
        name = itemData.get("name");
        title = itemData.get("title");
        texture = new Texture(Gdx.files.internal("data/items/"+name+".png"));
    }
    
    public JsonValue getValue(String s) {
        return itemData.get(s);
    }

    public Texture getTexture() {
        return texture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
}
