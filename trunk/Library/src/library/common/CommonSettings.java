/*
 * CommonSettings.java
 *
 * Created on 27 marzec 2007, 20:51
 */

package library.common;

/**
 *
 * @author Piotrek
 */
public class CommonSettings {
    
    private static CommonSettings instance=null;
    
    private int port;
    private String objectName;
    
    /** Creates a new instance of CommonSettings */
    protected CommonSettings() {
        port = 1099;
        objectName = "library";
    }
    
    public static CommonSettings getInstance() {
        if (instance == null)
            instance = new CommonSettings();
        return instance;
    }
    
    /** returns number of port on which to establish RMI connection
     * between client and server
     */
    public int getPort() {
        return port;
    }
    
    /** returns name of RMI object on RMI on server */
    public String getObjectName() {
        return objectName;
    }
    
}
