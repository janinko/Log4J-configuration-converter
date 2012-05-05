package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.HashSet;
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
    
    /* Category is deprecated synonym of Logger, this boolean keeps
     * information about actual name of Logger.
     */
    private boolean isCategory = false;

    public Logger(String name) {
    	loggerName = name;
        this.params = new HashMap<String, String>();
        this.appenderRefs = new HashSet<String>();
    }

    public void isCategory(boolean b){
    	isCategory = b;
    }

    public boolean isAdditivity() {
        return additivity;
    }

    public void setAdditivity(boolean additivity) {
        this.additivity = additivity;
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

    void printXML(Document doc, Element config) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
