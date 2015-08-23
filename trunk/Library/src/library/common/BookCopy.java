/*
 * BookCopy.java
 *
 * Created on 16 marzec 2007, 12:29
 */

package library.common;

import java.io.Serializable;
import java.util.Calendar;

/**
 *
 * @author student
 */
public class BookCopy implements Serializable {
    
    /** state of the book copy.
     * AVAIL - book available, BORROWED - book is borrowed,
     * UNAVAIL - book cannot be borrowed (eg. is repaired),
     * REQUESTED - book was requested
     */
    public enum State {AVAIL,BORROWED,UNAVAIL,REQUESTED};
    
    public int copyId;
    
    /** date of book return, <b>may be null</b> if the book has not been borrowed */
    public Calendar returnDate;
    
    public State state;
    
    public Book book;
    
    /** Creates a new instance of BookCopy */
    public BookCopy(int state, int copyId, Calendar returnDate) {
        try {
            this.state = State.values()[state];
        } catch(Exception e) {
            e.printStackTrace();
            this.state = State.UNAVAIL;
        }
        this.copyId = copyId;
        this.returnDate = returnDate;
    }
    
    public BookCopy(int state, int copyId) {
        this(state, copyId,null);
    }
    
    public BookCopy(State state) {
        this.state = state;
    }
    
    private static final long serialVersionUID = 1672962649873290654L;
    
}
