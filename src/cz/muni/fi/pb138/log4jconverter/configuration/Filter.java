package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class Filter {
    //required
    private String className;
    //optional
    private HashMap<String,String> params;

    public Filter() {
        this.params = new HashMap<String,String>();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Filter other = (Filter) obj;
        if ((this.className == null) ? (other.className != null) : !this.className.equals(other.className)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.className != null ? this.className.hashCode() : 0);
        return hash;
    }
    
}
