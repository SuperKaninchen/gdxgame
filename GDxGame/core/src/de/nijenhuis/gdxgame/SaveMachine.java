/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

/**
 *
 * @author max
 */
public class SaveMachine {

    private Json json;
    private JsonValue cache;
    private FileHandle saveFile;
    
    public SaveMachine() {
        Preferences prefs = Gdx.app.getPreferences("gdxgame");
        String filePath = prefs.getString("saveFile", "persistent.json");
        saveFile = Gdx.files.internal(filePath);
    }
    
    public void loadFromFile() {
        cache = new JsonReader().parse(saveFile.readString());
    }
    
    public void saveToFile() {
        String saveData = cache.asString();
        saveFile.writeString(saveData, false);
    }
    
    public void saveValue(Object value, String path) {
        JsonValue child = cache;
        for(String s : path.split("/")) {
            if(!child.has(s)) {
                child.
            }
            child = child.get(s);
        }
        child = value
    }

}
