package controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByXPath;

import page.Page;
import page.StartPage;
import runner.TestRunner;
import util.ActionFactory;
import util.PageFactory;
import util.WebUtils;
import util.XMLFileReader;
import enumeration.ActionType;
import enumeration.ArtezHost;
import enumeration.ArtezInstance;
import enumeration.OperationType;
import enumeration.PageType;
import enumeration.ResultType;
import enumeration.TestType;
import exception.ActionNotSupportedException;
import exception.E2RMErrorException;
import exception.InvalidInputException;
import exception.InvalidReportFileExtensionException;
import exception.PageFactoryException;
import exception.PageUnreachableException;
import exception.ReportVerificationException;
import exception.SeleniumException;
import exception.URLException;
import exception.VerificationException;


public class Controller {
	
	protected XMLFileReader params;
	protected Page currentPage;
	protected Page nextPage;
	protected StartPage startPage;
	protected List<PageType> Pages;
	protected boolean testResult;
		
	protected ArtezInstance testInstance;
	protected TestType testType;
	
	private int numOfSteps;
	
	
	

	public Controller(XMLFileReader params, ArtezInstance testInstance) throws InvalidInputException 
	{
		this.params = params;
		this.Pages = new ArrayList<PageType>();
		numOfSteps = params.getNumberOfTestSteps();
		this.testInstance = testInstance;
		this.testType = TestType.fromString(params.getStartPage());
		
	}
	
	public boolean perform(WebDriver driver) 
	{	
		List<String> expectedErrorMessages = getExpectedErrorList();
		try
		{
			testResult = true;			
			for (int stepIndex = 1; stepIndex <= numOfSteps; stepIndex++) 
			{
				ActionType actionType = ActionType.fromString(params.getAction(stepIndex));	
				PageType pagetype = PageType.fromString(params.getPage(stepIndex));
				
				if(stepIndex == 1){
					start(params, driver);
				}
				else{
					if(actionType.equals(ActionType.GOTO)){
						currentPage.leavePage(driver, pagetype);
						this.currentPage = PageFactory.makePage(params, pagetype, stepIndex);	
					}
					else{
						this.currentPage = PageFactory.makePage(params, pagetype, stepIndex);	
						currentPage.action(driver);
					}
				}
			}
		}
		catch (E2RMErrorException e) 
		{	
			// Handle E2RMErrorException by checking if the error is expected.			
			for (String m : e.getErrors()) 
			{
				if (expectedErrorMessages.contains(m)) 
				{
					expectedErrorMessages.remove(m);
					
					//String verifiedMessage = "Expected E2RM error message '"+ m + "' on page: '" + e.getErrorLocation().getUrlKeyword() +"'";
					//TestRunner.logger.addLogInfo(OperationType.VERIFY, "Expected Failure", "", verifiedMessage, driver.getCurrentUrl(), ResultType.SUCCESS);
				} 
				else 
				{
					//String errorMessage = "Unexpected E2RM error message '"+ m + "' on page: '" + e.getErrorLocation().getUrlKeyword() +"'";
					String snapName = WebUtils.TakeScreenShot(driver);
					//TestRunner.logger.addLogInfo(OperationType.TEST_FAILURE, "UnExpected Failure", "", errorMessage, driver.getCurrentUrl(), ResultType.ERROR, snapName);
					//System.err.println(errorMessage);
					testResult = false;
				}
			}
			
		}
		catch (SeleniumException | InvalidInputException | PageUnreachableException | URLException | ActionNotSupportedException| PageFactoryException e) {
		
			// Report any errors during the test case and count the case as a fail.
			String snapName = WebUtils.TakeScreenShot(driver);
			System.err.println(e.getMessage());
			TestRunner.logger.addLogInfo(OperationType.TEST_FAILURE, "", "", e.getMessage(), driver.getCurrentUrl(), ResultType.ERROR, snapName);
			testResult = false;
		}
		catch (VerificationException e) {
			String snapName = WebUtils.TakeScreenShot(driver);
			// Report any errors during a call to verify and count the case as a fail.
			TestRunner.logger.addLogInfo(OperationType.VERIFY, "",  "", e.getMessage(), driver.getCurrentUrl(), ResultType.ERROR, snapName);
			System.err.println(e.getMessage());
			testResult = false;
		}
		return testResult;
	}	
	
	/**
	 * Go to the starting url for this testCase. This method is always called at the beginning of perform().
	 * @throws SeleniumException 
	 * @throws InvalidInputException 
	 * @throws URLException 
	 * @throws E2RMErrorException 
	 * @throws VerificationException 
	 * @throws PageFactoryException 
	 */
	private void start(XMLFileReader params, WebDriver driver) throws InvalidInputException, E2RMErrorException, URLException, SeleniumException, VerificationException
	{	
		try 
		{
			int StartIndex = 1;
			ActionType actionType = ActionType.fromString(params.getAction(StartIndex));			
			if(!actionType.equals(ActionType.GOTO)){
				throw new InvalidInputException("Step1 must have a 'goto' action");
			}
			
			PageType startPageType = PageType.fromString(params.getPage(StartIndex));	
			startPage = (StartPage) StartPage.makeStartPage(params, startPageType, StartIndex, testType);	
	
			this.currentPage = this.startPage;
			
			try {
				startPage.startPage(driver);
			} catch (WebDriverException e) {
				throw new SeleniumException(makeSeleniumExceptionMessage(this.currentPage, e), e);
			}

			
		} catch (WebDriverException e) {
			throw new SeleniumException(makeSeleniumExceptionMessage(this.currentPage, e), e);
		}		
	}
	

	
	/**
	 * Get a list of expected E2RM errors from the params (input file).
	 */
	private List<String> getExpectedErrorList() 
	{
/*
		int index = 1;
		List<String> result = new ArrayList<String>();
		while(!params.getTestValidationValue("EXPECTEDERROR_" + index).isEmpty()){
			result.add(params.getTestValidationValue("EXPECTEDERROR_" + index).trim());
			index++;
		}
		return result;
*/
		return null;
	}	

	
	/**
	 * Wrap the Selenium run time exception with more debug info.
	 */
	protected String makeSeleniumExceptionMessage(Page page, Throwable e) {
		
		String result = "SeleniumException found on page " + page.getClass();
		String fullMessage = e.getMessage();
		return result + "\n" + fullMessage.substring(0, fullMessage.indexOf("\n"));
	}	
}
