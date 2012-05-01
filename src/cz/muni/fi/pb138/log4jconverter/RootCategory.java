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

/**
<p>The syntax for configuring the root logger is:
 * <pre>
 * log4j.rootCategory=[level], appenderName, appenderName, ...
 * </pre>
 */
public class RootCategory implements AbstractModel{
    
    private RootLogger.Levels level;
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

    @Override
    public String toString() {
        return "RootCategory="  + level + ", " + appenderNames;
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
