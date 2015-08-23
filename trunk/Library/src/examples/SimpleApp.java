/*
 * SimpleApp.java
 *
 * Created on 22 marzec 2007, 00:00
 */

package examples;

/**
 *
 * @author Piotrek
 */
public class SimpleApp {
    
    private void dumpSettings(SimpleSettings s) {
        System.out.printf("%d %s %f\n", s.getIntValue(), s.getStringValue(), s.getFloatValue());
    }
    
    /** Creates a new instance of SimpleApp */
    public SimpleApp() {
        SimpleSettings settings = new SimpleSettings("simpleApp");
        dumpSettings(settings);
        System.out.println("settings loaded");
        settings.load();
        dumpSettings(settings);
        settings.setIntValue(settings.getIntValue() + 1);
        System.out.println("incremented intValue");
        dumpSettings(settings);
        settings.save();
        System.out.println("settings saved");
    }
    
    public static void main(String[] args) {
        SimpleApp a = new SimpleApp();
    }
    
}
