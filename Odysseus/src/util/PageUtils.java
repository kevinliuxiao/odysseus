package util;

import java.awt.AWTException; 
import java.awt.Robot; 
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent; 

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import runner.TestRunner;

import enumeration.OperationType;
import enumeration.ResultType;
import exception.InvalidInputException;
import exception.SeleniumException;
import exception.URLException;
import exception.VerificationException;


public class PageUtils
{
	
	public static final String TOGGLE_TRUE = "true";
	public static final String TOGGLE_FALSE = "false";
	private static int SnapCount = 1;
	private static int waitTimeInSecs = 30;
	private static int pollingwaitTime = 3000;  // millisecs
	private static String fileName;
	

	/**
	 * This function performs the actual interactions with web elements on the webpages through Selenium calls.
	 * For all components other than Verifiers, this is the only function you need to use for any web interactions.
	 * See Verifiers for other use of Selenium.
	 * 
	 * @param driver Current browser.
	 * @param opType The type of interaction. (CLICK, TYPE(enter text), SELECT(from a list), TOGGLE(a checkbox))
	 * @param identifier The web element to be operated on.
	 * @param keys The value of operation for TYPE, SELECT and TOGGLE.
	 */

	public static void performInteraction(WebDriver driver, OperationType opType, String description, By identifier, String keys) {
		// Don't do anything if the value is empty.
		if ((!(OperationType.CLICK.equals(opType) ||OperationType.CLICK_VERIFY.equals(opType) || OperationType.ALERT_CLICK.equals(opType))) && (keys == null || "".equals(keys))) {
			return;
		}
		
		boolean result = false;
				
		switch (opType) {
		case TYPE:
			System.out.println("Type " + keys + " in " + identifier.toString() + "\tCurrent page: " + driver.getCurrentUrl());
			break;
		case APPEND:
			System.out.println("Append " + keys + " in " + identifier.toString() + "\tCurrent page: " + driver.getCurrentUrl());
			break;
		case CLICK:
			System.out.println("Clicking " + identifier.toString() +  "\tCurrent page: " + driver.getCurrentUrl());
			break;
		case CLICK_VERIFY:
			System.out.println("Clicking" + identifier.toString() + " verifying " + keys +  "\tCurrent page: " + driver.getCurrentUrl());
			break;
		case ALERT_CLICK:
			System.out.println("Clicking " + identifier.toString() + "\tCurrent page: " + driver.getCurrentUrl());
			break;
		case SELECT:
			System.out.println("Select " + keys + " for " + identifier.toString() + "\tCurrent page: " + driver.getCurrentUrl());
			break;
		case SELECT_INDEX:
			System.out.println("Select index" + keys + " for " + identifier.toString() + "\tCurrent page: " + driver.getCurrentUrl());
			break;
		case TOGGLE:
			System.out.println("Toggle " + identifier.toString() + " to " + keys + "\tCurrent page: " + driver.getCurrentUrl());
			break;
		case FILEUPLOAD:
			System.out.println("FILEUPLOAD " + identifier.toString() + " to " + keys + "\tCurrent page: " + driver.getCurrentUrl());
			break;
		default:
			break;
		}
		try {
			WebElement e;
/*
			if(opType.equals(OperationType.SELECT) || opType.equals(OperationType.SELECT_INDEX))
			{
				e =driver.findElement(identifier);
			}
			else
			{
*/
				WaitForJavascript(driver);
				e = waitForElementToBeDisplayedAndClickable(driver, identifier);
//			}

			
			if (e == null) {
				throw new WebDriverException("Cannot find " + identifier.toString());
			}
			
			switch (opType) {
			case TYPE:
				e.clear();
				e.sendKeys(keys);
				break;
			case APPEND:
				e.sendKeys(keys);
				break;
			case CLICK:
				try 
				{
					((JavascriptExecutor) driver).executeScript("$(arguments[0].click())",e);					
				} 
				catch (WebDriverException exception) 
				{
					e.click();
				}
				break;
			case CLICK_VERIFY:
				try 
				{
					((JavascriptExecutor) driver).executeScript("$(arguments[0].click())",e);
					result = driver.getPageSource().contains(keys);
				} 
				catch (WebDriverException exception) 
				{
					e.click();
					result = driver.getPageSource().contains(keys);
				}
				break;
			case ALERT_CLICK:
				try
				{
					e.click();
					waitForAlert(driver, identifier);
					driver.switchTo().alert().accept();
				}
			    catch (WebDriverException exception) {
					((JavascriptExecutor) driver).executeScript("$(arguments[0].click())",e);	
					waitForAlert(driver, identifier);
					driver.switchTo().alert().accept();
			    }
				break;
			case SELECT:
				
				WaitAndSelect(driver, e, keys);
				/*
			  	Select providerSelect = new Select(e);
				providerSelect.selectByVisibleText(keys);					
			*/
				break;
			case SELECT_INDEX:
				Select providerIndexSelect = new Select(e);
				try {
					providerIndexSelect.selectByIndex(Integer.parseInt(keys));
				} catch (NumberFormatException nfe) {
					throw new WebDriverException("Cannot find index " + keys);
				}
				break;
			case TOGGLE:
				if ((TOGGLE_TRUE.equals(keys) && !e.isSelected()) || (TOGGLE_FALSE.equals(keys) && e.isSelected())) {
					try 
					{
						((JavascriptExecutor) driver).executeScript("$(arguments[0].click())",e);					
					} 
					catch (WebDriverException exception) 
					{
						e.click();
					}
				}
				break;
			case FILEUPLOAD:
				try
				{
					File fileImage = new File("testFiles/" + keys);
					String filePath = fileImage.getAbsolutePath();
					driver.findElement(identifier).sendKeys(filePath);
				
/*
					if(keys != null && !keys.isEmpty())
					{
						fileName = keys;
						WebUtils.performInteraction(driver, OperationType.CLICK, identifier, "");
	
						File fileImage = new File("testFiles/" + fileName);
						String filePath = fileImage.getAbsolutePath();
						setClipboardData(filePath);
						
						Robot robot = new Robot();
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyPress(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_V);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						robot.delay(5000);
						robot.keyPress(KeyEvent.VK_ENTER);
						robot.keyRelease(KeyEvent.VK_ENTER);
					}
*/
				}
				catch(Exception exp)
				{
					System.out.println("Image not found in getImage method");
				}

				break;
				default:
					break;
			}
		}
		catch (WebDriverException exception) {
			String snapName = TakeScreenShot(driver);
			TestRunner.logger.addLogInfo(opType, description, identifier.toString(), keys, driver.getCurrentUrl(), ResultType.FAILURE, snapName);
			if(opType.equals(OperationType.CLICK_VERIFY) && result == false)
			{
				snapName = TakeScreenShot(driver);
				TestRunner.logger.addLogInfo(opType, description,  identifier.toString(), keys, driver.getCurrentUrl(), ResultType.FAILURE, snapName);
			}
			throw exception;

		}
		TestRunner.logger.addLogInfo(opType, description, identifier.toString(), keys, driver.getCurrentUrl(), ResultType.SUCCESS);	
		if(opType.equals(OperationType.CLICK_VERIFY) && result == true)
		{
			TestRunner.logger.addLogInfo(opType, description, identifier.toString(), keys, driver.getCurrentUrl(), ResultType.SUCCESS);	
		}		
	}
	
	
	/**
     * This method will set any parameter string to the system's clipboard.
     */
	private static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}


	/**
	 * Checks if the element is in the DOM and displayed.
	 * 
	 * @param driver - The driver object to use to perform this element search
	 * @param by - selector to find the element
	 * @return boolean
	 */
	public static boolean isElementDisplayed(WebDriver driver, By identifier) 
	{			   
		WebElement e = waitForElementToBeDisplayed(driver, identifier);
		if(e != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Checks if the element is in the DOM, regardless of being displayed or not.
	 * 
	 * @param driver - The driver object to use to perform this element search
	 * @param by - selector to find the element
	 * @return boolean
	 */
	public static boolean isElementPresentButMayNotBeDisplayed(WebDriver driver, By identifier) 
	{
		WebElement e = waitForElementPresent(driver, identifier);
		if(e != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Sends element text, if element is in the DOM and displayed.
	 * 
	 * @param driver - The driver object to use to perform this element search
	 * @param by - selector to find the element
	 * @return String
	 */
	public static String getDisplayedElementText(WebDriver driver, By identifier) 
	{
		WebElement e = waitForElementToBeDisplayed(driver, identifier);
		if (e == null) {
			throw new WebDriverException("Cannot find " + identifier.toString());
		}
		return e.getText();
	}
	
	/**
	 * Sends element text, if element is in the DOM but may not be displayed.
	 * 
	 * @param driver - The driver object to use to perform this element search
	 * @param by - selector to find the element
	 * @return String
	 */
	public static String getNonDisplayedElementText(WebDriver driver, By identifier) 
	{
		WebElement e = waitForElementPresent(driver, identifier);
		if (e == null) {
			throw new WebDriverException("Cannot find " + identifier.toString());
		}
		return e.getText();
	}
	
	/**
	 * Sends element value, if element is in the DOM and displayed.
	 * 
	 * @param driver - The driver object to use to perform this element search
	 * @param by - selector to find the element
	 * @return String
	 */
	public static String getDisplayedElementValue(WebDriver driver, By identifier) 
	{
		WebElement e = waitForElementToBeDisplayed(driver, identifier);
		if (e == null) {
			throw new WebDriverException("Cannot find " + identifier.toString());
		}
		return e.getAttribute("value");
	}
	
	/**
	 * Sends element value, if element is in the DOM but may not be displayed.
	 * 
	 * @param driver - The driver object to use to perform this element search
	 * @param by - selector to find the element
	 * @return String
	 */
	public static String getNonDisplayedElementValue(WebDriver driver, By identifier) 
	{
		WebElement e = waitForElementPresent(driver, identifier);
		if (e == null) {
			throw new WebDriverException("Cannot find " + identifier.toString());
		}
		return e.getAttribute("value");
	}
	
	private static WebElement findLabelButMayNotBeDisplayed(WebDriver driver, By identifier, String labelText) {
		List<WebElement> elementsList =  waitForListElementsPresent(driver, identifier);
		for (WebElement e : elementsList) {
			if (e.getText().equals(labelText)) {
				return e;
			}
		}
		return null;
	}

	public static String getLabelForMayNotBeDisplayed(WebDriver driver, By identifier, String labelText) {
		WebElement e = findLabelButMayNotBeDisplayed(driver, identifier, labelText);
		if (e == null) {
			throw new WebDriverException("Cannot find " + identifier.toString());
		}
		return e.getAttribute("for");
	}
		
	/**
	 * Sends element Href, if element is in the DOM and displayed.
	 * 
	 * @param driver - The driver object to use to perform this element search
	 * @param by - selector to find the element
	 * @return String
	 */
	public static String getDisplayedLinkHref(WebDriver driver, By identifier) {
		WebElement e = waitForElementToBeDisplayed(driver, identifier);
		if (e == null) {
			throw new WebDriverException("Cannot find " + identifier.toString());
		}
		return e.getAttribute("href");
	}
	
	/**
	 * Sends element Href, if element is in the DOM but may not be displayed.
	 * 
	 * @param driver - The driver object to use to perform this element search
	 * @param by - selector to find the element
	 * @return String
	 */
	public static String getNonDisplayedLinkHref(WebDriver driver, By identifier) {
		
		WebElement e = waitForElementPresent(driver, identifier);
		if (e == null) {
			throw new WebDriverException("Cannot find " + identifier.toString());
		}
		return e.getAttribute("href");
	}
	
	public static void clickLabel(WebDriver driver, String description,  By identifier, String labelText){
		try 
		{
			WebElement e = findLabelButMayNotBeDisplayed(driver, identifier, labelText);
			waitForElementToBeClickable(driver, e);
			e.click();
			TestRunner.logger.addLogInfo(OperationType.CLICK, description, identifier.toString(), "", driver.getCurrentUrl(), ResultType.SUCCESS);	

		} catch (WebDriverException | NullPointerException exception) {
			String snapName = TakeScreenShot(driver);
			TestRunner.logger.addLogInfo(OperationType.CLICK, description, identifier.toString(), "", driver.getCurrentUrl(), ResultType.FAILURE, snapName);
			throw exception;
		}

	}

	public static List<String> getLinks(WebDriver driver, By identifier) {
		List<WebElement> eList =  waitForListElementsPresent(driver, identifier);
		List<String> linkList = new ArrayList<String>();
		
		for (WebElement e : eList) {
			linkList.add(e.getAttribute("value"));
		}
		return linkList;
	}

	public static void switchFrames(WebDriver driver, String frame) 
	{
		try
		{
			nullifyImplicitWait(driver);
			  
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs, pollingwaitTime); 
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));		
			resetImplicitWait(driver);
		} catch (Exception ex) {
			throw new WebDriverException("Cannot find frame:" + frame);
		}
	}
	
	public static void switchFrames(WebDriver driver, By identifier) {
		try
		{
			nullifyImplicitWait(driver);
			  
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs, pollingwaitTime); 
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(identifier));		
			resetImplicitWait(driver);
		} catch (Exception ex) {
			throw new WebDriverException("Cannot find frame:" + identifier.toString());
		}
		
	}
	
	public static void switchWindows(WebDriver driver, String window) {
		driver.switchTo().window(window);		
	}
	
	public static void switchToPopup(WebDriver driver, String window) {
		Set<String> s = driver.getWindowHandles();
		
		Iterator<String> ite = s.iterator();
		 
		while(ite.hasNext()) {
		String popupHandle=ite.next().toString();
		 if(!popupHandle.contains(window)) {
		 	driver.switchTo().window(popupHandle);
		 }
		}
		
	}
	
	public static void switchToDefault(WebDriver driver) {
		driver.switchTo().defaultContent();
		
	}
	
	public static void closeAlert(WebDriver driver) {
	    
		Alert alert = driver.switchTo().alert();
	    alert.accept();
	     
	   /* if (!alert.getText().matches(text)) {
	    	throw new WebDriverException("Alert did not match expected text. Expected: " + text + "\nActual: " + alert.getText());
	    }*/

	  }
	
	public static void moveBack(WebDriver driver){
		driver.navigate().back();
	}	
	
	public static String getRegID(WebDriver driver) {
		String currentURL = driver.getCurrentUrl();
		String patternString = "RegistrationID=([^&]*)";
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(currentURL);
		
		if (matcher.find()) {
			String regID = matcher.group(1);
			return regID;
		}
		return null;
	}
	
	/**
	 * This function is used to keep results from pre-actions. For example, a donation action depends on an event creation action; so the event link from
	 * the event creation action can be store in the SAME params object in order for the donation action to use.
	 * 
	 * NOTE: This function may be used in any Pages or Verifiers since the places where the extra information appears may be different for each action/page.
	 * 
	 * @param params The object that stores all input for the entire test case.
	 * @param key The name of the new entry in <code>params</code>.
	 * @param value The value of the new entry in <code>params</code>.
	 */
	public static void addParam(Properties params, String key, String value) {
		if (params.containsKey(key)) {
			System.out.println("WARNING: Key " + key + " already exists. The original value was: " + (String) params.getProperty(key) + " Now: " + value); 
		}
		
		if (value == null)
			params.remove(key);
		else 
			params.put(key, value);
	}
	
	public static String TakeScreenShot(WebDriver driver) 
	{
		String snapName = null;
		try 
		{
			snapName = "screenshot_" + SnapCount + ".png";
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File logFile = new File("logs/Screenshots/", snapName);	
			logFile.createNewFile();
			FileUtils.copyFile(scrFile, logFile);
			SnapCount++;
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
		
		return snapName;	
	}
	
	/**
	 * This method is called by Page and StartPage class. It waits for a set time, until page is loaded
	 * 
	 * @param driver Current browser.
	 * @param nextPage The nextpage url 
	 * @throws URLException 
	 */
	public static void waitForPageLoad(WebDriver driver, final String nextPage) throws URLException
	{
		try
		{
			nullifyImplicitWait(driver);
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs, pollingwaitTime);			
		    ExpectedCondition e = new ExpectedCondition<Boolean>() 
		    {
		          public Boolean apply(WebDriver d) {
		        	  return (d.getCurrentUrl().toLowerCase().contains("/" + nextPage.toLowerCase() + ".aspx"));
		          }
		    };

		    wait.until(e);
			resetImplicitWait(driver);
	//		waitForJQueryProcessing(driver);
			WaitForJavascript(driver);
		}
		catch(Exception e)
		{
			throw new URLException(nextPage, driver.getCurrentUrl() + "after waiting " + waitTimeInSecs + " secs for page to load");
		}
	}
		
	public static String randomStringGenerator(int length) 
	{
	  SecureRandom random = new SecureRandom();

	    String rand = new BigInteger(130, random).toString(32);
	    
	    return "_" + rand.substring(0, length);
	}
	

	/***************************************************************************************************************
	 * *************************************************************************************************************
	 * *************************************************************************************************************
	 * *************************************************************************************************************
	 * *************************************************************************************************************
	 * *************************************************************************************************************
	 * *************************************************************************************************************
	 * *************************************************************************************************************
	 * *************************************************************************************************************
	 * *************************************************************************************************************
	*/
	
	private static WebElement waitForElementToBeDisplayedAndClickable(WebDriver driver, final By identifier) {
		WebElement element; 
		try{	
			nullifyImplicitWait(driver);
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs, pollingwaitTime); 
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
			if(element != null)
			{
				element = wait.until(ExpectedConditions.elementToBeClickable(identifier));
			}

			resetImplicitWait(driver);
			return element; 

		} catch (Exception e) {
			return null; 
		}
	}
	
	
	/**
	  * Wait for the element to be present in the DOM, and displayed on the page. 
	  * And returns the first WebElement using the given method.
	  * 
	  * @param WebDriver	The driver object to be used 
	  * @param By	selector to find the element
	  * @param int	The time in seconds to wait until returning a failure
	  *
	  * @return WebElement	the first WebElement using the given method, or null (if the timeout is reached)
	  */
	private static WebElement waitForElementToBeDisplayed(WebDriver driver, By identifier ) {
		WebElement element; 
		try{	
			nullifyImplicitWait(driver);
			WebDriverWait wait = new WebDriverWait(driver, 5, pollingwaitTime); 
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
			resetImplicitWait(driver);
			return element; //return the element	
		} catch (Exception e) {
			return null; 
		} 
	}
	
	/**
	  * Wait for the alert to be present 
	  * And returns the first WebElement using the given method.
	  * 
	  * @param WebDriver	The driver object to be used 
	  * @param By	selector to find the element
	  *
	  */
	public static void waitForAlert(WebDriver driver, By identifier) {
		try{	
//			nullifyImplicitWait(driver);
			  
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs, pollingwaitTime);
			wait.until(ExpectedConditions.alertIsPresent());		
//			resetImplicitWait(driver);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	/**
	  * Wait for the element to be present in the DOM, and displayed on the page and clickable. 
	  * And returns the first WebElement using the given method.
	  * 
	  * @param WebDriver	The driver object to be used 
	  * @param By	selector to find the element
	  * @param int	The time in seconds to wait until returning a failure
	  *
	  */
	
	public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
		try{	
			nullifyImplicitWait(driver);
			  
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs, pollingwaitTime);
			element = wait.until(ExpectedConditions.elementToBeClickable(element));		
			resetImplicitWait(driver);
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	
	
	

	/**
	  * Wait for the element to be present in the DOM, regardless of being displayed or not.
	  * And returns the first WebElement using the given method.
	  *
	  * @param WebDriver	The driver object to be used 
	  * @param By	selector to find the element
	  * @param int	The time in seconds to wait until returning a failure
	  * 
	  * @return WebElement	the first WebElement using the given method, or null (if the timeout is reached)
	  */
	public static WebElement waitForElementPresent(WebDriver driver, By identifier) {
		WebElement element; 
		try{
			nullifyImplicitWait(driver);
			
			WebDriverWait wait = new WebDriverWait(driver, 5, pollingwaitTime);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(identifier));
			
			resetImplicitWait(driver);
			return element; //return the element
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null; 
	}
	

	/**
	  * Wait for the List<WebElement> to be present in the DOM, regardless of being displayed or not.
	  * Returns all elements within the current page DOM. 
	  * 
	  * @param WebDriver	The driver object to be used 
	  * @param By	selector to find the element
	  * @param int	The time in seconds to wait until returning a failure
	  *
	  * @return List<WebElement> all elements within the current page DOM, or null (if the timeout is reached)
	  */
	public static List<WebElement> waitForListElementsPresent(WebDriver driver, final By identifier) {
		List<WebElement> elements; 
		try{	
			nullifyImplicitWait(driver);
			  
			WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs, pollingwaitTime);
			wait.until((new ExpectedCondition<Boolean>() {
	            @Override
	            public Boolean apply(WebDriver driverObject) {
	        		try {
	        			driverObject.findElements(identifier); 
	        			return true; 
	        		} catch (NoSuchElementException e) {
	        			return false;
	        		}
	            }
	        }));
			
			elements = driver.findElements(identifier); 
			resetImplicitWait(driver);
			return elements; //return the element	
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null; 
	}
		
	/**
	  * Wait for an element to appear on the refreshed web-page.
	  * And returns the first WebElement using the given method.
	  *
	  * This method is to deal with dynamic pages.
	  * 
	  * 
	  * @param WebDriver	The driver object to use to perform this element search
	  * @param locator	selector to find the element
	  * @param int	The time in seconds to wait until returning a failure
	  * 
	  * @return WebElement	the first WebElement using the given method, or null(if the timeout is reached)
	  * 
	  */
	 public static WebElement waitForElementRefresh(WebDriver driver, final By identifier) {
		WebElement element; 
		try{	
			nullifyImplicitWait(driver);
		        new WebDriverWait(driver, waitTimeInSecs, pollingwaitTime) {
		        }.until(new ExpectedCondition<Boolean>() {

		            @Override
		            public Boolean apply(WebDriver driverObject) {
		                driverObject.navigate().refresh(); //refresh the page ****************
		            	try {			
		        			return driverObject.findElement(identifier).isDisplayed();
		        		} catch (NoSuchElementException e) {
		        			return false;
		        		}
		            }
		        });
		    element = driver.findElement(identifier);
			resetImplicitWait(driver);
			return element; //return the element
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null; 
	 }
	 
	/**
	  * Wait for the Text to be present in the given element, regardless of being displayed or not.
	  *
	  * @param WebDriver	The driver object to be used to wait and find the element
	  * @param locator	selector of the given element, which should contain the text
	  * @param String	The text we are looking
	  * @param int	The time in seconds to wait until returning a failure
	  * 
	  * @return boolean 
	  */
	public static boolean waitForTextPresent(WebDriver driver, final WebElement element, final String text) {
		boolean isPresent = false; 
		try{	
			nullifyImplicitWait(driver);
	        new WebDriverWait(driver, waitTimeInSecs) {
	        }.until(new ExpectedCondition<Boolean>() {
	
	            @Override
	            public Boolean apply(WebDriver driverObject) {
	            	return isTextPresent(driverObject, element, text); //is the Text in the DOM
	            }
	        });
	        isPresent = isTextPresent(driver, element, text);
			resetImplicitWait(driver);
			return isPresent; 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false; 
	}
	



	/** 
	 * Waits for the Condition of JavaScript.  
	 * @param WebDriver		The driver object to be used to wait and find the element
	 * @param String	The javaScript condition we are waiting. e.g. "return (xmlhttp.readyState >= 2 && xmlhttp.status == 200)" 
	 * @param int	The time in seconds to wait until returning a failure
	 * 
	 * @return boolean true or false(condition fail, or if the timeout is reached)
	 **/
	public static boolean waitForJavaScriptCondition(WebDriver driver, final String javaScript, int timeOutInSeconds) {
		boolean jscondition = false; 
		try{	
			nullifyImplicitWait(driver);
	        new WebDriverWait(driver, timeOutInSeconds) {
	        }.until(new ExpectedCondition<Boolean>() {
	
	            @Override
	            public Boolean apply(WebDriver driverObject) {
	            	return (Boolean) ((JavascriptExecutor) driverObject).executeScript(javaScript);
	            }
	        });
	        jscondition =  (Boolean) ((JavascriptExecutor) driver).executeScript(javaScript); 
			resetImplicitWait(driver);
			return jscondition; 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false; 
	}

	
	/** Waits for the completion of Ajax jQuery processing by checking "return jQuery.active == 0" condition.  
	 *
	 * @param WebDriver - The driver object to be used to wait and find the element
	 * @param int - The time in seconds to wait until returning a failure
	 * 
	 * @return boolean true or false(condition fail, or if the timeout is reached)
	 * */
	public static boolean waitForJQueryProcessing(WebDriver driver){
		boolean jQcondition = false; 
		try{	
			nullifyImplicitWait(driver);
	        new WebDriverWait(driver, waitTimeInSecs, pollingwaitTime) {
	        }.until(new ExpectedCondition<Boolean>() {
	
	            @Override
	            public Boolean apply(WebDriver driverObject) {
	            	return (Boolean) ((JavascriptExecutor) driverObject).executeScript("return jQuery.active == 0");
	            }
	        });
	        jQcondition = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
			resetImplicitWait(driver);
			return jQcondition; 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return jQcondition; 
   }
	
	public static void WaitForJavascript(WebDriver driver){		
		try{	
			nullifyImplicitWait(driver);
	        new WebDriverWait(driver, waitTimeInSecs, pollingwaitTime) {
	        }.until(new ExpectedCondition<Boolean>() {
	
	            @Override
	            public Boolean apply(WebDriver driverObject) {
		            return ((JavascriptExecutor)driverObject).executeScript("return document.readyState").equals("complete") 
		            		   && (Boolean) ((JavascriptExecutor) driverObject).executeScript("return jQuery.active == 0");
	            }
	        });
			resetImplicitWait(driver);

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	

	/**
	 * Coming to implicit wait, If you have set it once then you would have to explicitly set it to zero to nullify it -
	 */
	private static void nullifyImplicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
	} 
	

	/**
	 * Set driver implicitlyWait() time. 
	 */
	public static void setImplicitWait(WebDriver driver, int waitTime_InSeconds) {
		driver.manage().timeouts().implicitlyWait(waitTime_InSeconds, TimeUnit.SECONDS);  
	} 
	
	/**
	 * Reset ImplicitWait.  
	 * To reset ImplicitWait time you would have to explicitly 
	 * set it to zero to nullify it before setting it with a new time value. 
	 */
	private static void resetImplicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //nullify implicitlyWait() 
		driver.manage().timeouts().implicitlyWait(TestRunner.implicitWaitTime, TimeUnit.SECONDS); //reset implicitlyWait
	} 

    /**
	   * Checks if the text is present in the element. 
      * 
	   * @param driver - The driver object to use to perform this element search
	   * @param by - selector to find the element that should contain text
	   * @param text - The Text element you are looking for
	   * @return true or false
	   */
	private static boolean isTextPresent(WebDriver driver, WebElement element, String text)
	{
		try {
				return element.getText().contains(text);
		} catch (NullPointerException e) {
				return false;
		}
	}
	
	public static void WaitAndSelect(WebDriver driver, final WebElement elem, String key) {

			nullifyImplicitWait(driver);
				 
				List<WebElement> options = elem.findElements(By.tagName("option"));
				
				if(options.size() > 0)
				{
				    for(WebElement option : options){
				        if(option.getText().equals(key)) {
							WebDriverWait wait = new WebDriverWait(driver, waitTimeInSecs, pollingwaitTime);
							wait.until(ExpectedConditions.elementToBeClickable(option));		
		
					        option.click();
							
				            WaitForJavascript(driver);
//				            waitForJQueryProcessing(driver);
							break;
				        }
				    }
				}
			resetImplicitWait(driver);

	}
	
	/**
	 * Checks that the current page contains the expected feedback for verification.
	 * This overloading verify method takes in a frame that the driver will switch to before finding the webElement.
	 * @throws SeleniumException 
	 * @throws VerificationException 
	 * @throws InvalidInputException 
	 */
	public static Hashtable verify(WebDriver driver, String identifier, String message, String frame, String key) throws SeleniumException, VerificationException, InvalidInputException {

			Hashtable resultHash = new Hashtable();
		
			String feedback = "";
			boolean result;
			
			if(!frame.isEmpty())
			{
				WebUtils.switchFrames(driver, frame);
				feedback = WebUtils.getDisplayedElementText(driver, new ById(identifier));
				WebUtils.switchToDefault(driver);
			}
			else
			{
				feedback = WebUtils.getDisplayedElementText(driver, new ById(identifier));	
			}
			
			if (!feedback.contains(message)) 
			{
				result = false;
			}
			else
			{
				result = true;
			}
			
			resultHash.put("result", result);
			resultHash.put("feedback", feedback);
			
			return resultHash;
	}
		
	public static void datePicker(WebDriver driver, String description, By textBoxIdentifier, By calPrevMonthIdentifier, By calNextMonthIdentifier,By calMonthAndYearIdentifier, 
			By dateWidgetIdentifier, String date) 
	{
		try
		{
			 WebElement dateWidget = null;
			 List<WebElement> rows = null;
			 List<WebElement> columns = null;
			 List<String> list = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
			 // Expected Date, Month and Year
			 int expMonth;
			 int expYear;
			 String expDay = null;
			 // Calendar Month and Year
			 String calMonth = null;
			 String calYear = null;
			 String calMonthAndYear = null;
			 boolean dateNotFound;
			 String expMonthAndYear;
			 
	//		  driver.switchTo().frame(0);
			  driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			  //Click on textbox of Date so that datepicker will appear
			  driver.findElement(textBoxIdentifier).click();
			  
			  String[] array = date.split("/");
	
			  dateNotFound = true;
			  
			  expDay = array[0];
			  expMonth= Integer.parseInt(array[1]);
			  expYear = Integer.parseInt(array[2]);
			  
			  while(dateNotFound)
			  {
				  calMonthAndYear = driver.findElement(calMonthAndYearIdentifier).getText();
				  String[] split =  calMonthAndYear.split("\\s+");
				  
			   calMonth = split[0].trim();
			   calYear = split[1].trim();
			   
			   expMonthAndYear = list.get(expMonth - 1) + " " + expYear;
			   
	//		   if(list.indexOf(calMonth)+1 == expMonth && (expYear == Integer.parseInt(calYear)))
			   if(calMonthAndYear.equalsIgnoreCase(expMonthAndYear))
			   {
	//		    selectDate(driver, dateWidget,rows,columns, expDate);
					 dateWidget = driver.findElement(dateWidgetIdentifier);
					 rows=dateWidget.findElements(By.tagName("tr"));
					 columns=dateWidget.findElements(By.tagName("td"));
					
					 for (WebElement cell: columns)
					 {
						 System.out.println(cell);
					  //Selects Date
						  if (cell.getText().equals(expDay))
						  {
						   //cell.findElement(By.linkText(expDay)).click();
							  cell.click();
							  TestRunner.logger.addLogInfo(OperationType.SELECT_DATE, description, textBoxIdentifier.toString(), date, driver.getCurrentUrl(), ResultType.SUCCESS);	
						   break;
						  }
					 }
					 dateNotFound = false;
			   }
			   else if(list.indexOf(calMonth)+1 < expMonth && (expYear == Integer.parseInt(calYear)) || expYear > Integer.parseInt(calYear))
			   {
			    driver.findElement(calNextMonthIdentifier).click(); 
			    
			   }
			   else if(list.indexOf(calMonth)+1 > expMonth && (expYear == Integer.parseInt(calYear)) || expYear < Integer.parseInt(calYear))
			   {
			    driver.findElement(calPrevMonthIdentifier).click(); 
			   }
			  }
			  try 
			  {
				Thread.sleep(3000);
			  } 
			  catch (InterruptedException e) 
			  {
				// TODO Auto-generated catch block
				e.printStackTrace();
				String snapName = TakeScreenShot(driver);
				snapName = TakeScreenShot(driver);
				TestRunner.logger.addLogInfo(OperationType.SELECT_DATE, description, textBoxIdentifier.toString(), date, driver.getCurrentUrl(), ResultType.FAILURE, snapName);
			  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String snapName = TakeScreenShot(driver);
			snapName = TakeScreenShot(driver);
			TestRunner.logger.addLogInfo(OperationType.SELECT_DATE, description, textBoxIdentifier.toString(), date, driver.getCurrentUrl(), ResultType.FAILURE, snapName);
		}
	}
}

