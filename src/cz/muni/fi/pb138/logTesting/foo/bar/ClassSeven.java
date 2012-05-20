package cz.muni.fi.pb138.logTesting.foo.bar;

import org.apache.log4j.Logger;

public class ClassSeven {
	private static Logger logger = Logger.getLogger(ClassSeven.class);
	public ClassSeven() {
		logger.info("Constructing ClassSeven");
	}
}
