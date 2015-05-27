package exception;

public class InvalidInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2722096150107418368L;

	public InvalidInputException(String entry, String exp) {
		super("Invalid input for entry \"" + entry + "\" in the test case: " + ((exp != null) ? exp : "value not found"));
	}
	
	public InvalidInputException(String msg) {
		super(msg);
	}

}
