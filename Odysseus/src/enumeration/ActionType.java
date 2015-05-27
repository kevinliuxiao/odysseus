package enumeration;

import exception.InvalidInputException;

public enum ActionType { 

	GOTO("goto"),
	ADMIN_LOGIN("admin_login"),
	
	MET_EVENT_SETUP("MET_event_setup"),
	DD_EVENT_SETUP("DD_event_setup"),
	TRIBUTE_EVENT_SETUP("TRIBUTE_event_setup"),
	PI_EVENT_SETUP("PI_event_setup"),
	PET_EVENT_SETUP("PET_event_setup"),
	VERIFY_GENERAL_INFO("verify_general_info"),
	VERIFY_FUNDRAISING_SUMMARY("verify_fundraising_summary"),
	
	MET_EVENT_SETTING("MET_event_setting"),
	DD_EVENT_SETTING("DD_event_setting"), 
	TRIBUTE_EVENT_SETTING("TRIBUTE_event_setting"),
	PI_EVENT_SETTING("PI_event_setting"), 
	PET_EVENT_SETTING("PET_event_setting"),
	
	SETUP_LOCATION_SEARCH_OPTIONS ("setup_location_search_options"),
	CREATE_NEW_LOCATION ("create_new_location"),
	CREATE_WAVE ("create_wave"),
	SETUP_LOCATION_EVENT_DETAILS("setup_location_event_details"),
	
	VERIFY_LOCATION_GENERAL_INFO ("verify_location_general_info"),
	VERIFY_LOCATION_FUNDRAISING_SUMMARY ("verify_location_fundraising_summary"),
	EDIT_LOCATION_GENERAL_INFO ("edit_location_general_info"),
	DELETE_LOCATION("delete_location"),
	SETUP_NEW_LOCATION_TYPE("setup_new_location_type"),
	UPDATE_LOCATION_TYPE("update_location_type"),
	CREATE_NEW_REGISTRATION_TYPE_FOR_INDIVIDUALS("create_new_registration_type_for_individuals"),
	SETUP_BIZ_RULES("setup_biz_rules"),
	SETUP_LOCATIONS("setup_locations");
	
//	CREATE_MET("create_met"),
//	CREATE_DD("create_dd"),
//	CREATE_TRIBUTE("create_tribute"),
//	CREATE_PI("create_pi"),
//	CREATE_PET("create_pet"),
//	CREATE_NEW_EVENT("create_new_event"),
//	COPY_NEW_EVENT("copy_new_event");
	
	private final String actionKeyword;
	
	ActionType(String actionKeyword) {
		this.actionKeyword = actionKeyword;
	}
	
	public String toString() {
		return actionKeyword;
	}
	
	public static ActionType fromString(String text) throws InvalidInputException {
		
		text = text.replaceAll("^\\s+", "");
		text = text.trim();
		
		if (text != null) {
	      for (ActionType a : ActionType.values()) {
	        if (text.equalsIgnoreCase(a.toString())) {
	          return a;
	        }
	      }
	    }
	    throw new InvalidInputException("ActionType: '" + text + "' does not exist in ActionType Class");
	  }
}