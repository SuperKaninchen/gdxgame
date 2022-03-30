/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame.Quest;

import com.badlogic.gdx.utils.JsonValue;
import de.nijenhuis.gdxgame.SaveMachine;

/**
 *
 * @author max
 */
public class Quest {
    
    private JsonValue questData;
    
    private String name;
    private String description;
    
    public Quest(int pId) {
        questData = SaveMachine.loadValue("quests/" + pId);
    }
    
}
