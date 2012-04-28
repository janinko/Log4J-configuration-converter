package cz.muni.fi.pb138.log4jconverter;

import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;

public class Configuration implements AbstractModel{
	RootLogger rootLogger;
        RootCategory rootCategory;
	HashSet<Renderer> renderers;
	HashMap<String,Appender> appenders;
	HashMap<String,Logger> loggers;
        HashMap<String,Category> categoryes;

	@Override
	public void printXML(Writer w) {
		// TODO Auto-generated method stub
	}

	@Override
	public void printProperties(Writer w) {
		// TODO Auto-generated method stub
	}

	
}
