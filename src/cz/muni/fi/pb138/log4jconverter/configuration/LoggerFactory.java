package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cz.muni.fi.pb138.log4jconverter.PropertiesParser;

/**
 *
 * @author Admin
 */
public class LoggerFactory {
    //required

    private String className;
    //optional
    private HashMap<String, String> params = new HashMap<String, String>();

    /*
     * CategoryFactory is deprecated synonym of LoggerFactory, this boolean
     * keeps information about actual name of LoggerFactory.
     */
    private boolean isCategoryFactory = false;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void addParam(String key, String value) {
        params.put(key, value);
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public void isCategoryFactory(boolean b) {
        isCategoryFactory = b;
    }
	
	
	public void generateProperties(Properties p) {
		if (className != null) p.setProperty(PropertiesParser.PREFIX + "." + PropertiesParser.LOGGER_FACTORY, className);
	}

    public void generateXML(Document doc, Element config) {
        Element factory;
        if (!isCategoryFactory) {
            factory = doc.createElement("loggerFactory");
        } else {
            factory = doc.createElement("categoryFactory");
        }

        if (!params.isEmpty()) {
            Iterator<Entry<String, String>> it = params.entrySet().iterator();
            while (it.hasNext()) {
            	Entry<String, String> e = it.next();
                Element param = doc.createElement("param");

                param.setAttribute("name", e.getKey());
                param.setAttribute("value", e.getValue());
                factory.appendChild(param);

            }

        }
        
        config.appendChild(factory);

    }
}
