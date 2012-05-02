/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class Priority {
    
    public enum PriorValues{
        DEBUG, INFO, WARN ,ERROR ,FATAL
    }
    //implies
    private String className;
    //optional
    private PriorValues values;
    //requires
    private HashMap<String,String> params = new HashMap<String,String>();

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

    public PriorValues getValues() {
        return values;
    }

    public void setValues(PriorValues values) {
        this.values = values;
    }
    
    
    
            
    
}
