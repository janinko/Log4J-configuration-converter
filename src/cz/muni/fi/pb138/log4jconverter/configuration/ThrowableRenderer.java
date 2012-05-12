package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Steve
 */
public class ThrowableRenderer {
    //required
    private String className;
    //optional
    private HashMap<String,String> params;

    public ThrowableRenderer() {
        this.params = new HashMap<String, String>() ;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
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

    public void generateXML(Document doc, Element config) {
        Element throwRenderer = doc.createElement("throwableRenderer");
        throwRenderer.setAttribute("class", className);
        
        if (!params.isEmpty()) {
            Iterator<Entry<String, String>> it = params.entrySet().iterator();
            while (it.hasNext()) {
            	Entry<String, String> e = it.next();
                Element param = doc.createElement("param");

                param.setAttribute("name",e.getKey());
                param.setAttribute("value",e.getValue());
                throwRenderer.appendChild(param);
                
            }
            
        }
        
        
        config.appendChild(throwRenderer);
    }

}
