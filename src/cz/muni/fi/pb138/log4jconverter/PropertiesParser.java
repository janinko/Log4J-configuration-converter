/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;

import cz.muni.fi.pb138.log4jconverter.configuration.Appender;
import cz.muni.fi.pb138.log4jconverter.configuration.Configuration;
import cz.muni.fi.pb138.log4jconverter.configuration.Root;

/**
 * 
 * Init Configuration from property external file.
 * 
 * 
 *
 * @author fivekeyem
 */
public class PropertiesParser implements Parser {
	
    static final String               PREFIX    = "log4j";
    static final String             APPENDER    = "appender";
    static final String             CATEGORY    = "category";
    static final String               LOGGER    = "logger";
    static final String        ROOT_CATEGORY    = "rootCategory";
    static final String          ROOT_LOGGER    = "rootLogger";
    static final String                DEBUG    = "debug";
    
    static final String      CATEGORY_PREFIX    = "log4j.category.";
    static final String        LOGGER_PREFIX    = "log4j.logger.";
    static final String       FACTORY_PREFIX    = "log4j.factory";
    static final String    ADDITIVITY_PREFIX    = "log4j.additivity.";
    static final String ROOT_CATEGORY_PREFIX    = "log4j.rootCategory";
    static final String   ROOT_LOGGER_PREFIX    = "log4j.rootLogger";
    static final String      APPENDER_PREFIX    = "log4j.appender.";
    static final String      RENDERER_PREFIX    = "log4j.renderer.";
    static final String     THRESHOLD_PREFIX    = "log4j.threshold";
    private static final String      THROWABLE_RENDERER_PREFIX = "log4j.throwableRenderer";
    private static final String LOGGER_REF	= "logger-ref";
    private static final String   ROOT_REF	= "root-ref";
    private static final String APPENDER_REF_TAG= "appender-ref";  

    /** Key for specifying the {@link org.apache.log4j.spi.LoggerFactory
        LoggerFactory}.  Currently set to "<code>log4j.loggerFactory</code>".  */
    public static final String LOGGER_FACTORY_KEY = "log4j.loggerFactory";

    /**
        * If property set to true, then hierarchy will be reset before configuration.
        */
    private static final String RESET_KEY = "log4j.reset";

    static final private String INTERNAL_ROOT_NAME = "root";
    
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PropertiesParser.class);
    
    private Properties properties;
    private Configuration configuration;
    
    
    public PropertiesParser(Properties properties) {
        this.properties = properties;
        this.configuration = null;
    }
    
    
    public void writeAllProperties() {
        Enumeration e = properties.propertyNames();

        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            System.out.println(key + " -- " + properties.getProperty(key));
        }
    }

	// goes for all properties, split key into array and parse
    private void parsePropeties(){
    	for(Entry<Object, Object> e : properties.entrySet()){
    		String[] key = ((String) e.getKey()).split("\\.");
    		String value = (String) e.getValue();
    		
    		try {
				parseProperty(key,value);
			} catch (ParseException e1) {
				logger.warn("Unexpected property: " + e.getKey() + "=" + e.getValue(),e1);
			}
    	}
    	
    }
    
    private void parseProperty(String[] key, String value) throws ParseException {
    	if(key.length < 2) throw new ParseException("Key must have at least 2 parts");
    	if(!PREFIX.equals(key[0])) throw new ParseException("Key must have prefix " + PREFIX);
    	
    	String specifier = key[1];
    	
    	if(APPENDER.equals(specifier)){
    		parseAppender(key, value);
    	}else if(LOGGER.equals(specifier) || CATEGORY.equals(specifier)){
    		parseLogger(key, value);
    	}else if(ROOT_LOGGER.equals(specifier) || ROOT_CATEGORY.equals(specifier)){
    		parseRootLogger(key, value);
    	}else if(DEBUG.equals(specifier)){
    		if(value.equals("true")){
    			configuration.setDebug(true);
    		}else if(value.equals("false")){
    			configuration.setDebug(false);
    		}else{
    			throw new ParseException("Unknown value for " + PREFIX + "." + DEBUG);
    		}
    	}
    	
	}


	private void parseRootLogger(String[] key, String value) {
		if (logger.isTraceEnabled()) { logger.trace("parsing root logger: '" + value + "'"); }
        
                Root rootLogger = new Root();

                // ziskej jednotlive appendery
                String[] appdenderName = value.split(",");
                for (int i = 1; i < appdenderName.length; i++) {
                    // pridej novy appender
                    rootLogger.addAppenderRef(appdenderName[i].trim());

                    if (logger.isTraceEnabled()) { logger.trace("new appender ("+ appdenderName[i].trim() +") created"); }
                }

                // nakonec uloz vse do configuration
                configuration.setRoot(rootLogger);
                if (logger.isTraceEnabled()) { logger.trace("configuration saved"); }		
	}


	private void parseLogger(String[] key, String value) {
		if (logger.isTraceEnabled()) { logger.trace("parsing logger: " +concateKeyParts(key, 0)+"=" + value); }
		// TODO Auto-generated method stub
		
	}


	private void parseAppender(String[] key, String value) throws ParseException {
		if (logger.isTraceEnabled()) { logger.trace("parsing appender: " +concateKeyParts(key, 0)+"=" + value); }
		if(key.length < 3) throw new ParseException("Appender key must have at least 3 parts");
		
		String appenderName = key[2];
		Appender appender = configuration.getAppender(appenderName);
		
		if(key.length == 3){ // log4j.appender.APNAME = org.example.myclass
			appender.setClassName(value);
		}else{ // length > 3
			if("layout".equals(key[3])){
				parseLayout(key,value,appender);
			}else if("filter".equals(key[3])){
				//TODO
			}else if(key.length == 4){ // this should be miscellaneous parameters
				appender.addParam(key[3],value);
			}else{ // if it has length > 4 and isn't parsed yet, it is wrong or unknown
				new ParseException("Unknown appender key");
			}
		}
		
	}

	private void parseLayout(String[] key, String value, Appender appender) throws ParseException {
		if (logger.isTraceEnabled()) { logger.trace("parsing appender layout: " +concateKeyParts(key, 0)+"=" + value); }
		if(key.length < 4) throw new ParseException("Appender layout key must have at least 4 parts");
		
		if(key.length == 4){
			appender.getLayout().setClassName(value);
		}else if(key.length == 5){ // parameter
			appender.getLayout().addParam(key[4], value);
		}else{
			throw new ParseException("Unknown layout key");
		}
	}

	@Override
	public Configuration parse() {
		if(configuration == null){
			configuration = new Configuration();
			parsePropeties();
		}
		return configuration;
	}
    
	private static String concateKeyParts(String[] key, int from){
		if(from >= key.length) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(key[from]);
		for(int i=from+1; i < key.length; i++){
			sb.append('.');
			sb.append(key[i]);
		}
		
		return sb.toString();
	}
    
}
