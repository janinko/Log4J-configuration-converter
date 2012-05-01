package cz.muni.fi.pb138.log4jconverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Jonge
 */
public class InputLoaderTest {
    private static final String fileStringXML = "TestingFile.xml";
    private static final String fileStringProperties = "TestingFile.properties";
    private static final File fileXML = new File(fileStringXML);
    private static final File fileProperties = new File(fileStringProperties);
        
    @BeforeClass
    public static void setUp() {        
        try {
            fileXML.createNewFile();
            fileProperties.createNewFile();
        } catch (IOException ex){
            System.out.println("Exception when creating the testing files.");
        }
    }
    
    @AfterClass
    public static void tearDown() {
        if (!fileXML.delete() || !fileProperties.delete()) {
            System.out.println("Exception when deleting the testing files.");
        }
    }
    
    @Test
    public void InputLoaderConstructTest() {
        String fileStringNull = null;
        String fileStringEmpty = "";
        String fileStringInvalid = "InvalidFile";
        
        InputStream fileStreamNull = null;
        
        try {
            InputLoader stringNull = new cz.muni.fi.pb138.log4jconverter.InputLoader(fileStringNull);
        } catch (IllegalArgumentException ex) {
            // OK
        }
        
        try {
            InputLoader stringEmpty = new cz.muni.fi.pb138.log4jconverter.InputLoader(fileStringEmpty);
        } catch (IllegalArgumentException ex) {
            // OK
        }
        
        try {
            InputLoader streamNull = new cz.muni.fi.pb138.log4jconverter.InputLoader(fileStreamNull);
        } catch (IllegalArgumentException ex) {
            // OK
        }
        
        /*try {
            InputLoader stringInvalid = new cz.muni.fi.pb138.log4jconverter.InputLoader(fileStringInvalid);
        } catch (FileNotFoundException ex) {
            // OK
        }*/
        
        InputLoader inputLoaderXML = new cz.muni.fi.pb138.log4jconverter.InputLoader(fileStringXML);
        InputLoader inputLoaderProperties = new cz.muni.fi.pb138.log4jconverter.InputLoader(fileStringProperties);
        
        assertEquals(fileStringXML, inputLoaderXML.getNameOfFile());
        assertEquals(fileStringProperties, inputLoaderProperties.getNameOfFile());
    }
    
    @Test
    public void InputLoaderFileTypeTest() {
        String fileNull = null;
        String fileEmpty = "";
        String fileInvalid = "InvalidFile";
    }
}
