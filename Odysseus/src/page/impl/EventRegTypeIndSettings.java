package page.impl;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.PageFactoryException;
import exception.SeleniumException;
import exception.VerificationException;
import generators.EventSetUpGenerator;
import generators.LocationSetUpGenerator;
import page.Page;
import page.PageObject;
import util.XMLFileReader;

public class EventRegTypeIndSettings extends Page {

	private PageObject regTypeAction;
	private PageObject name;
	private PageObject exportID;
	private PageObject language;
	private PageObject individuals;
	private PageObject team_captains;
	private PageObject team_members;
	
	private PageObject title;
	private PageObject short_description;
	private PageObject description_self_select;
	private PageObject Description_business_rules;
	
	private String defaultValue = "yes";
	
	public EventRegTypeIndSettings(XMLFileReader params, int stepIndex) throws InvalidInputException {
		super(params,stepIndex);
		this.nextPages.add(PageType.EVENT_REG_TYPE_IND_BIZ_RULES);
		this.nextPages.add(PageType.EVENT_REG_TYPE_IND_SETTINGS);
		this.nextPages.add(PageType.EVENT_REG_TYPE_IND_LOCATIONS);

		this.name = new PageObject(params, stepIndex,"name");    
		this.exportID = new PageObject(params, stepIndex,"exportID");    
		this.language = new PageObject(params, stepIndex,"language");    
		this.individuals = new PageObject(params, stepIndex,"individuals");    
		this.team_captains = new PageObject(params, stepIndex,"team_captains");    
		this.team_members = new PageObject(params, stepIndex,"team_members");    
		
		this.title = new PageObject(params, stepIndex,"title");    
		this.short_description = new PageObject(params, stepIndex,"short_description");    
		this.description_self_select = new PageObject(params, stepIndex,"description_self_select");    
		this.Description_business_rules = new PageObject(params, stepIndex,"Description_business_rules");   
	}

	protected void Goto(WebDriver driver, PageType nextPage) throws InvalidInputException, SeleniumException, VerificationException {
		if (PageType.EVENT_REG_TYPE_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "buttonSubmit", new By.ById("buttonSubmit"), null, null);
		}else if (PageType.EVENT_REG_TYPE_IND_BIZ_RULES.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "button Next", new By.ById("buttonNext"), null, null);
		}else if (PageType.EVENT_REG_TYPE_IND_LOCATIONS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Locations", new By.ByLinkText("Locations"), null, null);
		} 
	}

	protected List<String> checkPageError(WebDriver driver) throws InvalidInputException, SeleniumException, VerificationException {
		WebElement errorMessage = driver.findElement(new By.ById("ucEventRegTypeSettings_feedback"));
		List<String> errors = new ArrayList<String>();
		errors.add(errorMessage.getText());
		return errors;
	}

	protected void performAction(WebDriver driver, ActionType actionType)throws InvalidInputException, SeleniumException, VerificationException {

		if(actionType.equals(ActionType.CREATE_NEW_REGISTRATION_TYPE_FOR_INDIVIDUALS)){
			CreateNewRegistrationTypeforIndividuals(driver);
		}	
	}

	private void CreateNewRegistrationTypeforIndividuals(WebDriver driver) throws SeleniumException {
		performInteraction(driver, OperationType.TYPE, "Name", new By.ById("ucEventRegTypeSettings_textboxName"), name, EventSetUpGenerator.registrationName());
		performInteraction(driver, OperationType.TYPE, "Export ID", new By.ById("ucEventRegTypeSettings_textboxExportID"), exportID, EventSetUpGenerator.registrationTypeExportID());
		
		performInteraction(driver, OperationType.TOGGLE, "Individuals checkbox",new By.ById("ucEventRegTypeSettings_checkboxIndividual"), individuals, defaultValue);
		performInteraction(driver, OperationType.TOGGLE, "Team Captains checkbox",new By.ById("ucEventRegTypeSettings_checkboxTeamCaptain"), team_captains, defaultValue);
		performInteraction(driver, OperationType.TOGGLE, "Team Members checkbox",new By.ById("ucEventRegTypeSettings_checkboxTeamMember"), team_members, defaultValue);

		performInteraction(driver, OperationType.SELECT, "Language", new By.ById("ucEventRegTypeSettings_ucSelectLanguage_dropdownlistLanguages"), language, LocationSetUpGenerator.language());
		performInteraction(driver, OperationType.TYPE, "Title", new By.ById("ucEventRegTypeSettings_textboxTitle"), title,  EventSetUpGenerator.registrationTypeDisplayName());
		performInteraction(driver, OperationType.TYPE, "Short Description", new By.ById("ucEventRegTypeSettings_textboxShortDescription"), short_description, EventSetUpGenerator.registrationTypeDescriptionEn());
		performInteraction(driver, OperationType.TYPE, "Description: Self Select",new By.ById("ucEventRegTypeSettings_textboxSelfSelect"), description_self_select, EventSetUpGenerator.registrationTypeDescriptionEn());
		performInteraction(driver, OperationType.TYPE, "Description: Business Rules", new By.ById("ucEventRegTypeSettings_textboxBusinessRules"), Description_business_rules, EventSetUpGenerator.registrationTypeDescriptionEn());
	}

}
