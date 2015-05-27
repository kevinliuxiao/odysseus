package util;
import java.util.ArrayList;
import java.util.List;

import page.impl.EventRegTypeIndBizRules;
import enumeration.ActionType;
import enumeration.PageType;
import exception.InvalidInputException;

public class ActionFactory 
{
	private static List<ActionType> actionList = new ArrayList<ActionType>();
	
//	private static void addDefaultAction(List<ActionType> actionList)
//	{
//		actionList.add(ActionType.GOTO);
//	}

	public static List<ActionType> actionList(PageType pageType) {	
		
		List<ActionType> actionList = new ArrayList<ActionType>();
//		addDefaultAction(actionList);
		
		switch (pageType) 
		{
			case EVENT_UPDATE:
				break;
			case LOGIN:
				actionList.add(ActionType.ADMIN_LOGIN);
				break;
			case START:
				break;
			case EVENT_SUMMARY:
				actionList.add(ActionType.VERIFY_GENERAL_INFO);
				actionList.add(ActionType.VERIFY_FUNDRAISING_SUMMARY);
				break;
			case EVENT_SETUP_WIZARD:				
				actionList.add(ActionType.MET_EVENT_SETUP);
				actionList.add(ActionType.DD_EVENT_SETUP);
				actionList.add(ActionType.TRIBUTE_EVENT_SETUP);
				actionList.add(ActionType.PI_EVENT_SETUP);
				actionList.add(ActionType.PET_EVENT_SETUP);
				break;
			case EVENT_SETTINGS:				
				actionList.add(ActionType.MET_EVENT_SETTING);
				actionList.add(ActionType.DD_EVENT_SETTING);
				actionList.add(ActionType.TRIBUTE_EVENT_SETTING);
				actionList.add(ActionType.PI_EVENT_SETTING);
				actionList.add(ActionType.PET_EVENT_SETTING);
				break;	
			case EVENT_LOCATIONS:				
				actionList.add(ActionType.SETUP_LOCATION_SEARCH_OPTIONS);
				break;	
			case EVENT_LOCATION_CREATE_CONTENT:				
				actionList.add(ActionType.CREATE_NEW_LOCATION);
				break;	
			case EVENT_LOCATION_UPDATE_CONTENT:				
				actionList.add(ActionType.CREATE_NEW_LOCATION);
				break;	
			case EVENT_LOCATION_UPDATE_DETAILS:	
				actionList.add(ActionType.SETUP_LOCATION_EVENT_DETAILS);
				actionList.add(ActionType.CREATE_WAVE);
				break;	
			case EVENT_LOCATION_CREATE_DETAILS:	
				actionList.add(ActionType.SETUP_LOCATION_EVENT_DETAILS);
				actionList.add(ActionType.CREATE_WAVE);
				break;	
			case EVENT_LOCATION_DETAILS:	
				actionList.add(ActionType.VERIFY_LOCATION_GENERAL_INFO);
				actionList.add(ActionType.VERIFY_LOCATION_FUNDRAISING_SUMMARY);
				actionList.add(ActionType.EDIT_LOCATION_GENERAL_INFO);
				actionList.add(ActionType.DELETE_LOCATION);
				break;	
			case EVENT_LOCATION_TYPE_CREATE:	
				actionList.add(ActionType.SETUP_NEW_LOCATION_TYPE);
				break;	
			case EVENT_LOCATION_TYPE_UPDATE:	
				actionList.add(ActionType.UPDATE_LOCATION_TYPE);
				break;	
			case EVENT_REG_TYPE_IND_SETTINGS:	
				actionList.add(ActionType.CREATE_NEW_REGISTRATION_TYPE_FOR_INDIVIDUALS);
				break;	
			case EVENT_REG_TYPE_IND_BIZ_RULES:
				actionList.add(ActionType.SETUP_BIZ_RULES);
				break;	
			case EVENT_REG_TYPE_IND_LOCATIONS:	
				actionList.add(ActionType.SETUP_LOCATIONS);				
				break;	
				
			default:		
		}
		return actionList;
	}
}
