package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class represents the ErrorHandler configuration from Abstract Model 
 * Each field represent every single component of Log4j errorHandler configuration
 * 
 * @author Steve
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
        this.params = new LinkedHashMap<String, String>();
        this.loggerRefs = new LinkedHashSet<String>();
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
            Iterator<Entry<String, String>> it = params.entrySet().iterator();
            while (it.hasNext()) {
            	Entry<String, String> e = it.next();
                Element param = doc.createElement("param");

                param.setAttribute("name",e.getKey());
                param.setAttribute("value",e.getValue());
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
