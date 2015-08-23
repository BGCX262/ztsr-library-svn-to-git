package library.common;

import java.io.Serializable;
import java.util.Calendar;

public class Book extends AbstractBook implements Serializable {
    
    /** attention: may be null if {@link #borrowed} is false */
    public Calendar returnDate;

    public boolean borrowed;

    public int bookId;
    
    public boolean hasAvailableCopies;

    public Book(String title, String author, String publisher, int publishYear, int id) {
        super(title, author, publisher, publishYear);
        bookId = id;
        hasAvailableCopies = false;
        borrowed = false;
    }
    
    public Book(String title, String author, String publisher, int publishYear) {
        this(title, author, publisher, publishYear, 0);
    }
    
    private static final long serialVersionUID = 7236658448454561510L;
}
