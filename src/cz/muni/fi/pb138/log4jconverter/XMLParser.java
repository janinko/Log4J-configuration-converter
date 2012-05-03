package cz.muni.fi.pb138.log4jconverter;

import org.w3c.dom.Document;

import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;

public class XMLParser implements Parser {
	
	private Document doc;
        private Configuration configuration;
	
	public XMLParser(Document document){
		this.doc = document;
                this.configuration = null;
	}
        
        
        /*
         * Helpful for development
         */
        public void writeAllXML() {
            // TODO
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
