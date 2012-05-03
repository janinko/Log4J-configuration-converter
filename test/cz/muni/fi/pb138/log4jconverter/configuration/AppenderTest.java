package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import org.junit.*;

import cz.muni.fi.pb138.log4jconverter.configuration.Appender;
import static org.junit.Assert.*;

/**
 *
 * @author Jonge
 */
public class AppenderTest {
    
    @Test
    public void AppenderSetGetTest() {
        Appender appender1 = new Appender();
        Appender appender2 = new Appender();
        
        // Name test
        appender1.setAppenderName("Appender1");
        appender2.setAppenderName("Appender2");
        assertEquals(appender1.getAppenderName(), "Appender1");
        assertEquals(appender2.getAppenderName(), "Appender2");
        
        appender1.setAppenderName("");
        appender2.setAppenderName(null);
        assertEquals(appender1.getAppenderName(), "");
        assertNull(appender2.getAppenderName());
        
        // File size
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("MaxFileSize", "20KB");
        appender1.setParams(params);
        assertEquals(appender1.getParams(), params);
        //appender2.setMaxFileSize("30KB");
        //assertEquals(appender1.getMaxFileSize(), "30KB");
        
        //appender1.setMaxFileSize("");
        //appender2.setMaxFileSize(null);
        //assertEquals(appender1.getMaxFileSize(), "");
        //assertNull(appender2.getMaxFileSize());
    }
}
