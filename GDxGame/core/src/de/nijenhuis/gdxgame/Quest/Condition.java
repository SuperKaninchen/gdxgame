/*
 * © Max Nijenhuis
 * java-mn@mnijenhuis.de
 */
package de.nijenhuis.gdxgame.Quest;

import com.badlogic.gdx.utils.JsonValue;

/**
 *
 * @author max
 */
public class Condition {
    
    private int type;
    /*
    0: retrieve
    1: kill
    2: score
    */
    private int targetId;
    private int targetAmount;
    
    public Condition(JsonValue data) {
        type = data.getInt("type");
        targetId = data.getInt("targetId");
        
    }
    
    
}
