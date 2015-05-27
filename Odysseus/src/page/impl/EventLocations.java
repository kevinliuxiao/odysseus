package page.impl;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;

import page.Page;
import page.PageObject;
import util.XMLFileReader;
import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;
import exception.VerificationException;

public class EventLocations extends Page {

	private String defaultDropDownValue = "Enable";
	private String defaultNumLocations = "5";
	private PageObject drop_down_list_of_location_types_and_cities, postal_code_search, number_of_locations, wave_code_search, edit_location_name, edit_location_type_name;
	
	public EventLocations(XMLFileReader params, int stepIndex) throws InvalidInputException {
		super(params,stepIndex);
		nextPages.add(PageType.EVENT_LOCATION_CREATE_CONTENT);
		nextPages.add(PageType.EVENT_LOCATION_DETAILS);
		nextPages.add(PageType.EVENT_LOCATION_UPDATE_DETAILS);
		nextPages.add(PageType.EVENT_SUMMARY);
		nextPages.add(PageType.LOGIN_REGISTER);
		nextPages.add(PageType.EVENT_LOCATION_TYPE_CREATE);
		
				
		drop_down_list_of_location_types_and_cities = new PageObject(params, stepIndex, "drop_down_list_of_location_types_and_cities");
		postal_code_search = new PageObject(params, stepIndex, "postal_code_search");
		number_of_locations = new PageObject(params, stepIndex, "number_of_locations");
		wave_code_search = new PageObject(params, stepIndex, "wave_code_search");
		edit_location_name = new PageObject(params, stepIndex, "edit_location_name");
		edit_location_type_name = new PageObject(params, stepIndex, "edit_location_type_name");

	}

	@Override
	protected void Goto(WebDriver driver, PageType nextPage) throws SeleniumException {
		if (PageType.EVENT_LOCATION_CREATE_CONTENT.equals(nextPage)){
			performInteraction(driver, OperationType.CLICK, "Create New Location", new By.ById("linkbuttonLocationCreate"), null, null);		
		}
		else if (PageType.EVENT_LOCATION_TYPE_CREATE.equals(nextPage))
		{
			performInteraction(driver, OperationType.CLICK, "Setup New Location Type", new ById("linkbuttonLocationTypeCreate"), null, null);	
		}
		else if (PageType.EVENT_LOCATION_TYPE_UPDATE.equals(nextPage))
		{
			performInteraction(driver, OperationType.CLICK, edit_location_type_name.value(), new By.ByLinkText(edit_location_type_name.value()), null, null);	
		}
		else if (PageType.EVENT_LOCATION_DETAILS.equals(nextPage)) 
		{
			performInteraction(driver, OperationType.CLICK, edit_location_name.value(), new By.ByLinkText(edit_location_name.value()), null, null);	
		}
		else if (PageType.LOGIN_REGISTER.equals(nextPage)) 
		{
			performInteraction(driver, OperationType.CLICK, "event link", new ById("eventLink_hyperlinkEventLink"), null, null);	
		}
		// side menu items
		else if (PageType.EVENT_SUMMARY.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Event Summary", new By.ByLinkText("Event Summary"), null, null);	
		} 
		else if (PageType.EVENT_SETTINGS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Event Settings", new By.ByLinkText("Event Settings"), null, null);	
		} else if (PageType.EVENT_EMAIL_PREFACE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Emails", new By.ByLinkText("Emails"), null, null);	
		} else if (PageType.EVENT_REG_TYPE_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Registration Types", new By.ByLinkText("Registration Types"), null, null);	
		}else if (PageType.VOLUNTEER_MANAGEMENT.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Volunteer Management", new By.ByLinkText("Volunteer Management"), null, null);	
		}else if (PageType.EVENT_SUGGESTED_DONATION_AMOUNTS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Suggested Donation Amounts", new By.ByLinkText("Suggested Donation Amounts"), null, null);	
		}else if (PageType.EVENT_WAIVER.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Waiver", new By.ByLinkText("Waiver"), null, null);	
		}else if (PageType.EVENT_TICKETING_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Purchase Items", new By.ByLinkText("Purchase Items"), null, null);	
		} else if (PageType.EVENT_PAPER_PLEDGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Downloadable Forms", new By.ByLinkText("Downloadable Forms"), null, null);	
		} else if (PageType.CONTENT_QUESTION_LIST.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "User Defined Fields", new By.ByLinkText("User Defined Fields"), null, null);	
		}else if (PageType.EVENT_WIDGETS_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Widgets", new By.ByLinkText("Widgets"), null, null);	
		}else if (PageType.EVENT_ECARD_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Card Management", new By.ByLinkText("Card Management"), null, null);	
		}else if (PageType.EVENT_EMAILS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Event Emails", new By.ByLinkText("Emails"), null, null);	
		}else if (PageType.EVENT_PAYMENT_TYPES.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Payment Types", new By.ByLinkText("Payment Types"), null, null);	
		}else if (PageType.CONTENT_SELECT_PAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Content",  new By.ByLinkText("Content"), null, null);	
		}else if (PageType.EVENT_MEDIA_VIEWER_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Media",  new By.ByLinkText("Media"), null, null);	
		}else if (PageType.EVENT_USER_INTERFACE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Style Settings",  new By.ByLinkText("Style Settings"), null, null);	
		} 
		
	}

	@Override
	protected List<String> checkPageError(WebDriver driver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException, SeleniumException, VerificationException {
		if(actionType.equals(ActionType.SETUP_LOCATION_SEARCH_OPTIONS)){
			setUpLocationSearchOptions(driver);
		}
	}
	
	private void setUpLocationSearchOptions(WebDriver driver) throws SeleniumException, VerificationException, InvalidInputException
	{
		performInteraction(driver, OperationType.SELECT, "Drop down list of Location Types and Cities", new ById("dropdownlistTypeCityLookupEnabled"),  drop_down_list_of_location_types_and_cities, defaultDropDownValue);
		performInteraction(driver, OperationType.SELECT, "Postal Code Search", new ById("dropdownlistPostalCodeLookupEnabled"), postal_code_search, defaultDropDownValue);	
		performInteraction(driver, OperationType.SELECT, "Number of Locations", new ById("dropdownlistNumberLocationDisplay"), number_of_locations, defaultNumLocations);	
		performInteraction(driver, OperationType.SELECT, "Wave Code Search", new ById("dropdownlistWaveCodeSearchEnabled"), wave_code_search, defaultDropDownValue);
		
		performInteraction(driver, OperationType.CLICK, "Save Button", new ById("buttonSave"), null, null);
		verify(driver, "verify feedback", new ById("feedback"), "Location Settings successfully updated", null);
	}

}
