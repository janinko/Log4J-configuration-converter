package cz.muni.fi.pb138.log4jconverter;

import java.io.Writer;
import java.util.HashSet;
import java.util.HashMap;

public class Configuration implements AbstractModel{
	RootLogger rootLogger;
	HashSet<Renderer> renderers;
	HashMap<String,Appender> appenders;
	
	

	@Override
	public void printXML(Writer w) {
		// TODO Auto-generated method stub
	}

	@Override
	public void printProperties(Writer w) {
		// TODO Auto-generated method stub
	}

	
}
