package cz.muni.fi.pb138.logTesting.foo;

import org.apache.log4j.Logger;

public class ClassSix {
	private static Logger logger = Logger.getLogger(ClassSix.class);
	public ClassSix() {
		logger.info("Constructing ClassSix");
	}
}
