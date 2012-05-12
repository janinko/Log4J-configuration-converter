package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Admin
 */
public class ErrorHandler {
    //required
    private String className;
    //optional
    private HashMap<String,String> params;
    private boolean rootRef;
    private HashSet<String> loggerRefs;
    private String appenderRef;
    
    public ErrorHandler()
    {
        this.params = new HashMap<String, String>();
        this.loggerRefs = new HashSet<String>();
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAppenderRef() {
        return appenderRef;
    }

    public void setAppenderRef(String appenderRef) {
        this.appenderRef = appenderRef;
    }

    public String getClassName() {
        return className;
    }

    public HashSet<String> getLoggerRefs() {
        return loggerRefs;
    }

    public void addLoggerRef(String loggerRefs) {
        this.loggerRefs.add(loggerRefs);
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void addParam(String name, String value) {
        params.put(name, value);
    }

    public boolean isRootRef() {
        return rootRef;
    }

    public void setRootRef(boolean rootRef) {
        this.rootRef = rootRef;
    }

 public  void generateXML(Document doc, Element appender) {
        Element error = doc.createElement("errorHandler");
        error.setAttribute("class", className);
        
        if (!params.isEmpty()) {
            Iterator it1 = params.keySet().iterator();
            Iterator it2 = params.values().iterator();
            while (it1.hasNext()) {
                Element param = doc.createElement("param");

                param.setAttribute("name",it1.next().toString());
                param.setAttribute("value",it2.next().toString());
                error.appendChild(param);
                
            }
            
        }
        // neviem ci to spravi jeden alebo 2 tagy
        if(rootRef){
          Element ref = doc.createElement("root-ref");  
          error.appendChild(ref);
        }
        
        for(String logRef : loggerRefs)
        {
            Element ref = doc.createElement("logger-ref"); 
            ref.setAttribute("ref",logRef);
            error.appendChild(ref);
            
        }
        
        if(appenderRef!= null)
        {
             Element ref = doc.createElement("appender-ref"); 
             ref.setAttribute("ref",appenderRef);
             error.appendChild(ref);
        }
        
        
        appender.appendChild(error);
    }

}
