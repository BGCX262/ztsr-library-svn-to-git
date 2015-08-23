package library.common;

import java.io.Serializable;

public abstract class AbstractSession implements Serializable {
    public abstract boolean isAnonymous();
}
