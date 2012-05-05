package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class Layout {
    //required
    private String className;
    //optional
    private HashMap<String,String> params;

    public Layout() {
        this.params = new HashMap<String,String>();
    }
    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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
    
    // layout moze byt iba jeden cize equals a hashcode si myslim ze su zbytocne

}
