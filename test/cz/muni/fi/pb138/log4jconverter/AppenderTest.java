package cz.muni.fi.pb138.log4jconverter;

import java.util.HashMap;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Jonge
 */
public class AppenderTest {
    
    @Test
    public void AppenderSetGetTest() {
        Appender appender1 = null;
        Appender appender2 = null;
        
        // Name test
        appender1.setAppenderName("Appender1");
        appender2.setAppenderName("Appender2");
        assertEquals(appender1.getAppenderName(), "Appender1");
        assertEquals(appender1.getAppenderName(), "Appender2");
        
        appender1.setAppenderName("");
        appender2.setAppenderName(null);
        assertEquals(appender1.getAppenderName(), "");
        assertNull(appender2.getAppenderName());
        
        // File size
        appender1.setMaxFileSize("20KB");
        appender2.setMaxFileSize("30KB");
        assertEquals(appender1.getMaxFileSize(), "20KB");
        assertEquals(appender1.getMaxFileSize(), "30KB");
        
        appender1.setMaxFileSize("");
        appender2.setMaxFileSize(null);
        assertEquals(appender1.getMaxFileSize(), "");
        assertNull(appender2.getMaxFileSize());
    }
}
