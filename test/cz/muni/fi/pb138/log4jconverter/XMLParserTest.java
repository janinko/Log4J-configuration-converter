/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import cz.muni.fi.pb138.log4jconverter.Helper;
import cz.muni.fi.pb138.log4jconverter.configuration.Renderer;
import cz.muni.fi.pb138.log4jconverter.configuration.ThrowableRenderer;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
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
    private XMLParser parser;
   

    
    @Before
    public void setUp() throws ParserConfigurationException, IOException, SAXException {
        
         
         File file = new File("../test/cz/muni/fi/pb138/log4jconverter/Configuration.xml");
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         DocumentBuilder db = dbf.newDocumentBuilder();
         this.doc = db.parse(file);
         this.parser = new XMLParser(doc);
         config = parser.parse();
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
     @Test
     public void testParseConfigurationAttributes()
     {
         
         assertEquals(config.getThreshold().toString(),"DEBUG");
         assertEquals(config.getDebug().toString(),"false");
         assertEquals(config.isReset(),false);
         
       
         
         
       
          
          
          
         
     }
      @Test
     public void testParseRenderer()
      {
         for(Renderer r : config.getRenderers())
         {
         assertEquals(r.getRenderedClass(),"RenderedClass");
         assertEquals(r.getRenderingClass(),"RenderingClass");
         }
          
      }
      @Test
      public void testParseThrowableRenderer()
      {
         ThrowableRenderer throwRen = config.getThrowableRenderer();
         assertEquals(throwRen.getClassName(),"throwableRenderer.cs");
         HashSet<String> keys = (HashSet<String>) throwRen.getParams().keySet();
         HashSet<String> values = (HashSet<String>) throwRen.getParams().values();
         for(String s : keys)
         {
             assertEquals(s,"ParamName");
         }
          for(String s : values)
         {
             assertEquals(s,"ParamValue");
         }
      }
     
    
    
    
    
}
