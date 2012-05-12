package cz.muni.fi.pb138.log4jconverter;


public class Config {
	String inputFile = null;
	String outputFile = null;
	InputLoader.Type outputType = null;
	
	public Config(String args[]){
		int i=0;
		while(i < args.length){
			String arg = args[i++];
			
			if(arg.startsWith("--")){	//LONG
				arg = arg.substring(2);
				
				if(arg.equals("input") && i < args.length){
					inputFile = args[i++];
				}else if(args.equals("output") && i < args.length){
					outputFile = args[i++];
				}else if(args.equals("type") && i < args.length){
					outputType = parseType(args[i++]);
				}
			}else if(arg.startsWith("-")){	//SHORT
				arg = arg.substring(1);
				
				if(arg.equals("i") && i < args.length){
					inputFile = args[i++];
				}else if(args.equals("o") && i < args.length){
					outputFile = args[i++];
				}else if(args.equals("t") && i < args.length){
					outputType = parseType(args[i++]);
				}
			}
		}
	}

	private InputLoader.Type parseType(String type) {
		if(type.toUpperCase().equals("XML")){
			return InputLoader.Type.XML;
		}else if(type.toLowerCase().equals("properties")){
			return InputLoader.Type.PROPERTIES;
		}
		return null;
	}

}
