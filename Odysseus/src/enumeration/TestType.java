package enumeration;

import exception.InvalidInputException;

public enum TestType 
{
	ADMIN("admin"),
	INTERNAL_ADMIN("internal_admin");
	
	private final String text;
	
	TestType(String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public static TestType fromString(String key) throws InvalidInputException {
		
		if (key != null) {
	      for (TestType a : TestType.values()) {
	        if (key.equalsIgnoreCase(a.text)) {
	          return a;
	        }
	      }
	    }
	    throw new InvalidInputException("", key);
	  }
}