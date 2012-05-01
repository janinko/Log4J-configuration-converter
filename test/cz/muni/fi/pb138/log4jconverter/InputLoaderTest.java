package cz.muni.fi.pb138.log4jconverter;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Jonge
 */
public class InputLoaderTest {
    
    @Test
    public void InputLoaderStringTest() {
        try {
            new cz.muni.fi.pb138.log4jconverter.InputLoader((String) null);
        } catch (IllegalArgumentException ex) {
            // OK
        }
        
        try {
            new cz.muni.fi.pb138.log4jconverter.InputLoader("");
        } catch (IllegalArgumentException ex) {
            // OK
        }
    }
}
