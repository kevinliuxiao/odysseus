package enumeration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import exception.InvalidInputException;

public enum ArtezHost {

	//admin sites
	
	QA_MOBILE("aeqa.artez.com"),
	QA("adminqa.artez.com"),
	SANDBOX("adminrc.artez.com"),
	QA_2012("adminqa2012a.artezhq.com"),
	DEV_2012("admindev2012a.artezhq.com"),
	QA_2012_LOADBALANCED("adminqa.artezhq.com"),
	
	//Personal sites
	
	QA_MOBILE_PERSONAL_SITE("meqa.artez.com"),
	QA_PERSONAL_SITE("unsecureqa.artez.com"),
	SANDBOX_PERSONAL_SITE("myrc.artez.com"),
	QA_2012_PERSONAL_SITE("myqa2012a.artezhq.com"),
	DEV_2012_PERSONAL_SITE("mydev2012a.artezhq.com"),
	QA_2012_LOADBALANCED_PERSONAL_SITE("myqa.artezhq.com"),

	//Event sites
	
	QA_MOBILE_EVENT_SITE("seqa.artez.com"),
	QA_EVENT_SITE("secureqa.artez.com"),
	SANDBOX_EVENT_SITE("securerc.artez.com"),
	QA_2012_EVENT_SITE("secureqa2012a.artezhq.com"),
	DEV_2012_EVENT_SITE("securedev2012a.artezhq.com"),
	QA_2012_LOADBALANCED_EVENT_SITE("secureqa.artezhq.com"),
	
	//Internal admin sites
	
	QA_MOBILE_INTERNAL_ADMIN_SITE("iamqamobile.artez.com"),
	QA_INTERNAL_ADMIN_SITE("iamqa.artez.com"),
	SANDBOX_INTERNAL_ADMIN_SITE("iamrc.artez.com"),
	QA_2012_INTERNAL_ADMIN_SITE("iamqa2012a.artezhq.com"),
	DEV_2012_INTERNAL_ADMIN_SITE("iamdev2012a.artezhq.com"),
	QA_2012_LOADBALANCED_INTERNAL_ADMIN_SITE("iamqa.artezhq.com");
	
	
	private final String text;
	
	ArtezHost(String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
	
	public static ArtezHost fromString(String text) throws InvalidInputException {
		
		if (text != null) {
	      for (ArtezHost a : ArtezHost.values()) {
	        if (text.equals(a.text)) {
	          return a;
	        }
	      }
	    }
	    throw new InvalidInputException(text);
	  }
	
//	public static String converToArtezURL(String url) throws InvalidInputException
//	{
//		if (url != null) {
//	      for (ArtezHost a : ArtezHost.values()) {
//	        if (url.contains(a.text)) {
//	        	
//				try 
//				{
//		        	URL aURL = new URL(url);
///*					
//				    System.out.println("protocol = " + aURL.getProtocol()); //http
//				    System.out.println("authority = " + aURL.getAuthority()); //example.com:80
//				    System.out.println("host = " + aURL.getHost()); //example.com
//				    System.out.println("port = " + aURL.getPort()); //80
//				    System.out.println("path = " + aURL.getPath()); //  /docs/books/tutorial/index.html
//				    System.out.println("query = " + aURL.getQuery()); //name=networking
//				    System.out.println("filename = " + aURL.getFile()); ///docs/books/tutorial/index.html?name=networking
//				    System.out.println("ref = " + aURL.getRef()); //DOWNLOADING
//*/				    
//				    return url.replace(aURL.getHost(), a.text).trim().toString();
//				} 
//				catch (MalformedURLException e) 
//				{
//				    throw new InvalidInputException(url);
//				}
//	        }
//	      }
//	    }
//		return url;
//	}
}
