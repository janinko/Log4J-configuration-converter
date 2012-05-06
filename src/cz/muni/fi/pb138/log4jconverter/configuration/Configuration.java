package cz.muni.fi.pb138.log4jconverter.configuration;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Configuration{
    
    public enum Tresholds{
        all,trace,debug,info,warn,error,fatal,off,
    }
    
    
    private Document doc = null;
    private Tresholds treshold = null;
    private Boolean debug = null;
    private boolean reset = false;
    
    private Root root;
    private HashSet<Renderer> renderers;
    private ThrowableRender throwableRenderer;
    private HashMap<String, Appender> appenders;
    private HashMap<String, Logger> loggers;
    private HashMap<String, Plugin> plugins;
    private LoggerFactory logFactory;
    

    public Configuration() throws ParserConfigurationException {
        renderers = new HashSet<Renderer>();
        appenders = new HashMap<String, Appender>();
        loggers = new HashMap<String, Logger>();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        this.doc = builder.newDocument();
    }

    /* returns Appender by its name, if it does'n exists,
	 * creates new one.
     */
    public Appender getAppender(String name) { 
        if (!appenders.containsKey(name)) {
            appenders.put(name, new Appender(name));
        }
        return appenders.get(name);
    }

    /* returns Logger by its name, if it does'n exists,
	 * creates new one.
     */
    public Logger getLogger(String name) { 
        if (!loggers.containsKey(name)) {
        	loggers.put(name, new Logger(name));
        }
        return loggers.get(name);
    }
    
    /*
     * It's used in tests
     * default visibility is OK for tests
     */
    boolean isAppender(String name) {
        if (appenders.containsKey(name)) {
            return true;
        }
        return false;
    }

    public HashMap<String, Appender> getAppenders() {
        return appenders;
    }

    public void setAppenders(HashMap<String, Appender> appenders) {
        this.appenders = appenders;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public LoggerFactory getLogFactory() {
        return logFactory;
    }

    public void setLogFactory(LoggerFactory logFactory) {
        this.logFactory = logFactory;
    }

    public HashMap<String, Logger> getLoggers() {
        return loggers;
    }

    public void setLoggers(HashMap<String, Logger> loggers) {
        this.loggers = loggers;
    }

    public HashMap<String, Plugin> getPlugins() {
        return plugins;
    }

    public void setPlugins(HashMap<String, Plugin> plugins) {
        this.plugins = plugins;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public Tresholds getTreshold() {
        return treshold;
    }

    public void setTreshold(Tresholds treshold) {
        this.treshold = treshold;
    }

    public HashSet<Renderer> getRenderers() {
        return renderers;
    }

    public void setRenderers(HashSet<Renderer> renderers) {
        this.renderers = renderers;
    }

    public Root getRoot() {
        return root;
    }

    public void setRoot(Root root) {
        this.root = root;
    }

    public ThrowableRender getThrowableRenderer() {
        return throwableRenderer;
    }

    public void setThrowableRenderer(ThrowableRender throwableRenderer) {
        this.throwableRenderer = throwableRenderer;
    }
    
    public void addRenderer(Renderer r) {
        renderers.add(r);
    }
    
    public void addAppender(Appender a) {
        appenders.put(a.getAppenderName(), a);
    }

    public void printXML(Writer w) {
        
        Element config = doc.createElement("log4j:configuration");
        config.setAttribute("xmlns:log4j","http://jakarta.apache.org/log4j/");
        if(treshold !=null){
            config.setAttribute("treshold",treshold.toString());
        }
        if(debug!=null)
        {
            config.setAttribute("debug",debug.toString());
        }
        if(reset){
        config.setAttribute("reset","true");    
        }
        else{
        config.setAttribute("reset","false");        
        }
        
        
        
      
        for(Renderer renderer : renderers)
        {
            renderer.printXML(doc, config);
        }
        
        if(throwableRenderer!= null){
            throwableRenderer.printXML(doc, config);
        }
        
        for(Appender appender : appenders.values())
        {
            appender.printXML(doc, config);
        }
        
       
        
        for (Plugin plugin : plugins.values())
        {
            plugin.printXML(doc, config);
        }
         for(Logger logger : loggers.values())
        {
           logger.printXML(doc, config);
        }
         
          if(root!= null)
        {
           root.printXML(doc, config);
        }
        
        if(logFactory!= null)
        {
            logFactory.printXML(doc, config);
        }
        
        doc.appendChild(config);    
        
        }

    public void printProperties(Writer w) {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        return root.toString();
    }
    // nvm ci sa to hodi do tejto classy
     private  void serializetoXML(URI output)
            throws IOException, TransformerConfigurationException, TransformerException {
        // Vytvorime instanci tovarni tridy
        TransformerFactory factory = TransformerFactory.newInstance();
        // Pomoci tovarni tridy ziskame instanci tzv. kopirovaciho transformeru
        Transformer transformer = factory.newTransformer();
        // Vstupem transformace bude dokument v pameti
        DOMSource source = new DOMSource(doc);
        // Vystupem transformace bude vystupni soubor
        StreamResult result = new StreamResult(output.toString());
        // Provedeme transformaci
        transformer.transform(source, result);
    }

    private void serializetoXML(File output) throws IOException,
            TransformerException {
        serializetoXML(output.toURI());
    }

}
