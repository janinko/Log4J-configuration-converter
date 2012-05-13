/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.w3c.dom.Document;

/**
 *
 * @author fivekeyem
 */
public class Demo {
    
	public static void main(String[] args) throws FileNotFoundException, IOException {
		InputLoader il = new InputLoader("examples/exampleConfigs.properties");
	
		Properties p = il.getProperties();
		
		p.list(System.out);
		
		System.out.println("--------- prop -> abst -> xml .... ---------");
		
		PropertiesParser pp = new PropertiesParser(p);
		
		Configuration c = pp.parse();
		
		Document xml = c.generateXML();
		
		Helper.printAllXml(xml.getChildNodes(), 0);
		
		System.out.println("---------- xml -> abst -> prop");
		
		XMLParser xmlp = new XMLParser(xml);
		c = xmlp.parse();
		
		Properties p2 = c.generateProperties();
		
		System.out.println("--------- write prop -------------");
		
		p2.list(System.out);
		
	}
            
}
