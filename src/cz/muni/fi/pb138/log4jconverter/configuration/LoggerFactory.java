package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class LoggerFactory {
    //required
    private String className;
    //optional
    private HashMap<String,String> params = new HashMap<String,String>();

    /* CategoryFactory is deprecated synonym of LoggerFactory, this boolean keeps
     * information about actual name of LoggerFactory.
     */
    private boolean isCategoryFactory = false;

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
    
    public void isCategoryFactory(boolean b){
    	isCategoryFactory = b;
    }
    
}
