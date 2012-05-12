package cz.muni.fi.pb138.log4jconverter;


public class Config {
	String inputFile = null;
	String outputFile = null;
	InputLoader.Type outputType = null;
	InputLoader.Type inputType = null;
	
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
				}else if(args.equals("output-type") && i < args.length){
					outputType = parseType(args[i++]);
				}else if(args.equals("input-type") && i < args.length){
					inputType = parseType(args[i++]);
				}else if(args.equals("help") && i < args.length){
					printHelp();
				}
			}else if(arg.startsWith("-")){	//SHORT
				arg = arg.substring(1);
				
				if(arg.equals("i") && i < args.length){
					inputFile = args[i++];
				}else if(args.equals("o") && i < args.length){
					outputFile = args[i++];
				}else if(args.equals("T") && i < args.length){
					outputType = parseType(args[i++]);
				}else if(args.equals("t") && i < args.length){
					inputType = parseType(args[i++]);
				}else if(args.equals("h") && i < args.length){
					printHelp();
				}
			}
		}
	}

	private void printHelp() {
		System.out.println("log4j configuration converter");
		System.out.println("Convert log4j configuration from Properties file to XML or vice versa");
		System.out.println();
		System.out.println("Arguments:");
		System.out.println("  -o FILE --output FILE       Generated configutation will be written to file FILE.");
		System.out.println("                              If not set, configuration will be written to standart output.");
		System.out.println("  -i FILE --input FILE        Configuration will be read from file FILE.");
		System.out.println("                              If not set, configuration will be read from standart input.");
		System.out.println("  -t TYPE --input-type TYPE   Specify the input file type. If not set,");
		System.out.println("                              converter will try to determine file type automaticaly.");
		System.out.println("  -T TYPE --output-type TYPE  Specify the output file type. If not set,");
		System.out.println("                              converter will output the other one.");
		System.out.println();
		System.out.println("TYPE can be XML or Properties.");
		System.exit(0);		
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
