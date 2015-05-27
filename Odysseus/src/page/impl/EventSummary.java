package page.impl;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;
import page.Page;
import page.PageObject;
import util.XMLFileReader;

public class EventSummary extends Page{
	
	public EventSummary(XMLFileReader params, int stepIndex) throws InvalidInputException
	{
		super(params,stepIndex);
		this.nextPages.add(PageType.EVENT_EMAIL_PREFACE);
		this.nextPages.add(PageType.EVENT_EMAILS);
		this.nextPages.add(PageType.EVENT_SETTINGS);
		this.nextPages.add(PageType.EVENT_REG_TYPE_MANAGE);
		this.nextPages.add(PageType.EVENT_USER_INTERFACE);
		this.nextPages.add(PageType.EVENT_ECARD_MANAGE);
		this.nextPages.add(PageType.EVENT_PAPER_PLEDGE);
		this.nextPages.add(PageType.EVENT_TICKETING_MANAGE);
		this.nextPages.add(PageType.EVENT_UPDATE);
		this.nextPages.add(PageType.EVENT_SUMMARY_LINKS);
		this.nextPages.add(PageType.EVENT_PAYMENT_TYPES);
		this.nextPages.add(PageType.EVENT_LOCATIONS);
		this.nextPages.add(PageType.EVENT_WAIVER);
		this.nextPages.add(PageType.EVENT_WIDGETS_MANAGE);
		this.nextPages.add(PageType.EVENT_OPEN_CONFIRM);
		this.nextPages.add(PageType.EVENT_CLOSE_CONFIRM);
	}

	public void Goto(WebDriver driver, PageType nextPage) throws SeleniumException {
		
		if (PageType.EVENT_OPEN_CONFIRM.equals(nextPage)){
		    performInteraction(driver, OperationType.CLICK, "Open Public Access To Event", new By.ById("linkbuttonEventOpen"), null, null);
	     }
		else if (PageType.EVENT_CLOSE_CONFIRM.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Close Public Access To Event", new By.ById("linkbuttonEventClose"), null, null);
		}
		// side menu items
		if (PageType.EVENT_SETTINGS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Event Settings", new By.ByLinkText("Event Settings"), null, null);
		}
		else if (PageType.EVENT_SUMMARY.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Submit Button", new By.ById("buttonSubmit"), null, null);
		}
		else if (PageType.EVENT_LOCATIONS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Locations", new By.ByLinkText("Locations"), null, null);
		}
		else if (PageType.EVENT_REG_TYPE_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Registrations", new By.ByLinkText("Registrations"), null, null);
		}
		else if (PageType.VOLUNTEER_MANAGEMENT.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Volunteer Management", new By.ByLinkText("Volunteer Management"), null, null);
		}
		else if (PageType.EVENT_SUGGESTED_DONATION_AMOUNTS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Suggested Donation Amounts", new By.ByLinkText("Suggested Donation Amounts"), null, null);
		}
		else if (PageType.EVENT_WAIVER.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Waiver", new By.ByLinkText("Waiver"), null, null);
		}
		else if (PageType.EVENT_TICKETING_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Purchase Items", new By.ByLinkText("Purchase Items"), null, null);
		}
		else if (PageType.EVENT_PAPER_PLEDGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Downloadable Forms", new By.ByLinkText("Downloadable Forms"), null, null);
		}
		else if (PageType.CONTENT_QUESTION_LIST.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "User Defined Fields", new By.ByLinkText("User Defined Fields"), null, null);
		}
		else if (PageType.EVENT_WIDGETS_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Widgets", new By.ByLinkText("Widgets"), null, null);
		}
		else if (PageType.EVENT_ECARD_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Card Management", new By.ByLinkText("Card Management"), null, null);
		}
		else if (PageType.EVENT_EMAILS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Emails", new By.ByLinkText("Emails"), null, null);
		}
		else if (PageType.EVENT_PAYMENT_TYPES.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Payment Types", new By.ByLinkText("Payment Types"), null, null);
		}
		else if (PageType.CONTENT_SELECT_PAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Content", new By.ByLinkText("Content"), null, null);
		}
		else if (PageType.EVENT_MEDIA_VIEWER_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Media", new By.ByLinkText("Media"), null, null);
		}
		else if (PageType.EVENT_USER_INTERFACE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Style Settings", new By.ByLinkText("Style Settings"), null, null);
		}
	}
	
	protected List<String> checkPageError(){
		return null;
	}

	@Override
	protected List<String> checkPageError(WebDriver driver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException {
		if(actionType.equals(ActionType.VERIFY_GENERAL_INFO)){
			verifyGeneralInfo(driver);
		}
		else if(actionType.equals(ActionType.VERIFY_FUNDRAISING_SUMMARY)){
			verifyFundraisingSummary(driver);
		}		
	}

	private void verifyGeneralInfo(WebDriver driver) {
		
	}

	private void verifyFundraisingSummary(WebDriver driver) {
		
	}


}
