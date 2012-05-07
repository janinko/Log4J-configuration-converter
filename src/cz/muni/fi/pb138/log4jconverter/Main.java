package cz.muni.fi.pb138.log4jconverter;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import cz.muni.fi.pb138.log4jconverter.InputLoader.Type;


public class Main {
	public static Type confOut = Type.OTHER;

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
       PropertyConfigurator.configure("log4j.properties"); 

       Config config = new Config(args);
       
               
    }
    
    // nvm ci sa to hodi do tejto classy
    private  void serializetoXML(URI output, Document doc)
           throws IOException, TransformerConfigurationException, TransformerException {
       // Vytvorime instanci tovarni tridy
       TransformerFactory factory = TransformerFactory.newInstance();
       // Pomoci tovarni tridy ziskame instanci tzv. kopirovaciho transformeru
       Transformer transformer = factory.newTransformer();
       // Vstupem transformace bude dokument v pameti
       DOMSource source = new DOMSource(doc);
       // Vystupem transformace bude vystupni soubor
       StreamResult result = new StreamResult(output.toString());
       // Provedeme transformaci
       transformer.transform(source, result);
   }

   private void serializetoXML(File output, Document doc) throws IOException,
           TransformerException {
       serializetoXML(output.toURI(), doc);
   }
    

   
    
}
