package enumeration;

import exception.InvalidInputException;

public enum ArtezInstance {
	
	QA_MOBILE("aeqa.artez.com"),
	QA("adminqa.artez.com"),
	SANDBOX("adminrc.artez.com"),
	QA_2012("adminqa2012a.artezhq.com"),
	DEV_2012("admindev2012a.artezhq.com"),
	QA_2012_LOADBALANCED("adminqa.artezhq.com");
	
	private final String text;
	
	ArtezInstance(String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public static ArtezInstance fromString(String text) throws InvalidInputException {
		
		if (text != null) {
	      for (ArtezInstance a : ArtezInstance.values()) {
	        if (text.equals(a.text)) {
	          return a;
	        }
	      }
	    }
	    throw new InvalidInputException(text);
	  }
}
