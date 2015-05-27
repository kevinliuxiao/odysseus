package exception;

import java.util.ArrayList;
import java.util.List;

import page.Page;

public class E2RMErrorException extends Exception {

	private static final long serialVersionUID = 5830829862991409419L;
	/**
	 * 
	 */

	private List<String> errors;
	private Page errorLocation;
	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public Page getErrorLocation() {
		return errorLocation;
	}

	public void setErrorLocation(Page errorLocation) {
		this.errorLocation = errorLocation;
	}

	public E2RMErrorException(List<String> errors, Page errorLocation) {
		
		if (errors == null) {
			this.errors = new ArrayList<String>();
		} else {
			this.errors = errors;
		}
		this.errorLocation = errorLocation;
	}
}
