package cz.muni.fi.pb138.logTesting.foo.barbar;

import org.apache.log4j.Logger;

public class ClassNine {
	private static Logger logger = Logger.getLogger(ClassNine.class);
	public ClassNine() {
		logger.info("Constructing ClassNine");
	}
}
