package cz.muni.fi.pb138.logTesting;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import cz.muni.fi.pb138.logTesting.foo.ClassFive;

public class Main {
	private static Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		PropertyConfigurator.configure(args[0]);
		Logger.getRootLogger().setLevel(Level.ALL);

		method1();
		method2();
		method3();
		method4();
		method5();
		
		ClassOne c1 = new ClassOne();
		ClassTwo c2 = new ClassTwo();
		ClassTrhee c3 = new ClassTrhee();
		ClassFour c4 = new ClassFour();
		ClassFive c5 = new ClassFive();

		c1.method();
		c2.method();
		c3.method();
		c4.method();
		c5.method();

		logger.fatal("Fatal message");
		logger.warn("Warn message");
	}
	
	private static void method1() {
		logger.trace("Log message 1");
		
	}

	private static void method2() {
		logger.debug("Log message 2");
		
	}
	
	private static void method3() {
		logger.info("Log message 3");
		
	}
	
	private static void method4() {
		logger.warn("Log message 4");
		
	}
	
	private static void method5() {
		logger.error("Log message 5");
		
	}
}
