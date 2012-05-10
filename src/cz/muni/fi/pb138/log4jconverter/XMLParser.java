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

            // errorHandler
            NodeList errorHandlerList = appenderElement.getElementsByTagName("errorHandler");
            if (errorHandlerList.getLength() == 1) {
                ErrorHandler errorHandler = parseErrorHandler((Element) errorHandlerList.item(0));
                appender.setErrorHandler(errorHandler);
            }
            
            // param
            NodeList childNodes = appenderElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
                childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("param")) {
                    appender.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
            }
            
            // rollingPolicy
            NodeList rollingPolicyList = appenderElement.getElementsByTagName("rollingPolicy");
            if (rollingPolicyList.getLength() == 1) {
                RollingPolicy rollingPolicy = parseRollingPolicy((Element) rollingPolicyList.item(0));
                appender.setRollingPolicy(rollingPolicy);
            }
            
            // triggeringPolicy
            NodeList triggeringPolicyList = appenderElement.getElementsByTagName("triggeringPolicy");
            if (triggeringPolicyList.getLength() == 1) {
                TriggeringPolicy triggeringPolicy = parseTriggeringPolicy((Element) triggeringPolicyList.item(0));
                appender.setTriggeringPolicy(triggeringPolicy);
            }
            
            // connectionSource
            NodeList connectionSourceList = appenderElement.getElementsByTagName("connectionSource");
            if (connectionSourceList.getLength() == 1) {
                ConnectionSource connectionSource = parseConnectionSource((Element) connectionSourceList.item(0));
                appender.setConnectionSource(connectionSource);
            }
            
            // TODO: Load additional attributes
            
            return appender;
        }
        
        private ErrorHandler parseErrorHandler(Element errorHandlerElement) {
            ErrorHandler errorHandler = new ErrorHandler();
            int i;
            
            // Required attribute
            errorHandler.setClassName(errorHandlerElement.getAttribute("class"));

            // param
            NodeList childNodes = errorHandlerElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
                childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("param")) {
                    errorHandler.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                }
            }
            
            // root-ref
            NodeList rootRefList = errorHandlerElement.getElementsByTagName("root-ref");
            if (rootRefList.getLength() == 1) {
                errorHandler.setRootRef(true);
            }

            // logger-ref
            NodeList loggerRefList = errorHandlerElement.getElementsByTagName("logger-ref");
            for (i = 0; i < loggerRefList.getLength(); i++) {
                Element loggerRefElement = (Element) loggerRefList.item(i);
                errorHandler.addLoggerRef(loggerRefElement.getAttribute("ref"));
            }

            // appender-ref
            NodeList appenderRefList = errorHandlerElement.getElementsByTagName("appender-ref");
            if (appenderRefList.getLength() == 1) {
                Element appenderRefElement = (Element) appenderRefList.item(0);
                errorHandler.setAppenderRef(appenderRefElement.getAttribute("ref"));
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
            
            // param or filter
            NodeList childNodes = triggeringPolicyElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
                childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("param")) {
                    triggeringPolicy.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
                } else if (childElement.getTagName().equals("filter")) {
                    triggeringPolicy.addFilter(parseFilter(childElement));
                }
            }
            
            return triggeringPolicy;
        }
        
        private ConnectionSource parseConnectionSource(Element connectionSourceElement) {
            ConnectionSource connectionSource = new ConnectionSource();
            int i;
            
            connectionSource.setClassName(connectionSourceElement.getAttribute("class"));
            
            // dataSource
            NodeList dataSourceList = connectionSourceElement.getElementsByTagName("dataSource");
            if (dataSourceList.getLength() == 1) {
                Element dataSourceElement = (Element) dataSourceList.item(0);
                connectionSource.setDataSource(parseDataSource(dataSourceElement));
            }
            
            // param
            NodeList childNodes = connectionSourceElement.getChildNodes();
            Element childElement;
            for (i = 0; i < childNodes.getLength(); i++) {
                childElement = (Element) childNodes.item(i);
                if (childElement.getTagName().equals("param")) {
                    connectionSource.addParam(childElement.getAttribute("name"), childElement.getAttribute("value"));
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
        
	@Override
	public Configuration parse() {
            if (configuration == null) {
                configuration = new Configuration();
                parseXML();
            }
            return configuration;
	}

}
