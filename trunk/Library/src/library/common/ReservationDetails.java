/*
 * ReservationDetails.java
 *
 * Created on 16 marzec 2007, 12:54
 */

package library.common;

import java.io.Serializable;

/**
 * 
 * @author student
 */
public abstract class ReservationDetails implements Serializable {
    
    /** number of people reserving this book */
    public int queueSize;
    
    /** when reader has reserved the book, this field will give his or her
     * position in queue (from 1 up).
     * Check {@link #youHaveReserved()} before reading this!
     * Value of this field when youHaveReserved() returns false is undefined.
     */
    public int youInQueue;
    
    public BookCopy bookCopy;
    
    public abstract boolean youHaveReserved();
    
}
