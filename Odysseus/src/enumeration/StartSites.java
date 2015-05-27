/*

package enumeration;

import exception.InvalidInputException;

public enum StartSites {

	//admin sites
	
	QA_MOBILE("https://aeqa.artez.com"),
	QA("https://adminqa.artez.com"),
	SANDBOX("https://adminrc.artez.com"),
	QA_2012("https://adminqa2012a.artezhq.com"),
	DEV_2012("https://admindev2012a.artezhq.com"),
	E2RM("admin.e2rm.com"),
	QA_2012_LOADBALANCED("https://adminqa.artezhq.com"),
	
	//Internal admin sites
	
	QA_MOBILE_INTERNAL_ADMIN_SITE("https://iamqamobile.artez.com"),
	QA_INTERNAL_ADMIN_SITE("https://iamqa.artez.com"),
	SANDBOX_INTERNAL_ADMIN_SITE("https://iamrc.artez.com"),
	QA_2012_INTERNAL_ADMIN_SITE("https://iamqa2012a.artezhq.com"),
	DEV_2012_INTERNAL_ADMIN_SITE("https://iamdev2012a.artezhq.com"),
	E2RM("admin.e2rm.com"),
	QA_2012_LOADBALANCED_INTERNAL_ADMIN_SITE("https://iamqa.artezhq.com");
	
	
	private final String text;
	
	StartSites(String text) {
		this.text = text;
	}

	public static StartSites fromString(String text) throws InvalidInputException {
		
		if (text != null) {
	      for (StartSites a : StartSites.values()) {
	        if (a.text.contains(text)) {
	          return a;
	        }
	      }
	    }
	    throw new InvalidInputException(text);
	  }
}
*/