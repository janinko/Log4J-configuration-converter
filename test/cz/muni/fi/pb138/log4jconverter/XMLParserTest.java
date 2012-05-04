/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

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
