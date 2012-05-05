package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class Filter implements Comparable<Filter> {
    //required
    private String className;
    //optional
    private HashMap<String,String> params;
    //used in Properties to distinguish filters and to order them
    private String name;

    public Filter() {
        this.params = new HashMap<String,String>();
    }
    
    public Filter(Filter f) {
    	className = f.className;
    	params = f.params;
    	name = f.name;
	}

	public String getName(){
    	return name;
    }
    
    public void setName(String name){
    	this.name = name;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filter other = (Filter) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (params == null) {
			if (other.params != null)
				return false;
		} else if (!params.equals(other.params))
			return false;
		return true;
	}

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((params == null) ? 0 : params.hashCode());
		return result;
	}

	@Override
	public int compareTo(Filter o) {
		if(name == null || o.name == null){
			throw new IllegalArgumentException("Uncomparable filters");
		}
		return name.compareTo(o.name);
	}
    
}
