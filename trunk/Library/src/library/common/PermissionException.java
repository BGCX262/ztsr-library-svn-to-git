package library.common;

/** thrown on attempt of action requiring larger permissions, eg.
 * anonymous user tries to borrow books
 */
public class PermissionException extends LibraryException {
    public PermissionException() {
        super();
    }
    
    public PermissionException(String s) {
        super(s);
    }
    
    private static final long serialVersionUID = -6717116049399798036L;
}
