package page.impl;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import page.Page;
import util.XMLFileReader;
import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;

public class EventSelect extends Page {
	
	public EventSelect(XMLFileReader params, int stepIndex) throws InvalidInputException {
		super(params,stepIndex);
//		PageType pageType = PageType.fromString(this.getClass().getSimpleName());
		
		this.nextPages.add(PageType.EVENT_SETUP_WIZARD);
		this.nextPages.add(PageType.EVENT_SUMMARY);
	}

	@Override
	protected List<String> checkPageError(WebDriver driver) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void Goto(WebDriver driver, PageType nextPage) throws SeleniumException {
		if (PageType.EVENT_SETUP_WIZARD.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "", new By.ByLinkText("Setup New Event"), null, null);
		}
	}

	@Override
	protected void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException {
		// TODO Auto-generated method stub
		
	}
}
