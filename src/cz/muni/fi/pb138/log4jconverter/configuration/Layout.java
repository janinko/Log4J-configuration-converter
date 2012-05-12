package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
	
	
	
	public void generateProperties(Properties p, String prefixKey) {
		if (className != null) p.setProperty(prefixKey, className);

		// prefixKey.PARAM=VALUE
		if (!params.isEmpty()) {
			Iterator<Entry<String, String>> i = params.entrySet().iterator(); 
			while(i.hasNext()) { 
				Entry<String, String> pairs = i.next();
				String paramKey = pairs.getKey();
				String paramValue = pairs.getValue();	
				p.setProperty(prefixKey + "." + paramKey, paramValue);
			} 
		}
	}
	
    
    // layout moze byt iba jeden cize equals a hashcode si myslim ze su zbytocne

    public void generateXML(Document doc, Element appender) {
        Element layout = doc.createElement("layout");
      
        layout.setAttribute("class", className);
        
        
        if (!params.isEmpty()) {
            Iterator<Entry<String, String>> it = params.entrySet().iterator();
            while (it.hasNext()) {
            	Entry<String, String> e = it.next();
                Element param = doc.createElement("param");

                param.setAttribute("name",e.getKey());
                param.setAttribute("value",e.getValue());
                layout.appendChild(param);
                
            }
            
        }
        
        
        appender.appendChild(layout);
    }
    

}
