package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.Iterator;
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
    
    // layout moze byt iba jeden cize equals a hashcode si myslim ze su zbytocne

    public void printXML(Document doc, Element appender) {
        Element layout = doc.createElement("layout");
      
        layout.setAttribute("class", className);
        
        
        if (!params.isEmpty()) {
            Iterator it1 = params.keySet().iterator();
            Iterator it2 = params.values().iterator();
            while (it1.hasNext()) {
                Element param = doc.createElement("param");

                param.setAttribute("name",it1.next().toString());
                param.setAttribute("value",it2.next().toString());
                layout.appendChild(param);
                
            }
            
        }
        
        
        appender.appendChild(layout);
    }
    

}
