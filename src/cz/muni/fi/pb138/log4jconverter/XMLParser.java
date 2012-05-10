package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.configuration.Configuration.Threshold;
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
        
        public void parseXML() {            
            int i;
            
            // Log4J configuration tag
            Element rootElement;
            NodeList rootElementList = doc.getElementsByTagName("log4j:configuration");
            if (rootElementList.getLength() == 1) {
                rootElement = (Element) rootElementList.item(0);
                if (rootElement.hasAttribute("threshold")) {
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
            }
            
            // Renderers
            Renderer renderer;
            NodeList rendererList = doc.getElementsByTagName("renderer");
            for (i = 0; i < rendererList.getLength(); i++) {
                renderer = parseRenderer((Element) rendererList.item(i));
                configuration.addRenderer(renderer);
            }
            
            // ThrowableRenderers
            ThrowableRenderer throwableRenderer;
            NodeList throwableRendererList = doc.getElementsByTagName("throwableRenderer");
            if (throwableRendererList.getLength() == 1) {
                throwableRenderer = parseThrowableRenderer((Element) throwableRendererList.item(0));
                configuration.setThrowableRenderer(throwableRenderer);
            }
            
            // Appenders
            Appender appender;
            NodeList appenderList = doc.getElementsByTagName("appender");
            for (i = 0; i < appenderList.getLength(); i++) {
                appender = parseAppender((Element) appenderList.item(i));
                configuration.addAppender(appender);
            }
            
            // TODO: Load additional attributes
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
            
            // Required attributes
            appender.setAppenderName(appenderElement.getAttribute("name"));
            appender.setClassName(appenderElement.getAttribute("class"));
            
            // Child elements
            NodeList childNodes = appenderElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
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
        
        private ErrorHandler parseErrorHandler(Element errorHandlerElement) {
            ErrorHandler errorHandler = new ErrorHandler();
            int i;
            
            errorHandler.setClassName(errorHandlerElement.getAttribute("class"));
            
            // Child elements
            NodeList childNodes = errorHandlerElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
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
                childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("param")) {
                    layout.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
            }
            
            return layout;
        }
        
	@Override
	public Configuration parse() {
            if (configuration == null) {
                configuration = new Configuration();
                parseXML();
            }
            return configuration;
	}

}
