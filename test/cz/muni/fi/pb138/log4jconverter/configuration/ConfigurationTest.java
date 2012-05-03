/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter.configuration;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author fivekeyem
 */
public class ConfigurationTest {
    
    private Configuration c;
    
    public ConfigurationTest() {
    }

    
    @Before
    public void setUp() {
        c = new Configuration();
    }

    
    @Test
    public void testCreateAppender1() {
        Appender a1 = prepareAppander1();  
        Appender a2 = c.getAppender(a1.getAppenderName());
        
        assertEquals(a1, a2);
    }
    
    
    @Test
    public void testCreateAppender2() {
        Appender a1 = prepareAppander1();  
        
        assertFalse(c.isAppender(a1.getAppenderName()));
        c.getAppender(a1.getAppenderName());                // creating new appender
        assertTrue(c.isAppender(a1.getAppenderName()));
        
         
    }
    

    private Appender prepareAppander1() {
            Appender a1 = new Appender();
            a1.setAppenderName("A1");
            a1.setClassName("org.apache.log4j.ConsoleAppender");
            return a1;
        }

}
