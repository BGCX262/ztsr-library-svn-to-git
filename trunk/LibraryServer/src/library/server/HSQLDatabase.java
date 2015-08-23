package library.server;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HSQLDatabase extends Database {
    private boolean running;

    public HSQLDatabase() {
        running = false;
        try {
            String filename = ServerSettings.getInstance().getDatabaseName();
            Class.forName("org.hsqldb.jdbcDriver");
            connection = DriverManager.getConnection("jdbc:hsqldb:"
                    + filename,    // filenames
                    "sa",                     // username
                    "");                      // password
            running = true;
            try {
                Statement st = connection.createStatement();
                // would throw exception here if the table exists
                st.executeUpdate("CREATE TABLE Readers(login INT)"); 
                st.executeUpdate("DROP TABLE Readers"); // exception not thrown, tables do not exist
                System.err.println("creating tables");
                createTables();
            } catch(SQLException e) {
                //System.err.println("tables exist"); // tables exist
                //e.printStackTrace();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /** closes database */
    protected void finalize() throws Throwable {
        try {
            shutdown();
        } finally {
            super.finalize();
        }
    }
    
    /** shuts down the database if it hasn't already been shut down */
    public void shutdown() {
        try {
            synchronized (this) {
                if (!running) return;
                if(connection.isClosed())
                    return;
                running = false;
                connection.createStatement().execute("SHUTDOWN");
                connection.close();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
