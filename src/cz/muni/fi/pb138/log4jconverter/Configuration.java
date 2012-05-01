package cz.muni.fi.pb138.log4jconverter;

import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;

public class Configuration implements AbstractModel{
	RootLogger rootLogger;
        RootCategory rootCategory;
	HashSet<Renderer> renderers;
	HashMap<String,Appender> appenders;
	HashMap<String,Logger> loggers;
        HashMap<String,Category> categoryes;
        
    public Configuration(){
    	renderers = new HashSet<Renderer>();
    	appenders = new HashMap<String,Appender>();
    	loggers = new HashMap<String,Logger>();
    	categoryes = new HashMap<String,Category>();
    }
        
    public Appender getAppender(String name){
    	if(appenders.containsKey(name))
    		return appenders.get(name);
    	
    	Appender a = new Appender(name);
    	appenders.put(name, a);
    	
    	return a;
    }
    
    public Logger getLogger(String name){
    	//TODO
    	return null;
    }
    
    public Category getCategory(String name){
    	//TODO
    	return null;
    }
    
    public void addRenderer(Renderer r){
    	renderers.add(r);
    }
    
    public void setRootLogger(RootLogger rl){
    	rootLogger = rl;
    }
    
    public void setRootCategory(RootCategory rc){
    	rootCategory = rc;
    }

    public RootLogger getRootLogger() {
    	return rootLogger;
	}
        
	@Override
	public void printXML(Writer w) {
		// TODO Auto-generated method stub
	}

	@Override
	public void printProperties(Writer w) {
		// TODO Auto-generated method stub
	}

        
        
        @Override
        public String toString() {
            return rootLogger.toString();
        }
        
	
}
