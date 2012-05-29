package cz.muni.fi.pb138.log4jconverter.configuration;

import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Properties;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import cz.muni.fi.pb138.log4jconverter.PropertiesParser;

/**
 * This class represents the Root configuration from Abstract Model 
 * Each field represent every single component of Log4j root  configuration
 * 
 * @author Steve
 */

public class Root {
    //optional

    private HashMap<String, String> params = new LinkedHashMap<String, String>();
    private Level level;
    private HashSet<String> appenderRefs = new LinkedHashSet<String>();

    /*
     * RootCategory is deprecated synonym of RootLogger, this boolean keeps
     * information about actual name of RootLogger.
     */
    private boolean isRootCategory = false;
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PropertiesParser.class);

    public void addAppenderRef(String appenderName) {
        appenderRefs.add(appenderName);
    }

    public HashSet<String> getAppenderRefs() {
        return appenderRefs;
    }

    public void setAppenderRefs(HashSet<String> appenderRefs) {
        this.appenderRefs = appenderRefs;
    }

    public boolean isIsRootCategory() {
        return isRootCategory;
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

    public void setIsRootCategory(boolean isRootCategory) {
        this.isRootCategory = isRootCategory;
    }

    public void isRootCategory(boolean b) {
        isRootCategory = b;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String toString() {
        String result = "";
        for (String appenderRef : appenderRefs) {
            result += appenderRef + ", ";
        }
        return result;
    }

    public void generateProperties(Properties p) {
    	StringBuilder value = new StringBuilder();
		if (level != null) value.append(level.getValues());
		for (String appenderRef : appenderRefs) {
			value.append(", ");
			value.append(appenderRef);
		}
        p.setProperty( ( isRootCategory ? PropertiesParser.ROOT_CATEGORY_PREFIX : PropertiesParser.ROOT_LOGGER_PREFIX ), value.toString());
		if (logger.isTraceEnabled()) { logger.trace(PropertiesParser.ROOT_LOGGER_PREFIX+"="+p.getProperty(PropertiesParser.ROOT_LOGGER_PREFIX)); }
    }

	
	// TODO check isRootCategory and create category
    public void generateXML(Document doc, Element config) {
        Element root = doc.createElement("root");

        if (!params.isEmpty()) {
            Iterator<Entry<String, String>> it = params.entrySet().iterator();
            while (it.hasNext()) {
            	Entry<String, String> e = it.next();
                Element param = doc.createElement("param");

                param.setAttribute("name", e.getKey());
                param.setAttribute("value", e.getValue());
                root.appendChild(param);

            }

        }

        if (level != null) {
            level.generateXML(doc, root);
        }

        for (String ref : appenderRefs) {
            Element apRef = doc.createElement("appender-ref");
            apRef.setAttribute("ref", ref);
            root.appendChild(apRef);
        }
        
        
        config.appendChild(root);

    }

    public void printProperties(Writer w) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
