package library.common;

import java.io.Serializable;

/**
 * Stores search pattern for books.
 *
 * Methods like hasX() (where X=capitalised field name) check if field has been entered.
 * By default, all hasX() will return false, but after calling setX()
 * hasX will return true.
 * Methods like clearX() remove field X from search pattern. As result,
 * next calls to hasX() will return false.
 */
public class SearchPattern extends AbstractBook implements Serializable {
    
    private static final String CLEAR_STRING="";
    private static final int CLEAR_INT=0;
    
    /** find only books having available copies */
    public boolean onlyAvailableCopies;
    
    /** creates blank search pattern (all hasX() will return false) */
    public SearchPattern() {
        super(CLEAR_STRING,CLEAR_STRING,CLEAR_STRING,CLEAR_INT);
        onlyAvailableCopies = false;
    }
    
    public boolean hasPublishYear() {
        return getPublishYear() != CLEAR_INT;
    }
    
    public void clearPublishYear() {
        setPublishYear(CLEAR_INT);
    }
    
    public boolean hasAuthor() {
        return !getAuthor().equals(CLEAR_STRING);
    }
    
    public void clearAuthor() {
        setAuthor(CLEAR_STRING);
    }
    
    public boolean hasTitle() {
        return !getTitle().equals(CLEAR_STRING);
    }
    
    public void clearTitle() {
        setTitle(CLEAR_STRING);
    }
    
    public boolean hasPublisher() {
        return !getPublisher().equals(CLEAR_STRING);
    }
    
    public void clearPublisher() {
        setPublisher(CLEAR_STRING);
    }
    
    private static final long serialVersionUID = -583176276458583700L;
}
