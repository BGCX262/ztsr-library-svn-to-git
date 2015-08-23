/*
 * QueuePlaceCounterTest.java
 * JUnit based test
 *
 * Created on 23 marzec 2007, 18:54
 */

package library.server;

import junit.framework.*;

/**
 *
 * @author Piotrek
 */
public class QueuePlaceCounterTest extends TestCase {
    
    public QueuePlaceCounterTest(String testName) {
        super(testName);
    }

    /**
     * Test of getPlace method, of class library.server.QueuePlaceCounter.
     */
    public void testGetPlace() {
        System.out.println("getPlace");
        
        QueuePlaceCounter instance = new QueuePlaceCounter(10);
        
        int expResult = 0;
        int result = instance.getPlace();
        assertEquals(expResult, result);
        
        
        instance = new QueuePlaceCounter(1); 
        instance.handlePair(1,22);
        
        expResult = 1;
        result = instance.getPlace();
        assertEquals(expResult, result);
        
        
        instance = new QueuePlaceCounter(8); 
        instance.handlePair(3,4); // 1->2->3->4->5->6->7->8
        instance.handlePair(6,7);
        instance.handlePair(1,2);
        instance.handlePair(2,3);
        instance.handlePair(8,22);
        instance.handlePair(5,6);
        instance.handlePair(7,8);
        instance.handlePair(4,5);
        
        expResult = 1;
        result = instance.getPlace();
        assertEquals(expResult, result);
        
        
        instance = new QueuePlaceCounter(1); 
        instance.handlePair(3,4); // 1->2->3->4->5->6->7->8
        instance.handlePair(6,7);
        instance.handlePair(1,2);
        instance.handlePair(2,3);
        instance.handlePair(8,22);
        instance.handlePair(5,6);
        instance.handlePair(7,8);
        instance.handlePair(4,5);
        
        expResult = 8;
        result = instance.getPlace();
        assertEquals(expResult, result);
        
        
        instance = new QueuePlaceCounter(5); 
        instance.handlePair(3,4); // 1->2->3->4->5->6->7->8
        instance.handlePair(6,7);
        instance.handlePair(1,2);
        instance.handlePair(2,3);
        instance.handlePair(8,22);
        instance.handlePair(5,6);
        instance.handlePair(7,8);
        instance.handlePair(4,5);
        
        expResult = 4;
        result = instance.getPlace();
        assertEquals(expResult, result);
        
        
        instance = new QueuePlaceCounter(5); 
        instance.handlePair(1,2); // 1->2->3->4->5->6->7->8
        instance.handlePair(2,3);
        instance.handlePair(3,4);
        instance.handlePair(4,5);
        instance.handlePair(5,6);
        instance.handlePair(6,7);
        instance.handlePair(7,8);
        instance.handlePair(8,22);
        
        expResult = 4;
        result = instance.getPlace();
        assertEquals(expResult, result);
        
        
        instance = new QueuePlaceCounter(5); 
        instance.handlePair(8,22); // 1->2->3->4->5->6->7->8
        instance.handlePair(7,8);
        instance.handlePair(6,7);
        instance.handlePair(5,6);
        instance.handlePair(4,5);
        instance.handlePair(3,4);
        instance.handlePair(2,3);
        instance.handlePair(1,2);
        
        expResult = 4;
        result = instance.getPlace();
        assertEquals(expResult, result);
    }
    
}
