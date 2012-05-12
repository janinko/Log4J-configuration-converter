package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Admin
 */
public class RollingPolicy {
    //required
    private String name;
    private String className;
    //optional
    private HashMap<String,String> params;

    public RollingPolicy() {
        this.params = new HashMap<String,String>();
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
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

    public void generateXML(Document doc, Element appender) {
        Element rollPolicy = doc.createElement("rollingPolicy");
        rollPolicy.setAttribute("name", name);
        rollPolicy.setAttribute("class", className);
        
         if (!params.isEmpty()) {
             Iterator<Entry<String, String>> it = params.entrySet().iterator();
             while (it.hasNext()) {
             	Entry<String, String> e = it.next();
                Element param = doc.createElement("param");

                param.setAttribute("name",e.getKey());
                param.setAttribute("value",e.getValue());
                rollPolicy.appendChild(param);
                
            }
            
        }
         
         
         appender.appendChild(rollPolicy);
    }
}
