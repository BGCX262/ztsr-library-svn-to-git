/*
 * Operation.java
 *
 * Created on 25 marzec 2007, 16:37
 */

package library.server;

import library.common.LibraryException;

/**
 * Abstract operation on database. Command design pattern.
 * @author Piotrek
 */
public abstract class Operation {
    
    private Database database;
    
    public Operation(Database database) {
        this.database = database;
    }
    
    final public Database getDatabase() {
        return database;
    }
    
    abstract public void perform() throws LibraryException;
}
