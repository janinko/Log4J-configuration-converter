/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Admin
 */
public class XMLPrinter {
    
    Configuration config;
    Document doc;
    
    
    public XMLPrinter(Configuration config)
    {
        if(config == null)
            throw new IllegalArgumentException("config");
        this.config = config;
        this.doc = null;
    }
    
    
    
}
