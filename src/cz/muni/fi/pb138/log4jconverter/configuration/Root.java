package cz.muni.fi.pb138.log4jconverter.configuration;

import cz.muni.fi.pb138.log4jconverter.PropertiesParser;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Properties;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Admin
 */
/**
 * <p>The syntax for configuring the root logger is:
 * <pre>
 * log4j.rootLogger=[level], appenderName, appenderName, ...
 * </pre>
 *
 * <p>This syntax means that an optional <em>level</em> can be supplied followed
 * by appender names separated by commas.
 *
 * <p>The level value can consist of the string values OFF, FATAL, ERROR, WARN,
 * INFO, DEBUG, ALL or a <em>custom level</em> value. A custom level value can
 * be specified in the form
 * <code>level#classname</code>.
 *
 * <p>If a level value is specified, then the root level is set to the
 * corresponding level. If no level value is specified, then the root level
 * remains untouched.
 *
 * <p>The root logger can be assigned multiple appenders.
 *
 * <p>Each <i>appenderName</i> (separated by commas) will be added to the root
 * logger. The named appender is defined using the appender syntax defined
 * above.
 */
public class Root {
    //optional

    private HashMap<String, String> params = new HashMap<String, String>();
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
		value.append(level.getValues());
		for (String appenderRef : appenderRefs) {
			value.append(", ");
			value.append(appenderRef);
		}
        p.setProperty(PropertiesParser.ROOT_LOGGER_PREFIX, value.toString());
		if (logger.isTraceEnabled()) { logger.trace(PropertiesParser.ROOT_LOGGER_PREFIX+"="+p.getProperty(PropertiesParser.ROOT_LOGGER_PREFIX)); }
    }

    public void printXML(Document doc, Element config) {
        Element root = doc.createElement("root");

        if (!params.isEmpty()) {
            Iterator it1 = params.keySet().iterator();
            Iterator it2 = params.values().iterator();
            while (it1.hasNext()) {
                Element param = doc.createElement("param");

                param.setAttribute("name", it1.next().toString());
                param.setAttribute("value", it2.next().toString());
                root.appendChild(param);

            }

        }

        if (level != null) {
            level.printXML(doc, root);
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
