package util;

import java.util.Properties;

import page.Page;
import page.impl.*;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.PageFactoryException;

public class PageFactory {
	
	public static Page makePage(XMLFileReader params, PageType type, int stepIndex) throws InvalidInputException, PageFactoryException {
		switch (type) 
		{
			case EVENT_UPDATE:
				return new EventUpdate(params, stepIndex);
			case LOGIN:
				return new Login(params, stepIndex);
			case START:
				return new Start(params, stepIndex);
			case EVENT_SUMMARY:
				return new EventSummary(params, stepIndex);
			case EVENT_SELECT:
				return new EventSelect(params, stepIndex);
			case EVENT_SETUP_WIZARD:
				return new EventSetupWizard(params, stepIndex);
			case EVENT_SETTINGS:
				return new EventSettings(params, stepIndex);
			case EVENT_LOCATIONS:
				return new EventLocations(params, stepIndex);
			case EVENT_LOCATION_CREATE_CONTENT:
				return new EventLocationCreateContent(params, stepIndex);
			case EVENT_LOCATION_CREATE_DETAILS:
				return new EventLocationCreateDetails(params, stepIndex);	
			case EVENT_LOCATION_UPDATE_CONTENT:
				return new EventLocationUpdateContent(params, stepIndex);
			case EVENT_LOCATION_UPDATE_DETAILS:
				return new EventLocationUpdateDetails(params, stepIndex);	
			case EVENT_LOCATION_DETAILS:
				return new EventLocationDetails(params, stepIndex);
			case EVENT_LOCATION_TYPE_CREATE:
				return new EventLocationTypeCreate(params, stepIndex);	
			case EVENT_LOCATION_TYPE_UPDATE:
				return new EventLocationTypeUpdate(params, stepIndex);
			case EVENT_REG_TYPE_MANAGE:
				return new EventRegTypeManage(params, stepIndex);
			case EVENT_REG_TYPE_IND_SETTINGS:
				return new EventRegTypeIndSettings(params, stepIndex);
			case EVENT_REG_TYPE_IND_BIZ_RULES:
				return new EventRegTypeIndBizRules(params, stepIndex);
			case EVENT_REG_TYPE_IND_LOCATIONS:
				return new EventRegTypeIndLocations(params, stepIndex);
				
			default:		
				throw new PageFactoryException(type + " does not exist in Page factory");
		}
	}
}



