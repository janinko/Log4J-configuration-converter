package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Admin
 */
public class TriggeringPolicy {
    //required

    private String name;
    private String className;
    //optional 
    // moze byt bud params alebo filters nemozu byt oba naraz cize v parseroch bude treba urobit nejaku podmienku
    private HashMap<String, String> params;
    private HashSet<Filter> filters;

    public TriggeringPolicy() {

        this.params = new HashMap<String, String>();
        this.filters = new HashSet<Filter>();
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
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

    public HashSet<Filter> getFilters() {
        return filters;
    }

    public void setFilters(HashSet<Filter> filters) {
        this.filters = filters;
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void printXML(Document doc, Element appender) {
        Element triggPolicy = doc.createElement("triggeringPolicy");
        triggPolicy.setAttribute("name", name);
        triggPolicy.setAttribute("class", className);

        if (!params.isEmpty()) {
            Iterator it1 = params.keySet().iterator();
            Iterator it2 = params.values().iterator();
            while (it1.hasNext()) {
                Element param = doc.createElement("param");

                param.setAttribute("name", it1.next().toString());
                param.setAttribute("value", it2.next().toString());
                triggPolicy.appendChild(param);

            }

        } else {
            for (Filter filter : filters) {
                filter.printXML(doc, triggPolicy);
            }
        }

        appender.appendChild(triggPolicy);
    }
}
