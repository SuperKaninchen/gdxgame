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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author max
 */
public class SaveMachine {
    
    private static JsonValue saveCache;
    private static FileHandle saveFile;
    private static JsonValue itemDataCache;
    private static FileHandle itemDataFile;
    
    private static Preferences prefs;
    
    // Muss vor dem verwenden aufgerufen werden, um statische Variablen zu
    // initialisieren.
    public static void init() throws IOException {
        prefs = Gdx.app.getPreferences("gdxgame");
        prefs.putString("saveFile", "persistent.json");
        prefs.putString("itemDataFile", "data/items.json");
        prefs.flush();
        
        saveCache = loadFromFile(getFile("saveFile"));
        itemDataCache = loadFromFile(getFile("itemDataFile"));
    }
    
    // Fr die init(). Kümmert sich um exceptions n stuff
    private static FileHandle getFile(String fileName) throws IOException {
        String filePath = prefs.getString(fileName, "persistent.json");
        FileHandle file = Gdx.files.internal(filePath);
        if (!file.exists()) {
            System.out.println("File '" + fileName + "' at '" + filePath + "' not found!");
            try {
                file.file().createNewFile();
            } catch (IOException ex) {
                throw new IOException();
            }
            file.writeString("{}", false);
        }
        return file;
    }
    
    // Lädt den Inhalt der Datei in den statischen Cache
    public static JsonValue loadFromFile(FileHandle file) {
        return (new JsonReader()).parse(file.readString());
    }
    
    // Speichert den Inhalt des statischen Caches innerhalb der Datei
    public static void saveToFile(JsonValue data, FileHandle file) {
        file.writeString(data.asString(), false);
    }
    
    // Speichert einen Wert in Form von JsonValue bei path.
    // path -> siehe loadValue()
    public static void saveValue(JsonValue value, String path) {
        JsonValue child;
        if (path.startsWith("items/")) {
            child = itemDataCache;
        } else {
            child = saveCache;
        }
        for (String s : path.split("/")) {
            if (!child.has(s)) {
                child.addChild(s, new JsonValue("."));
            }
            child = child.get(s);
        }
        child = value;
    }
    
    // Lädt einen Wert in Form von JsonValue bei path.
    // path bescheibt einen pseudo-Dateipfad durch die Json-Objekte.
    // z.B. würde player/inventory/3 das dritte Element des Inventars des
    // Spielers laden.
    public static JsonValue loadValue(String path) {
        JsonValue child;
        if (path.startsWith("items/")) {
            child = itemDataCache;
        } else {
            child = saveCache;
        }
        for (String s : path.split("/")) {
            try {
                child = child.get(Integer.parseInt(s));
            } catch (NumberFormatException numberFormatException) {
                child = child.get(s);
            }
        }
        return child;
    }
    
}
