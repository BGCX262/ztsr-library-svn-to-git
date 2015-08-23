/*
 * BorrowTimeTest.java
 * JUnit based test
 *
 * Created on 28 marzec 2007, 15:19
 */

package library.server;

import junit.framework.*;

/**
 *
 * @author Piotrek
 */
public class BorrowTimeTest extends TestCase {
    
    public BorrowTimeTest(String testName) {
        super(testName);
    }

    /**
     * Test of getMonths method, of class library.server.BorrowTime.
     */
    public void testGetMonths() {
        System.out.println("getMonths");
        
        BorrowTime instance = new BorrowTime();
        
        int expResult = 0;
        int result = instance.getMonths();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMonths method, of class library.server.BorrowTime.
     */
    public void testSetMonths() {
        System.out.println("setMonths");
        
        int months = 55;
        BorrowTime instance = new BorrowTime();
        
        instance.setMonths(months);
        assertEquals(months, instance.getMonths());
    }

    /**
     * Test of getDays method, of class library.server.BorrowTime.
     */
    public void testGetDays() {
        System.out.println("getDays");
        
        BorrowTime instance = new BorrowTime();
        
        int expResult = 0;
        int result = instance.getDays();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDays method, of class library.server.BorrowTime.
     */
    public void testSetDays() {
        System.out.println("setDays");
        
        int days = 67;
        BorrowTime instance = new BorrowTime();
        
        instance.setDays(days);
        assertEquals(days, instance.getDays());
    }

    /**
     * Test of equals method, of class library.server.BorrowTime.
     */
    public void testEquals() {
        System.out.println("equals");
        
        BorrowTime example = new BorrowTime();
        BorrowTime instance = new BorrowTime();
        
        boolean expResult = true;
        boolean result = instance.equals(example);
        assertEquals(expResult, result);
        
        example.setDays(1);
        expResult = false;
        result = instance.equals(example);
        assertEquals(expResult, result);
        
        example.setDays(5);
        example.setMonths(25);
        instance.setDays(5);
        instance.setMonths(25);
        expResult = true;
        result = instance.equals(example);
        assertEquals(expResult, result);
    }
    
}
