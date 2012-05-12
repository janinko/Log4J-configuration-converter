/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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
import org.xml.sax.SAXException;

import cz.muni.fi.pb138.log4jconverter.configuration.Appender;
import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;
import cz.muni.fi.pb138.log4jconverter.configuration.ConnectionSource;
import cz.muni.fi.pb138.log4jconverter.configuration.DataSource;
import cz.muni.fi.pb138.log4jconverter.configuration.ErrorHandler;
import cz.muni.fi.pb138.log4jconverter.configuration.Filter;
import cz.muni.fi.pb138.log4jconverter.configuration.Layout;
import cz.muni.fi.pb138.log4jconverter.configuration.Level;
import cz.muni.fi.pb138.log4jconverter.configuration.Logger;
import cz.muni.fi.pb138.log4jconverter.configuration.LoggerFactory;
import cz.muni.fi.pb138.log4jconverter.configuration.Plugin;
import cz.muni.fi.pb138.log4jconverter.configuration.Renderer;
import cz.muni.fi.pb138.log4jconverter.configuration.RollingPolicy;
import cz.muni.fi.pb138.log4jconverter.configuration.Root;
import cz.muni.fi.pb138.log4jconverter.configuration.ThrowableRenderer;
import cz.muni.fi.pb138.log4jconverter.configuration.TriggeringPolicy;

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
            new XMLParser(null);
            fail();
        } catch (IllegalArgumentException ex) {
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void testParseConfigurationAttributes() {
        assertNotNull(config);
        assertEquals(config.getThreshold().toString(), "debug");
        assertEquals(config.getDebug().toString(), "false");
        assertEquals(config.isReset(), false);









    }

    @Test
    public void testParseRenderer() {
        for (Renderer r : config.getRenderers()) {
            assertNotNull(r);
            assertEquals(r.getRenderedClass(), "RenderedClass");
            assertEquals(r.getRenderingClass(), "RenderingClass");
        }

    }

    @Test
    public void testParseThrowableRenderer() {

        ThrowableRenderer throwRen = config.getThrowableRenderer();
        assertNotNull(throwRen);
        assertEquals(throwRen.getClassName(), "throwableRenderer.cs");
        testParams(throwRen.getParams());

    }

    @Test
    public void testParseAppenders() {
        for (Appender ap : config.getAppenders().values()) {
            assertNotNull(ap);
            assertEquals(ap.getAppenderName(), "AppendName");
            assertEquals(ap.getClassName(), "AppendClass");

            //testing ErrorHandler
            ErrorHandler error = ap.getErrorHandler();
            assertNotNull(error);
            assertEquals(error.getClassName(), "ErrorClass");

            testParams(error.getParams());

            assertEquals(error.isRootRef(), true);

            for (String s : error.getLoggerRefs()) {
                assertEquals(s, "LoggerRef");
            }
            assertEquals(error.getAppenderRef(), "AppendRef");
            //testing rolling policy
            testParams(ap.getParams());
            RollingPolicy rollPolicy = ap.getRollingPolicy();
            assertNotNull(rollPolicy);
            assertEquals(rollPolicy.getName(), "rollingPolicyName");
            assertEquals(rollPolicy.getClass(), "rollingPolicyClass");
            testParams(rollPolicy.getParams());

            //testing triggering policy
            TriggeringPolicy trigPolicy = ap.getTriggeringPolicy();
            assertNotNull(trigPolicy);
            assertEquals(trigPolicy.getName(), "triggeringPolicyName");
            assertEquals(trigPolicy.getClass(), "triggeringPolicyClass");
            testParams(trigPolicy.getParams());
            for (Filter f : trigPolicy.getFilters()) {
                assertEquals(f.getClassName(), "FilterClass");
                testParams(f.getParams());
            }

            // testing connection source
            ConnectionSource conSource = ap.getConnectionSource();
            assertNotNull(conSource);
            assertEquals(conSource.getClassName(), "connectionSourceClass");

            DataSource dataSource = conSource.getDataSource();
            assertNotNull(dataSource);
            assertEquals(dataSource.getClassName(), "dataSourceClass");
            testParams(dataSource.getParams());

            testParams(conSource.getParams());

            //testing Layout
            Layout layout = ap.getLayout();
            assertNotNull(layout);
            assertEquals(layout.getClassName(), "layoutClass");
            testParams(layout.getParams());
            // testing filters

            for (Filter f : ap.getFilters()) {
                assertEquals(f.getClassName(), "FilterClass");
                testParams(f.getParams());
            }
            //testing appender-Refs
            for (String s : ap.getAppenderRefs()) {
                assertEquals(s, "AppendRef");
            }


        }
    }

    @Test
    public void testParsePlugin() {
        for (Plugin p : config.getPlugins().values()) {
            assertNotNull(p);
            assertEquals(p.getName(), "PluginName");
            assertEquals(p.getClassName(), "PluginClass");

            testParams(p.getParams());

            ConnectionSource conSource = p.getConnSource();
            assertNotNull(conSource);
            assertEquals(conSource.getClassName(), "connectionSourceClass");

            DataSource dataSource = conSource.getDataSource();
            assertNotNull(dataSource);
            assertEquals(dataSource.getClassName(), "dataSourceClass");
            testParams(dataSource.getParams());

            testParams(conSource.getParams());
        }

    }

    @Test
    public void testParseLogger() {
        for (Logger log : config.getLoggers()) {
            assertNotNull(log);
            assertEquals(log.getName(), "LoggerName");
            assertEquals(log.getClassName(), "LoggerClass");
            testParams(log.getParams());

            testLevel(log.getLevel()); 
            

            for (String s : log.getAppenderRefs()) {
                assertEquals(s, "AppendRef");
            }
        }

    
    }
    
     @Test
     public void testParseLoggerFactory()
    {
        LoggerFactory logFac = config.getLogFactory();
        assertNotNull(logFac);
        assertEquals(logFac.getClassName(),"loggerFactoryClass");
        testParams(logFac.getParams());
        
        
        
    }
    
     @Test
     public void testParseRoot()
     {
         Root root = config.getRoot();
         assertNotNull(root);
         testParams(root.getParams());
         testLevel(root.getLevel()); 
         for (String s : root.getAppenderRefs()) {
                assertEquals(s, "AppendRef");
            }
         
         
     
     }

    private static void testParams(HashMap<String, String> params) {
        HashSet<String> keys = (HashSet<String>) params.keySet();
        HashSet<String> values = (HashSet<String>) params.values();
        for (String s : keys) {
            assertEquals(s, "ParamName");
        }
        for (String s : values) {
            assertEquals(s, "ParamValue");
        }
    }
    
    private static void testLevel(Level lev)
    {
         assertNotNull(lev);
            assertEquals(lev.getClassName(), "LevelClass");
            assertEquals(lev.getValues(), "DEBUG");
            testParams(lev.getParams());

           
        
    }
}
