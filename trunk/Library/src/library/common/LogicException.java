package library.common;

/** attempt of illogical action, eg. reserving non-existing books,
 * requesting books that are not available and so on
 */
public class LogicException extends LibraryException {
    public LogicException() {
        super();
    }
    
    public LogicException(String s) {
        super(s);
    }
    
    private static final long serialVersionUID = 5154123902203815459L;
}
