package page;

import java.util.List;

import org.openqa.selenium.WebDriver;

import page.impl.Login;
import util.WebUtils;
import util.XMLFileReader;
import enumeration.ArtezHost;
import enumeration.ArtezInstance;
import enumeration.PageType;
import enumeration.TestType;
import exception.E2RMErrorException;
import exception.InvalidInputException;
import exception.SeleniumException;
import exception.URLException;
import exception.VerificationException;

/**
 * @author czhang
 * This class represents a start page which is the first page for any action.
 * Any action must start with a start() call to a the start page for the action.
 */
public abstract class StartPage extends Page {
		
	public StartPage(XMLFileReader params, int stepIndex) throws InvalidInputException {
		super(params,stepIndex);
	}

	protected abstract void start(WebDriver driver) throws InvalidInputException;
	
	public void startPage(WebDriver driver) throws E2RMErrorException, URLException, InvalidInputException, SeleniumException, VerificationException {
		
		start(driver);
		WebUtils.waitForPageLoad(driver, this.getClass().getSimpleName());
		
		if (!driver.getCurrentUrl().toLowerCase().contains("/" + this.getClass().getSimpleName().toLowerCase() + ".aspx")) {
			List<String> errors = checkPageError(driver);
			if (errors != null) {
				throw new E2RMErrorException(errors, this);
			} else {
				throw new URLException(this.getClass().getSimpleName(), driver.getCurrentUrl());
			}
		}
	}
	
	public static Page makeStartPage(XMLFileReader params, PageType type, int stepIndex, TestType testType) throws InvalidInputException {	
		
		switch (testType) 
		{		
			case ADMIN:
				return new Login(params, stepIndex);
			case INTERNAL_ADMIN:
				return new Login(params, stepIndex);
			default:		
				throw new InvalidInputException(testType.toString() + " does not exist in StartPage");
		}
	}
//	
//	private static void runAdmin(WebDriver driver, ArtezInstance instance) throws InvalidInputException
//	{
//		switch (instance) 
//		{		
//			case QA_MOBILE:
//				url = "https://" + ArtezHost.QA_MOBILE.toString();
//				break;
//			case QA:
//				url = "https://" + ArtezHost.QA.toString();
//				break;
//			case SANDBOX:
//				url = "https://" + ArtezHost.SANDBOX.toString();
//				break;
//			case QA_2012:
//				url = "https://" + ArtezHost.QA_2012.toString();
//				break;
//			case DEV_2012:
//				url = "https://" + ArtezHost.DEV_2012.toString();
//				break;
//			case QA_2012_LOADBALANCED:
//				url = "https://" + ArtezHost.QA_2012_LOADBALANCED.toString();
//				break;
//			default:		
//				throw new InvalidInputException(instance + " does not exist in StartURL");
//		}
//	}
//	
//	private static void runInternalAdmin(WebDriver driver, ArtezInstance instance) throws InvalidInputException
//	{
//		switch (instance) 
//		{		
//			case QA_MOBILE:
//				url = "https://" + ArtezHost.QA_MOBILE_INTERNAL_ADMIN_SITE.toString();
//				break;
//			case QA:
//				url = "https://" + ArtezHost.QA_INTERNAL_ADMIN_SITE.toString();
//				break;
//			case SANDBOX:
//				url = "https://" + ArtezHost.SANDBOX_INTERNAL_ADMIN_SITE.toString();
//				break;
//			case QA_2012:
//				url = "https://" + ArtezHost.QA_2012_INTERNAL_ADMIN_SITE.toString();
//				break;
//			case DEV_2012:
//				url = "https://" + ArtezHost.DEV_2012_INTERNAL_ADMIN_SITE.toString();
//				break;
//			case QA_2012_LOADBALANCED:
//				url = "https://" + ArtezHost.QA_2012_LOADBALANCED_INTERNAL_ADMIN_SITE.toString();
//				break;
//			default:		
//				throw new InvalidInputException(instance + " does not exist in StartURL");
//		}
//	}
}
