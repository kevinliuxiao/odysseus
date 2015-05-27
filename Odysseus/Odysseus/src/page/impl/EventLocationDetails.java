package page.impl;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;

import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;
import exception.VerificationException;
import page.Page;
import util.XMLFileReader;

public class EventLocationDetails extends Page {

	public EventLocationDetails(XMLFileReader params, int stepIndex) throws InvalidInputException {
		super(params,stepIndex);
		nextPages.add(PageType.EVENT_LOCATIONS);
	
		this.nextPages.add(PageType.EVENT_LOCATION_RECEIPT_DETAILS);
		this.nextPages.add(PageType.EVENT_LOCATION_UPDATE_DETAILS);
		this.nextPages.add(PageType.EVENT_SUMMARY);
		this.nextPages.add(PageType.EVENT_LOCATION_UPDATE_CONTENT);
		
	}

	protected void Goto(WebDriver driver, PageType nextPage) throws SeleniumException {
		
		if (PageType.EVENT_LOCATION_RECEIPT_DETAILS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "", new ById("hyperLinkEditTaxBundle"), null, null);
		} else if (PageType.EVENT_SUMMARY.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "", new By.ByLinkText("Event"), null, null);
		} else if (PageType.EVENT_LOCATION_UPDATE_DETAILS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "", new By.ByLinkText("Edit Location Details"), null, null);
		}else if (PageType.EVENT_LOCATION_UPDATE_CONTENT.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "", new By.ByLinkText("Edit Information"), null, null);
		}
	}

	protected List<String> checkPageError(WebDriver driver) {
		return null;
	}

	protected void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException, SeleniumException, VerificationException {
		if(actionType.equals(ActionType.VERIFY_LOCATION_GENERAL_INFO)){
			verifyLocationGeneralInfo(driver);
		}
		else if(actionType.equals(ActionType.VERIFY_LOCATION_FUNDRAISING_SUMMARY)){
			verifyLocationFundraisingSummary(driver);
		}
		else if(actionType.equals(ActionType.DELETE_LOCATION)){
			deleteLocation(driver);
		}
	}

	private void deleteLocation(WebDriver driver) throws SeleniumException {
		performInteraction(driver, OperationType.CLICK, "", new ById("linkbuttonDeleteLocation"), null, null);
	}
	private void verifyLocationFundraisingSummary(WebDriver driver) {

	}
	private void verifyLocationGeneralInfo(WebDriver driver) {
	
	}
}
