package cz.muni.fi.pb138.log4jconverter;

import java.io.IOException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.SAXException;

import cz.muni.fi.pb138.log4jconverter.InputLoader.Type;
import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;


public class Main {
	public static Type confOut = Type.OTHER;

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
        InputLoader il1 = new InputLoader("examples/exampleConfigs1.properties");
        Properties properties = il1.getProperties();
       
        PropertiesParser pp = new PropertiesParser(properties);
        c = pp.parse();     
        System.out.println(c);
        
       
       
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

    static final String        OUTL = "--out";
    static final String        OUTS = "-o";
    static final String         XML = "XML";
    static final String PROPERTIES  = "Properties";
    static void parseArguments(String[] args){
    	for(int i=0; i<args.length; i++){
    		if(args[i].startsWith(OUTL)){
    			if(args[i].equals(OUTL) && i+1 < args.length){
    				if(args[i+1].toLowerCase().equals(XML.toLowerCase())){
    					confOut=Type.XML;
        				i++;
        				continue;
    				}else if(args[i+1].toLowerCase().equals(PROPERTIES.toLowerCase())){
    					confOut=Type.PROPERTIES;
        				i++;
        				continue;
    				}
    			}
				continue;
    		}else if(args[i].startsWith(OUTS)){
    			if(args[i].substring(OUTS.length()).startsWith("=")){
    				String co = args[i].substring(OUTS.length()+1);
    				if(co.toLowerCase().equals(XML.toLowerCase())){
    					confOut=Type.XML;
        				continue;
    				}else if(co.toLowerCase().equals(PROPERTIES.toLowerCase())){
    					confOut=Type.PROPERTIES;
        				continue;
    				}
    			}else if(args[i].equals(OUTS) && i+1 < args.length){
    				if(args[i+1].toLowerCase().equals(XML.toLowerCase())){
    					confOut=Type.XML;
        				i++;
        				continue;
    				}else if(args[i+1].toLowerCase().equals(PROPERTIES.toLowerCase())){
    					confOut=Type.PROPERTIES;
        				i++;
        				continue;
    				}
    			}
				continue;
    		}
    	}
    }
    
}
