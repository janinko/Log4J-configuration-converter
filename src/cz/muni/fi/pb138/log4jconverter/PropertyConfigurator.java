/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

/**
 * 
 * Init Configuration from property external file.
 * 
 * 
 *
 * @author fivekeyem
 */
public class PropertyConfigurator {
    
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
    
    
    //public Pr
    
    
}
