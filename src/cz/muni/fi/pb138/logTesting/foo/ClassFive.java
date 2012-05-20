package cz.muni.fi.pb138.logTesting.foo;

import org.apache.log4j.Logger;

import cz.muni.fi.pb138.logTesting.foo.bar.ClassEight;
import cz.muni.fi.pb138.logTesting.foo.bar.ClassSeven;
import cz.muni.fi.pb138.logTesting.foo.barbar.ClassNine;
import cz.muni.fi.pb138.logTesting.foo.barbar.ClassTen;

public class ClassFive {
	private static Logger logger = Logger.getLogger(ClassFive.class);
	
	public ClassFive() {
		logger.error("constructing ClassFive");
	}
	
	public void method() {
		logger.info("creating ClassSix");
		ClassSix c6 = new ClassSix();
		logger.info("creating ClassSeven");
		ClassSeven c7 = new ClassSeven();
		logger.info("creating ClassEight");
		ClassEight c8 = new ClassEight();
		logger.info("creating ClassNine");
		ClassNine c9 = new ClassNine();
		logger.info("creating ClassTen");
		ClassTen c10 = new ClassTen();

		logger.error("Throwable message", new RuntimeException(
				                          new IllegalArgumentException("This is IllegalArgumentException message")));
	}

}
