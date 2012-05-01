/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import java.io.Writer;
import java.util.HashSet;

/**
 *
 * @author Admin
 */




public class Logger implements AbstractModel{
    
    private String loggerName;
    private RootLogger.Levels level;
    //optional
    private HashSet<Appender> appenderNames;
    
    public HashSet<Appender>  getAppenderNames() {
        return appenderNames;
    }

    public void setAppenderNames(HashSet<Appender> appenderNames) {
        this.appenderNames = appenderNames;
    }

    public RootLogger.Levels getLevel() {
        return level;
    }

    public void setLevel(RootLogger.Levels level) {
        this.level = level;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Logger other = (Logger) obj;
        if ((this.loggerName == null) ? (other.loggerName != null) : !this.loggerName.equals(other.loggerName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.loggerName != null ? this.loggerName.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString() {
        return loggerName +"="  + level + ", " + appenderNames;
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
