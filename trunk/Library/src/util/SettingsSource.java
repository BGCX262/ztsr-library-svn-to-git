/*
 * SettingsSource.java
 *
 * Created on 21 marzec 2007, 22:57
 */

package util;

/**
 * Saves and loads settings. May be an INI file, XML file, database or even
 * something more exotic.
 * @author Piotrek
 */
public interface SettingsSource {
    public void load(AbstractSettings settings);
    public void save(AbstractSettings settings);
    
}
