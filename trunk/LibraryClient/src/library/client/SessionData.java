package library.client;

import java.rmi.RemoteException;
import library.common.AbstractSession;
import library.common.LibraryReaderInterface;
import library.common.LoginData;

/**
 * @author Karolina
 */
public class SessionData {
    
    private String login;
    private LibraryReaderInterface library;
    private AbstractSession session;
    
    /** Creates a new instance of SessionData */
    public SessionData() {
        login = "";
        library = null;
        session = null;
    }
    
    public boolean isAnonymous() { return (session == null) ? true : session.isAnonymous(); }
    
    public void setLogin(String l) { login = l; }
    
    public String getLogin() { return login; }
    
    public void setLibrary(LibraryReaderInterface l) { library = l; }
    
    public LibraryReaderInterface getLibrary() { return library; }
    
    /** logs in; if not logged out, logs out first */
    public void logIn(LoginData ldata) throws RemoteException {
        if (session != null)
            logOut();
        setSession(getLibrary().logIn(ldata));
    }
    
    /** logs out; has no effect if already logged out */
    public void logOut() throws RemoteException {
        AbstractSession session = getSession();
        if (session != null) {
            getLibrary().logOut(session);
            setSession(null);
        }
    }

    private void setSession(AbstractSession s) { session = s; }
    
    public AbstractSession getSession() { return session; }
    
}
