/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter.configuration;

import cz.muni.fi.pb138.log4jconverter.Helper;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.custommonkey.xmlunit.XMLAssert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author fivekeyem
 */
public class AppenderGenerateXmlTest {
	Document doc2;
	Element config2;
	
	Document doc1;
	Element config1;
	Element e;
	Element appender;
	
	private Appender a;
	private Layout layout;
	private HashMap<String, String> params;
	
	@Before
    public void setUp() throws ParserConfigurationException  {
		doc1 = Helper.createDocument();
		doc2 = Helper.createDocument();
		config1 = Helper.createBasicSkeleton(doc1);
		config2 = Helper.createBasicSkeleton(doc2);
		
		appender = (Element) config1.appendChild(doc1.createElement("appender"));
		
		a = new Appender("A1");
		layout = new Layout();
		params = new HashMap<String, String>();

    }

	@Test
	public void testAppenderOneLayout() {
		// xml tree
		appender.setAttribute("name", "A1");
		appender.setAttribute("class", "org.apache.log4j.ConsoleAppender");
		
		
		e = doc1.createElement("layout");
		e.setAttribute("class", "org.apache.log4j.PatternLayout");
		
		Element e2 = doc1.createElement("param");
		e2.setAttribute("name", "ConversionPattern");
		e2.setAttribute("value", "%-4r [%t] %-5p %c %x - %m%n");
		e.appendChild(e2);
		
		appender.appendChild(e);
		
		// abstract
		params.put("ConversionPattern", "%-4r [%t] %-5p %c %x - %m%n");
		layout.setClassName("org.apache.log4j.PatternLayout");
		layout.setParams(params);
		a.setClassName("org.apache.log4j.ConsoleAppender");
		a.setLayout(layout);
		a.printXML(doc2, config2);
		
		// asserts
		XMLAssert.assertXMLEqual(doc1, doc2);
	}

	
	@Test
	public void testAppenderOnly() {
		// xml tree
		appender.setAttribute("name", "A1");
		appender.setAttribute("class", "org.apache.log4j.ConsoleAppender");
		
		// abstract
		a.setClassName("org.apache.log4j.ConsoleAppender");
		a.printXML(doc2, config2);
		
		// asserts
		XMLAssert.assertXMLEqual(doc1, doc2);
	}
	
		
}
