/*
 * XMLSettingsSource.java
 *
 * Created on 21 marzec 2007, 23:02
 */

package util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Piotrek
 */
public abstract class XMLSettingsSource implements SettingsSource {
    private String filename;
    private String programName;
    
    private List<String> keysToSave;
    private List<String> valsToSave;
    
    /**
     * creates source
     *@param filename name of file from which options will be read and where they
     * will be saved
     *@param programName name of program like "Library"
     */
    public  XMLSettingsSource(String programName, String filename) {
        this.filename = filename;
        this.programName = programName;
        keysToSave = new LinkedList<String>();
        valsToSave = new LinkedList<String>();
    }
    
    /** creates source with default filename "settings.xml".
     *@param programName name of program like "Library"
     */
    public XMLSettingsSource(String programName) {
        this("settings.xml", programName);
    }
    
    /** changes name of file where settings are saved and from which are loaded.
     * Please use .xml extension.
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    /**
     *loads settings from a file (if it exists). If file does not exist, does
     * not do anything.
     */
    public void load(AbstractSettings settings) {
        File file = new File(filename);
        if( ! file.exists() ) {
            System.err.println("cannot load settings because file "+filename+ " does not exist");
            return;
        }
        try {
            DefaultHandler handler = new SettingsLoader(settings);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(file, handler);
        } catch(Exception e ) {
            System.err.println("Error loading settings");
            System.err.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * erases settings file and saves settings to file
     */
    public void save(AbstractSettings settings) {
        try{
            keysToSave.clear();
            valsToSave.clear();
            prepareToSave(settings);
            
            File f = new File(filename);
            if( f.exists() ) f.delete();
            InputSource inputSource = new InputSource();
            SettingsSaver saxReader = new SettingsSaver(settings,programName);
            SAXSource source = new SAXSource(saxReader, inputSource);
            StreamResult result = new StreamResult(f);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.transform(source, result);
        } catch(Exception e) {
            System.err.println("Cannot save settings");
            System.err.println(e.getLocalizedMessage());
        }
    }
    
    /** handle key in loading settings.
     * Obviously, keys and types of values should be the same as used in
     * {@link #prepareToSave prepareToSave()}
     */
    protected abstract void handleKey(String key, String value, AbstractSettings settings);
    
    /** save all values using {@link #saveValue saveValue()} method.
     * Obviously, keys and types of values should be the same as used in
     * {@link #handleKey handleKey()}
     */
    protected abstract void prepareToSave(AbstractSettings settings);
    
    /** prepares settings key and value to be saved */
    protected void saveValue(String key, String value) {
        keysToSave.add(key);
        valsToSave.add(value);
    }
    
    /** prepares settings key and value to be saved */
    protected void saveValue(String key, Number value) {
        keysToSave.add(key);
        valsToSave.add(value.toString());
    }
    
    /** loads settings from file */
    class SettingsLoader extends DefaultHandler {
        private String currElement;
        private AbstractSettings settings;
        
        public SettingsLoader(AbstractSettings settings) {
            currElement = "";
            this.settings = settings;
        }
        
        public void startElement(String aURI, String aLocal, String aQName,
                Attributes aAttrs)
                throws SAXException {
            currElement = aQName;
        }
        
        public void endElement(String aURI, String aLocal, String qName)
        throws SAXException {
            currElement = "";
        }
        
        public void characters(char[] aCh, int aStart, int aLength)
        throws SAXException {
            if (currElement.equals(""))
                return;
            String value = new String(aCh, aStart, aLength);
            value = value.replaceAll("\\n|\\r|\\f","");
            value = value.trim();
            if (value.equals(""))
                return;
            handleKey(currElement,value,settings);
            
        }
    } // MyHandler
    
    
    
    /** saves settings */
    class SettingsSaver implements XMLReader {
        private AbstractSettings settings;
        private String programName;
        
        private ContentHandler handler;
        private String ns = ""; // namespace
        final int indent = 3; // indent size (in spaces)
        private Attributes atts = new AttributesImpl(); // null attributes
        
        public SettingsSaver(AbstractSettings settings, String programName) {
            this.settings = settings;
            this.programName = programName;
        }
        
        private void indentation(int indentSize) throws SAXException {
            char[] indent = new char[indentSize];
            for(int i=0; i<indentSize; ++i)
                indent[i] = ' ';
            handler.ignorableWhitespace(indent, 0, indentSize);
        }
        
        private void newLine() throws SAXException {
            handler.ignorableWhitespace("\n".toCharArray(), 0, 1);
        }
        
        /** new line and indent at next line */
        private void newLine(int indentSize) throws SAXException {
            String indent = "\n";
            for(int i=0; i<indentSize; i++) {
                indent = indent.concat(" ");
            }
            indentation(indentSize);
        }
        
        private void writeOption(String name, String value) throws SAXException {
            indentation(indent);
            handler.startElement(ns, name, name, atts);
            handler.characters(value.toCharArray(), 0, value.length());
            handler.endElement(ns, name, name);  newLine();
        }
        
        private void writeOption(String name, Number value) throws SAXException {
            writeOption(name, value.toString());
        }
        
        /** zapisuje ustawienia */
        public void parse(InputSource input)
        throws IOException, SAXException {
            
            String val;
            if( handler == null ) throw new SAXException("no content handler");
            
            handler.startDocument();
            newLine();
            
            AttributesImpl mainAtts = new AttributesImpl();
            handler.startElement(ns, programName, programName, mainAtts);  newLine();
            handler.startElement(ns, "settings", "settings", atts); newLine();
            
            Iterator<String> keyIter=keysToSave.iterator(), valIter=valsToSave.iterator();
            for( ; keyIter.hasNext() ; ) {
                String key = keyIter.next();
                String value = valIter.next();
                
                writeOption(key,value);
            }
            
            handler.endElement(ns,"settings","settings"); newLine();
            handler.endElement(ns,programName,programName); newLine();
            handler.endDocument();
        }
        
        // <editor-fold defaultstate="collapsed" desc="Some dumb BS ">
        /** Allow an application to register a content event handler. */
        public void setContentHandler(ContentHandler handler) {
            this.handler = handler;
        }
        
        /** Return the current content handler. */
        public ContentHandler getContentHandler() {
            return this.handler;
        }
        
        //=============================================
        // IMPLEMENT THESE FOR A ROBUST APP
        //=============================================
        /** Allow an application to register an error event handler. */
        public void setErrorHandler(ErrorHandler handler) {
        }
        
        /** Return the current error handler. */
        public ErrorHandler getErrorHandler() {
            return null;
        }
        
        //=============================================
        // IGNORE THESE
        //=============================================
        /** Parse an XML document from a system identifier (URI). */
        public void parse(String systemId)
        throws IOException, SAXException {
        }
        
        /** Return the current DTD handler. */
        public DTDHandler getDTDHandler() {
            return null;
        }
        
        /** Return the current entity resolver. */
        public EntityResolver getEntityResolver() {
            return null;
        }
        
        /** Allow an application to register an entity resolver. */
        public void setEntityResolver(EntityResolver resolver) {
        }
        
        /** Allow an application to register a DTD event handler. */
        public void setDTDHandler(DTDHandler handler) {
        }
        
        /** Look up the value of a property. */
        public Object getProperty(String name) {
            return null;
        }
        
        /** Set the value of a property. */
        public void setProperty(String name, Object value) {
        }
        
        /** Set the state of a feature. */
        public void setFeature(String name, boolean value) {
        }
        
        /** Look up the value of a feature. */
        public boolean getFeature(String name) {
            return false;
        }
        //</editor-fold>
        
    }
    
}

