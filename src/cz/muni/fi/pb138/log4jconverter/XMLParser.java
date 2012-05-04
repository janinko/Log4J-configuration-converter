package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.configuration.Appender;
import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;
import java.io.File;
import java.net.URI;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLParser implements Parser {
	
	private Document doc;
        private Configuration configuration;
	
	public XMLParser(Document document){
            this.doc = document;
            this.configuration = null;
	}
        
        public void parseXML() {
            Element element;
            
            // Load appenders
            Appender appender;
            NodeList appenderNodes = doc.getElementsByTagName("appender");
            
            for (int i = 0; i < appenderNodes.getLength(); i++) {
                appender = null;
                element = (Element) appenderNodes.item(i);
                appender.setAppenderName(element.getAttribute("name"));
                appender.setClassName(element.getAttribute("class"));
                
                // Do this after the whole appender loading process
                configuration.addAppender(appender);
            }
        }
        
        /*
         * This method just prints the document, not the configuration (for testing purposes only)
         */
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

	@Override
	public Configuration parse() {
            if(configuration == null){
                configuration = new Configuration();
                parseXML();
            }
            return configuration;
	}

}
