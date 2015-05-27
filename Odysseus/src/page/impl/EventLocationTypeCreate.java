package page.impl;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By.ById;

import page.Page;
import page.PageObject;
import util.XMLFileReader;
import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;
import exception.VerificationException;
import generators.LocationSetUpGenerator;

public class EventLocationTypeCreate extends Page {
	
	private PageObject language;
	private PageObject name;
	private PageObject description;
	private PageObject export_Location_TypeID;
	private PageObject display_Location; 

	public EventLocationTypeCreate(XMLFileReader params, int stepIndex) throws InvalidInputException {
		super(params,stepIndex);
		nextPages.add(PageType.EVENT_LOCATIONS);
		
		language = new PageObject(params, stepIndex,"language"); 
		name = new PageObject(params, stepIndex,"name"); 
		description = new PageObject(params, stepIndex,"description"); 
		export_Location_TypeID = new PageObject(params, stepIndex,"export_Location_TypeID"); 
		display_Location = new PageObject(params, stepIndex,"display_Location");
	}

	@Override
	protected void Goto(WebDriver driver, PageType nextPage)throws InvalidInputException, SeleniumException, VerificationException {
		if (PageType.EVENT_LOCATIONS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "", new ById("buttonSubmit"), null, null);
		}
	}

	@Override
	protected List<String> checkPageError(WebDriver driver)throws InvalidInputException, SeleniumException, VerificationException {
		return null;
	}

	@Override
	protected void performAction(WebDriver driver, ActionType actionType)throws InvalidInputException, SeleniumException, VerificationException {
		if(actionType.equals(ActionType.SETUP_NEW_LOCATION_TYPE)){
			setupNewLocationType(driver);
		}
	}

	private void setupNewLocationType(WebDriver driver) throws SeleniumException {
		performInteraction(driver, OperationType.SELECT, "Language", new ById("ucEventLocationType_ucSelectLanguage_dropdownlistLanguages"),  language, LocationSetUpGenerator.language());
		performInteraction(driver, OperationType.TYPE, "name", new ById("ucEventLocationType_textboxLocationTypeName"), name, LocationSetUpGenerator.location_name_en());	
		performInteraction(driver, OperationType.TYPE, "description", new ById("ucEventLocationType_textboxLocationTypeDescription"), description, LocationSetUpGenerator.location_description());	
		performInteraction(driver, OperationType.TYPE, "Export Location Type ID", new ById("ucEventLocationType_textboxExportLocationTypeID"), export_Location_TypeID, LocationSetUpGenerator.location_exportid());
		performInteraction(driver, OperationType.CLICK, "Display Location", new ById("ucEventLocationType_checkboxDisplayLocation"), display_Location, LocationSetUpGenerator.location_name_en());
	}
	
}
