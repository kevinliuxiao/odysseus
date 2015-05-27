package page.impl;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

public class EventLocationUpdateContent extends Page{

	private PageObject location_name_en;
	private PageObject location_name_fr;
	private PageObject location_exportid;
	private PageObject location_description;
	private PageObject address_field_1;
	private PageObject address_field_2;
	private PageObject address_field_3;
	private PageObject address_field_4;

	private PageObject city_town; 
	private PageObject province_state;
	private PageObject postal_zipcode;
	private PageObject country;
	private PageObject language; 
	private PageObject image_a;
	private PageObject image_b; 
	private PageObject content_area_a;
	private PageObject content_area_b;
	
	private boolean iscreateNewLocation;
	
	public EventLocationUpdateContent(XMLFileReader params, int stepIndex) throws InvalidInputException {
		super(params,stepIndex);
		nextPages.add(PageType.EVENT_LOCATION_DETAILS);
		
		location_name_en = new PageObject(params, stepIndex,"location_name_en");    
		location_name_fr = new PageObject(params, stepIndex,"location_name_fr");   	
		location_exportid = new PageObject(params, stepIndex,"location_exportid");  	
		location_description = new PageObject(params, stepIndex,"location_description");   
		address_field_1 = new PageObject(params, stepIndex,"address_field_1"); 
		address_field_2 = new PageObject(params, stepIndex,"address_field_2");   
		address_field_3 = new PageObject(params, stepIndex,"address_field_3");  
		address_field_4 = new PageObject(params, stepIndex,"address_field_4");   
		city_town = new PageObject(params, stepIndex,"city_town"); 
		province_state = new PageObject(params, stepIndex,"province_state");
		postal_zipcode = new PageObject(params, stepIndex,"postal_zipcode");
		country = new PageObject(params, stepIndex,"country"); 
		language = new PageObject(params, stepIndex,"language"); 
		image_a = new PageObject(params, stepIndex,"image_a"); 
		image_b = new PageObject(params, stepIndex,"image_b"); 
		content_area_a = new PageObject(params, stepIndex,"content_area_a");
		content_area_b = new PageObject(params, stepIndex,"content_area_b");
	}

	protected void Goto(WebDriver driver, PageType nextPage) throws SeleniumException, VerificationException, InvalidInputException {
		if (PageType.EVENT_LOCATION_DETAILS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "", new By.ById("buttonSubmit"), null, null);
			if(iscreateNewLocation){
				verify(driver, "feedback", new By.ById("feedback"), "Location Updated Successfully", null);
			}
		} 
		
	}

	@Override
	protected List<String> checkPageError(WebDriver driver) {
		WebElement errorMessage = driver.findElement(new ById("ucEventLocationContent_feedback"));
		List<String> errors = new ArrayList<String>();
		errors.add(errorMessage.getText());
		return errors;
	}

	@Override
	protected void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException, SeleniumException, VerificationException {
		if(actionType.equals(ActionType.CREATE_NEW_LOCATION)){
			createNewLocation(driver);
		}
		
	}
	
	public void createNewLocation(WebDriver driver) throws SeleniumException, VerificationException, InvalidInputException 
	{	
		iscreateNewLocation = true;
		performInteraction(driver, OperationType.TYPE, "Location Name English", new ById("ucEventLocationContent_textboxLocationName"), location_name_en, LocationSetUpGenerator.location_name_en());
		performInteraction(driver, OperationType.TYPE, "Location Name French", new ById("ucEventLocationContent_textboxLocationNameFR"), location_name_fr, LocationSetUpGenerator.location_name_fr());

		performInteraction(driver, OperationType.TYPE, "Event Location Export ID",new ById("ucEventLocationContent_textboxExportLocationID"), location_exportid, LocationSetUpGenerator.location_exportid());		
		performInteraction(driver, OperationType.TYPE, "Location Description",new ById("ucEventLocationContent_textboxLocationDescription"), location_description, LocationSetUpGenerator.location_description());
		performInteraction(driver, OperationType.TYPE, "Address field 1", new ById("ucEventLocationContent_textboxAddressLine1"), address_field_1, LocationSetUpGenerator.address());
//		performInteraction(driver, OperationType.TYPE, "Address field 2", new ById("ucEventLocationContent_textboxAddressLine2"), address_field_2, LocationSetUpGenerator.address());
//		performInteraction(driver, OperationType.TYPE, "Address field 3", new ById("ucEventLocationContent_textboxAddressLine3"), address_field_3, LocationSetUpGenerator.address());
//		performInteraction(driver, OperationType.TYPE, "Address field 4", new ById("ucEventLocationContent_textboxAddressLine4"), address_field_4, LocationSetUpGenerator.address());
		performInteraction(driver, OperationType.TYPE, "City/Town", new ById("ucEventLocationContent_textboxCity"), city_town, LocationSetUpGenerator.city_town());
		performInteraction(driver, OperationType.SELECT, "Province/State", new ById("ucEventLocationContent_dropdownlistProvince"), province_state, LocationSetUpGenerator.province_state());
		performInteraction(driver, OperationType.TYPE, "Postal/Zip Code", new ById("ucEventLocationContent_ucPostalCode_txtPostalCode"), postal_zipcode, LocationSetUpGenerator.postal_zipcode());
		performInteraction(driver, OperationType.SELECT, "Country", new ById("ucEventLocationContent_dropdownlistCountry"), country, LocationSetUpGenerator.country());
		
		performInteraction(driver, OperationType.SELECT, "Language", new ById("ucEventLocationContent_ucSelectLanguage_dropdownlistLanguages"), language, LocationSetUpGenerator.language());
		performInteraction(driver, OperationType.FILEUPLOAD, "Image A", new ById("ucEventLocationContent_ucImageLoader_fileUpload"), image_a, LocationSetUpGenerator.image_a());
		performInteraction(driver, OperationType.FILEUPLOAD, "Image B", new ById("ucEventLocationContent_ucImageLoaderB_fileUpload"), image_b, LocationSetUpGenerator.image_b());
		performInteraction(driver, OperationType.TYPE, "Content Area A", new ById("ucEventLocationContent_textboxLocationLongDescription1"), content_area_a, LocationSetUpGenerator.content_area_a());
		performInteraction(driver, OperationType.TYPE, "Content Area B", new ById("ucEventLocationContent_textboxLocationLongDescription2"), content_area_b, LocationSetUpGenerator.content_area_b());

	}

}
