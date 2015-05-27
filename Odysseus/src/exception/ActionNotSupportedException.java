package exception;

import enumeration.ActionType;
import enumeration.PageType;

public class ActionNotSupportedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3586262593614714809L;

	public ActionNotSupportedException(PageType pageType, ActionType actionType) {
		super("Operation '" + actionType.toString() + "' is not supported for pageType: '" + pageType + "' ");
	}
	
	public ActionNotSupportedException(String text) {
		super(text);
	}


}
