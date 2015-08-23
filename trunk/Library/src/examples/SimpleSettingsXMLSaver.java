/*
 * SimpleSettingsXMLSaver.java
 *
 * Created on 21 marzec 2007, 23:38
 */

package examples;

import util.AbstractSettings;
import util.XMLSettingsSource;

/**
 *
 * @author Piotrek
 */
public class SimpleSettingsXMLSaver extends XMLSettingsSource {
    
    private final static String FILENAME="app_settings.xml";
    
    /**
     * Creates a new instance of SimpleSettingsXMLSaver
     */
    public SimpleSettingsXMLSaver(String programName) {
        super(programName, FILENAME);
    }

    protected void handleKey(String key, String value, AbstractSettings settings) {
        try {
            SimpleSettings s = (SimpleSettings)settings;
            
            if (key.equals("intValue"))
                s.setIntValue(Integer.parseInt(value));
            else if (key.equals("stringValue"))
                s.setStringValue(value);
            else if (key.equals("floatValue"))
                s.setFloatValue(Float.parseFloat(value));
            //else DEBUG ONLY
            //    System.err.printf("unknown key: %s with value %s\n", key, value);
        } catch(Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    protected void prepareToSave(AbstractSettings settings) {
        try {
            SimpleSettings s = (SimpleSettings)settings;
            
            saveValue("intValue", s.getIntValue());
            saveValue("stringValue", s.getStringValue());
            saveValue("floatValue", s.getFloatValue());
        } catch(Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
    
}
