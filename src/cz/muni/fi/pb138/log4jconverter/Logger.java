/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pb138.log4jconverter;

import java.util.HashSet;

/**
 *
 * @author Admin
 */
/**<h3>Configuring loggers</h3>

    <p>The syntax for configuring the root logger is:
    <pre>
      log4j.rootLogger=[level], appenderName, appenderName, ...
    </pre>

    <p>This syntax means that an optional <em>level</em> can be
    supplied followed by appender names separated by commas.

    <p>The level value can consist of the string values OFF, FATAL,
    ERROR, WARN, INFO, DEBUG, ALL or a <em>custom level</em> value. A
    custom level value can be specified in the form
    <code>level#classname</code>.

    <p>If a level value is specified, then the root level is set
    to the corresponding level.  If no level value is specified,
    then the root level remains untouched.

    <p>The root logger can be assigned multiple appenders.

    <p>Each <i>appenderName</i> (separated by commas) will be added to
    the root logger. The named appender is defined using the
    appender syntax defined above.
    */



public class Logger {
    
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
    
    
}
