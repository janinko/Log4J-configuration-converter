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


public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
       PropertyConfigurator.configure("log4j.properties"); 

       // ABSTRAKT MODEL
       Configuration configuration = new Configuration();
    
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
        * 
       InputLoader inputLoader = new InputLoader("examples/exampleConfigs.properties");
       Properties properties = inputLoader.getProperties();
       
       PropertyConfiguration pc = new PropertyConfiguration(properties, configuration);
       pc.parseProperty();
       
        System.out.println(configuration);
        * 
        */
       
               
    }
    
}
