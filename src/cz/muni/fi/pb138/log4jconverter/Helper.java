/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 *
 * @author fivekeyem
 */
public class Helper {
	
	// t = 0 always for init
	public static void printAllXml(NodeList nl, int t) {
		for (int i = 0; i < nl.getLength(); i++) {
			Element e = (Element) nl.item(i);
			for (int k = 1; k <= t; k++) {
				System.out.print("    ");
			}
			NamedNodeMap attributes = e.getAttributes();
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < attributes.getLength(); j++) {
				sb.append(attributes.item(j));
				sb.append(" ");
			}
			if (!e.getNodeName().equals("#text")) System.out.println(e.getNodeName() + " - " + sb.toString());
			printAllXml(nl.item(i).getChildNodes(), t + 1);
		}
	}
	
	
	public static Element createBasicSkeleton(Document doc) {
		Element config = doc.createElement("log4j:configuration");
		config.setAttribute("xmlns:log4j","http://jakarta.apache.org/log4j/");
		doc.appendChild(config);
		return config;
	}
	
	public static Document createDocument() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();
		return doc;
	}
	
}
