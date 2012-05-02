/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter.configuration;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Admin
 */
public class Category {
    
     //required

    private String cattegoryName;
    //implies
    private String className;
    //neviem ci som spravne pochopil z toho dtd ze deafulat hodnota je true
    private boolean additivity = true;
    //optional
    private HashMap<String, String> params;
    private HashSet<String> appenderRefs;
    //bud level alebo priority
    private Level level;
    private Priority priority;

    public Category() {
        this.params = new HashMap<String, String>();
        this.appenderRefs = new HashSet<String>();
    }

    
    public boolean isAdditivity() {
        return additivity;
    }

    public void setAdditivity(boolean additivity) {
        this.additivity = additivity;
    }

    public HashSet<String> getAppenderRefs() {
        return appenderRefs;
    }

    public void setAppenderRefs(HashSet<String> appenderRefs) {
        this.appenderRefs = appenderRefs;
    }

    public String getCattegoryName() {
        return cattegoryName;
    }

    public void setCattegoryName(String cattegoryName) {
        this.cattegoryName = cattegoryName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Category other = (Category) obj;
        if ((this.cattegoryName == null) ? (other.cattegoryName != null) : !this.cattegoryName.equals(other.cattegoryName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.cattegoryName != null ? this.cattegoryName.hashCode() : 0);
        return hash;
    }
    
    
    
}
