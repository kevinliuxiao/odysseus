package page.impl;
import java.util.List;

import org.apache.xalan.xsltc.runtime.Hashtable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;
import exception.VerificationException;
import page.Page;
import page.PageGroupObject;
import page.PageObject;
import util.XMLFileReader;

public class EventRegTypeIndLocations extends Page {

	private String DEFAULTVALUE = "yes";
	private PageGroupObject locations;
	
	public EventRegTypeIndLocations(XMLFileReader params, int stepIndex) throws InvalidInputException {
		super(params, stepIndex);
		nextPages.add(PageType.EVENT_REG_TYPE_MANAGE);

		locations = new PageGroupObject(params, "locations", stepIndex, "displaychk", "location_name", "eventdate");
	}

	protected void Goto(WebDriver driver, PageType nextPage)throws InvalidInputException, SeleniumException, VerificationException {
		if (PageType.EVENT_REG_TYPE_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "buttonSubmit", new By.ById("buttonFinish"), null, null);
		}
	}

	protected List<String> checkPageError(WebDriver driver) throws InvalidInputException, SeleniumException, VerificationException {
		return null;
	}

	protected void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException, SeleniumException, VerificationException {
		if(actionType.equals(ActionType.SETUP_LOCATIONS)){
			setupLocations(driver);
		}
	}
	
	private void setupLocations(WebDriver driver) throws SeleniumException {
		WebElement table = driver.findElement(By.id("datagridLocations")); 
		// Now get all the TR elements from the table 
		List<WebElement> allRows = table.findElements(By.tagName("tr")); 
		System.out.println(allRows.size());
		
		for(int index=2; index <= allRows.size(); index++)
		{
//			String displayChkStr = "//*[@id=\"datagridLocations\"]/tbody/tr[" + index + "]/td[1]";
			String displayChkXpath = "//*[@id=\"datagridLocations_ctl0" + index + "_checkboxLocation\"]";
			String locationXpath = "//*[@id=\"datagridLocations\"]/tbody/tr[" + index + "]/td[2]";
			String eventDateXpath = "//*[@id=\"datagridLocations\"]/tbody/tr[" + index + "]/td[3]";
			String locationNameText = driver.findElement(By.xpath(locationXpath)).getText();
			String eventDateText = driver.findElement(By.xpath(eventDateXpath)).getText();
			
			for(int i= 1; i <= locations.size(); i++)
			{
				PageObject location_name = locations.value(i, "location_name");
				PageObject eventdate = locations.value(i, "eventdate");
				PageObject displaychk = locations.value(i, "displaychk");
				
				if(!displaychk.value().isEmpty()){
					if(location_name.value().equalsIgnoreCase(locationNameText) && eventdate.value().equalsIgnoreCase(eventDateText)){
						performInteraction(driver, OperationType.TOGGLE, locationNameText ,new By.ByXPath(displayChkXpath), displaychk, DEFAULTVALUE); 
					}
					else if(location_name.value().equalsIgnoreCase(locationNameText)){
						performInteraction(driver, OperationType.TOGGLE, locationNameText ,new By.ByXPath(displayChkXpath), displaychk, DEFAULTVALUE); 
					}
					else if(eventdate.value().equalsIgnoreCase(eventDateText)){
						performInteraction(driver, OperationType.TOGGLE, locationNameText ,new By.ByXPath(displayChkXpath), displaychk, DEFAULTVALUE); 
					}
				}
				else{
					performInteraction(driver, OperationType.TOGGLE, locationNameText ,new By.ByXPath(displayChkXpath), displaychk, DEFAULTVALUE); 
				}

			}

		}
	}

//	public void clickonElements(WebDriver driver) throws SeleniumException{
//		// Grab the table 
////		Hashtable hashTable = new Hashtable();
////		WebElement table = driver.findElement(By.id("datagridLocations")); 
////
////		// Now get all the TR elements from the table 
////		List<WebElement> allRows = table.findElements(By.tagName("tr")); 
////
////		int count = 1;
////		// And iterate over them, getting the cells 
////		for (WebElement row : allRows) { 
////		    List<WebElement> cells = row.findElements(By.tagName("td")); 
////
////		    // Print the contents of each cell
////		    for (WebElement cell : cells) { 
////		        System.out.println(cell.getText());
////		        hashTable.put(count, value);
////		    }
////		}
//		
//		WebElement table = driver.findElement(By.id("datagridLocations")); 
//		// Now get all the TR elements from the table 
//		List<WebElement> allRows = table.findElements(By.tagName("tr")); 
//		System.out.println(allRows.size());
//		
//		for(int index=2; index <= allRows.size(); index++)
//		{
//			String displayChkStr = "//*[@id=\"datagridLocations\"]/tbody/tr[" + index + "]/td[1]";
//			String locationStr = "//*[@id=\"datagridLocations\"]/tbody/tr[" + index + "]/td[2]";
//			String eventDateStr = "//*[@id=\"datagridLocations\"]/tbody/tr[" + index + "]/td[3]";
//			String locationNameStr = driver.findElement(By.xpath(locationStr)).getText();
//
//			performInteraction(driver, OperationType.TOGGLE, locationStr ,new By.ById(displayChkStr), location, DEFAULTVALUE); 
//
//		}
//	}
}
