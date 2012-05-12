package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Admin
 */
public class ConnectionSource {
    //required
    private String className;
    //optional
    private DataSource dataSource;
    private HashMap<String,String> params;

    public ConnectionSource() {
        this.params = new HashMap<String, String>();
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
            Iterator it1 = params.keySet().iterator();
            Iterator it2 = params.values().iterator();
            while (it1.hasNext()) {
                Element param = doc.createElement("param");

                param.setAttribute("name",it1.next().toString());
                param.setAttribute("value",it2.next().toString());
                conSource.appendChild(param);
                
            }
            
        }
        
        
        elem.appendChild(conSource);
    }

}
