package cz.muni.fi.pb138.logTesting;

import org.apache.log4j.Logger;

public class ClassTrhee {
	private static Logger logger = Logger.getLogger(ClassTrhee.class);

	public ClassTrhee() {
		logger.warn("constructing ");
	}

	public void method() {
		logger.warn("Throwable message", new RuntimeException(
				                          new IllegalArgumentException(
				                           new NullPointerException("This is NullPointerException message"))));

	}

}
