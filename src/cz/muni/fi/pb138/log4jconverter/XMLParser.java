package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.configuration.Configuration.Threshold;
import cz.muni.fi.pb138.log4jconverter.configuration.Level.Levels;
import cz.muni.fi.pb138.log4jconverter.configuration.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author Jonge
 */
public class XMLParser implements Parser {
	
	private Document doc;
        private Configuration configuration;
	
	public XMLParser(Document doc) {
            if (doc == null) {
                throw new IllegalArgumentException("doc");
            }
            this.doc = doc;
            this.configuration = null;
	}
        
        @Override
	public Configuration parse() {
            if (configuration == null) {
                configuration = new Configuration();
                parseXML();
            }
            return configuration;
	}
        
        public void parseXML() {            
            int i;
            
            // Log4J configuration tag
            Element rootElement = doc.getDocumentElement();
            if (rootElement.hasAttribute("threshold") && ! "null".equals(rootElement.getAttribute("threshold"))) {
                configuration.setThreshold(Threshold.valueOf(rootElement.getAttribute("threshold").toUpperCase()));
            }
            if (rootElement.hasAttribute("debug")) {
                if (rootElement.getAttribute("debug").equals("true")) {
                    configuration.setDebug(true);
                } else if (rootElement.getAttribute("debug").equals("false")) {
                    configuration.setDebug(false);
                }
                // setDebug(null) is default value
            }
            if (rootElement.hasAttribute("reset")) {
                if (rootElement.getAttribute("reset").equals("true")) {
                    configuration.setReset(true);
                }
                // setReset(false) is default value
            }
            
            // Child elements
            NodeList childNodes = rootElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                // renderer
                if (childElement.getTagName().equals("renderer")) {
                    configuration.addRenderer(parseRenderer(childElement));
                }
                // throwableRenderer
                else if (childElement.getTagName().equals("throwableRenderer")) {
                    configuration.setThrowableRenderer(parseThrowableRenderer(childElement));
                }
                // appender
                else if (childElement.getTagName().equals("appender")) {
                    configuration.addAppender(parseAppender(childElement));
                }
                // plugin
                else if (childElement.getTagName().equals("plugin")) {
                    configuration.addPlugin(parsePlugin(childElement));
                }
                // logger (or deprecated category)
                else if (childElement.getTagName().equals("logger")
                        || childElement.getTagName().equals("category")) {
                    configuration.addLogger(parseLogger(childElement));
                }
                // loggerFactory (or deprecated categoryFactory)
                else if (childElement.getTagName().equals("loggerFactory")
                        || childElement.getTagName().equals("categoryFactory")) {
                    configuration.setLogFactory(parseLoggerFactory(childElement));
                }
                // root
                else if (childElement.getTagName().equals("root")) {
                    configuration.setRoot(parseRoot(childElement));
                }
            }
        }
        
        private Renderer parseRenderer(Element rendererElement) {
            Renderer renderer = new Renderer();
            
            renderer.setRenderedClass(rendererElement.getAttribute("renderedClass"));
            renderer.setRenderingClass(rendererElement.getAttribute("renderingClass"));
            
            return renderer;
        }
        
        private ThrowableRenderer parseThrowableRenderer(Element throwableRendererElement) {
            ThrowableRenderer throwableRenderer = new ThrowableRenderer();
            int i;
            
            throwableRenderer.setClassName(throwableRendererElement.getAttribute("class"));
            
            // param
            NodeList childNodes = throwableRendererElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("param")) {
                    throwableRenderer.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
            }
            
            return throwableRenderer;
        }
        
        private Appender parseAppender(Element appenderElement) {
            Appender appender = new Appender();
            int i;
            
            appender.setAppenderName(appenderElement.getAttribute("name"));
            appender.setClassName(appenderElement.getAttribute("class"));
            
            // Child elements
            NodeList childNodes = appenderElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                // param
                if (childElement.getTagName().equals("param")) {
                    appender.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
                // filter
                else if (childElement.getTagName().equals("filter")) {
                    appender.addFilter(parseFilter(childElement));
                }
                // appender-ref
                else if (childElement.getTagName().equals("appender-ref")) {
                    appender.addAppenderRef(childElement.getAttribute("ref"));
                }
                // errorHandler
                else if (childElement.getTagName().equals("errorHandler")) {
                    appender.setErrorHandler(parseErrorHandler(childElement));
                }
                // rollingPolicy
                else if (childElement.getTagName().equals("rollingPolicy")) {
                    appender.setRollingPolicy(parseRollingPolicy(childElement));
                }
                // triggeringPolicy
                else if (childElement.getTagName().equals("triggeringPolicy")) {
                    appender.setTriggeringPolicy(parseTriggeringPolicy(childElement));
                }
                // connectionSource
                else if (childElement.getTagName().equals("connectionSource")) {
                    appender.setConnectionSource(parseConnectionSource(childElement));
                }
                // layout
                else if (childElement.getTagName().equals("layout")) {
                    appender.setLayout(parseLayout(childElement));
                }
            }            
            
            return appender;
        }
        
        private Plugin parsePlugin(Element pluginElement) {
            Plugin plugin = new Plugin();
            int i;
            
            plugin.setName(pluginElement.getAttribute("name"));
            plugin.setClassName(pluginElement.getAttribute("class"));
            
            // Child elements
            NodeList childNodes = pluginElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                // param
                if (childElement.getTagName().equals("param")) {
                    plugin.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
                // connectionSource
                else if (childElement.getTagName().equals("connectionSource")) {
                    plugin.setConnSource(parseConnectionSource(childElement));
                }
            }
            
            return plugin;
        }
        
        private Logger parseLogger(Element loggerElement) {
            Logger logger = new Logger();
            int i;
            
            logger.setName(loggerElement.getAttribute("name"));
            
            if (loggerElement.hasAttribute("name")) {
                logger.setName(loggerElement.getAttribute("name"));
            }
            
            logger.setClassName(loggerElement.getAttribute("class"));
            
            // Child elements
            NodeList childNodes = loggerElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                // param
                if (childElement.getTagName().equals("param")) {
                    logger.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
                // appender-ref
                else if (childElement.getTagName().equals("appender-ref")) {
                    logger.addAppenderRef(childElement.getAttribute("ref"));
                }
                // level (or deprecated priority)
                else if (childElement.getTagName().equals("level")
                        || childElement.getTagName().equals("priority")) {
                    logger.setLevel(parseLevel(childElement));
                }
            }
            
            return logger;
        }
        
        private LoggerFactory parseLoggerFactory(Element loggerFactoryElement) {
            LoggerFactory loggerFactory = new LoggerFactory();
            int i;
            
            loggerFactory.setClassName(loggerFactoryElement.getAttribute("class"));
            
            // param
            NodeList childNodes = loggerFactoryElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("param")) {
                    loggerFactory.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
            }
            
            return loggerFactory;
        }
        
        private Root parseRoot(Element rootElement) {
            Root root = new Root();
            int i;
            
            // Child elements
            NodeList childNodes = rootElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                // param
                if (childElement.getTagName().equals("param")) {
                    root.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
                // appender-ref
                else if (childElement.getTagName().equals("appender-ref")) {
                    root.addAppenderRef(childElement.getAttribute("ref"));
                }
                // level (or deprecated priority)
                else if (childElement.getTagName().equals("level")
                        || childElement.getTagName().equals("priority")) {
                    root.setLevel(parseLevel(childElement));
                }
            }
            
            return root;
        }
        
        
        
        private ErrorHandler parseErrorHandler(Element errorHandlerElement) {
            ErrorHandler errorHandler = new ErrorHandler();
            int i;
            
            errorHandler.setClassName(errorHandlerElement.getAttribute("class"));
            
            // Child elements
            NodeList childNodes = errorHandlerElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                // param
                if (childElement.getTagName().equals("param")) {
                    errorHandler.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
                // root-ref
                else if (childElement.getTagName().equals("root-ref")) {
                    errorHandler.setRootRef(true);
                }
                // logger-ref
                else if (childElement.getTagName().equals("logger-ref")) {
                    errorHandler.addLoggerRef(childElement.getAttribute("ref"));
                }
                // appender-ref
                else if (childElement.getTagName().equals("appender-ref")) {
                    errorHandler.setAppenderRef(childElement.getAttribute("ref"));
                }
            }
            
            return errorHandler;
        }
        
        private RollingPolicy parseRollingPolicy(Element rollingPolicyElement) {
            RollingPolicy rollingPolicy = new RollingPolicy();
            int i;
            
            rollingPolicy.setClassName(rollingPolicyElement.getAttribute("class"));
            
            if (rollingPolicyElement.hasAttribute("name")) {
                rollingPolicy.setName(rollingPolicyElement.getAttribute("name"));
            }
            
            // param
            NodeList childNodes = rollingPolicyElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("param")) {
                    rollingPolicy.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
            }
            
            return rollingPolicy;
        }
        
        private TriggeringPolicy parseTriggeringPolicy(Element triggeringPolicyElement) {
            TriggeringPolicy triggeringPolicy = new TriggeringPolicy();
            int i;
            
            triggeringPolicy.setClassName(triggeringPolicyElement.getAttribute("class"));
            
            if (triggeringPolicyElement.hasAttribute("name")) {
                triggeringPolicy.setName(triggeringPolicyElement.getAttribute("name"));
            }
            
            NodeList childNodes = triggeringPolicyElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                // param
                if (childElement.getTagName().equals("param")) {
                    triggeringPolicy.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
                // filter
                else if (childElement.getTagName().equals("filter")) {
                    triggeringPolicy.addFilter(parseFilter(childElement));
                }
            }
            
            return triggeringPolicy;
        }
        
        private ConnectionSource parseConnectionSource(Element connectionSourceElement) {
            ConnectionSource connectionSource = new ConnectionSource();
            int i;
            
            connectionSource.setClassName(connectionSourceElement.getAttribute("class"));
            
            NodeList childNodes = connectionSourceElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                // param
                if (childElement.getTagName().equals("param")) {
                    connectionSource.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
                // dataSource
                else if (childElement.getTagName().equals("dataSource")) {
                    connectionSource.setDataSource(parseDataSource(childElement));
                }
            }
            
            return connectionSource;
        }
        
        private Filter parseFilter(Element filterElement) {
            Filter filter = new Filter();
            int i;
            
            filter.setClassName(filterElement.getAttribute("class"));
            
            // param
            NodeList childNodes = filterElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("param")) {
                    filter.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
            }
            
            return filter;
        }
        
        private DataSource parseDataSource(Element dataSourceElement) {
            DataSource dataSource = new DataSource();
            int i;
            
            dataSource.setClassName(dataSourceElement.getAttribute("class"));
            
            // param
            NodeList childNodes = dataSourceElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("param")) {
                    dataSource.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
            }
            
            return dataSource;
        }
        
        private Layout parseLayout(Element layoutElement) {
            Layout layout = new Layout();
            int i;
            
            layout.setClassName(layoutElement.getAttribute("class"));
            
            // param
            NodeList childNodes = layoutElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("param")) {
                    layout.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
            }
            
            return layout;
        }
        
        private Level parseLevel(Element levelElement) {
            Level level = new Level();
            int i;
            
            level.setValues(Levels.valueOf(levelElement.getAttribute("value").toUpperCase()));
            
            if (levelElement.hasAttribute("class")) {
                level.setClassName(levelElement.getAttribute("class"));
            }
            
            // param
            NodeList childNodes = levelElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
            	if(!(childNodes.item(i) instanceof Element)) continue;
                childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("param")) {
                    level.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
            }
            
            return level;
        }

}
