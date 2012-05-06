package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Admin
 */
public class Logger {
    //required

    private String loggerName;
    //implies
    private String className;
    //podle dtd je deafulat hodnota true
    private boolean additivity = true;
    //optional
    private HashMap<String, String> params;
    private HashSet<String> appenderRefs;
    private Level level;
    /*
     * Category is deprecated synonym of Logger, this boolean keeps information
     * about actual name of Logger.
     */
    private boolean isCategory = false;

    public Logger(String name) {
        loggerName = name;
        this.params = new HashMap<String, String>();
        this.appenderRefs = new HashSet<String>();
    }

    public void isCategory(boolean b) {
        isCategory = b;
    }

    public boolean isAdditivity() {
        return additivity;
    }

    public void setAdditivity(boolean additivity) {
        this.additivity = additivity;
    }

	public void addAppenderRef(String appenderName) {
		appenderRefs.add(appenderName);
	}

    public HashSet<String> getAppenderRefs() {
        return appenderRefs;
    }

    public void setAppenderRefs(HashSet<String> appenderRefs) {
        this.appenderRefs = appenderRefs;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Logger other = (Logger) obj;
        if ((this.loggerName == null) ? (other.loggerName != null) : !this.loggerName.equals(other.loggerName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.loggerName != null ? this.loggerName.hashCode() : 0);
        return hash;
    }

    public void printXML(Document doc, Element config) {

        Element logger;
        if (!isCategory) {
            logger = doc.createElement("logger");
        } else {
            logger = doc.createElement("category");
        }
        logger.setAttribute("loggerName", loggerName);
        if (className != null) {
            logger.setAttribute("class", className);
        }
        if (additivity) {
            logger.setAttribute("additivity", "true");
        } else {
            logger.setAttribute("additivity", "false");
        }


        if (!params.isEmpty()) {
            Iterator it1 = params.keySet().iterator();
            Iterator it2 = params.values().iterator();
            while (it1.hasNext()) {
                Element param = doc.createElement("param");

                param.setAttribute("name", it1.next().toString());
                param.setAttribute("value", it2.next().toString());
                logger.appendChild(param);

            }

        }

        if (level != null) {
            level.printXML(doc, logger);
        }

        for (String ref : appenderRefs) {
            Element apRef = doc.createElement("appender-ref");
            apRef.setAttribute("ref", ref);
            logger.appendChild(apRef);
        }


        config.appendChild(logger);

    }
}
