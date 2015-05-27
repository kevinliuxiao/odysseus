package exception;

public class URLException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6345835052667585485L;

	public URLException(String expectedURL, String actualURL) {
		super("The browser arrives at an unexpected page. Expected page: " + expectedURL + " Actual page: " + actualURL);
	}

}
