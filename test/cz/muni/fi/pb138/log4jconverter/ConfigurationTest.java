package cz.muni.fi.pb138.log4jconverter;

import org.junit.Test;

import junit.framework.TestCase;

public class ConfigurationTest extends TestCase {
	
	@Test
	public void testAppenders(){
		Configuration conf = new Configuration();

		Appender a1 = conf.getAppender("Appender1");
		assertNotNull(a1);
		
		Appender a2 = conf.getAppender("Appender2");
		assertNotNull(a2);
		
		Appender a3 = conf.getAppender("Appender3");
		assertNotNull(a3);

		assertSame(a1, conf.getAppender("Appender1"));
		assertSame(a2, conf.getAppender("Appender2"));
		assertSame(a3, conf.getAppender("Appender3"));
	}

}
