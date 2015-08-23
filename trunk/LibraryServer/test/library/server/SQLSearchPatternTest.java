/*
 * SQLSearchPatternTest.java
 * JUnit based test
 *
 * Created on 20 marzec 2007, 01:04
 */

package library.server;

import junit.framework.*;
import library.common.SearchPattern;

/**
 *
 * @author Piotrek
 */
public class SQLSearchPatternTest extends TestCase {
    
    public SQLSearchPatternTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }

    /**
     * Test of getSQLStatement method, of class library.server.SQLSearchPattern.
     */
    public void testGetSQLStatement() {
        System.out.println("getSQLStatement");
        
        SearchPattern pattern = new SearchPattern();
        
        final String expResultBegin = "SELECT bookId,publishYear,author,title,publisher,"
                +"(SELECT COUNT(*) FROM BookCopies BC WHERE BC.bookId=B.bookId AND "
                +"state=0) available FROM Books B";
        
        SQLSearchPattern instance = new SQLSearchPattern(pattern);
        
        String expResult = expResultBegin;
        String result = instance.getSQLStatement();
        assertEquals(expResult,result);
        
        pattern.setPublishYear(2001);
        pattern.setAuthor(" Stefan  Grabiñski ");
        
        assertTrue(pattern.hasAuthor());
        assertTrue(pattern.hasPublishYear());
        assertFalse(pattern.hasTitle());
        assertFalse(pattern.hasPublisher());
        
        expResult = expResultBegin + " WHERE (author LIKE "
                +"'%Stefan%' AND author LIKE '%Grabiñski%') AND publishYear=2001";
        result = instance.getSQLStatement();
        
        System.out.println(result);
        System.out.println(expResult);
        assertEquals(expResult, result);
        
        pattern = new SearchPattern();
        pattern.onlyAvailableCopies = true;
        pattern.setPublisher("TSR");
        instance = new SQLSearchPattern(pattern);
        expResult = expResultBegin + " WHERE publisher='TSR' AND available > 0";
        result = instance.getSQLStatement();
        assertEquals(expResult, result);
        
        pattern = new SearchPattern();
        pattern.setTitle("Introduction to Algorithms");
        instance = new SQLSearchPattern(pattern);
        expResult = expResultBegin + " WHERE ((title LIKE '%Introduction%' OR " +
                "title LIKE '%introduction%') AND (title LIKE '%to%' OR " +
                "title LIKE '%To%') AND " +
                "(title LIKE '%Algorithms%' OR title LIKE '%algorithms%'))";
        result = instance.getSQLStatement();
        assertEquals(expResult, result);
    }
    
}
