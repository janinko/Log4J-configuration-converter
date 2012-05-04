package cz.muni.fi.pb138.log4jconverter;

public class ParseException extends Exception {

	private static final long serialVersionUID = 1L;

	public ParseException() {
		super();
	}

	public ParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParseException(String message) {
		super(message);
	}

	public ParseException(Throwable cause) {
		super(cause);
	}

}
