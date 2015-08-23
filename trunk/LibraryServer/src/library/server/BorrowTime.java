/*
 * BorrowTime.java
 *
 * Created on 28 marzec 2007, 14:43
 */

package library.server;

/**
 * Stores time for which books are borrowed.
 * @author Piotrek
 */
class BorrowTime {
    
    private int months;
    
    private int days;
    
    /** Creates a new instance of BorrowTime */
    public BorrowTime(int days, int months) {
        setDays(days);
        setMonths(months);
    }
    
    /** Creates a new instance with all fields set to 0 */
    public BorrowTime() {
        this(0,0);
    }

    public int getMonths() {
        return months;
    }

    /**
     *@throws IllegalArgumentException if months are negative
     */
    public void setMonths(int months) {
        if (months < 0)
            throw new IllegalArgumentException();
        this.months = months;
    }

    public int getDays() {
        return days;
    }

    /**
     *@throws IllegalArgumentException if days are negative
     */
    public void setDays(int days) {
        if (days < 0)
            throw new IllegalArgumentException();
        this.days = days;
    }

    public boolean equals(Object object) {
        try {
            BorrowTime second = (BorrowTime) object;
            return (days == second.days && months == second.months);
        } catch(Exception e) {
            return false;
        }
    }
    

    
}
