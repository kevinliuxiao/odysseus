package exception;

public class SeleniumException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9060933876740122106L;

	public SeleniumException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public SeleniumException(Throwable cause) {
		super(cause);
	}

}
