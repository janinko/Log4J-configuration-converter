/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author fivekeyem
 */
public class XMLParserTest {
    
    
    public XMLParserTest() {
    }

    
    @Before
    public void setUp() {
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
