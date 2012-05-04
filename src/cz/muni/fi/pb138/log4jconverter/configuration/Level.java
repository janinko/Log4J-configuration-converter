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
    //requires
    private Levels value;
    //implies
    private String className;
    //optional
    private HashMap<String,String> params = new HashMap<String,String>();
    
    /* Priority is deprecated synonym of Level, this boolean keeps
    information about actual name of Level.
	*/
    private boolean isPriority = false;

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

    public Levels getValues() {
        return value;
    }

    public void setValues(Levels value) {
        this.value = value;
    }
    
    public void isPriority(boolean b){
    	isPriority = b;
    }
    
}
