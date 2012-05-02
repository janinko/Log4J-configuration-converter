/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;

/**
 *
 * @author Steve
 */
    public class ThrowableRender {
    
    private String className;
    
    private HashMap<String,String> params;

    public ThrowableRender() {
        
        this.params = new HashMap<String, String>() ;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
    
    
    
    
    
}
