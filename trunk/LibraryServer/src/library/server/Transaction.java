/*
 * Transaction.java
 *
 * Created on 25 marzec 2007, 16:36
 */

package library.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import library.common.InternalErrorException;
import library.common.LibraryException;

/**
 * Performs SQL transaction.
 * @author Piotrek
 */
public class Transaction {
    
    private Operation operation;
    
    /** Creates a new instance of Transaction */
    public Transaction(Operation operation) {
        this.operation = operation;
    }
    
    public void perform() throws LibraryException {
        Connection conn = operation.getDatabase().getConnection();
        try {
            conn.setAutoCommit(false);
            
            operation.perform();
            
        } catch(SQLException e) {
            e.printStackTrace();
            try{ conn.createStatement().execute("ROLLBACK"); } catch(SQLException ex) { ex.printStackTrace(); }
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error"));
        } catch(LibraryException e) {
            try{ conn.createStatement().execute("ROLLBACK"); } catch(SQLException ex) { ex.printStackTrace(); }
            throw e;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch(SQLException e) { e.printStackTrace(); }
        }
    }
    
}
