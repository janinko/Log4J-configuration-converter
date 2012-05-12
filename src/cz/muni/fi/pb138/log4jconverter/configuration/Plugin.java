package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Admin
 */
public class Plugin {
    //required
    private String name;
    private String className;
    //optional
    private HashMap<String, String> params = new HashMap<String, String>();
    private ConnectionSource connSource;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ConnectionSource getConnSource() {
        return connSource;
    }

    public void setConnSource(ConnectionSource connSource) {
        this.connSource = connSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
    
    public void addParam(String key, String value) {
        params.put(key, value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Plugin other = (Plugin) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

   public void generateXML(Document doc, Element config) {
        Element plugin = doc.createElement("plugin");
        plugin.setAttribute("name", name);
        plugin.setAttribute("class", className);
        
        
         if (!params.isEmpty()) {
            Iterator it1 = params.keySet().iterator();
            Iterator it2 = params.values().iterator();
            while (it1.hasNext()) {
                Element param = doc.createElement("param");

                param.setAttribute("name",it1.next().toString());
                param.setAttribute("value",it2.next().toString());
                plugin.appendChild(param);
                
            }
            
        }
         if(connSource!=null)
         {
             connSource.generateXML(doc, plugin);
         }
         
         config.appendChild(plugin);
         
        
    }
 
}
