package cz.muni.fi.pb138.logTesting.foo.barbar;

import org.apache.log4j.Logger;

public class ClassTen {
	private static Logger logger = Logger.getLogger(ClassTen.class);
	public ClassTen() {
		logger.info("Constructing ClassTen");
	}
}
