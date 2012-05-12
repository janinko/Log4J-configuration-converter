/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import cz.muni.fi.pb138.log4jconverter.Helper;
import cz.muni.fi.pb138.log4jconverter.configuration.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
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
         assertNotNull(config);
         assertEquals(config.getThreshold().toString(),"DEBUG");
         assertEquals(config.getDebug().toString(),"false");
         assertEquals(config.isReset(),false);
         
       
         
         
       
          
          
          
         
     }
      @Test
     public void testParseRenderer()
      {
         for(Renderer r : config.getRenderers())
         {
         assertNotNull(r);
         assertEquals(r.getRenderedClass(),"RenderedClass");
         assertEquals(r.getRenderingClass(),"RenderingClass");
         }
          
      }
      @Test
      public void testParseThrowableRenderer()
      {
          
         ThrowableRenderer throwRen = config.getThrowableRenderer();
         assertNotNull(throwRen);
         assertEquals(throwRen.getClassName(),"throwableRenderer.cs");
         testParams(throwRen.getParams());
         
      }
      
       @Test
       public void testParseAppenders()
       {
           for(Appender ap : config.getAppenders().values())
           {
               assertNotNull(ap);
               assertEquals(ap.getAppenderName(),"AppendName");
               assertEquals(ap.getClassName(),"AppendClass");
               
               //testing ErrorHandler
               ErrorHandler error = ap.getErrorHandler();
               assertNotNull(error);
               assertEquals(error.getClassName(),"ErrorClass");
                
               testParams(error.getParams());
               
               assertEquals(error.isRootRef(), true);
               
               for(String s : error.getLoggerRefs())
               {
                   assertEquals(s,"LoggerRef");
               }
               assertEquals(error.getAppenderRef(),"AppendRef");
               //testing rolling policy
               testParams(ap.getParams());
               RollingPolicy rollPolicy = ap.getRollingPolicy();
               assertNotNull(rollPolicy);
               assertEquals(rollPolicy.getName(),"rollingPolicyName" );
               assertEquals(rollPolicy.getClass(),"rollingPolicyClass" );
               testParams(rollPolicy.getParams());
               
               //testing triggering policy
               TriggeringPolicy trigPolicy = ap.getTriggeringPolicy();
               assertNotNull(trigPolicy);
               assertEquals(trigPolicy.getName(),"triggeringPolicyName" );
               assertEquals(trigPolicy.getClass(),"triggeringPolicyClass" );
               testParams(trigPolicy.getParams());
               for(Filter f : trigPolicy.getFilters())
               {
                   assertEquals(f.getClassName(),"FilterClass");
                   testParams(f.getParams());
               }
               
               // testing connection source
               ConnectionSource conSource = ap.getConnectionSource();
               assertNotNull(conSource);
               assertEquals(conSource.getClassName(),"connectionSourceClass");
               
               DataSource dataSource = conSource.getDataSource();
               assertNotNull(dataSource);
               assertEquals(dataSource.getClassName(),"dataSourceClass");
               testParams(dataSource.getParams());
               
               testParams(conSource.getParams());
               
               //testing Layout
               Layout layout = ap.getLayout();
               assertNotNull(layout);
               assertEquals(layout.getClassName(),"layoutClass");
               testParams(layout.getParams());
               // testing filters
               
               for(Filter f : ap.getFilters())
               {
                   assertEquals(f.getClassName(),"FilterClass");
                   testParams(f.getParams());
               }
               //testing appender-Refs
               for(String s : ap.getAppenderRefs())
               {
                   assertEquals(s,"AppendRef");
               }
               
               
           }
       }
       
       
       @Test
       public void testParsePlugin()
       {
           for(Plugin p : config.getPlugins().values())
           {
               assertNotNull(p);
               assertEquals(p.getName(), "PluginName");
               assertEquals(p.getClassName(), "PluginClass");
               
               testParams(p.getParams());
               
               ConnectionSource conSource = p.getConnSource();
               assertNotNull(conSource);
               assertEquals(conSource.getClassName(),"connectionSourceClass");
               
               DataSource dataSource = conSource.getDataSource();
               assertNotNull(dataSource);
               assertEquals(dataSource.getClassName(),"dataSourceClass");
               testParams(dataSource.getParams());
               
               testParams(conSource.getParams());
           }
           
       }
     
    
       private static void testParams(HashMap<String,String> params)
       {
            HashSet<String> keys = (HashSet<String>) params.keySet();
            HashSet<String> values = (HashSet<String>) params.values();
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
