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
public class RollingPolicy {
    //required
    
    private String name;
    private String className;
    //optional
    
    
    private HashMap<String,String> params;

    public RollingPolicy(String name, String className) {
        this.name = name;
        this.className = className;
        this.params = new HashMap<String,String>();
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
    }
    

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
    
    
    
    
    
    
    
}
