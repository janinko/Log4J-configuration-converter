package cz.muni.fi.pb138.log4jconverter.configuration;

import cz.muni.fi.pb138.log4jconverter.PropertiesParser;
import java.util.*;
import java.util.Map.Entry;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This class represents the Appender configuration from Abstract Model 
 * Each field represent every single component of Log4j appender configuration
 * 
 * @author Steve
 */
public class Appender {
    //required

    private String appenderName;
    private String className;
    //optional
    private Layout layout;
    private ArrayList<Filter> filters; // filters are ordered
    private ErrorHandler errorHandler;
    private RollingPolicy rollingPolicy;
    private TriggeringPolicy triggeringPolicy;
    private ConnectionSource connectionSource;
    private HashMap<String, String> params;
    private HashSet<String> appenderRefs;

    public Appender() {
        this.filters = new ArrayList<Filter>();
        this.params = new HashMap<String, String>();
        this.appenderRefs = new HashSet<String>();
    }

    public Appender(String name) {
        this.appenderName = name;
        this.filters = new ArrayList<Filter>();
        this.params = new HashMap<String, String>();
        this.appenderRefs = new HashSet<String>();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public String getAppenderName() {
        return appenderName;
    }

    public void setAppenderName(String appenderName) {
        this.appenderName = appenderName;
    }

	public Filter getFilter(String filterName) {
		for(Filter filter : filters){
			if(filterName.equals(filter.getName())){
				return filter;
			}
		}
		Filter filter = new Filter();
		filter.setName(filterName);
		filters.add(filter);
		return filter;
	}

    // Add copy of filter to filters
    public void addFilter(Filter f) {
        filters.add(f);
    }

    public ArrayList<Filter> getFilters() {
        return filters;
    }

    public void setFilters(ArrayList<Filter> filters) {
        this.filters = filters;
    }

    public Layout getLayout() {
    	if(layout == null){
    		layout = new Layout();
    	}
        return layout;
    }

    public void setLayout(Layout l) {
        layout = l;
    }

    public HashSet<String> getAppenderRefs() {
        return appenderRefs;
    }

    public void setAppenderRefs(HashSet<String> appenderRefs) {
        this.appenderRefs = appenderRefs;
    }
    
    public void addAppenderRef(String appenderRef) {
        appenderRefs.add(appenderRef);
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public void setConnectionSource(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
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

    public RollingPolicy getRollingPolicy() {
        return rollingPolicy;
    }

    public void setRollingPolicy(RollingPolicy rollingPolicy) {
        this.rollingPolicy = rollingPolicy;
    }

    public TriggeringPolicy getTriggeringPolicy() {
        return triggeringPolicy;
    }

    public void setTriggeringPolicy(TriggeringPolicy triggeringPolicy) {
        this.triggeringPolicy = triggeringPolicy;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Appender other = (Appender) obj;
        if ((this.appenderName == null) ? (other.appenderName != null) : !this.appenderName.equals(other.appenderName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.appenderName != null ? this.appenderName.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "." + appenderName;
    }

    public void generateProperties(Properties p) {
		// log4j.appender.appenderName
		String prefixKey = PropertiesParser.APPENDER_PREFIX + appenderName;
		
		// log4j.appender.appenderName=fully.qualified.name.of.appender.class
		if (className != null) p.setProperty(prefixKey, className);

		// log4j.appender.appenderName.option1=value1
		if (!params.isEmpty()) {
			Iterator<Entry<String, String>> i = params.entrySet().iterator(); 
			while(i.hasNext()) { 
				Map.Entry<String, String> pairs = i.next();
				String paramKey = pairs.getKey();
				String paramValue = pairs.getValue();	
				p.setProperty(prefixKey + "." + paramKey, paramValue);
			} 
		}
		
		// log4j.appender.appenderName.layout=fully.qualified.name.of.layout.class
		// log4j.appender.appenderName.layout.option1=value1
		if (layout != null) {
			layout.generateProperties(p, prefixKey + ".layout");
		}
		
		// log4j.appender.appenderName.filter.ID=fully.qualified.name.of.filter.class
		// log4j.appender.appenderName.filter.ID.option1=value1
		if (filters != null) {
			HashSet<Integer> filterNamesInt = new HashSet<Integer>();
			for (Filter filter : filters) {
				try {
					filterNamesInt.add(Integer.parseInt(filter.getName()));
				} catch (NumberFormatException e) {
					// filterName is String and its ok
				}
			}
			
			int i = 1;	
			for (Filter filter : filters) {
				while (filterNamesInt.contains(i)) {
					i++;
				}
				filter.generateProperties(p, prefixKey + ".filter" + 
						// ".ID" if exists
						( (filter.getName() != null) ? ("." + filter.getName()) : "." + i) );
						i++;
			}
		}
    }

    public void generateXML(Document doc, Element config) {
        Element appender = doc.createElement("appender");
        appender.setAttribute("name", appenderName);
        appender.setAttribute("class", className);


        if (errorHandler != null) {
            errorHandler.generateXML(doc, appender);
        }
        if (!params.isEmpty()) {
            Iterator<Entry<String, String>> it = params.entrySet().iterator();
            while (it.hasNext()) {
            	Entry<String, String> e = it.next();
                Element param = doc.createElement("param");

                param.setAttribute("name",e.getKey());
                param.setAttribute("value",e.getValue());
                appender.appendChild(param);
                
            }
            
        }
        
        if(rollingPolicy!=null)
        {
            rollingPolicy.generateXML(doc,appender);
        }
        
        if(triggeringPolicy!=null)
        {
            triggeringPolicy.generateXML(doc,appender);
        }
        
        if(connectionSource!=null)
        {
            connectionSource.generateXML(doc,appender);
        }
        
        if (layout != null) {
            layout.generateXML(doc, appender);
        }
        
        // if filter have name, we must order filters by name
        boolean sort = false;
        for(Filter filter : filters){
        	if(filter.getName() != null){
        		sort = true;
        		break;
        	}
        }
        if(sort){
            Collections.sort(filters);
        }
        for (Filter filter : filters) {
            filter.generateXML(doc, appender);
        }
        for(String ref : appenderRefs)
        {
            Element apRef = doc.createElement("appender-ref");
            apRef.setAttribute("ref", ref);
            appender.appendChild(apRef);
        }


        config.appendChild(appender);



    }
}
