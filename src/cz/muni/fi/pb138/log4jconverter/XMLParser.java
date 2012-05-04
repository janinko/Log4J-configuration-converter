package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

public class XMLParser implements Parser {
	
	private Document doc;
        private Configuration configuration;
	
	public XMLParser(Document document){
            this.doc = document;
            this.configuration = null;
	}
        
        public void writeAllXML(URI output) throws TransformerConfigurationException, TransformerException {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(output.toString());
            transformer.transform(source, result);
        }
        
        public void writeAllXML(File output) throws TransformerConfigurationException, TransformerException {
            writeAllXML(output.toURI());
        }
        
        public void parseXML() {
            // TODO
        }

	@Override
	public Configuration parse() {
            if(configuration == null){
                configuration = new Configuration();
                parseXML();
            }
            return configuration;
	}

}
