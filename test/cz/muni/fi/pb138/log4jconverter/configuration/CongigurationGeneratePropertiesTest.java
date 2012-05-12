package cz.muni.fi.pb138.log4jconverter.configuration;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import cz.muni.fi.pb138.log4jconverter.PropertiesParser;
import cz.muni.fi.pb138.log4jconverter.configuration.Level.Levels;

public class CongigurationGeneratePropertiesTest {
	private Configuration c;
	private static String PREFIX = PropertiesParser.PREFIX + ".";
	

	@Before
	public void setUp() throws Exception {
		c = new Configuration();
	}

	@Test
	public void testDebug() {
		c.setDebug(true);
		Properties p = c.generateProperties();
		assertTrue(p.containsKey(PREFIX + PropertiesParser.DEBUG));
		assertEquals("true",p.getProperty((PREFIX + PropertiesParser.DEBUG)));
	}

	@Test
	public void testTreshold() {
		c.setThreshold(Configuration.Threshold.debug);
		Properties p = c.generateProperties();
		assertTrue(p.containsKey(PREFIX + PropertiesParser.THRESHOLD));
		assertEquals( "DEBUG",p.getProperty(PREFIX + PropertiesParser.THRESHOLD) );
	}

	@Test
	public void testLoggerFactory() {
		String classname = "org.example.Factory";
		LoggerFactory lf = new LoggerFactory();
		lf.setClassName(classname);
		c.setLogFactory(lf);
		Properties p = c.generateProperties();
		assertTrue(p.containsKey(PREFIX + PropertiesParser.LOGGER_FACTORY));
		assertEquals(classname,p.getProperty((PREFIX + PropertiesParser.LOGGER_FACTORY)));
	}

	@Test
	public void testAppenders() {
		c.getAppender("A1").setClassName("org.a1");
		c.getAppender("A2").setClassName("org.a2");
		c.getAppender("A3").setClassName("org.a3");
		
		Properties p = c.generateProperties();

		assertTrue(p.containsKey(PREFIX + PropertiesParser.APPENDER + ".A1"));
		assertEquals("org.a1",p.getProperty((PREFIX + PropertiesParser.APPENDER + ".A1")));
		assertTrue(p.containsKey(PREFIX + PropertiesParser.APPENDER + ".A2"));
		assertEquals("org.a2",p.getProperty((PREFIX + PropertiesParser.APPENDER + ".A2")));
		assertTrue(p.containsKey(PREFIX + PropertiesParser.APPENDER + ".A3"));
		assertEquals("org.a3",p.getProperty((PREFIX + PropertiesParser.APPENDER + ".A3")));
	}

	@Test
	public void testLoggers() {
		Level lvl = new Level();
		lvl.setValues(Levels.DEBUG);
		c.getLogger("org.l1").setLevel(lvl);
		c.getLogger("org.l2").setLevel(lvl);
		c.getLogger("org.l3").setLevel(lvl);
		
		Properties p = c.generateProperties();

		assertTrue(p.containsKey(PREFIX + PropertiesParser.LOGGER + ".org.l1"));
		assertEquals("DEBUG",p.getProperty((PREFIX + PropertiesParser.LOGGER + ".org.l1")));
		assertTrue(p.containsKey(PREFIX + PropertiesParser.LOGGER + ".org.l2"));
		assertEquals("DEBUG",p.getProperty((PREFIX + PropertiesParser.LOGGER + ".org.l2")));
		assertTrue(p.containsKey(PREFIX + PropertiesParser.LOGGER + ".org.l3"));
		assertEquals("DEBUG",p.getProperty((PREFIX + PropertiesParser.LOGGER + ".org.l3")));
	}

	@Test
	public void testRootLogger() {
		Root r = new Root();
		Level lvl = new Level();
		lvl.setValues(Levels.DEBUG);
		r.setLevel(lvl);
		r.addAppenderRef("A1");
		
		c.setRoot(r);
		
		Properties p = c.generateProperties();

		assertTrue(p.containsKey(PREFIX + PropertiesParser.ROOT_LOGGER));
		assertEquals("DEBUG, A1",p.getProperty((PREFIX + PropertiesParser.ROOT_LOGGER)));
	}

}
