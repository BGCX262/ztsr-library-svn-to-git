package library.client;

import util.AbstractSettings;
import util.XMLSettingsSource;

/**
 *
 * @author Karolina
 */
public class ClientSettingsSaver extends XMLSettingsSource {
    
    private final static String FILENAME="settings.xml";
    
    
    public ClientSettingsSaver(String programName) {
        super(programName, FILENAME);
    }

    protected void handleKey(String key, String value, AbstractSettings settings) {
        try {
            ClientSettings s = (ClientSettings)settings;
            
            if (key.equals("address"))
                s.setAddress(value);
            //else DEBUG ONLY
            //    System.err.printf("unknown key: %s with value %s\n", key, value);
        } catch(Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    protected void prepareToSave(AbstractSettings settings) {
        try {
            ClientSettings s = (ClientSettings)settings;
            
            saveValue("address", s.getAddress());
        } catch(Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
    
}
