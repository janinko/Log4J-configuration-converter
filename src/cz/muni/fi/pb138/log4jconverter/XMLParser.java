package cz.muni.fi.pb138.log4jconverter;

import org.w3c.dom.Document;

public class XMLParser implements Parser {
	
	Document doc;
	
	public XMLParser(Document document){
		doc = document;
	}

	@Override
	public Configuration parse() {
		// TODO Auto-generated method stub
		return null;
	}

}
