/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter.configuration;

import cz.muni.fi.pb138.log4jconverter.Helper;
import cz.muni.fi.pb138.log4jconverter.configuration.Level.Levels;
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
public class RootGenerateXmlTest {
	Document doc2;
	Element config2;
	
	Document doc1;
	Element config1;
	Element e;
	Element root;
	
	private Root r;
	private Level l;
	
	@Before
    public void setUp() throws ParserConfigurationException  {
		doc1 = Helper.createDocument();
		doc2 = Helper.createDocument();
		config1 = Helper.createBasicSkeleton(doc1);
		config2 = Helper.createBasicSkeleton(doc2);
		
		root = (Element) config1.appendChild(doc1.createElement("root"));
		
		r = new Root();
    	l = new Level();
    }

	@Test
	public void testRootOneRef() {
		// xml tree
		e = doc1.createElement("level");
		e.setAttribute("value", "DEBUG");
		root.appendChild(e);
		e = doc1.createElement("appender-ref");
		e.setAttribute("ref", "console");
		root.appendChild(e);
		
		// abstract
		l.setValues(Levels.DEBUG);
		r.setLevel(l);
		r.addAppenderRef("console");
		r.generateXML(doc2, config2);
		
		// asserts
		XMLAssert.assertXMLEqual(doc1, doc2);
	}
	
	
	@Test
	public void testRootTwoRefs() {
		// xml tree
		e = doc1.createElement("level");
		e.setAttribute("value", "DEBUG");
		root.appendChild(e);
		e = doc1.createElement("appender-ref");
		e.setAttribute("ref", "console");
		root.appendChild(e);
		e = doc1.createElement("appender-ref");
		e.setAttribute("ref", "A1");
		root.appendChild(e);
		
		// abstract
		l.setValues(Levels.DEBUG);
		r.setLevel(l);
		r.addAppenderRef("console");
		r.addAppenderRef("A1");
		r.generateXML(doc2, config2);
		
		// asserts
		XMLAssert.assertXMLEqual(doc1, doc2);
	}
	
	
	@Test
	public void testRootOnlyLevel() {
		// xml tree
		e = doc1.createElement("level");
		e.setAttribute("value", "FATAL");
		root.appendChild(e);
		
		// abstract
		l.setValues(Levels.FATAL);
		r.setLevel(l);
		r.generateXML(doc2, config2);
		
		// asserts
		XMLAssert.assertXMLEqual(doc1, doc2);
	}
	
	
	@Test
	public void testRootEmpty() {
		// xml tree

		// abstract
		XMLAssert.assertXMLNotEqual(doc1, doc2);
		r.generateXML(doc2, config2);

		// asserts
		XMLAssert.assertXMLEqual(doc1, doc2);
	}
	
	
	@Test
	public void testRootPriority() {
		// xml tree
		e = doc1.createElement("priority");
		e.setAttribute("value", "INFO");
		root.appendChild(e);
		
		// abstract
		l.setValues(Levels.INFO);
		l.isPriority(true);
		r.setLevel(l);
		r.generateXML(doc2, config2);
		
		// asserts
		XMLAssert.assertXMLEqual(doc1, doc2);
	}
	
	
	
		
}
