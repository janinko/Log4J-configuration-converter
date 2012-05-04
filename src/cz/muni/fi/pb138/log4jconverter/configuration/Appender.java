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
 * <h3>Appender configuration</h3>

    <p>Appender configuration syntax is:
    <pre>
    # For appender named <i>appenderName</i>, set its class.
    # Note: The appender name can contain dots.
    log4j.appender.appenderName=fully.qualified.name.of.appender.class

    # Set appender specific options.
    log4j.appender.appenderName.option1=value1
    ...
    log4j.appender.appenderName.optionN=valueN
    </pre>

    For each named appender you can configure its {@link Layout}. The
    syntax for configuring an appender's layout is:
    <pre>
    log4j.appender.appenderName.layout=fully.qualified.name.of.layout.class
    log4j.appender.appenderName.layout.option1=value1
    ....
    log4j.appender.appenderName.layout.optionN=valueN
    </pre>

    The syntax for adding {@link Filter}s to an appender is:
    <pre>
    log4j.appender.appenderName.filter.ID=fully.qualified.name.of.filter.class
    log4j.appender.appenderName.filter.ID.option1=value1
    ...
    log4j.appender.appenderName.filter.ID.optionN=valueN
    </pre>
    The first line defines the class name of the filter identified by ID;
    subsequent lines with the same ID specify filter option - value
    paris. Multiple filters are added to the appender in the lexicographic
    order of IDs.

    The syntax for adding an {@link ErrorHandler} to an appender is:
    <pre>
    log4j.appender.appenderName.errorhandler=fully.qualified.name.of.filter.class
    log4j.appender.appenderName.errorhandler.root-ref={true|false}
    log4j.appender.appenderName.errorhandler.logger-ref=loggerName
    log4j.appender.appenderName.errorhandler.appender-ref=appenderName
    log4j.appender.appenderName.errorhandler.option1=value1
    ...
    log4j.appender.appenderName.errorhandler.optionN=valueN
    </pre>
 * 
 */

public class Appender {
    //required
    private String appenderName;
    private String className;
    
    
    //optional
    
    private Layout layout;
    private HashSet<Filter> filters;
    private ErrorHandler errorhandler;
    private RollingPolicy rollingPolicy;
    private TriggeringPolicy triggeringPolicy;
    private ConnectionSource connectionSource;
    private HashMap<String,String> params;
    private HashSet<String> appenderRefs;

    
    
    public Appender() {
        
        this.filters = new HashSet<Filter>();
        this.params = new HashMap<String,String>();
        this.appenderRefs = new HashSet<String>();
        
    }
    
    public Appender(String name) {
        this.appenderName = name;
        this.filters = new HashSet<Filter>();
        this.params = new HashMap<String,String>();
        this.appenderRefs = new HashSet<String>();
        
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ErrorHandler getErrorhandler() {
        return errorhandler;
    }

    public void setErrorhandler(ErrorHandler errorhandler) {
        this.errorhandler = errorhandler;
    }
    
    
   

    public String getAppenderName() {
        return appenderName;
    }

    public void setAppenderName(String appenderName) {
        this.appenderName = appenderName;
    }
    
   


    public HashSet<Filter> getFilters() {
        return filters;
    }

    public void setFilters(HashSet<Filter> filters) {
        this.filters = filters;
    }

    public Layout getLayout()
    {
        return layout;
    }
    
    
    public void setLayout(Layout l){
    	layout = l;
    }
    public HashSet<String> getAppenderRefs() {
        return appenderRefs;
    }

    public void setAppenderRefs(HashSet<String> appenderRefs) {
        this.appenderRefs = appenderRefs;
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public void setConnectionSource(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
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

    public RollingPolicy getRollingPolicy() {
        return rollingPolicy;
    }

    public void setRollingPolicy(RollingPolicy rollingPolicy) {
        this.rollingPolicy = rollingPolicy;
    }

    public TriggeringPolicy getTriggeringPolicy() {
        return triggeringPolicy;
    }

    public void setTriggeringPolicy(TriggeringPolicy triggeringPolicy) {
        this.triggeringPolicy = triggeringPolicy;
    }
    
  
    

  
   

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Appender other = (Appender) obj;
        if ((this.appenderName == null) ? (other.appenderName != null) : !this.appenderName.equals(other.appenderName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.appenderName != null ? this.appenderName.hashCode() : 0);
        return hash;
    }


    
    @Override
    public String toString() {
        return "." + appenderName;
    }
    

}
