/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 * @author max
 */
public class TextureMachine {
    
    private static TextureAtlas characters;
    private static TextureAtlas items;
    
    public static void init() {
        characters = new TextureAtlas(Gdx.files.internal("data/packs/characters.atlas"));
        items = new TextureAtlas(Gdx.files.internal("data/packs/items.atlas"));
    }
    
    public static Sprite getSprite(String path) {
        TextureAtlas atlas;
        if (path.startsWith("characters/")) {
            atlas = characters;
        } else {
            atlas = items;
        }
        Sprite sprite = atlas.createSprite(path.split("/")[1]);
        return sprite;
    }
    
}
