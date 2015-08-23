package library.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Database {
    protected Connection connection;
    
    public Database() {
    }

    public Connection getConnection() {
        return connection;
    }

    /** creates all tables required by the project */
    protected void createTables() {
        try {
            Statement st = connection.createStatement();
            
            st.executeUpdate("CREATE TABLE Readers(login INT PRIMARY KEY,"
                    +"password VARCHAR(24))");
            st.executeUpdate("CREATE TABLE Books(bookId INT IDENTITY PRIMARY KEY," +
                    "author VARCHAR(40) NOT NULL, title VARCHAR(40) NOT NULL," +
                    " publisher VARCHAR(30) NOT NULL, publishYear INT NOT NULL," +
                    "CONSTRAINT BookC1 UNIQUE (author,title,publisher,publishYear))");
            st.executeUpdate("CREATE TABLE BookCopies(copyId INT IDENTITY PRIMARY KEY," +
                    "bookId INT, state INT DEFAULT 0, returnDate DATE DEFAULT '2000-01-01'," +
                    "borrowedBy INT DEFAULT -1," +
                    "CONSTRAINT BCC1 FOREIGN KEY (bookId) REFERENCES Books(bookId))");
            st.executeUpdate("CREATE TABLE Requests(copyId INT, readerId INT," +
                    "CONSTRAINT ReqC1 PRIMARY KEY (copyId,readerId)," +
                    "CONSTRAINT ReqC2 FOREIGN KEY(readerId) REFERENCES Readers(login)," +
                    "CONSTRAINT ReqC3 FOREIGN KEY (copyId) REFERENCES BookCopies(copyId))");
            st.executeUpdate("CREATE TABLE Reservations(reservId INT IDENTITY PRIMARY KEY," +
                    "readerId INT, copyId INT, nextReserv INT, " +
                    "CONSTRAINT ResC1 UNIQUE (readerId,copyId)," +
                    "CONSTRAINT ResC2 FOREIGN KEY (readerId) REFERENCES Readers(login)," +
                    "CONSTRAINT ResC3 FOREIGN KEY (copyId) REFERENCES BookCopies(copyId))");
            
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    /** closes database (by default does not do anything) */
    public void shutdown() {}
}
