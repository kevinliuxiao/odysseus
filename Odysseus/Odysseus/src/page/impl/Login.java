package page.impl;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By.ById;

import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;
import generators.AdminLoginGenerator;
import page.PageObject;
import page.StartPage;
import runner.TestRunner;
import util.XMLFileReader;

public class Login extends StartPage{
	private PageObject orgID, username, password;
	
	public Login(XMLFileReader params, int stepIndex) throws InvalidInputException
	{
		super(params,stepIndex);
//		PageType pageType = PageType.fromString(this.getClass().getSimpleName());
		this.nextPages.add(PageType.START);
		
		orgID = new PageObject(params, stepIndex, "orgID");  
		username = new PageObject(params, stepIndex, "username");  
		password = new PageObject(params, stepIndex, "password");  
	}
	
	protected void start(WebDriver driver) throws InvalidInputException {
		driver.get("https://" + TestRunner.getTestInstance() + "/Login.aspx");
		
	}

	public void Goto(WebDriver driver, PageType nextPage) throws SeleniumException {
		if(nextPage.equals(PageType.START)){
			performInteraction(driver, OperationType.CLICK, "Click on Submit button", new ById("buttonSubmit"),  null,  null);
		}
	}
	
	protected List<String> checkPageError(WebDriver driver) {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException, SeleniumException {
		if(actionType.equals(ActionType.ADMIN_LOGIN)){
			LoginAdmin(driver);
		}		
	}
	
	public void LoginAdmin(WebDriver driver) throws InvalidInputException, SeleniumException {
		AdminLoginGenerator.Generate();
		performInteraction(driver, OperationType.TYPE, "OrganizationID", new ById("textOrganizationID"), orgID, AdminLoginGenerator.organizationID());
		performInteraction(driver, OperationType.TYPE, "username", new ById("textUsername"), username, AdminLoginGenerator.username());
		performInteraction(driver, OperationType.TYPE, "password", new ById("textPassword"), password , AdminLoginGenerator.password());

	}

}
