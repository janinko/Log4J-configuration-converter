/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.RootLogger.Levels;

import java.io.Writer;
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

public class Appender implements AbstractModel{
    
    private String appenderName;
    private HashSet<Option> options;
    private Layout layout;
    private HashSet<Filter> filters;
    private Errorhandler errorhandler;
    private Levels treshold;
    
    
    private String maxBackupIndex;
    private String maxFileSize;
    private String file;
    private boolean append;
    
    private HashSet<Item> items; //dostal som napad vsetky tie rozne vedlajsie atributy(ci co toje) davat do jedneho HashSetu

    public boolean isAppend() {
        return append;
    }

    public void setAppend(boolean append) {
        this.append = append;
    }

    public String getAppenderName() {
        return appenderName;
    }

    public void setAppenderName(String appenderName) {
        this.appenderName = appenderName;
    }
    
    public void setErrorHandler(Errorhandler eh){
    	errorhandler = eh;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public HashSet<Filter> getFilters() {
        return filters;
    }

    public void setFilters(HashSet<Filter> filters) {
        this.filters = filters;
    }

    public HashSet<Item> getItems() {
        return items;
    }

    public void setItems(HashSet<Item> items) {
        this.items = items;
    }
    
    public void setLayout(Layout l){
    	layout = l;
    }

    public String getMaxBackupIndex() {
        return maxBackupIndex;
    }

    public void setMaxBackupIndex(String maxBackupIndex) {
        this.maxBackupIndex = maxBackupIndex;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    public void setMaxFileSize(String maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    public HashSet<Option> getOptions() {
        return options;
    }

    public void setOptions(HashSet<Option> options) {
        this.options = options;
    }

    public Levels getTreshold() {
        return treshold;
    }

    public void setTreshold(Levels treshold) {
        this.treshold = treshold;
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
	public void printXML(Writer w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printProperties(Writer w) {
		// TODO Auto-generated method stub
		
	}
    
   
    
}
