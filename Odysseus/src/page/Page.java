package page;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;




import runner.TestRunner;
import util.ActionFactory;
import util.WebUtils;
import util.XMLFileReader;
import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import enumeration.ResultType;
import exception.ActionNotSupportedException;
import exception.E2RMErrorException;
import exception.InvalidInputException;
import exception.PageUnreachableException;
import exception.SeleniumException;
import exception.URLException;
import exception.VerificationException;

public abstract class Page
{  
	public static final String TOGGLE_TRUE = "true";
	public static final String TOGGLE_FALSE = "false";
	public static final String TOGGLE_YES = "yes";
	public static final String TOGGLE_NO = "no";
		
	protected List<PageType> nextPages;
	public List<ActionType> actions;
	private static PageType currPageType;
	private static XMLFileReader params;
	private static int stepIndex;
	
	public Page(XMLFileReader params, int stepIndex) throws InvalidInputException {
		Page.params = params;
		Page.stepIndex = stepIndex;
		this.nextPages = new ArrayList<PageType>();
		actions = new ArrayList<ActionType>();
		currPageType = PageType.fromString(this.getClass().getSimpleName());
	}
	
	
	protected abstract void Goto(WebDriver driver, PageType nextPage) throws InvalidInputException, SeleniumException, VerificationException;
	protected abstract List<String> checkPageError(WebDriver driver) throws InvalidInputException, SeleniumException, VerificationException;
	protected abstract void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException, SeleniumException, VerificationException;
	
//	protected abstract void actions();
//	protected abstract void nextPages();
    
	public void action(WebDriver driver) throws PageUnreachableException, E2RMErrorException, URLException, SeleniumException, ActionNotSupportedException, InvalidInputException, VerificationException 
	{
		PageType nextPageType = PageType.fromString(params.getPage(stepIndex));
		ActionType actionType = ActionType.fromString(params.getAction(stepIndex));
		List<ActionType> actions = ActionFactory.actionList(nextPageType);
		
//		if(actionType.equals(ActionType.GOTO)){
//			leavePage(driver, nextPageType);
//		 }
//		else{
			if (actionType == null || !actions.contains(actionType)) {
				throw new ActionNotSupportedException(PageType.fromString(this.getClass().getSimpleName()), actionType);
			} 
			performAction(driver, actionType);
		//}

	}
	
	private Method getMethod(String methodName)
	{
		 for(Method method : this.getClass().getDeclaredMethods())
		 {
			 if(method.getName().equalsIgnoreCase(methodName))
			 {
				 return method;
			 }
		 }
		return null;
	}
	
	private boolean hasPropertiesParam(Method method)
	{
		 for(Class<?> type : method.getParameterTypes())
		 {
			 if(type.getSimpleName().equalsIgnoreCase("Properties"))
			 {
				 return true;
			 }
		 }
		return false;
	}
	
	private boolean hasNoParam(Method method)
	{
		if(method.getParameterTypes().length == 0)
		{
			return true;
		}
		return false;
	}
	
	public void leavePage(WebDriver driver, PageType nextPage) throws InvalidInputException, PageUnreachableException, URLException, E2RMErrorException, SeleniumException, VerificationException
	{
		if (nextPages == null || !nextPages.contains(nextPage)) {
			throw new PageUnreachableException(this.getClass().getSimpleName(), nextPage);
		} 
		Goto(driver, nextPage);
		
		WebUtils.waitForPageLoad(driver, nextPage);
		
		if (!driver.getCurrentUrl().toLowerCase().contains("/" + nextPage.urlKeyword().toLowerCase() + ".aspx")) {
			List<String> errors = checkPageError(driver);
			if (errors != null) {
				throw new E2RMErrorException(errors, this);
			} else {
				throw new URLException(nextPage.urlKeyword(), driver.getCurrentUrl());
			}
		}
	}
	

	/**
	 * This function performs the actual interactions with web elements on the webpages through Selenium calls.
	 * For all components other than Verifiers, this is the only function you need to use for any web interactions.
	 * See Verifiers for other use of Selenium.
	 * 
	 * @param driver Current browser.
	 * @param opType The type of interaction. (CLICK, TYPE(enter text), SELECT(from a list), TOGGLE(a checkbox))
	 * @param identifier The web element to be operated on.
	 * @param keys The value of operation for TYPE, SELECT and TOGGLE.
	 * @throws SeleniumException 
	 */

