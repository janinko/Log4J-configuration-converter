package cz.muni.fi.pb138.log4jconverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        

       InputLoader inputLoader = new InputLoader("exampleConfigs.xml");
       System.out.println(inputLoader.getType());
    
       NodeList nl = inputLoader.getDOM().getElementsByTagName("param");
       
       for (int i = 0; i < nl.getLength(); i++) {
           Node node = nl.item(i);
           if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element e = (Element) node;
                System.out.println(e.getNodeName());
           }
       }
       
       
       /*
       Properties properties = inputLoader.getProperties();
       String one = properties.getProperty("log4j.appender.A1.layout");
       System.out.println(one);
       * 
       */
               
    }
    
}
