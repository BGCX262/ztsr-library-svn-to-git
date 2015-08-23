/*
 * ServerSettingsXMLSaver.java
 *
 * Created on 25 marzec 2007, 17:57
 */

package library.server;

import util.AbstractSettings;
import util.XMLSettingsSource;
import util.database.Enumerator;

/**
 *
 * @author Piotrek
 */
class ServerSettingsXMLSaver extends XMLSettingsSource {
    
    private final static String FILENAME="settings.xml";
    
    /**
     * Creates a new instance of SimpleSettingsXMLSaver
     */
    public ServerSettingsXMLSaver(String programName) {
        super(programName, FILENAME);
    }

    protected void handleKey(String key, String value, AbstractSettings settings) {
        try {
            ServerSettings s = (ServerSettings)settings;
            
            if (key.equals("databaseName"))
                s.setDatabaseName(value);
            else if (key.equals("borrowTime")) {
                BorrowTime bt = stringToBorrowTime(value);
                if (! bt.equals(new BorrowTime(0,0)))
                    s.setBorrowTime(bt);
            } else if (key.equals("rmiRegistryHost"))
                s.setRmiRegistryHost(value);
            else if (key.equals("useRmi"))
                s.setUseRMI(Integer.parseInt(value) == 1);
            //else DEBUG ONLY
            //    System.err.printf("unknown key: %s with value %s\n", key, value);
        } catch(Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    protected void prepareToSave(AbstractSettings settings) {
        try {
            ServerSettings s = (ServerSettings)settings;
            
            saveValue("databaseName", s.getDatabaseName());
            saveValue("borrowTime", borrowTimeToString(s.getBorrowTime()));
            saveValue("rmiRegistryHost", s.getRmiRegistryHost());
            saveValue("useRmi", boolToString(s.useRMI()));
        } catch(Exception e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
    
    protected String boolToString(boolean b) {
        return b ? "1" : "0";
    }
    
    protected String borrowTimeToString(BorrowTime t) {
        Enumerator retval=new Enumerator(" ",false);
        if (t.getDays() != 0) retval.add(t.getDays()).add("D");
        if (t.getMonths() != 0) retval.add(t.getMonths()).add("M");
        return retval.toString();
    }
    
    /**
     *@returns borrow time or (0,0) if cannot read from string
     */
    protected BorrowTime stringToBorrowTime(String string) {
        BorrowTime retval = new BorrowTime(0,0);
        string = string.trim();
        String[] parts = string.split("\\s");
        if (parts.length != 2 && parts.length != 4)
            return retval;
        
        try {
            for (int i=0; i<parts.length; i+=2) {
                int number = Integer.parseInt(parts[i]);
                if (parts[i+1].equalsIgnoreCase("D"))
                    retval.setDays(number);
                else if (parts[i+1].equalsIgnoreCase("M"))
                    retval.setMonths(number);
            }
        } catch (Exception ex) {
            return retval;
        }
        
        return retval;
    }
    
}
