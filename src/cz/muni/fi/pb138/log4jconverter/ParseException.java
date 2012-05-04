package cz.muni.fi.pb138.log4jconverter;

public class ParseExceception extends Exception {

	private static final long serialVersionUID = 1L;

	public ParseExceception() {
		super();
	}

	public ParseExceception(String message, Throwable cause) {
		super(message, cause);
	}

	public ParseExceception(String message) {
		super(message);
	}

	public ParseExceception(Throwable cause) {
		super(cause);
	}

}
