/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;
import static org.junit.Assert.fail;
import cz.muni.fi.pb138.log4jconverter.Helper;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author fivekeyem
 */
public class XMLParserTest {
    
    
    public XMLParserTest() {
    }
    private Document doc;
    private Configuration config;
   

    
    @Before
    public void setUp() throws ParserConfigurationException, IOException, SAXException {
         config = new Configuration();
         File file = new File("../test/cz/muni/fi/pb138/log4jconverter/Configuration.xml");
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         DocumentBuilder db = dbf.newDocumentBuilder();
         this.doc = db.parse(file);
    }

    @Test
    public void testInit() {
        try {
            XMLParser xmlp = new XMLParser(null);
            fail();
        } catch (IllegalArgumentException ex) { }
        catch (Exception ex) {
            fail();   
        }
    }

}
