package cz.muni.fi.pb138.logTesting;

import org.apache.log4j.Logger;

public class ClassFour {
	private static Logger logger = Logger.getLogger(ClassFour.class);

	public ClassFour() {
		logger.trace("constructing ClassFour");
	}

	public void method() {
		logger.trace("Throwable message", new NullPointerException());

	}

}
