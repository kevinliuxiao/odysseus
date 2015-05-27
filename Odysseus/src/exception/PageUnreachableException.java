package exception;

import enumeration.PageType;

public class PageUnreachableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9017506167422446538L;

	public PageUnreachableException(String currentPage, PageType type) {
		super("Cannot reach: '" + type.urlKeyword() + "' page from: '" + currentPage + "' page");
	}

	public PageUnreachableException(String message) {
		super(message);
	}

}
