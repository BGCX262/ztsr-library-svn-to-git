package library.server;

import library.common.AbstractSession;

class Session extends AbstractSession {
    private int sessionId;

    private static final int ANON_SESSIONID=-1;

    /**
     * creates anonymous session
     */
    public Session() {
        sessionId = ANON_SESSIONID;
    }

    /**
     * creates non-anonymous session
     */
    public Session(int id) {
        sessionId = id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public boolean isAnonymous() {
        return sessionId == ANON_SESSIONID;
    }
    
    private static final long serialVersionUID = -8126522070738064862L;
}