	public static void performInteraction(WebDriver driver, OperationType opType, String description, By identifier, PageObject pageObject, String keys) throws SeleniumException{
		
		if(pageObject != null && !pageObject.value().isEmpty())
		{
			keys = pageObject.value();
			TestRunner.setReuseValue(makeReuseKey(pageObject.key()), keys);
		}
		else if(keys != null && !keys.isEmpty())
		{
			TestRunner.setReuseValue(makeReuseKey(pageObject.key()), keys);
		}
		
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
				WebUtils.WaitForJavascript(driver);
				e = WebUtils.waitForElementToBeDisplayedAndClickable(driver, identifier);
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
					WebUtils.waitForAlert(driver, identifier);
					driver.switchTo().alert().accept();
				}
			    catch (WebDriverException exception) {
					((JavascriptExecutor) driver).executeScript("$(arguments[0].click())",e);	
					WebUtils.waitForAlert(driver, identifier);
					driver.switchTo().alert().accept();
			    }
				break;
			case SELECT:
				
				WebUtils.WaitAndSelect(driver, e, keys);
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
				if ((TOGGLE_TRUE.equalsIgnoreCase(keys) && !e.isSelected()) || (TOGGLE_FALSE.equalsIgnoreCase(keys) && e.isSelected()) || 
						(TOGGLE_YES.equalsIgnoreCase(keys) && !e.isSelected()) || (TOGGLE_NO.equalsIgnoreCase(keys) && e.isSelected())) {
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
			String snapName = WebUtils.TakeScreenShot(driver);
			TestRunner.logger.addLogInfo(opType, description, identifier.toString(), keys, driver.getCurrentUrl(), ResultType.FAILURE, snapName);
			if(opType.equals(OperationType.CLICK_VERIFY) && result == false)
			{
				snapName = WebUtils.TakeScreenShot(driver);
				TestRunner.logger.addLogInfo(opType, description,  identifier.toString(), keys, driver.getCurrentUrl(), ResultType.FAILURE, snapName);
			}
			throw new SeleniumException(exception);
		}
		TestRunner.logger.addLogInfo(opType, description, identifier.toString(), keys, driver.getCurrentUrl(), ResultType.SUCCESS);	
		if(opType.equals(OperationType.CLICK_VERIFY) && result == true)
		{
			TestRunner.logger.addLogInfo(opType, description, identifier.toString(), keys, driver.getCurrentUrl(), ResultType.SUCCESS);	
		}		
	}
	
	public static void datePicker(WebDriver driver, String description, By textBoxIdentifier, By calPrevMonthIdentifier, By calNextMonthIdentifier,By calMonthAndYearIdentifier, 
			By dateWidgetIdentifier, PageObject pageObject, String date) 
	{
		try
		{
			if(pageObject != null && !pageObject.value().isEmpty())
			{
				date = pageObject.value();
				TestRunner.setReuseValue(makeReuseKey(pageObject.key()), date);
			}
			else if(date != null && !date.isEmpty())
			{
				TestRunner.setReuseValue(makeReuseKey(pageObject.key()), date);
			}
			
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
				String snapName = WebUtils.TakeScreenShot(driver);
				snapName = WebUtils.TakeScreenShot(driver);
				TestRunner.logger.addLogInfo(OperationType.SELECT_DATE, description, textBoxIdentifier.toString(), date, driver.getCurrentUrl(), ResultType.FAILURE, snapName);
			  }
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String snapName = WebUtils.TakeScreenShot(driver);
			snapName = WebUtils.TakeScreenShot(driver);
			TestRunner.logger.addLogInfo(OperationType.SELECT_DATE, description, textBoxIdentifier.toString(), date, driver.getCurrentUrl(), ResultType.FAILURE, snapName);
		}
	}
	
	private static String makeReuseKey( String tag)
	{
		return currPageType.urlKeyword() + "." + tag;
	}
	
	/**
	 * Checks that the current page contains the expected feedback for verification.
	 * This overloading verify method takes in a frame that the driver will switch to before finding the webElement.
	 * @throws SeleniumException 
	 * @throws VerificationException 
	 * @throws InvalidInputException 
	 */
	
	public static void verify(WebDriver driver, String description, By identifier, String expectedMessage, String frame) throws SeleniumException, VerificationException, InvalidInputException {
		
			String feedback = "";
			
			if(frame != null && !frame.isEmpty())
			{
				WebUtils.switchFrames(driver, frame);
				feedback = WebUtils.getDisplayedElementText(driver, identifier);
				WebUtils.switchToDefault(driver);
			}
			else
			{
				feedback = WebUtils.getDisplayedElementText(driver, identifier);	
			}
			
			if (feedback.contains(expectedMessage)) 
			{
				 TestRunner.logger.addLogInfo(OperationType.VERIFY, description, identifier.toString(), expectedMessage, driver.getCurrentUrl(), ResultType.SUCCESS);	
			}
			else
			{
				String snapName = WebUtils.TakeScreenShot(driver);
				snapName = WebUtils.TakeScreenShot(driver);
				TestRunner.logger.addLogInfo(OperationType.SELECT_DATE, description, identifier.toString(), expectedMessage, driver.getCurrentUrl(), ResultType.FAILURE, snapName);
			}
	}
}
