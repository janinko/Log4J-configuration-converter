package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Steve
 */
public class ThrowableRender {
    //required
    private String className;
    //optional
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

	public void addParam(String key, String value) {
		params.put(key, value);
	}

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public void printXML(Document doc, Element config) {
        Element throwRenderer = doc.createElement("throwableRenderer");
        throwRenderer.setAttribute("class", className);
        
        if (!params.isEmpty()) {
            Iterator it1 = params.keySet().iterator();
            Iterator it2 = params.values().iterator();
            while (it1.hasNext()) {
                Element param = doc.createElement("param");

                param.setAttribute("name",it1.next().toString());
                param.setAttribute("value",it2.next().toString());
                throwRenderer.appendChild(param);
                
            }
            
        }
        
        
        config.appendChild(throwRenderer);
    }

}
