package exception;

public class InvalidReportFileExtensionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2722096150107418368L;

	public InvalidReportFileExtensionException(String entry) {
		super("Invalid file extension: Expected: (.xlsx/.xls)   Actual: " + entry);
	}

}