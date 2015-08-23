package library.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

public interface LibraryReaderInterface extends Remote {
    
    /** Logs in. May throw {@link LoginException} or {@link InternalErrorException} */
    public AbstractSession logIn(LoginData ld) throws RemoteException;

    /** Logs out. May throw {@link PermissionException} when session expired.
     * Has no effect on anon sessions (no errors in this case).
     */
    public void logOut(AbstractSession session) throws RemoteException;

    /** Finds books. May throw {@link InternalErrorException} on database error. */
    public Collection<Book> findBooks(SearchPattern pattern) throws RemoteException;

    public Collection<BookCopy> getBorrowedBooks(AbstractSession session) throws RemoteException;

    public Collection<BookCopy> getRequestedBooks(AbstractSession session) throws RemoteException;

    public Collection<ReservationDetails> getReservedBooks(AbstractSession session) throws RemoteException;
    
    /** returns information on copies of certain book, but without information about
     * user's position in reservation queue 
     */
    public Collection<ReservationDetails> getBookCopies(int bookId) throws RemoteException;

    public void requestBooks(AbstractSession session, int[] copyIds) throws RemoteException;

    public void reserveBooks(AbstractSession session, int[] copyIds) throws RemoteException;

    public void cancelReservations(AbstractSession session, int[] copyIds) throws RemoteException;
}
