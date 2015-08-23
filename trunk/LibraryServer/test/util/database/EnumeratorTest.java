/*
 * EnumeratorTest.java
 * JUnit based test
 *
 * Created on 20 marzec 2007, 20:00
 */

package util.database;

import junit.framework.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Piotrek
 */
public class EnumeratorTest extends TestCase {
    
    public EnumeratorTest(String testName) {
        super(testName);
    }

    /**
     * Test of size method, of class util.database.Enumerator.
     */
    public void testSize() {
        System.out.println("size");
        
        Enumerator instance = new Enumerator();
        
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        
        expResult = 3;
        instance.add("one").add("two", 2).addNotQuote("three","3");
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of clear method, of class util.database.Enumerator.
     */
    public void testClear() {
        System.out.println("clear");
        
        Enumerator instance = new Enumerator();
        
        instance.clear();
        
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        
        instance.add("one").add("two", 2).addNotQuote("three","3");
        
        instance.clear();
        
        expResult = 0;
        result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class util.database.Enumerator.
     */
    public void testToString() {
        System.out.println("toString");
        
        Enumerator instance = new Enumerator(" AND ");
        
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        
        instance.addNotQuote("one").add("two", 2).add("three","3").addNotQuote("four","'%4%'"," LIKE ");
        
        expResult = "one AND two=2 AND three='3' AND four LIKE '%4%'";
        result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class util.database.Enumerator.
     */
    public void testAdd() {
        System.out.println("add");
        
        String key = "one";
        String value = "1";
        String tempLinker = " LIKE ";
        Enumerator instance = new Enumerator(",",true);
        
        String expResult = "one LIKE '1'";
        String result = instance.add(key, value, tempLinker).toString();
        assertEquals(expResult, result);
        
        instance = new Enumerator(",",true);
        expResult="1,'string',2,'string2'";
        result = instance.add(1).add("string").add(2).add("string2").toString();
        assertEquals(expResult, result);
        
        instance = new Enumerator(",",false);
        expResult="1,string,2,string2";
        result = instance.add(1).add("string").add(2).add("string2").toString();
        assertEquals(expResult, result);
        
        instance = new Enumerator(" AND ",true);
        expResult="one=1 AND three='three'";
        result = instance.add("one",1).add("").add("three","three").toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of addNotQuote method, of class util.database.Enumerator.
     */
    public void testAddNotQuote() {
        System.out.println("addNotQuote");
        
        String key = "one";
        String value = "1";
        String tempLinker = " LIKE ";
        Enumerator instance = new Enumerator();
        
        String expResult = "one LIKE 1";
        String result = instance.addNotQuote(key, value, tempLinker).toString();
        assertEquals(expResult, result);
    }
    
}
