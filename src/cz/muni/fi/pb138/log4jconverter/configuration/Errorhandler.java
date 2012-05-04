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
public class ErrorHandler {
    
    //required
    private String className;
    
    //optional
    private HashMap<String,String> params;
    private boolean rootRef;
    private HashSet<String> loggerRefs;
    private String appenderRef;
    
    
    public ErrorHandler()
    {
        
        this.params = new HashMap<String, String>();
        this.loggerRefs = new HashSet<String>();
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAppenderRef() {
        return appenderRef;
    }

    public void setAppenderRef(String appenderRef) {
        this.appenderRef = appenderRef;
    }

    public String getClassName() {
        return className;
    }

   

    public HashSet<String> getLoggerRefs() {
        return loggerRefs;
    }

    public void setLoggerRefs(HashSet<String> loggerRefs) {
        this.loggerRefs = loggerRefs;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public boolean isRootRef() {
        return rootRef;
    }

    public void setRootRef(boolean rootRef) {
        this.rootRef = rootRef;
    }
    
    
    
  
    
    
}
