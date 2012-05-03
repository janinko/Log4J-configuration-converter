/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter.configuration;


import java.util.HashMap;
import java.util.HashSet;

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
public class Root{

    
    
    private HashMap<String,String> params = new HashMap<String,String>();
    //bud priority alebo Level
    
    private Priority priority;
    private Level level;
    private HashSet<String> appenderRefs = new HashSet<String>();
    private boolean isRootCategory = false;

    
    public void addAppenderRef(String appenderName){
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

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public void setIsRootCategory(boolean isRootCategory) {
        this.isRootCategory = isRootCategory;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }


   

   

    public void isRootCategory(boolean b){
    	isRootCategory = b;
    }

    

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

   



}
