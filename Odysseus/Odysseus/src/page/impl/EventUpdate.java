package page.impl;

import java.util.List;

import org.openqa.selenium.WebDriver;

import enumeration.ActionType;
import enumeration.PageType;
import exception.InvalidInputException;
import page.Page;
import util.XMLFileReader;

public class EventUpdate extends Page{
	
	public EventUpdate(XMLFileReader params, int stepIndex) throws InvalidInputException
	{
		super(params,stepIndex);
	}

	public void Goto(WebDriver driver, PageType nextPage) {

	}
	
	protected List<String> checkPageError()
	{
		return null;
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
