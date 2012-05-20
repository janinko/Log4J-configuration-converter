package cz.muni.fi.pb138.logTesting.foo.bar;

import org.apache.log4j.Logger;

public class ClassEight {
	private static Logger logger = Logger.getLogger(ClassEight.class);
	public ClassEight() {
		logger.info("Constructing ClassEight");
	}
}
