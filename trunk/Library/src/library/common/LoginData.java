package library.common;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class LoginData implements Serializable {
    private static final int ANON_LOGIN=0;
    
    private int login;

    private String passwordHash;
    
    /** creates anonymous login data */
    public LoginData() {
        this.login = ANON_LOGIN;
        this.passwordHash = "";
    }

    public LoginData(int login, String password) {
        this.login = login;
        this.passwordHash = "";
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            passwordHash = new String(md.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getLogin() {
        return login;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }
    
    /** returns true if login data doesn't belong to any particular user */
    public boolean isAnonymous() {
        return login == ANON_LOGIN;
    }
    
    private static final long serialVersionUID = -8852428413159135582L;
}
