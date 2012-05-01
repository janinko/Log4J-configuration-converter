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
 * <p>The syntax for configuring the root logger is:
 * <pre>
 * log4j.rootLogger=[level], appenderName, appenderName, ...
 * </pre>
 *
 * <p>This syntax means that an optional <em>level</em> can be supplied followed
 * by appender names separated by commas.
 *
 * <p>The level value can consist of the string values OFF, FATAL, ERROR, WARN,
 * INFO, DEBUG, ALL or a <em>custom level</em> value. A custom level value can
 * be specified in the form
 * <code>level#classname</code>.
 *
 * <p>If a level value is specified, then the root level is set to the
 * corresponding level. If no level value is specified, then the root level
 * remains untouched.
 *
 * <p>The root logger can be assigned multiple appenders.
 *
 * <p>Each <i>appenderName</i> (separated by commas) will be added to the root
 * logger. The named appender is defined using the appender syntax defined
 * above.
 */
public class RootLogger implements AbstractModel{

    public enum Levels {

        OFF, FATAL,
        ERROR, WARN, INFO, DEBUG, ALL
    }
    private Levels level;
    private HashSet<String> appenderNames;

    public HashSet<String> getAppenderNames() {
        return appenderNames;
    }

    public void setAppenderNames(HashSet<String> appenderNames) {
        this.appenderNames = appenderNames;
    }

    public Levels getLevel() {
        return level;
    }

    public void setLevel(Levels level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "RootLogger=" + level + ", " + appenderNames ;
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
