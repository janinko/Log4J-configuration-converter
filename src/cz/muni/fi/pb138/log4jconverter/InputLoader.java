/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import java.io.*;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * This class represents basic operations for loading
 * files. 
 * 
 * @author fivekeyem
 */
public class InputLoader {
    
    
    public enum Type {
        XML, PROPERTIES, OTHER
    }
    
    private Document xmlDoc = null;
    private Properties propertiesDoc = null;
    
    private String nameOfFile;
    private InputStream is;
    
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(InputLoader.class);
    
    
    public InputLoader(InputStream is) {
        this.is = is;
    }
    
    public InputLoader(String nameOfFile) {
        if (nameOfFile == null) {
            throw new NullPointerException("No name of file");
        }
        if (nameOfFile.isEmpty()) {
            throw new IllegalArgumentException("Name is empty");
        }  
        this.nameOfFile = nameOfFile;
        try {            
            this.is = new FileInputStream(new File(nameOfFile)); 
            logger.info("File "+ nameOfFile +" is loaded");
        } catch (FileNotFoundException ex) {
            logger.error("File doesn't exist");
        }
    }
    
    
    // for now we recognize typ of input according to extension of file
    // in the future we'll do it according content
    public Type getType() {
        String extension = FilenameUtils.getExtension(nameOfFile);
        if (extension.equals("xml")) {
            return Type.XML;
        } else if (extension.equals("properties")) {
            return Type.PROPERTIES;
        }
        return Type.OTHER;
    }
    
    
    public Document getDOM() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        if (logger.isTraceEnabled()) { logger.trace("DocumentBuilder is ok"); }
        
        builder.setErrorHandler(new org.xml.sax.ErrorHandler() {
            //Ignore the fatal errors
            public void fatalError(SAXParseException exception) throws SAXException { }
            
            //Validation errors 
            public void error(SAXParseException e) throws SAXParseException {
                System.out.println("Error at " +e.getLineNumber() + " line.");
                System.out.println(e.getMessage());
                System.exit(0);
            }
            
            //Show warnings
            public void warning(SAXParseException err) throws SAXParseException {
                System.out.println(err.getMessage());
                System.exit(0);
            }
        });
        if (logger.isTraceEnabled()) { logger.trace("setErroHanler is ok"); }
        
        xmlDoc = builder.parse(this.is);
        return xmlDoc;
    }
    
    
    public Properties getProperties() throws IOException {
        Properties p = new Properties();
        p.load(this.is);
        return p;
    }
    
}
