package page.impl;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;
import exception.VerificationException;
import page.Page;
import page.PageObject;
import util.XMLFileReader;

public class EventRegTypeManage extends Page{

	private PageObject edit_regType_individual_name;
	private PageObject edit_regType_group_name;
	private PageObject edit_corporateTeam_name;
	
	public EventRegTypeManage(XMLFileReader params, int stepIndex) throws InvalidInputException {
		super(params,stepIndex);
		nextPages.add(PageType.EVENT_REG_TYPE_IND_SETTINGS);
		nextPages.add(PageType.EVENT_REG_TYPE_TEAM_SETTINGS);
		nextPages.add(PageType.CORPORATE_TEAM_PAGE);
		
		nextPages.add(PageType.EVENT_SETTINGS);
		nextPages.add(PageType.EVENT_SUMMARY);
		nextPages.add(PageType.EVENT_LOCATIONS);
		nextPages.add(PageType.VOLUNTEER_MANAGEMENT);
		nextPages.add(PageType.EVENT_SUGGESTED_DONATION_AMOUNTS);
		nextPages.add(PageType.EVENT_WAIVER);
		nextPages.add(PageType.EVENT_TICKETING_MANAGE);
		nextPages.add(PageType.EVENT_PAPER_PLEDGE);
		nextPages.add(PageType.CONTENT_QUESTION_LIST);
		nextPages.add(PageType.EVENT_WIDGETS_MANAGE);
		nextPages.add(PageType.EVENT_ECARD_MANAGE);
		nextPages.add(PageType.EVENT_EMAILS);
		nextPages.add(PageType.EVENT_PAYMENT_TYPES);
		nextPages.add(PageType.CONTENT_SELECT_PAGE);
		nextPages.add(PageType.EVENT_MEDIA_VIEWER_MANAGE);
		nextPages.add(PageType.EVENT_USER_INTERFACE);
		

		edit_regType_individual_name = new PageObject(params, stepIndex, "edit_regType_individual_name");
		edit_regType_group_name = new PageObject(params, stepIndex, "edit_regType_group_name");
		edit_corporateTeam_name = new PageObject(params, stepIndex, "edit_corporateTeam_name");
	}

	protected void Goto(WebDriver driver, PageType nextPage)throws InvalidInputException, SeleniumException,VerificationException {
		if (PageType.EVENT_REG_TYPE_IND_SETTINGS.equals(nextPage)) {
			if (!edit_regType_individual_name.value().isEmpty()) {
				performInteraction(driver, OperationType.CLICK, "EDIT_REGTYPE_IND", new By.ByLinkText(edit_regType_individual_name.value()), null, null);
			} else {
				performInteraction(driver, OperationType.CLICK, "Create New Registration Type for Individuals", new By.ById("linkCreateRegTypeInd"),  null, null);
			}
		} else if (PageType.EVENT_REG_TYPE_TEAM_SETTINGS.equals(nextPage)) {
			if (!edit_regType_group_name.value().isEmpty()) {
				performInteraction(driver, OperationType.CLICK, "EDIT_REGTYPE_GROUP", new By.ByLinkText(edit_regType_group_name.value()),  null, null);
			} else {
				performInteraction(driver, OperationType.CLICK, "Create New Registration Type for Groups", new By.ById("linkCreateRegTypeTeam"),  null, null);
			}
		} else if (PageType.CORPORATE_TEAM_PAGE.equals(nextPage)) {
			if (!edit_regType_group_name.value().isEmpty()) {
				performInteraction(driver, OperationType.CLICK, "Corporate Team Add Button", new By.ById("btnAddCorporateTeam"),  null, null);
			}
//			else{
//				
//			}
		} 
		// side menu items
		else if (PageType.EVENT_SETTINGS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Event Settings", new By.ByLinkText("Event Settings"),  null, null);
		}
		else if (PageType.EVENT_SUMMARY.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Submit Button", new By.ById("buttonSubmit"),  null, null);
		}
		else if (PageType.EVENT_LOCATIONS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Locations", new By.ByLinkText("Locations"), null, null);
		}
		else if (PageType.VOLUNTEER_MANAGEMENT.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Volunteer Management", new By.ByLinkText("Volunteer Management"),  null, null);
		}
		else if (PageType.EVENT_SUGGESTED_DONATION_AMOUNTS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Suggested Donation Amounts", new By.ByLinkText("Suggested Donation Amounts"),  null, null);
		}
		else if (PageType.EVENT_WAIVER.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Waiver", new By.ByLinkText("Waiver"),  null, null);
		}
		else if (PageType.EVENT_TICKETING_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Purchase Items", new By.ByLinkText("Purchase Items"),  null, null);
		}
		else if (PageType.EVENT_PAPER_PLEDGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Downloadable Forms", new By.ByLinkText("Downloadable Forms"),  null, null);
		}
		else if (PageType.CONTENT_QUESTION_LIST.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "User Defined Fields", new By.ByLinkText("User Defined Fields"),  null, null);
		}
		else if (PageType.EVENT_WIDGETS_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Widgets", new By.ByLinkText("Widgets"),  null, null);
		}
		else if (PageType.EVENT_ECARD_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Card Management", new By.ByLinkText("Card Management"),  null, null);
		}
		else if (PageType.EVENT_EMAILS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Emails", new By.ByLinkText("Emails"), null, null);
		}
		else if (PageType.EVENT_PAYMENT_TYPES.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Payment Types", new By.ByLinkText("Payment Types"),  null, null);
		}
		else if (PageType.CONTENT_SELECT_PAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Content", new By.ByLinkText("Content"),  null, null);
		}
		else if (PageType.EVENT_MEDIA_VIEWER_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Media", new By.ByLinkText("Media"),  null, null);
		}
		else if (PageType.EVENT_USER_INTERFACE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Style Settings", new By.ByLinkText("Style Settings"), null, null);
		}
	}

	protected List<String> checkPageError(WebDriver driver)throws InvalidInputException, SeleniumException, VerificationException {
		return null;
	}

	protected void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException, SeleniumException, VerificationException {
		
	}

}
