/*
 * AbstractSettings.java
 *
 * Created on 21 marzec 2007, 22:54
 */

package util;

/**
 *
 * @author Piotrek
 */
public abstract class AbstractSettings {
    
    private SettingsSource source;
    
    /** Creates a new instance of AbstractSettings without any source.
     * Thus, settings cannot be saved.
     */
    public AbstractSettings() {
        source = null;
    }
    
    public AbstractSettings(SettingsSource source) {
        this.source = source;
    }
    
    public void setSource(SettingsSource source) {
        this.source = source;
    }
    
    public void load() {
        if (source != null)
            source.load(this);
    }
    
    public void save() {
        if (source != null)
            source.save(this);
    }
    
}
