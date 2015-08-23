package library.common;

import java.rmi.RemoteException;

/** generic exception from library server
 * @see LogicException
 * @see LoginException
 * @see InternalErrorException
 * @see PermissionException
 */
public class LibraryException extends RemoteException {
    public LibraryException() {
        super();
    }
    
    public LibraryException(String s) {
        super(s);
    }
    
    private static final long serialVersionUID = 7290899876566747979L;
}
