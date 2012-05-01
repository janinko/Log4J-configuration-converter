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
        
    public Appender getAppender(String name){
    	//TODO
    	return null;
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

	@Override
	public void printXML(Writer w) {
		// TODO Auto-generated method stub
	}

	@Override
	public void printProperties(Writer w) {
		// TODO Auto-generated method stub
	}

	
}
