/*
 * ConcreteReservationDetails.java
 *
 * Created on 20 marzec 2007, 21:23
 */

package library.server;

/**
 *
 * @author Piotrek
 */
class ConcreteReservationDetails extends library.common.ReservationDetails {
    
    private static final int NOT_RESERVED = -1;
    
    /** Creates a new instance of ConcreteReservationDetails */
    public ConcreteReservationDetails(int queueSize) {
        this.queueSize = queueSize;
        this.youInQueue = NOT_RESERVED;
    }

    public boolean youHaveReserved() {
        return youInQueue != NOT_RESERVED;
    }
    
    private static final long serialVersionUID = 5439294359408964797L;
    
}
