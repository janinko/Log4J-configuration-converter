package cz.muni.fi.pb138.log4jconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;
import java.util.Enumeration;


public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
       PropertyConfigurator.configure("log4j.properties"); 

       // ABSTRAKT MODEL
       Configuration c;
    
       /*
        * InputLoader
        * 
       InputLoader inputLoader = new InputLoader("examples/exampleConfigs.xml");
       NodeList nl = inputLoader.getDOM().getElementsByTagName("param");
       
       for (int i = 0; i < nl.getLength(); i++) {
           Node node = nl.item(i);
           if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                System.out.println(e.getNodeName());
           }
       } 
       */
       
       
       /*
        * properties -> abstract 
        */
        InputLoader il1 = new InputLoader("examples/exampleConfigs.properties");
        Properties properties = il1.getProperties();
       
        PropertiesParser pp = new PropertiesParser(properties);
        c = pp.parse();     
        c.generateProperties().list(System.out);
			
		
        
       
       
       /*
        * xml -> abstract 
        *   
        InputLoader il2 = new InputLoader("examples/exampleConfigs1.xml");
        Document doc = il2.getDOM();
       
        XMLParser xmlp = new XMLParser(doc);
        c = xmlp.parse();
        System.out.println(c);
        * 
        */
               
    }
    
}
