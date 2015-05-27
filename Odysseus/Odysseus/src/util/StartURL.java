package util;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import enumeration.ActionType;
import enumeration.ArtezInstance;
import enumeration.PageType;
import enumeration.ArtezHost;
import enumeration.TestType;
import exception.InvalidInputException;

public class StartURL 
{
	
	public static void start(WebDriver driver, ArtezInstance instance, TestType testType) throws InvalidInputException {	
		
		switch (testType) 
		{		
			case ADMIN:
				runAdmin(driver, instance);
				break;
			case INTERNAL_ADMIN:
				runInternalAdmin(driver, instance);
				break;
			default:		
				throw new InvalidInputException(testType.toString() + " does not exist in StartURL");
		}
	}
	
	private static void runAdmin(WebDriver driver, ArtezInstance instance) throws InvalidInputException
	{
		switch (instance) 
		{		
			case QA_MOBILE:
				driver.get("https://" + ArtezHost.QA_MOBILE.toString());
				break;
			case QA:
				driver.get("https://" + ArtezHost.QA.toString());
				break;
			case SANDBOX:
				driver.get("https://" + ArtezHost.SANDBOX.toString());
				break;
			case QA_2012:
				driver.get("https://" + ArtezHost.QA_2012.toString());
				break;
			case DEV_2012:
				driver.get("https://" + ArtezHost.DEV_2012.toString());
				break;
			case QA_2012_LOADBALANCED:
				driver.get("https://" + ArtezHost.QA_2012_LOADBALANCED.toString());
				break;
			default:		
				throw new InvalidInputException(instance + " does not exist in StartURL");
		}
	}
	
	private static void runInternalAdmin(WebDriver driver, ArtezInstance instance) throws InvalidInputException
	{
		switch (instance) 
		{		
			case QA_MOBILE:
				driver.get("https://" + ArtezHost.QA_MOBILE_INTERNAL_ADMIN_SITE.toString());
				break;
			case QA:
				driver.get("https://" + ArtezHost.QA_INTERNAL_ADMIN_SITE.toString());
				break;
			case SANDBOX:
				driver.get("https://" + ArtezHost.SANDBOX_INTERNAL_ADMIN_SITE.toString());
				break;
			case QA_2012:
				driver.get("https://" + ArtezHost.QA_2012_INTERNAL_ADMIN_SITE.toString());
				break;
			case DEV_2012:
				driver.get("https://" + ArtezHost.DEV_2012_INTERNAL_ADMIN_SITE.toString());
				break;
			case QA_2012_LOADBALANCED:
				driver.get("https://" + ArtezHost.QA_2012_LOADBALANCED_INTERNAL_ADMIN_SITE.toString());
				break;
			default:		
				throw new InvalidInputException(instance + " does not exist in StartURL");
		}
	}
}
