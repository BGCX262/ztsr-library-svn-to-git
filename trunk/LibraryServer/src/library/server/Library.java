/*
 * Library.java
 *
 * Created on 20 marzec 2007, 23:22
 */

package library.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import library.common.*;

/**
 *
 * @author Piotrek
 */
public class Library extends UnicastRemoteObject implements LibraryReaderInterface {
    
    private Users users;
    
    private Database database;
    
    /** Creates a new instance of Library */
    public Library() throws RemoteException {
        users = new LoggedUsers();
        database = new HSQLDatabase();
    }
    
    public AbstractSession logIn(LoginData ld) throws RemoteException {
        return users.tryLogin(ld, database);
    }
    
    public void logOut(AbstractSession session) throws RemoteException {
        Session s = (Session)session;
        users.logOut(s);
    }
    
    public Collection<Book> findBooks(SearchPattern pattern) throws RemoteException {
        SQLSearchPattern p = new SQLSearchPattern(pattern);
        return p.performSearch(database);
    }
    
    public Collection<ReservationDetails> getBookCopies(int bookId) throws RemoteException {
        return SQLBook.getBookCopies(bookId, database);
    }
    
    public Collection<ReservationDetails> getReservedBooks(AbstractSession session) throws RemoteException {
        int readerId = getReaderId(session);
        return SQLBook.getReservedBooks(readerId,database);
    }
    
    public Collection<BookCopy> getRequestedBooks(AbstractSession session) throws RemoteException {
        int readerId = getReaderId(session);
        return SQLBook.getRequestedBooks(readerId,database);
    }
    
    public Collection<BookCopy> getBorrowedBooks(AbstractSession session) throws RemoteException {
        int readerId = getReaderId(session);
        return SQLBook.getBorrowedBooks(readerId,database);
    }
    
    /** reserves books in transaction */
    public void reserveBooks(AbstractSession session, int[] copyIds) throws RemoteException {
        int readerId = getReaderId(session);
        SQLBook.reserve(copyIds, readerId, database);
    }
    
    /** requests books in transaction */
    public void requestBooks(AbstractSession session, int[] copyIds) throws RemoteException {
        int readerId = getReaderId(session);
        SQLBook.request(copyIds, readerId, database);
    }
    
    /** borrows books in transaction (have to be requested first) */
    public void borrowBooks(int readerId, int[] copyIds) throws LibraryException {
        SQLBook.borrow(copyIds, readerId, database, this);
    }
    
    /** cancels reservations in transaction */
    public void cancelReservations(AbstractSession session, int[] copyIds) throws RemoteException {
        int readerId = getReaderId(session);
        SQLBook.cancelReservations(copyIds, readerId, database);
    }
    
    /** saves all data */
    public void shutdown() {
        database.shutdown();
    }
    
    /** adds book with first copy to database */
    public void addBook(Book book, BookCopy copy) throws LibraryException {
        SQLBook.save(database, book, copy);
    }
    
    /** adds book copy of existing book */
    public void addBookCopy(int bookId, BookCopy copy) throws LibraryException {
        SQLBook.save(database, bookId, copy);
    }
    
    public void deleteBook(int bookId) throws LibraryException {
        SQLBook.delete(database, bookId);
    }
    
    /** adds new reader to database */
    public void addReader(LoginData ld) throws LibraryException {
        new SQLUser(ld).save(database);
    }
    
    /** removes reader from database */
    public void deleteReader(LoginData ld) throws LibraryException {
        new SQLUser(ld).delete(database);
    }
    
    /** gets IDs of all registered readers */
    public Collection<Integer> getReaders() throws LibraryException {
        return SQLUser.getUsers(database);
    }
    
    /** counts date when book borrowed in this moment should be returned.
     */
    public Calendar countReturnDate() {
        Calendar retval = new GregorianCalendar(); // today
        BorrowTime bt = ServerSettings.getInstance().getBorrowTime();
        retval.add(Calendar.DAY_OF_YEAR, bt.getDays());
        retval.add(Calendar.MONTH, bt.getMonths());
        return retval;
    }
    
    /** gets id of reader using this session
     * @throws PermissionException when session is anonymous or has expired
     */
    private int getReaderId(AbstractSession session) throws LibraryException {
        try {
            Session s = (Session)session;
            if (!users.sessionExists(s))
                throw new PermissionException(ResourceBundle.getBundle("library/server/server").getString("you_need_to_log_in_to_do_this"));
            return users.getUserId(s);
        } catch(LibraryException e) {
            throw e;
        }  catch(Exception e) {
            throw new InternalErrorException(e.getLocalizedMessage());
        }
    }
    
    /** package-only method */
    Database getDatabase() {
        return database;
    }
    
    private static final long serialVersionUID = 6715564775379468631L;
    
}
