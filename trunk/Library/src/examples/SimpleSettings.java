/*
 * SimpleSettings.java
 *
 * Created on 21 marzec 2007, 23:37
 */

package examples;

import util.AbstractSettings;

/**
 *
 * @author Piotrek
 */
public class SimpleSettings extends AbstractSettings {
    
    private int intValue;
    
    private String stringValue;
    
    private float floatValue;
    
    /** Creates a new instance of SimpleSettings */
    public SimpleSettings(String programName) {
        super(new SimpleSettingsXMLSaver(programName));
        
        // set default values
        intValue = 0;
        stringValue = "default string";
        floatValue = 1.0f;
    }
    
    // ========= getters and setters

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        this.floatValue = floatValue;
    }
    
}
