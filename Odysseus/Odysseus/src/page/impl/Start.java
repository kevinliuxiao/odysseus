package page.impl;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By.ById;

import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;
import page.Page;
import runner.TestRunner;
import util.XMLFileReader;

public class Start extends Page{
	
	public Start(XMLFileReader params, int stepIndex) throws InvalidInputException
	{
		super(params,stepIndex);
		this.nextPages.add(PageType.EVENT_SELECT);
		this.nextPages.add(PageType.FIND_CONSTITUENT);
		this.nextPages.add(PageType.DEBATCH_SELECT_BATCH);
		this.nextPages.add(PageType.CONFIG_ADMINS);
	}

	protected List<String> checkPageError()
	{
		return null;
	}

	protected void Goto(WebDriver driver, PageType nextPage) throws SeleniumException {
				
		if (PageType.EVENT_SELECT.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Event Tab", new ById("ucBodyHead_hyperlinkEventTab"), null,  null);
		} else if (PageType.FIND_CONSTITUENT.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Relationship Tab", new ById("ucBodyHead_hyperlinkRelationshipsTab"),  null,  null);
		} else if (PageType.DEBATCH_SELECT_BATCH.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "DataEntry Tab", new ById("ucBodyHead_hyperlinkDataEntryTab"),  null,  null);
		} else if (PageType.CONFIG_ADMINS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Configuration Tab",new ById("ucBodyHead_hyperlinkConfigurationTab"),  null,  null);
		} 
	}

	@Override
	protected List<String> checkPageError(WebDriver driver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException {
		// TODO Auto-generated method stub
		
	}

}
