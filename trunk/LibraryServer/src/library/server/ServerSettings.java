/*
 * ServerSettings.java
 *
 * Created on 25 marzec 2007, 17:56
 */

package library.server;

import java.util.Calendar;
import util.AbstractSettings;

/**
 * Server settings. Singleton.
 * @author Piotrek
 */
public class ServerSettings extends AbstractSettings {
    
    private String databaseName;
    
    /** time for which books are borrowed */
    private BorrowTime borrowTime;
    
    /** address of machine where RMI registry is running */
    private String rmiRegistryHost;
    
    private boolean useRMI;
    
    private static ServerSettings instance=null;
    
    public static ServerSettings getInstance() {
        if (instance == null)
            instance = new ServerSettings();
        return instance;
    }
    
    /** Creates a new instance of ServerSettings */
    protected ServerSettings() {
        super(new ServerSettingsXMLSaver("libraryServer"));
        
        // set default settings
        setDatabaseName("library");
        setBorrowTime(new BorrowTime(0,1)); // one month
        setRmiRegistryHost("localhost");
        setUseRMI(true);
    }
    
    // getters and setters

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public BorrowTime getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(BorrowTime borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getRmiRegistryHost() {
        return rmiRegistryHost;
    }

    public void setRmiRegistryHost(String rmiRegistryHost) {
        this.rmiRegistryHost = rmiRegistryHost;
    }

    public boolean useRMI() {
        return useRMI;
    }

    public void setUseRMI(boolean useRMI) {
        this.useRMI = useRMI;
    }
    
    
    
}
