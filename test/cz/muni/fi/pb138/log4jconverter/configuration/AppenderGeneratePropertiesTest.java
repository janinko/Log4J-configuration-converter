package cz.muni.fi.pb138.log4jconverter.configuration;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

public class AppenderGeneratePropertiesTest {
	private Appender a;
	private Properties p;
	private String appenderName="A1";
	private String keyprefix="log4j.appender."+appenderName;

    @Before
    public void setUp() {
    	a = new Appender(appenderName);
    	p = new Properties();
    }

	@Test
	public void testAppenderClassName() {
		String classname = "org.apache.log4j.ConsoleAppender";
		a.setClassName(classname);

		a.generateProperties(p);
		
		assertTrue(p.containsKey(keyprefix));
		assertEquals(classname, p.getProperty(keyprefix));
	}

	@Test
	public void testAppenderLayout() {
		String layoutClass = "org.apache.log4j.PatternLayout";
		String paramName = "ConversionPattern";
		String paramValue = "%d %-5p %c @ %m%n";
		
		Layout l = new Layout();
		l.setClassName(layoutClass);
		l.addParam(paramName, paramValue);
		a.setLayout(l);

		a.generateProperties(p);
		
		assertTrue(p.containsKey(keyprefix+".layout"));
		assertEquals(layoutClass, p.getProperty(keyprefix+".layout"));
		assertTrue(p.containsKey(keyprefix+".layout."+paramName));
		assertEquals(paramValue, p.getProperty(keyprefix+".layout."+paramName));
	}

	@Test
	public void testAppenderFilter() {
		String filter1Name = "01";
		String filter1Class = "org.apache.log4j.varia.StringMatchFilter";
		String param1Name1 = "StringToMatch";
		String param1Value1 = "PATTERN";
		String filter2Name = "02";
		String filter2Class = "org.apache.log4j.varia.LevelMatchFilter";
		String param2Name1 = "AcceptOnMatch";
		String param2Value1 = "false";
		String param2Name2 = "LevelToMatch";
		String param2Value2 = "WARN";

		Filter f1 = new Filter();
		f1.setName(filter1Name);
		f1.setClassName(filter1Class);
		f1.addParam(param1Name1, param1Value1);
		
		Filter f2 = new Filter();
		f2.setName(filter2Name);
		f2.setClassName(filter2Class);
		f2.addParam(param2Name1, param2Value1);
		f2.addParam(param2Name2, param2Value2);
		
		a.addFilter(f1);
		a.addFilter(f2);
		
		a.generateProperties(p);

		String filterPrefix = keyprefix+".filter."+filter1Name;
		assertTrue(p.containsKey(filterPrefix));
		assertEquals(filter1Class, p.getProperty(filterPrefix));
		assertTrue(p.containsKey(filterPrefix+"."+param1Name1));
		assertEquals(param1Value1, p.getProperty(filterPrefix+"."+param1Name1));
		
		filterPrefix = keyprefix+".filter."+filter2Name;
		assertTrue(p.containsKey(filterPrefix));
		assertEquals(filter2Class, p.getProperty(filterPrefix));
		assertTrue(p.containsKey(filterPrefix+"."+param2Name1));
		assertEquals(param2Value1, p.getProperty(filterPrefix+"."+param2Name1));
		assertTrue(p.containsKey(filterPrefix+"."+param2Name2));
		assertEquals(param2Value2, p.getProperty(filterPrefix+"."+param2Name2));
	}

	@Test
	public void testAppenderAddParameters() {
		String paramName1 = "File";
		String paramValue1 = "org.apache.ojb.log";
		String paramName2 = "Append";
		String paramValue2 = "false";
		String paramName3 = "MaxBackupIndex";
		String paramValue3 = "500";
		String paramName4 = "MaxFileSize";
		String paramValue4 = "1048576";
		
		a.addParam(paramName1, paramValue1);
		a.addParam(paramName2, paramValue2);
		a.addParam(paramName3, paramValue3);
		a.addParam(paramName4, paramValue4);
		
		a.generateProperties(p);

		assertTrue(p.containsKey(keyprefix+"."+paramName1));
		assertEquals(paramValue1, p.getProperty(keyprefix+"."+paramName1));
		assertTrue(p.containsKey(keyprefix+"."+paramName2));
		assertEquals(paramValue2, p.getProperty(keyprefix+"."+paramName2));
		assertTrue(p.containsKey(keyprefix+"."+paramName3));
		assertEquals(paramValue3, p.getProperty(keyprefix+"."+paramName3));
		assertTrue(p.containsKey(keyprefix+"."+paramName4));
		assertEquals(paramValue4, p.getProperty(keyprefix+"."+paramName4));
	}

}
