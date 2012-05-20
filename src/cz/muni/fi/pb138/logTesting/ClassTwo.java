package cz.muni.fi.pb138.logTesting;

import org.apache.log4j.Logger;

public class ClassTwo {
	private static Logger logger = Logger.getLogger(ClassTwo.class);

	public ClassTwo() {
		logger.debug("constructing ClassTwo");
	}

	public void method() {
		logger.debug("Throwable message", new IllegalArgumentException(new NullPointerException("This is NullPointerException message")));

	}

}
