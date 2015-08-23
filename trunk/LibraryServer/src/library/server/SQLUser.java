/*
 * SQLUser.java
 *
 * Created on 20 marzec 2007, 23:50
 */

package library.server;

import java.sql.*;
import library.common.InternalErrorException;
import library.common.LibraryException;
import library.common.LoginData;
import library.common.LoginException;
import java.util.*;
import library.common.LogicException;

/**
 * Contains operations linking users and database.
 * @author Piotrek
 */
public class SQLUser {
    private LoginData ld;
    
    public SQLUser(LoginData ld) {
        this.ld = ld;
    }
    
    /** Tries login with given login data. If something goes wrong (eg. passwords
     * do not match), throws exception.
     */
    public void tryLogin(Database db) throws LibraryException {
        try {
            Connection conn = db.getConnection();
            Statement st = conn.createStatement();
            String text = "SELECT password FROM Readers WHERE login=";
            text += Integer.toString(ld.getLogin());
            ResultSet rs = st.executeQuery(text);
            
            if (!rs.next()) { // tries to move to first row
                st.close();
                throw new LoginException(ResourceBundle.getBundle("library/server/server").getString("no_such_user"));
            }
            
            String dbPassword = rs.getString(1);
            if (!dbPassword.equals(ld.getPasswordHash())) {
                st.close();
                throw new LoginException(ResourceBundle.getBundle("library/server/server").getString("wrong_password"));
            }
            
            st.close();
            
        } catch(SQLException e) {
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error") + ": " + e.getLocalizedMessage());
        } catch(NullPointerException e) {
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error"));
        }
    }
    
    /** creates new reader in database */
    public void save(Database db) throws LibraryException {
        try {
            if (ld.isAnonymous())
                throw new LogicException(ResourceBundle.getBundle("library/server/server").getString("cannot_create_user_with_anonymous_login"));
            
            Connection conn = db.getConnection();
            Statement st = conn.createStatement();
            String readerLogin = Integer.toString(ld.getLogin());
            
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Readers WHERE login="
                    +readerLogin);
            rs.next();
            int count = rs.getInt(1);
            if (count != 0)
                throw new InternalErrorException("user "+readerLogin+" already exists");
            
            st.executeUpdate("INSERT INTO Readers(login,password) VALUES("+readerLogin
                    +",'"+ld.getPasswordHash()+"')");
        }catch(SQLException e) {
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error") + ": " + e.getLocalizedMessage());
        }
    }
    
    /** deletes user from database */
    public void delete(Database db) throws LibraryException {
        Transaction t = new Transaction(new DeleteUserOperation(db,ld.getLogin()));
        t.perform();
    }
    
    /** returns collection of logins of all users in database */
    public static Collection<Integer> getUsers(Database db) throws LibraryException {
        try {
            Statement st = db.getConnection().createStatement();
            
            ResultSet rs = st.executeQuery("SELECT login FROM Readers");
            Collection<Integer> retval = new ArrayList<Integer>();
            while (rs.next()) {
                retval.add(rs.getInt(1));
            }
            
            return retval;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error"));
        }
    }
    
    static class DeleteUserOperation extends Operation {
        private int userId;
        
        public DeleteUserOperation(Database db, int userId) {
            super(db);
            this.userId = userId;
        }
        
        public void perform() throws LibraryException {
            try {
                Statement st = getDatabase().getConnection().createStatement();
                
                st.executeUpdate("DELETE FROM Readers WHERE login="+Integer.toString(userId));
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new InternalErrorException(ResourceBundle.getBundle("library/server/server").getString("database_error"));
            }
        }
    }
    
}
