/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import java.util.Properties;
import org.w3c.dom.Document;

/**
 * This class represents basic operations for loading
 * files. 
 * 
 * @author fivekeyem
 */
public class FileLoader {
    
    
    public enum Type {
        XML, PROPERTIES, OTHER
    }
    
    
    
    public Type getType() {
        // TODO
        return Type.OTHER;
    }
    
    /*
     * 
     * 
     * @return 
     */
    public Document getDOM() {
        // TODO
        return null;
    }
    
    
    public Properties getProperties() {
        // TODO
        return null;
    }
    
}
