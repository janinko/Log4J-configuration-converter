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
public class Level {
    
    public enum Levels {

        OFF, FATAL,
        ERROR, WARN, INFO, DEBUG, ALL
    }
    //implies
    private String className;
    //optional
    private Levels value;
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

    public Levels getValues() {
        return value;
    }

    public void setValues(Levels value) {
        this.value = value;
    }
    
}
