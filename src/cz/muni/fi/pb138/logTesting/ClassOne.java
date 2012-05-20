package cz.muni.fi.pb138.logTesting;

import org.apache.log4j.Logger;

public class ClassOne {
	private static Logger logger = Logger.getLogger(ClassOne.class);

	public ClassOne() {
		logger.info("constructing ClassOne");
	}
	
	public void method() {
		logger.info("Throwable message", new NullPointerException("This is NullPointerException message"));
		
	}

}
