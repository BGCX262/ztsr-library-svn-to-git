/*
 * ServerSettingsXMLSaverTest.java
 * JUnit based test
 *
 * Created on 28 marzec 2007, 15:00
 */

package library.server;

import junit.framework.*;
import util.AbstractSettings;
import util.XMLSettingsSource;
import util.database.Enumerator;

/**
 *
 * @author Piotrek
 */
public class ServerSettingsXMLSaverTest extends TestCase {
    
    static class MyServerSettings extends ServerSettings {
        public MyServerSettings() {
            super();
            setDatabaseName("nodatabasename");
            setBorrowTime(new BorrowTime(99,99));
        }
    }
    
    public ServerSettingsXMLSaverTest(String testName) {
        super(testName);
    }

    /**
     * Test of handleKey method, of class library.server.ServerSettingsXMLSaver.
     */
    public void testHandleKey() {
        System.out.println("handleKey");
        
        String key = "notvalidkey";
        String value = "notvalidvalue";
        MyServerSettings settings = new MyServerSettings();
        ServerSettingsXMLSaver instance = new ServerSettingsXMLSaver("testprogram");
        
        BorrowTime initialBT = new BorrowTime(99,99);
        
        instance.handleKey(key, value, settings);
        assertEquals(settings.getDatabaseName(), "nodatabasename");
        assertEquals(settings.getBorrowTime(), initialBT);
        
        instance.handleKey("databaseName", "mydatabasename", settings);
        assertEquals(settings.getDatabaseName(), "mydatabasename");
        assertEquals(settings.getBorrowTime(), initialBT);
        
        instance.handleKey("borrowTime", "", settings);
        boolean expResult = true;
        boolean result = initialBT.equals(settings.getBorrowTime());
        System.out.printf("%d %d\n", settings.getBorrowTime().getDays(), settings.getBorrowTime().getMonths());
        assertEquals(expResult, result);
        
        instance.handleKey("rmiRegistryHost", "myhostname", settings);
        assertEquals(settings.getRmiRegistryHost(), "myhostname");
        
        /*instance.handleKey("borrowTime", "-1 D", settings);
        assertTrue(initialBT.equals(settings.getBorrowTime()));
        
        instance.handleKey("borrowTime", "1 D", settings);
        assertTrue(settings.getBorrowTime().equals(new BorrowTime(1,0)));
        
        instance.handleKey("borrowTime", "6 M", settings);
        assertTrue(settings.getBorrowTime().equals(new BorrowTime(0,6)));
        
        instance.handleKey("borrowTime", "2 D  9 M", settings);
        assertTrue(settings.getBorrowTime().equals(new BorrowTime(2,9)));
        
        instance.handleKey("borrowTime", " 13 M 1  D ", settings);
        assertTrue(settings.getBorrowTime().equals(new BorrowTime(13,1)));*/
    }
    
    /**
     * Test of borrowTimeToString method, of class library.server.ServerSettingsXMLSaver.
     */
    public void testBorrowTimeToString() {
        System.out.println("borrowTimeToString");
        
        BorrowTime t = new BorrowTime(0,0);
        ServerSettingsXMLSaver instance = new ServerSettingsXMLSaver("testprogram");
        
        String expResult = "";
        String result = instance.borrowTimeToString(t);
        assertEquals(expResult, result);
        
        expResult = "3 D";
        result = instance.borrowTimeToString(new BorrowTime(3,0));
        assertEquals(expResult, result);
        
        expResult = "5 M";
        result = instance.borrowTimeToString(new BorrowTime(0,5));
        assertEquals(expResult, result);
        
        expResult = "7 D 5 M";
        result = instance.borrowTimeToString(new BorrowTime(7,5));
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToBorrowTime method, of class library.server.ServerSettingsXMLSaver.
     */
    public void testStringToBorrowTime() {
        System.out.println("stringToBorrowTime");
        
        String string = "";
        ServerSettingsXMLSaver instance = new ServerSettingsXMLSaver("testprogram");
        
        BorrowTime expResult = new BorrowTime(0,0);
        BorrowTime result = instance.stringToBorrowTime(string);
        assertEquals(expResult, result);
        
        expResult = new BorrowTime(0,0);
        result = instance.stringToBorrowTime("3 MD");
        assertEquals(expResult, result);
        
        expResult = new BorrowTime(0,0);
        result = instance.stringToBorrowTime("5 M D");
        assertEquals(expResult, result);
        
        expResult = new BorrowTime(0,0);
        result = instance.stringToBorrowTime("1.0 M");
        assertEquals(expResult, result);
        
        expResult = new BorrowTime(0,0);
        result = instance.stringToBorrowTime("-1 D");
        assertEquals(expResult, result);
        
        expResult = new BorrowTime(8,5);
        result = instance.stringToBorrowTime("5 M 8 D");
        assertEquals(expResult, result);
        
        expResult = new BorrowTime(5,8);
        result = instance.stringToBorrowTime("5 d 8 m");
        assertEquals(expResult, result);
        
        expResult = new BorrowTime(0,1);
        result = instance.stringToBorrowTime("1 m");
        assertEquals(expResult, result);
    }
    
}
