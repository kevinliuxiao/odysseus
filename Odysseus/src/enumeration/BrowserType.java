package enumeration;

import exception.InvalidInputException;

public enum BrowserType 
{
	CHROME("Chrome"),
	INTERNET_EXPLORER("Internet Explorer"),
	FIREFOX("Firefox"),
	SAFARI("Safari");
	
	private final String text;
	
	BrowserType(String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public static BrowserType fromString(String key) throws InvalidInputException {
		
		if (key != null) {
	      for (BrowserType a : BrowserType.values()) {
	        if (key.equalsIgnoreCase(a.text)) {
	          return a;
	        }
	      }
	    }
	    throw new InvalidInputException("", key);
	  }

}
