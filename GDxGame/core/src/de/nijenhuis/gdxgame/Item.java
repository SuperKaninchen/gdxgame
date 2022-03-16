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
    private static JSONObject itemData;
    
    public Item(int pId) {
        id = pId;
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
