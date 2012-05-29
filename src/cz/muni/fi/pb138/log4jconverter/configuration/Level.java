package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class represents the Level configuration from Abstract Model 
 * Each field represent every single component of Log4j level configuration
 * 
 * @author Steve
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
    private HashMap<String,String> params = new LinkedHashMap<String,String>();
    
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
    
    public boolean isPriority(){
    	return isPriority;
    }
    
    public void setPriority(boolean b){
    	isPriority = b;
    }
	   
    public void generateXML(Document doc, Element elem) {
        
        Element level;
        
        if(!isPriority)
        {
         level = doc.createElement("level");
        }
        else{
            level = doc.createElement("priority");
        }
        level.setAttribute("value", value.toString());
        if(className!= null)
        {
        level.setAttribute("class", className);   
        }
        
          if (!params.isEmpty()) {
              Iterator<Entry<String, String>> it = params.entrySet().iterator();
              while (it.hasNext()) {
              	Entry<String, String> e = it.next();
                Element param = doc.createElement("param");

                param.setAttribute("name", e.getKey());
                param.setAttribute("value", e.getValue());
                level.appendChild(param);

            }

        }
        
          
        
        elem.appendChild(level);
                
        
    }
    
}
