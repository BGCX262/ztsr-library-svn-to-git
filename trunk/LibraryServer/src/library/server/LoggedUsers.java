package library.server;

import library.common.LibraryException;
import library.common.LoginData;
import java.util.*;
import library.common.InternalErrorException;
import library.common.PermissionException;


public class LoggedUsers implements Users {
    
    /** contains logins of logged in users */
    private Set<Integer> logged;
    
    /** maps session IDs to user logins */
    private Map<Integer,Integer> userIds;
    
    /** last ID given to a session */
    private int lastSessionId;
    
    public LoggedUsers() {
        logged = new HashSet<Integer>();
        userIds = new HashMap<Integer,Integer>();
        lastSessionId=1;
    }
    
    public synchronized Session tryLogin(LoginData ld, Database db) throws LibraryException {
        if (ld.isAnonymous())
            return new Session(); // anonymous
        
        new SQLUser(ld).tryLogin(db); // throws exception on failure
        
        return createSession(ld.getLogin());
    }
    
    public boolean sessionExists(Session session) {
        if (session.isAnonymous())
            return false;
        return userIds.containsKey(session.getSessionId());
    }
    
    public synchronized void logOut(Session session) throws LibraryException {
        if(session.isAnonymous())
            return; // don't do anything with anon session
        if (!sessionExists(session))
            throw new PermissionException(ResourceBundle.getBundle("library/server/server").getString("tried_to_log_out_but_session_expired_or_did_not_exist"));
        logged.remove(getUserId(session));
        userIds.remove(session.getSessionId());
    }
    
    public int getUserId(Session session) throws LibraryException {
        if (!userIds.containsKey(session.getSessionId()))
            throw new InternalErrorException();
        return userIds.get(session.getSessionId());
    }
    
    /** creates non-anonymous session, remembers it in maps and returns.
     * Increments {@link #lastSessionId}.
     */
    protected Session createSession(int login) {
        Session retval = new Session(++lastSessionId);
        logged.add(login);
        userIds.put(retval.getSessionId(), login);
        return retval;
    }
}
