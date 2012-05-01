package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.InputLoader.Type;
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
    private static final String fileStringNoSuffix = "TestingFile";
    private static final String fileStringInvalidSuffix = "TestingFile.java";
    
    private static final File fileXML = new File(fileStringXML);
    private static final File fileProperties = new File(fileStringProperties);
    private static final File fileNoSuffix = new File(fileStringNoSuffix);
    private static final File fileInvalidSuffix = new File(fileStringInvalidSuffix);
        
    @BeforeClass
    public static void setUp() {        
        try {
            fileXML.createNewFile();
            fileProperties.createNewFile();
            fileNoSuffix.createNewFile();
            fileInvalidSuffix.createNewFile();
        } catch (IOException ex){
            System.out.println("Error when creating the testing files.");
        }
    }
    
    @AfterClass
    public static void tearDown() {
        if (!fileXML.delete()
                || !fileProperties.delete()
                || !fileNoSuffix.delete()
                || !fileInvalidSuffix.delete()) {
            System.out.println("Error when deleting the testing files.");
        }
    }
    
    @Test
    public void constructTest() {
        String fileStringNull = null;
        String fileStringEmpty = "";
        
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
    public void fileTypeTest() {
        InputLoader inputLoaderXML = new cz.muni.fi.pb138.log4jconverter.InputLoader(fileStringXML);
        InputLoader inputLoaderProperties = new cz.muni.fi.pb138.log4jconverter.InputLoader(fileStringProperties);
        InputLoader inputLoaderNoSuffix = new cz.muni.fi.pb138.log4jconverter.InputLoader(fileStringNoSuffix);
        InputLoader inputLoaderInvalidSuffix = new cz.muni.fi.pb138.log4jconverter.InputLoader(fileStringInvalidSuffix);
        
        assertEquals(inputLoaderXML.getType(), Type.XML);
        assertEquals(inputLoaderProperties.getType(), Type.PROPERTIES);
        assertEquals(inputLoaderNoSuffix.getType(), Type.OTHER);
        assertEquals(inputLoaderInvalidSuffix.getType(), Type.OTHER);
    }
}
