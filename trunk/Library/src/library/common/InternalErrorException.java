package library.common;

/** database error on server or similar technical problems */
public class InternalErrorException extends LibraryException {
    public InternalErrorException() {
        super();
    }
    
    public InternalErrorException(String s) {
        super(s);
    }
    
    private static final long serialVersionUID = -2395713285738215029L;
}
