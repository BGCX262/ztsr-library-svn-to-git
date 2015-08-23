package library.client;

import util.*;

/**
 * @author Karolina
 */
public class ClientSettings extends AbstractSettings {
    
    private String  address;
    
    /** Creates a new instance of ClientSettings */
    public ClientSettings(String programName) {
        super(new ClientSettingsSaver(programName));
        
        // set default value
        address = "localhost";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String a) {
        this.address = a;
    }

    public void loadSettings(){
        this.load();
        this.save();
    }
    
}

