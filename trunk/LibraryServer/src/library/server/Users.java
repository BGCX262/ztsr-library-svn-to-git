package library.server;

import library.common.LibraryException;
import library.common.LoginData;


public interface Users {
    public Session tryLogin(LoginData ld, Database db) throws LibraryException;

    /** @return true if session exists (user has logged in).
     * always returns false on anonymous sessions.
     */
    public boolean sessionExists(Session session);

    /** logs out logged user. has no effect when run by anon session.
     */
    public void logOut(Session session) throws LibraryException;

    /** @return ID of user using this session.
     * @throws InternalErrorException when this session does not exist
     */
    public int getUserId(Session session) throws LibraryException;
}
