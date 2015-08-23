package library.common;

/** invalid password on login */
public class LoginException extends LibraryException {
    public LoginException() {
        super();
    }
    
    public LoginException(String s) {
        super(s);
    }
    
    private static final long serialVersionUID = 3618039473034378830L;
}
