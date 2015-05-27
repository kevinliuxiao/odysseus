package exception;

public class InvalidPageException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 399987521545902001L;

	public InvalidPageException(String entry, String exp) {
		super("Invalid page for entry \"" + entry + "\" in the test case: " + ((exp != null) ? exp : "value not found"));
	}
	
	public InvalidPageException(String entry) {
		super("Invalid page: " + entry);
	}

}