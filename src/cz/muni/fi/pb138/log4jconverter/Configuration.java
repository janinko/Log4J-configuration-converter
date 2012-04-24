package cz.muni.fi.pb138.log4jconverter;

import java.io.Writer;
import java.util.HashSet;

public class Configuration implements AbstractModel{
	RootLogger rootLogger;
	HashSet<Renderer> renderers;
	HashSet<Appender> appenders;
	
	

	@Override
	public void printXML(Writer w) {
		// TODO Auto-generated method stub
	}

	@Override
	public void printProperties(Writer w) {
		// TODO Auto-generated method stub
	}

	
}
