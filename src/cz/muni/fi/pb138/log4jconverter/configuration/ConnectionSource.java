package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class represents the ConnectionSource configuration from Abstract Model 
 * Each field represent every single component of Log4j connectionSource configuration
 * 
 * @author Steve
 */
public class ConnectionSource {
    //required
    private String className;
    //optional
    private DataSource dataSource;
    private HashMap<String,String> params;

    public ConnectionSource() {
        this.params = new LinkedHashMap<String, String>();
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

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

  public  void generateXML(Document doc, Element elem) {
        Element conSource = doc.createElement("connectionSource");
      
        conSource.setAttribute("class", className);
        
        if(dataSource!=null)
        {
            dataSource.generateXML(doc,conSource);
        }
        
        if (!params.isEmpty()) {
            Iterator<Entry<String, String>> it = params.entrySet().iterator();
            while (it.hasNext()) {
            	Entry<String, String> e = it.next();
                Element param = doc.createElement("param");

                param.setAttribute("name",e.getKey());
                param.setAttribute("value",e.getValue());
                conSource.appendChild(param);
                
            }
            
        }
        
        
        elem.appendChild(conSource);
    }

}
