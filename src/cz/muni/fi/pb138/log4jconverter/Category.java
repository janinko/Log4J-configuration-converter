/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import cz.muni.fi.pb138.log4jconverter.RootLogger.Levels;

import java.io.Writer;
import java.util.HashSet;

/**
 *
 * @author Admin
 */
public class Category implements AbstractModel{
    private String categoryName;
    private RootLogger.Levels level;
    //optional
    private HashSet<Appender> appenderNames;

    public HashSet<Appender> getAppenderNames() {
        return appenderNames;
    }

    public void setAppenderNames(HashSet<Appender> appenderNames) {
        this.appenderNames = appenderNames;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Levels getLevel() {
        return level;
    }

    public void setLevel(Levels level) {
        this.level = level;
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
        if ((this.categoryName == null) ? (other.categoryName != null) : !this.categoryName.equals(other.categoryName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.categoryName != null ? this.categoryName.hashCode() : 0);
        return hash;
    }
     @Override
    public String toString() {
        return categoryName +"="  + level + ", " + appenderNames;
    }

	@Override
	public void printXML(Writer w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void printProperties(Writer w) {
		// TODO Auto-generated method stub
		
	}
    
}
