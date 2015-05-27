package page.impl;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By.ById;

import page.Page;
import page.PageGroupObject;
import page.PageObject;
import util.WebUtils;
import util.XMLFileReader;
import enumeration.ActionType;
import enumeration.OperationType;
import enumeration.PageType;
import exception.InvalidInputException;
import exception.SeleniumException;
import generators.EventSetUpGenerator;

public class EventSetupWizard extends Page {
	
	private PageObject event_type; 
	private PageObject language;
//	private  PageObject default_content_and_configuration;

	private PageObject create_new_event;
	private PageObject participants_register_both_individual_and_teams;
	private PageObject require_multiple_registration_types;
	private PageObject multiple_locations_for_your_event;
    private PageObject participants_required_to_accept_a_waiver;
    
	private PageObject eventname_en;
	private PageObject eventname_fr;
	
	private PageObject description_en; 
	private PageObject description_fr; 
	
	private PageObject payment_gateway;
	private PageObject fundraising_goal;
	private PageObject export_eventid;
	
	private PageObject event_start_date;
	private PageObject event_end_date;
	private PageObject registration_start_date;
	private PageObject registration_end_date;
	
	private PageObject event_start_time;
	private PageObject event_end_time;
	private PageObject registration_start_time;
	private PageObject registration_end_time;
	
	private PageObject sender_name;
	private PageObject reply_to_email;

	
	private PageObject allow_donor_to_donate_directly_event_or_donation_must_be_made_to_registrant_or_team;		
	private PageObject allow_donors_to_donate_to_team;	
	private PageObject split_team_donations_amongst_team_members_or_assign_directly_to_team;	
	private PageObject registrants_to_be_able_to_sponsor_themselves;
	private PageObject allow_donors_to_enter_offline_donations;	
	private PageObject enforce_a_minimum_donation_amount;
	private PageObject minimum_donation_amount;
	private PageObject donor_cover_the_processing_fee;	
	private PageObject processing_fee_percentage;
	private PageObject processing_fee_amount;
	private PageObject issue_tax_receipts_for_donations;
	private PageObject tax_receipts_templete;
	private PageObject tax_receipts_french_templete;
		 
	private PageObject waiver_content_en;
	private PageObject waiver_content_fr;
	private PageObject header_en;
	private PageObject header_fr;
	private PageObject footer_en;
	private PageObject footer_fr;
	private PageObject mobile_header_en;
	private PageObject mobile_header_fr;
	private PageObject mobile_footer_en;
	private PageObject mobile_footer_fr;
	
//	private PageObject location_name;
//	private PageObject location_name_french;
//	private PageObject location_exportid;
//	
//	private PageObject registrationtype_name;
//	private PageObject registrationtype_display_name_en;
//	private PageObject registrationtype_display_name_fr;
//	private PageObject registrationtype_export_id;
//	private PageObject registrationtype_description_en;
//	private PageObject registrationtype_description_fr;
//	private PageObject registrationtype_is_fee_associated;
//	private PageObject registrationtype_fee;

	
	private boolean default_selection = true;
	private int locations_number = 3;
	private int registration_types_number = 2;
	private PageGroupObject locations;
	private PageGroupObject registration_types;


	public EventSetupWizard(XMLFileReader params, int stepIndex) throws InvalidInputException {
		super(params,stepIndex);
		
		//PageType pageType = PageType.fromString(this.getClass().getSimpleName());		
		this.nextPages.add(PageType.EVENT_SUMMARY);
		this.nextPages.add(PageType.EVENT_TICKETING_MANAGE);
		this.nextPages.add(PageType.EVENT_EMAILS);
		this.nextPages.add(PageType.CONTENT_SELECT_PAGE);
		
		this.event_type = new PageObject(params, stepIndex, "event_type");  
		this.language = new PageObject(params, stepIndex, "language"); 
//		this.DEFAULT_CONTENT_AND_CONFIGURATION= new PageObject(params, stepIndex,"DEFAULT_CONTENT_AND_CONFIGURATION"); 

		this.create_new_event = new PageObject(params, stepIndex, "create_new_event");  
		
		
		this.participants_register_both_individual_and_teams = new PageObject(params, stepIndex, "participants_register_both_individual_and_teams"); 
		this.require_multiple_registration_types= new PageObject(params, stepIndex, "require_multiple_registration_types"); 
		this.multiple_locations_for_your_event = new PageObject(params, stepIndex, "multiple_locations_for_your_event");  
		this.participants_required_to_accept_a_waiver = new PageObject(params, stepIndex, "participants_required_to_accept_a_waiver");  
		
		
		eventname_en = new PageObject(params, stepIndex, "eventname_en");  
		eventname_fr = new PageObject(params, stepIndex, "eventname_fr");

		description_en = new PageObject(params, stepIndex, "description_en");
		description_fr = new PageObject(params, stepIndex, "description_fr");
				
		fundraising_goal = new PageObject(params, stepIndex, "fundraising_goal");
		
		
		event_start_date = new PageObject(params, stepIndex, "event_start_date");
		event_end_date = new PageObject(params, stepIndex, "event_end_date");
		registration_start_date = new PageObject(params, stepIndex, "registration_start_date");
		registration_end_date = new PageObject(params, stepIndex, "registration_end_date");
		
		event_start_time = new PageObject(params, stepIndex, "event_start_time");
		event_end_time = new PageObject(params, stepIndex, "event_end_time");
		registration_start_time = new PageObject(params, stepIndex, "registration_start_time");
		registration_end_time = new PageObject(params, stepIndex, "registration_end_time");
		
		export_eventid = new PageObject(params, stepIndex, "export_eventid");
		payment_gateway = new PageObject(params, stepIndex, "payment_gateway"); 
		
		sender_name = new PageObject(params, stepIndex,  "sender_name");
		reply_to_email = new PageObject(params, stepIndex,  "reply_to_email"); 

		
		allow_donor_to_donate_directly_event_or_donation_must_be_made_to_registrant_or_team = new PageObject(params, stepIndex,  "allow_donor_to_donate_directly_event_or_donation_must_be_made_to_registrant_or_team");		
		allow_donors_to_donate_to_team = new PageObject(params, stepIndex, "allow_donors_to_donate_to_team");		
		split_team_donations_amongst_team_members_or_assign_directly_to_team = new PageObject(params, stepIndex,  "split_team_donations_amongst_team_members_or_assign_directly_to_team");	
		registrants_to_be_able_to_sponsor_themselves = new PageObject(params, stepIndex, "registrants_to_be_able_to_sponsor_themselves");
		allow_donors_to_enter_offline_donations = new PageObject(params, stepIndex, "allow_donors_to_enter_offline_donations");	
		enforce_a_minimum_donation_amount = new PageObject(params, stepIndex, "enforce_a_minimum_donation_amount");	
		minimum_donation_amount = new PageObject(params, stepIndex, "minimum_donation_amount");	
		donor_cover_the_processing_fee = new PageObject(params, stepIndex, "donor_cover_the_processing_fee");
		processing_fee_percentage = new PageObject(params, stepIndex, "processing_fee_percentage");
		processing_fee_amount = new PageObject(params, stepIndex, "processing_fee_amount");
		
		issue_tax_receipts_for_donations = new PageObject(params, stepIndex, "issue_tax_receipts_for_donations");
		tax_receipts_templete = new PageObject(params, stepIndex, "tax_receipts_templete");
		tax_receipts_french_templete = new PageObject(params, stepIndex, "tax_receipts_french_templete");	
		
		waiver_content_en = new PageObject(params, stepIndex, "waiver_content_en");
		waiver_content_fr = new PageObject(params, stepIndex, "waiver_content_fr");
		

		header_en = new PageObject(params, stepIndex, "header_en");
		header_fr = new PageObject(params, stepIndex, "header_fr");
		footer_en = new PageObject(params, stepIndex, "footer_en");
		footer_fr = new PageObject(params, stepIndex, "footer_fr");
		mobile_header_en = new PageObject(params, stepIndex, "mobile_header_en");
		mobile_header_fr = new PageObject(params, stepIndex, "mobile_header_fr");
		mobile_footer_en = new PageObject(params, stepIndex, "mobile_footer_en");
		mobile_footer_fr = new PageObject(params, stepIndex, "mobile_footer_fr");
		
//		LOCATIONS = new PageGroupObject(params, "LOCATIONS", stepIndex, "LOCATION_NAME", "LOCATION_NAME_FRENCH", "LOCATION_EXPORTID");
//		REGISTRATION_TYPES = new PageGroupObject(params, "REGISTRATION_TYPES", stepIndex, "REGISTRATIONTYPE_NAME", "REGISTRATIONTYPE_DISPLAY_NAME_EN", 
//														"REGISTRATIONTYPE_DISPLAY_NAME_FR", "REGISTRATIONTYPE_EXPORT_ID", "REGISTRATIONTYPE_DESCRIPTION_EN",
//																	"REGISTRATIONTYPE_DESCRIPTION_FR", "REGISTRATIONTYPE_IS_FEE_ASSOCIATED", "REGISTRATIONTYPE_FEE");
		
//		location_name = new PageObject(params, stepIndex, "location_name");
//		location_name_french = new PageObject(params, stepIndex, "location_name_french");
//		location_exportid = new PageObject(params, stepIndex, "location_exportid");
		
//		registrationtype_name = new PageObject(params, stepIndex, "registrationtype_name");
//		registrationtype_display_name_en = new PageObject(params, stepIndex, "registrationtype_display_name_en");
//		registrationtype_display_name_fr = new PageObject(params, stepIndex, "registrationtype_display_name_fr");
//		
//		registrationtype_export_id = new PageObject(params, stepIndex, "registrationtype_export_id");
//		registrationtype_description_en = new PageObject(params, stepIndex, "registrationtype_description_en");
//		registrationtype_description_fr = new PageObject(params, stepIndex, "registrationtype_description_fr");
//		
//		registrationtype_is_fee_associated = new PageObject(params, stepIndex, "registrationtype_is_fee_associated");
//		registrationtype_fee = new PageObject(params, stepIndex, "registrationtype_fee");
		
		locations = new PageGroupObject(params, "locations", stepIndex, "location_name", "location_name_french", "location_exportid");
		registration_types = new PageGroupObject(params, "registration_types", stepIndex, "registrationtype_name", "registrationtype_display_name_en", 
				"registrationtype_display_name_fr", "registrationtype_export_id", "registrationtype_description_en",
				"registrationtype_description_fr", "registrationtype_is_fee_associated", "registrationtype_fee");
	}

	protected List<String> checkPageError(WebDriver driver) {
		// TODO Auto-generated method stub
		return null;
	}

	protected void Goto(WebDriver driver, PageType nextPage) throws SeleniumException {
		if (PageType.EVENT_SUMMARY.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "EventSummary Page", new ById("wcEventSetupWizard_btDone"), null, "");	
		}
		else if (PageType.EVENT_TICKETING_MANAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Adding Purchase items to your event", new By.ByLinkText("Adding Purchase items to your event"),  null,  null);
		}
		else if (PageType.EVENT_EMAILS.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Defining your own email content", new By.ByLinkText("Defining your own email content"),  null,  null);	
		}
		else if (PageType.CONTENT_SELECT_PAGE.equals(nextPage)) {
			performInteraction(driver, OperationType.CLICK, "Customizing your event pages", new By.ByLinkText("Customizing your event pages"),  null,  null);
		}
	}

	@Override
	protected void performAction(WebDriver driver, ActionType actionType) throws InvalidInputException, SeleniumException {		
		if(actionType.equals(ActionType.MET_EVENT_SETUP)){
			METEventSetUp(driver);
		}
		else if(actionType.equals(ActionType.DD_EVENT_SETUP)){
			DDEventSetUp(driver);
		}
		else if(actionType.equals(ActionType.TRIBUTE_EVENT_SETUP)){
			TRIBUTEEventSetUp(driver);		
		}
		else if(actionType.equals(ActionType.PI_EVENT_SETUP)){
			PIEventSetUp(driver);
		}
		else if(actionType.equals(ActionType.PET_EVENT_SETUP)){
			PETEventSetUp(driver);
		}
	}
	
	/***************************************************************************************************************************************************************************
	 ***************************************************************************************************************************************************************************
	 *************************************************************************************************************************************************************************** 
	 *****************************************************************************METEventSetUp*************************************************************************************** 
	 * @throws SeleniumException 
	 *************************************************************************************************************************************************************************** 
	 *************************************************************************************************************************************************************************** 
	 *************************************************************************************************************************************************************************** 
	 ***************************************************************************************************************************************************************************  
	 */
	
	public void METEventSetUp(WebDriver driver) throws InvalidInputException, SeleniumException {	
		
		
		performInteraction(driver, OperationType.CLICK, "Event/Campaign", new ById("wcEventSetupWizard_rblTemplateTypes_0"), null,  null);
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StartNavigationTemplateContainerID_btNext"),  null,  null);
		
		//==========================================================================STEP 1=======================================================================================	
		
		if(create_new_event.value().equalsIgnoreCase("yes") || create_new_event.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "I want to create a new event", new ById("wcEventSetupWizard_rblNewOrCopy_0"),  null,  null);
			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);
		}
		else if(create_new_event.value().equalsIgnoreCase("no")){		
			performInteraction(driver, OperationType.CLICK, "I want to create a copy of an existing event", new ById("wcEventSetupWizard_rblNewOrCopy_1"),  null,  null);
			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);
		}
				
		//===========================================================================STEP 2======================================================================================
				
		if(language.value().equalsIgnoreCase("both") || language.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Yes, English and French pages", new ById("wcEventSetupWizard_rblLanguages_0"),  null,  null);
		}
		else if(language.value().equalsIgnoreCase("english")){
			performInteraction(driver, OperationType.CLICK, "No, English pages only", new ById("wcEventSetupWizard_rblLanguages_1"),  null,  null);	
		}
					
		if(participants_register_both_individual_and_teams.value().equalsIgnoreCase("yes") || participants_register_both_individual_and_teams.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Yes, participants can register both as individuals and in teams", new ById("wcEventSetupWizard_rblEnableTeams_0"),  null,  null);	
		}
		else if(participants_register_both_individual_and_teams.value().equalsIgnoreCase("no")){
			performInteraction(driver, OperationType.CLICK, "No, participants can only register as individuals", new ById("wcEventSetupWizard_rblEnableTeams_1"), null,  null);
		}
				
		if(require_multiple_registration_types.value().equalsIgnoreCase("yes") || require_multiple_registration_types.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Yes, my event has multiple registation types", new ById("wcEventSetupWizard_rblRegistrations_0"),  null,  null);
		}
		else if(require_multiple_registration_types.value().equalsIgnoreCase("no")){
			performInteraction(driver, OperationType.CLICK, "No, my event does not have multiple registration types", new ById("wcEventSetupWizard_rblRegistrations_1"),  null,  null);
		}
				
		if(multiple_locations_for_your_event.value().equalsIgnoreCase("yes") || multiple_locations_for_your_event.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Yes, my event has multiple locations", new ById("wcEventSetupWizard_rblLocations_0"),  null,  null);
		}
		else if(multiple_locations_for_your_event.value().equalsIgnoreCase("no")){
			performInteraction(driver, OperationType.CLICK, "No, my event does not have muliple locations", new ById("wcEventSetupWizard_rblLocations_1"),  null,  null);
		}
				
		if(participants_required_to_accept_a_waiver.value().equalsIgnoreCase("yes")|| participants_required_to_accept_a_waiver.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Yes, participants are required to accept a waiver", new ById("wcEventSetupWizard_rblWaiver_0"), null,  null);	
		}
		else if(participants_required_to_accept_a_waiver.value().equalsIgnoreCase("no")){
			performInteraction(driver, OperationType.CLICK, "No, participants are not required to accept a waiver", new ById("wcEventSetupWizard_rblWaiver_1"),  null,  null);	
		}

		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null,  null);	
				
		//==========================================================================STEP 3=======================================================================================
			
		performInteraction(driver, OperationType.TYPE, "Event Name (English)", new ById("wcEventSetupWizard_tbEventNameEn"), eventname_en, EventSetUpGenerator.eventNameEn()); 
		performInteraction(driver, OperationType.TYPE, "Event Name (French)", new ById("wcEventSetupWizard_tbEventNameFr"), eventname_fr,  EventSetUpGenerator.eventNameFr()); 

		performInteraction(driver, OperationType.TYPE, "Event Description (English)", new ById("wcEventSetupWizard_tbEventDescription"), description_en, EventSetUpGenerator.descriptionEn());  
		performInteraction(driver, OperationType.TYPE, "Event Description (French)", new ById("wcEventSetupWizard_tbEventDescriptionFr"), description_fr, EventSetUpGenerator.descriptionFr());

		performInteraction(driver, OperationType.TYPE, "Fundraising Goal", new ById("wcEventSetupWizard_tbFundraisingGoal"), fundraising_goal, EventSetUpGenerator.fundraisingGoal());
				
		datePicker(driver, "Event Start", new ById("wcEventSetupWizard_tbEventStartDate"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
				new By.ByXPath("/html/body/div[2]/div[1]"), event_start_date , EventSetUpGenerator.eventStartDate()); 
		performInteraction(driver, OperationType.SELECT, "Event Start Time", new By.ByXPath("//*[@id=\"eventDatePair\"]/div[1]/span/select"), event_start_time, EventSetUpGenerator.eventStartTime()); 
		
		
		datePicker(driver, "Event End", new ById("wcEventSetupWizard_tbEventEndDate"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
				new By.ByXPath("/html/body/div[2]/div[1]"), event_end_date, EventSetUpGenerator.eventEndDate());
		performInteraction(driver, OperationType.SELECT, "Event End Time", new By.ByXPath("//*[@id=\"eventDatePair\"]/div[2]/span/select"), event_end_time, EventSetUpGenerator.eventEndTime()); 
		
		datePicker(driver, "Registration Start", new ById("wcEventSetupWizard_tbRegistrationStartDate"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
				new By.ByXPath("/html/body/div[2]/div[1]"), registration_start_date, EventSetUpGenerator.regStartDate()); 
		performInteraction(driver, OperationType.SELECT, "Registration Start Time", new By.ByXPath("//*[@id=\"wcEventSetupWizard_divRegistrationStart\"]/span/select"), registration_start_time, EventSetUpGenerator.regStartTime()); 
		
		datePicker(driver, "Registration End", new ById("wcEventSetupWizard_tbRegistrationEndDate"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
				new By.ByXPath("/html/body/div[2]/div[1]"),registration_end_date ,EventSetUpGenerator.regEndDate()); 
		performInteraction(driver, OperationType.SELECT, "Registration End Time", new By.ByXPath("//*[@id=\"wcEventSetupWizard_divRegistrationEnd\"]/span/select"), registration_end_time, EventSetUpGenerator.regEndTime()); 
		

		performInteraction(driver, OperationType.TYPE, "Registration End", new ById("wcEventSetupWizard_tbExportEventID"),export_eventid ,EventSetUpGenerator.eventExportID()); 

		performInteraction(driver, OperationType.SELECT, "Payment Gateway", new ById("wcEventSetupWizard_dlPayGateways"), payment_gateway ,EventSetUpGenerator.paymentGateway()); 
	
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);

		//==========================================================================STEP 4=======================================================================================	
		
		int LocationsSize = (locations.size() > locations_number) ? locations.size() : locations_number;
		for(int index= 1; index <= LocationsSize; index++)
		{
			if(index > 3){
				performInteraction(driver, OperationType.CLICK, "Add another location", new By.ByLinkText("Add another location"), null,  null); 
			}
			
			PageObject location_name =  locations.value(index, "location_name");
			PageObject location_name_french = locations.value(index, "location_name_french");
			PageObject location_exportid = locations.value(index, "location_exportid");
			
			performInteraction(driver, OperationType.TYPE, "Location Name" + index, new ById("wcEventSetupWizard_rpLocations_ctl0" + (index - 1) + "_tbLocationName"), location_name ,EventSetUpGenerator.locationName()); 
			performInteraction(driver, OperationType.TYPE, "Location Name French" + index, new ById("wcEventSetupWizard_rpLocations_ctl0" + (index - 1) + "_tbLocationNameFr"), location_name_french ,EventSetUpGenerator.locationNamefr());
			performInteraction(driver, OperationType.TYPE, "Location Export ID" + index, new ById("wcEventSetupWizard_rpLocations_ctl0" + (index - 1) + "_tbLocationExportID"),  location_exportid ,EventSetUpGenerator.locationExportID());
			
//			performInteraction(driver, OperationType.TYPE, "Location Name" + index, new ById("wcEventSetupWizard_rpLocations_ctl0" + (index - 1) + "_tbLocationName"), (!LOCATIONS.value(index, "LOCATION_NAME").isEmpty()) ? LOCATIONS.value(index, "LOCATION_NAME") :  ,EventSetUpGenerator.locationName()); 
//			performInteraction(driver, OperationType.TYPE, "Location Name French" + index, new ById("wcEventSetupWizard_rpLocations_ctl0" + (index - 1) + "_tbLocationNameFr"), (!LOCATIONS.value(index, "LOCATION_NAME_FRENCH").isEmpty()) ? LOCATIONS.value(index, "LOCATION_NAME_FRENCH") :  ,EventSetUpGenerator.locationNamefr());
//			performInteraction(driver, OperationType.TYPE, "Location Export ID" + index, new ById("wcEventSetupWizard_rpLocations_ctl0" + (index - 1) + "_tbLocationExportID"),  (!LOCATIONS.value(index, "LOCATION_EXPORTID").isEmpty()) ? LOCATIONS.value(index, "LOCATION_EXPORTID") :  ,EventSetUpGenerator.locationExportID());
		}
		
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);	

			
		//==========================================================================STEP 5=======================================================================================	
		performInteraction(driver, OperationType.TYPE, "Sender Name", new ById("wcEventSetupWizard_tbSenderName"), sender_name ,EventSetUpGenerator.senderName());
		performInteraction(driver, OperationType.TYPE, "Reply to Email Address", new ById("wcEventSetupWizard_tbReplyTo"), reply_to_email ,EventSetUpGenerator.replyToEmail());
				
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);
				
		
		//==========================================================================STEP 6=======================================================================================	
		
		if(allow_donor_to_donate_directly_event_or_donation_must_be_made_to_registrant_or_team.value().equalsIgnoreCase("yes") || allow_donor_to_donate_directly_event_or_donation_must_be_made_to_registrant_or_team.value().isEmpty()){
			
			performInteraction(driver, OperationType.CLICK, "Yes, in addition to making a donation to a registrant or team, donors can also donate directly to the event", new ById("wcEventSetupWizard_rblDirectDonation_0"),  null,  null);
		}
		else if(allow_donor_to_donate_directly_event_or_donation_must_be_made_to_registrant_or_team.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, all donations must be made to a registrant or a team", new ById("wcEventSetupWizard_rblDirectDonation_1"),  null,  null);	
		}
		
		
		if(allow_donors_to_donate_to_team.value().equalsIgnoreCase("yes")|| allow_donors_to_donate_to_team.value().isEmpty()){
		
			performInteraction(driver, OperationType.CLICK, "Yes, donors can donate to a team", new ById("wcEventSetupWizard_rbEnableTeamDonation"),  null,  null);
			
			if(split_team_donations_amongst_team_members_or_assign_directly_to_team.value().equalsIgnoreCase("yes")|| split_team_donations_amongst_team_members_or_assign_directly_to_team.value().isEmpty()) 
			{
				performInteraction(driver, OperationType.CLICK, "Yes, split team donations amongst team members", new ById("wcEventSetupWizard_rblYesSplitTeamDonation"), null,  null);
			}
			else if(split_team_donations_amongst_team_members_or_assign_directly_to_team.value().equalsIgnoreCase("no"))
			{
				performInteraction(driver, OperationType.CLICK, "No, assign team donations directly to the team", new ById("wcEventSetupWizard_rblNoSplitTeamDonation"),  null,  null);	
			}
		}
		else if(allow_donors_to_donate_to_team.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, donors cannot donate directly to a team", new ById("wcEventSetupWizard_rbDisableTeamDonation"),  null,  null);	
		}
		
		
		if(registrants_to_be_able_to_sponsor_themselves.value().equalsIgnoreCase("yes") || registrants_to_be_able_to_sponsor_themselves.value().isEmpty())
		{
			performInteraction(driver, OperationType.CLICK, "Yes, a registrant can do a self-sponsor", new ById("wcEventSetupWizard_rblSelfSponsor_0"),  null,  null);	
		}
		else if(registrants_to_be_able_to_sponsor_themselves.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, a registrant cannot do a self-sponsor", new ById("wcEventSetupWizard_rblSelfSponsor_1"),  null,  null);
		}
		
		if(allow_donors_to_enter_offline_donations.value().equalsIgnoreCase("yes") || allow_donors_to_enter_offline_donations.value().isEmpty())
		{
			performInteraction(driver, OperationType.CLICK, "Yes, registrants can enter offline donations", new ById("wcEventSetupWizard_rblOfflineDonation_0"),  null,  null);
		}
		else if(allow_donors_to_enter_offline_donations.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, registrants cannot enter offline donations", new ById("wcEventSetupWizard_rblOfflineDonation_1"),  null,  null);
		}
		
		if(enforce_a_minimum_donation_amount.value().equalsIgnoreCase("yes") || enforce_a_minimum_donation_amount.value().isEmpty())
		{
			performInteraction(driver, OperationType.CLICK, "Yes, I would like to enforce a minimum donation amount", new ById("wcEventSetupWizard_rdbEnforceMinDonationAmount"),  null,  null);
			performInteraction(driver, OperationType.TYPE, "minimum donation amount", new ById("wcEventSetupWizard_tbMinDonationAmount"), minimum_donation_amount ,EventSetUpGenerator.minimumDonationAmount());
		}
		else if(enforce_a_minimum_donation_amount.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, I don’t want to enforce a minimum donation amount", new ById("wcEventSetupWizard_rdbNoMinDonationAmount"),  null,  null);	
		}
		
		if(donor_cover_the_processing_fee.value().equalsIgnoreCase("yes") || donor_cover_the_processing_fee.value().isEmpty())
		{
			performInteraction(driver, OperationType.CLICK, "Yes, I would like to ask donors if they want to cover the processing fee", new ById("wcEventSetupWizard_rbCoverProcessingFee"),  null,  null);	
			performInteraction(driver, OperationType.TYPE, "Percentage", new ById("wcEventSetupWizard_tbFeePercentage"),  processing_fee_percentage ,EventSetUpGenerator.processingFeePercentage());
			performInteraction(driver, OperationType.TYPE, "Amount", new ById("wcEventSetupWizard_tbFeeAmount"), processing_fee_amount ,EventSetUpGenerator.processingFeeAmount());
		}
		else if(donor_cover_the_processing_fee.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, I don’t want to ask donors to cover the processing fee", new ById("wcEventSetupWizard_rbNoProcessingFee"),  null,  null);
		}
		
		if(issue_tax_receipts_for_donations.value().equalsIgnoreCase("yes") || issue_tax_receipts_for_donations.value().isEmpty())
		{
			performInteraction(driver, OperationType.CLICK, "Yes, I would like to issue tax receipts", new ById("wcEventSetupWizard_rbIssueTaxReceipt"),  null,  null);	
			
			performInteraction(driver, OperationType.SELECT, "English Template", new ById("wcEventSetupWizard_ddlTaxReceiptTemplates"), tax_receipts_templete ,EventSetUpGenerator.taxReceiptsTemplete());
			performInteraction(driver, OperationType.SELECT, "French Template", new ById("wcEventSetupWizard_ddlTaxReceiptTemplatesFr"), tax_receipts_french_templete ,EventSetUpGenerator.taxReceiptsTempleteFr());
		}
		else if(issue_tax_receipts_for_donations.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, I don’t want to issue tax receipts", new ById("wcEventSetupWizard_rbNoTaxReceipt"),  null,  null);	
		}

		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);	
		
		//==========================================================================STEP 7=======================================================================================	
		
		int regTypeSize = (registration_types.size() > registration_types_number) ? registration_types.size() : registration_types_number;
		for(int index= 1; index <= regTypeSize; index++)
		{
			if(index > 2)
			{
				performInteraction(driver, OperationType.CLICK, "", new By.ByLinkText("Add another registration type"),  null,  null);	
			}
			
			PageObject registrationtype_name = registration_types.value(index, "registrationtype_name");
			PageObject registrationtype_display_name_en = registration_types.value(index, "registrationtype_display_name_en");
			PageObject registrationtype_display_name_fr = registration_types.value(index, "registrationtype_display_name_fr");
			PageObject registrationtype_export_id = registration_types.value(index, "registrationtype_export_id");
			PageObject registrationtype_description_en = registration_types.value(index, "registrationtype_description_en");
			PageObject registrationtype_description_fr = registration_types.value(index, "registrationtype_description_fr");
			PageObject registrationtype_is_fee_associated = registration_types.value(index, "registrationtype_is_fee_associated");
			PageObject registrationtype_fee = registration_types.value(index, "registrationtype_fee");
			
			performInteraction(driver, OperationType.TYPE, "RegistrationType Name_"+ index, new ById("wcEventSetupWizard_repeaterRegistrations_ctl0" + (index -1)+ "_txtRegistrationName"), registrationtype_name ,EventSetUpGenerator.registrationName());
			performInteraction(driver, OperationType.TYPE, "RegistrationType Display Name(English)_"+ index, new ById("wcEventSetupWizard_repeaterRegistrations_ctl0" + (index -1)+ "_txtRegistrationDisplayNameEn"), registrationtype_display_name_en ,EventSetUpGenerator.registrationTypeDisplayName());
			performInteraction(driver, OperationType.TYPE, "RegistrationType Display Name(French)_"+ index, new ById("wcEventSetupWizard_repeaterRegistrations_ctl0" + (index -1)+ "_txtRegistrationDisplayNameFr"), registrationtype_display_name_fr ,EventSetUpGenerator.registrationTypeDisplayNameFr());
			performInteraction(driver, OperationType.TYPE, "RegistrationType Export ID_"+ index, new ById("wcEventSetupWizard_repeaterRegistrations_ctl0" + (index -1)+ "_txtRegistrationExportID"), registrationtype_export_id ,EventSetUpGenerator.registrationTypeExportID());
			performInteraction(driver, OperationType.TYPE, "RegistrationType Description(English)_"+ index, new ById("wcEventSetupWizard_repeaterRegistrations_ctl0" + (index -1)+ "_txtRegistrationDescriptionEn"),  registrationtype_description_en ,EventSetUpGenerator.registrationTypeDescriptionEn());
			performInteraction(driver, OperationType.TYPE, "RegistrationType Description(French)_"+ index, new ById("wcEventSetupWizard_repeaterRegistrations_ctl0" + (index -1)+ "_txtRegistrationDescriptionFr"),  registrationtype_description_fr ,EventSetUpGenerator.registrationTypeDescriptionFr());
			
			if(registrationtype_is_fee_associated.value().equalsIgnoreCase("yes") || registrationtype_is_fee_associated.value().isEmpty())
			{
				performInteraction(driver, OperationType.CLICK, "Registration is associated", new ById("wcEventSetupWizard_repeaterRegistrations_ctl0" + (index -1)+ "_rbnRegistrationYes"),  null,  null);
				performInteraction(driver, OperationType.TYPE, "Yes", new ById("wcEventSetupWizard_repeaterRegistrations_ctl0" + (index -1)+ "_txtRegistrationFee"), registrationtype_fee ,EventSetUpGenerator.registrationTypeFee());
			}
			else if(registrationtype_is_fee_associated.value().equalsIgnoreCase("no"))
			{
				performInteraction(driver, OperationType.CLICK, "Registration is not associated", new ById("wcEventSetupWizard_rbNoTaxReceipt"),  null,  null);	
			}
		}
		
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);

		//==========================================================================STEP 8=======================================================================================
	
		performInteraction(driver, OperationType.TYPE, "English Waiver", new ById("wcEventSetupWizard_txtWaiverEn"), waiver_content_en ,EventSetUpGenerator.waiverContentEn());
		performInteraction(driver, OperationType.TYPE, "French Waiver", new ById("wcEventSetupWizard_txtWaiverFr"),  waiver_content_fr ,EventSetUpGenerator.waiverContentFr());
		
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);	
		
		//==========================================================================STEP 9=======================================================================================	

		performInteraction(driver, OperationType.FILEUPLOAD, "Header English", new ById("wcEventSetupWizard_ucImageLoaderHeader_fileUpload"),  header_en ,EventSetUpGenerator.headerEn());
		performInteraction(driver, OperationType.FILEUPLOAD, "Header French", new ById("wcEventSetupWizard_ucImageLoaderHeaderFr_fileUpload"), header_fr ,EventSetUpGenerator.headerFr());
		performInteraction(driver, OperationType.FILEUPLOAD, "Footer English", new ById("wcEventSetupWizard_ucImageLoaderFooter_fileUpload"),  footer_en ,EventSetUpGenerator.footerEn());
		performInteraction(driver, OperationType.FILEUPLOAD, "Footer French", new ById("wcEventSetupWizard_ucImageLoaderFooterFr_fileUpload"), footer_fr ,EventSetUpGenerator.footerFr());
		performInteraction(driver, OperationType.FILEUPLOAD, "Mobile Header English", new ById("wcEventSetupWizard_ucImageLoaderMobileHeader_fileUpload"), mobile_header_en ,EventSetUpGenerator.mobileHeaderEn());
		performInteraction(driver, OperationType.FILEUPLOAD, "Mobile Header French", new ById("wcEventSetupWizard_ucImageLoaderMobileHeaderFr_fileUpload"),mobile_header_fr ,EventSetUpGenerator.mobileHeaderFr());
		performInteraction(driver, OperationType.FILEUPLOAD, "Mobile Footer English", new ById("wcEventSetupWizard_ucImageLoaderMobileFooter_fileUpload"),  mobile_footer_en ,EventSetUpGenerator.mobileFooterEn());
		performInteraction(driver, OperationType.FILEUPLOAD, "Mobile Footer French", new ById("wcEventSetupWizard_ucImageLoaderMobileFooterFr_fileUpload"), mobile_footer_fr ,EventSetUpGenerator.mobileFooterFr());	
		
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);	
		
		//==========================================================================STEP 10=======================================================================================	

		performInteraction(driver, OperationType.CLICK, "Finish Button", new ById("wcEventSetupWizard_FinishNavigationTemplateContainerID_btnFinish"), null,  null);
		
		
	}

	/***************************************************************************************************************************************************************************
	 ***************************************************************************************************************************************************************************
	 *************************************************************************************************************************************************************************** 
	 *****************************************************************************DDEventSetUp*************************************************************************************** 
	 * @throws SeleniumException 
	 *************************************************************************************************************************************************************************** 
	 *************************************************************************************************************************************************************************** 
	 *************************************************************************************************************************************************************************** 
	 ***************************************************************************************************************************************************************************  
	 */
	
	public void DDEventSetUp(WebDriver driver) throws InvalidInputException, SeleniumException {	
			performInteraction(driver, OperationType.CLICK, "Direct Donation", new ById("wcEventSetupWizard_rblTemplateTypes_1"),  null,  null);
			performInteraction(driver, OperationType.CLICK,"Next Button", new ById("wcEventSetupWizard_StartNavigationTemplateContainerID_btNext"), null,  null);
			
			if(create_new_event.value().equalsIgnoreCase("yes") || create_new_event.value().isEmpty()){
				performInteraction(driver, OperationType.CLICK, "I want to create a new event", new ById("wcEventSetupWizard_rblNewOrCopy_0"),  null,  null);
				performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);
			}
			else if(create_new_event.value().equalsIgnoreCase("no")){		
				performInteraction(driver, OperationType.CLICK, "I want to create a copy of an existing event", new ById("wcEventSetupWizard_rblNewOrCopy_1"),  null,  null);
				performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);
			}
							
			//===========================================================================STEP 1======================================================================================
					
			if(language.value().equalsIgnoreCase("both") || language.value().isEmpty()){
				performInteraction(driver, OperationType.CLICK, "Yes, English and French pages", new ById("wcEventSetupWizard_rblLanguages_0"),  null,  null);	
			}
			else if(language.value().equalsIgnoreCase("english")){
				performInteraction(driver, OperationType.CLICK, "No, English pages only", new ById("wcEventSetupWizard_rblLanguages_1"),  null,  null);	
			}

			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);	
					
			//==========================================================================STEP 2=======================================================================================
				
			performInteraction(driver, OperationType.TYPE, "Event Name (English)", new ById("wcEventSetupWizard_tbEventNameEn"),  eventname_en ,EventSetUpGenerator.eventNameEn()); 
			performInteraction(driver, OperationType.TYPE, "Event Name (French)", new ById("wcEventSetupWizard_tbEventNameFr"),  eventname_fr ,EventSetUpGenerator.eventNameFr()); 

			performInteraction(driver, OperationType.TYPE, "Event Description (English)", new ById("wcEventSetupWizard_tbEventDescription"), description_en ,EventSetUpGenerator.descriptionEn());  
			performInteraction(driver, OperationType.TYPE, "Event Description (French)", new ById("wcEventSetupWizard_tbEventDescriptionFr"), description_fr ,EventSetUpGenerator.descriptionFr());

			performInteraction(driver, OperationType.TYPE, "Fundraising Goal", new ById("wcEventSetupWizard_tbFundraisingGoal"),fundraising_goal ,EventSetUpGenerator.fundraisingGoal());
					
			datePicker(driver, "Event Start", new ById("wcEventSetupWizard_tbEventStartDate"), 
					new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
					new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
					new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
					new By.ByXPath("/html/body/div[2]/div[1]"), event_start_date ,EventSetUpGenerator.eventStartDate()); 
			performInteraction(driver, OperationType.SELECT, "Event Start Time", new By.ByXPath("//*[@id=\"eventDatePair\"]/div[1]/span/select"), event_start_time ,EventSetUpGenerator.eventStartTime()); 
			
			
			datePicker(driver, "Event End", new ById("wcEventSetupWizard_tbEventEndDate"), 
					new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
					new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
					new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
					new By.ByXPath("/html/body/div[2]/div[1]"),event_end_date ,EventSetUpGenerator.eventEndDate());
			performInteraction(driver, OperationType.SELECT, "Event End Time", new By.ByXPath("//*[@id=\"eventDatePair\"]/div[2]/span/select"), event_end_time ,EventSetUpGenerator.eventEndTime()); 
			
			performInteraction(driver, OperationType.TYPE, "Registration End", new ById("wcEventSetupWizard_tbExportEventID"), export_eventid ,EventSetUpGenerator.eventExportID()); 

			performInteraction(driver, OperationType.SELECT, "Payment Gateway", new ById("wcEventSetupWizard_dlPayGateways"), payment_gateway ,EventSetUpGenerator.paymentGateway()); 
		
			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);	

			//==========================================================================STEP 3=======================================================================================	

			performInteraction(driver, OperationType.TYPE, "Sender Name", new ById("wcEventSetupWizard_tbSenderName"), sender_name ,EventSetUpGenerator.senderName());
			performInteraction(driver, OperationType.TYPE, "Reply to Email Address", new ById("wcEventSetupWizard_tbReplyTo"), reply_to_email ,EventSetUpGenerator.replyToEmail());
					
			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);	
					
			
			//==========================================================================STEP 4=======================================================================================	
			
			if(enforce_a_minimum_donation_amount.value().equalsIgnoreCase("yes") || enforce_a_minimum_donation_amount.value().isEmpty())
			{
				performInteraction(driver, OperationType.CLICK, "Yes, I would like to enforce a minimum donation amount", new ById("wcEventSetupWizard_rdbEnforceMinDonationAmount"), null,  null);	
				performInteraction(driver, OperationType.TYPE, "minimum donation amount", new ById("wcEventSetupWizard_tbMinDonationAmount"), minimum_donation_amount ,EventSetUpGenerator.minimumDonationAmount());
			}
			else if(enforce_a_minimum_donation_amount.value().equalsIgnoreCase("no"))
			{
				performInteraction(driver, OperationType.CLICK, "No, I don’t want to enforce a minimum donation amount", new ById("wcEventSetupWizard_rdbNoMinDonationAmount"),  null,  null);	
			}
			
			if(donor_cover_the_processing_fee.value().equalsIgnoreCase("yes") || donor_cover_the_processing_fee.value().isEmpty())
			{
				performInteraction(driver, OperationType.CLICK, "Yes, I would like to ask donors if they want to cover the processing fee", new ById("wcEventSetupWizard_rbCoverProcessingFee"),  null,  null);	
				performInteraction(driver, OperationType.TYPE, "Percentage", new ById("wcEventSetupWizard_tbFeePercentage"),  processing_fee_percentage ,EventSetUpGenerator.processingFeePercentage());
				performInteraction(driver, OperationType.TYPE, "Amount", new ById("wcEventSetupWizard_tbFeeAmount"),  processing_fee_amount ,EventSetUpGenerator.processingFeeAmount());
			}
			else if(donor_cover_the_processing_fee.value().equalsIgnoreCase("no"))
			{
				performInteraction(driver, OperationType.CLICK, "No, I don’t want to ask donors to cover the processing fee", new ById("wcEventSetupWizard_rbNoProcessingFee"),  null,  null);	
			}
			
			if(issue_tax_receipts_for_donations.value().equalsIgnoreCase("yes") || issue_tax_receipts_for_donations.value().isEmpty())
			{
				performInteraction(driver, OperationType.CLICK, "Yes, I would like to issue tax receipts", new ById("wcEventSetupWizard_rbIssueTaxReceipt"),  null,  null);	
				
				performInteraction(driver, OperationType.SELECT, "English Template", new ById("wcEventSetupWizard_ddlTaxReceiptTemplates"), tax_receipts_templete ,EventSetUpGenerator.taxReceiptsTemplete());
				performInteraction(driver, OperationType.SELECT, "French Template", new ById("wcEventSetupWizard_ddlTaxReceiptTemplatesFr"), tax_receipts_french_templete ,EventSetUpGenerator.taxReceiptsTempleteFr());
			}
			else if(issue_tax_receipts_for_donations.value().equalsIgnoreCase("no"))
			{
				performInteraction(driver, OperationType.CLICK, "No, I don’t want to issue tax receipts", new ById("wcEventSetupWizard_rbNoTaxReceipt"),  null,  null);	
			}

			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);	
			
			//==========================================================================STEP 5=======================================================================================	
			
			performInteraction(driver, OperationType.FILEUPLOAD, "Header English", new ById("wcEventSetupWizard_ucImageLoaderHeader_fileUpload"), header_en ,EventSetUpGenerator.headerEn());
			performInteraction(driver, OperationType.FILEUPLOAD, "Header French", new ById("wcEventSetupWizard_ucImageLoaderHeaderFr_fileUpload"), header_fr ,EventSetUpGenerator.headerFr());
			performInteraction(driver, OperationType.FILEUPLOAD, "Footer English", new ById("wcEventSetupWizard_ucImageLoaderFooter_fileUpload"),  footer_en ,EventSetUpGenerator.footerEn());
			performInteraction(driver, OperationType.FILEUPLOAD, "Footer French", new ById("wcEventSetupWizard_ucImageLoaderFooterFr_fileUpload"), footer_fr ,EventSetUpGenerator.footerFr());
			performInteraction(driver, OperationType.FILEUPLOAD, "Mobile Header English", new ById("wcEventSetupWizard_ucImageLoaderMobileHeader_fileUpload"),  mobile_header_en ,EventSetUpGenerator.mobileHeaderEn());
			performInteraction(driver, OperationType.FILEUPLOAD, "Mobile Header French", new ById("wcEventSetupWizard_ucImageLoaderMobileHeaderFr_fileUpload"), mobile_header_fr ,EventSetUpGenerator.mobileHeaderFr());
			performInteraction(driver, OperationType.FILEUPLOAD, "Mobile Footer English", new ById("wcEventSetupWizard_ucImageLoaderMobileFooter_fileUpload"),  mobile_footer_en ,EventSetUpGenerator.mobileFooterEn());
			performInteraction(driver, OperationType.FILEUPLOAD, "Mobile Footer French", new ById("wcEventSetupWizard_ucImageLoaderMobileFooterFr_fileUpload"), mobile_footer_fr ,EventSetUpGenerator.mobileFooterFr());	
			
			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);	
			
			//==========================================================================STEP 6=======================================================================================	

			performInteraction(driver, OperationType.CLICK, "Finish Button", new ById("wcEventSetupWizard_FinishNavigationTemplateContainerID_btnFinish"),  null,  null);	
	}
	
	/***************************************************************************************************************************************************************************
	 ***************************************************************************************************************************************************************************
	 *************************************************************************************************************************************************************************** 
	 *****************************************************************************TRIBUTEEventSetUp*************************************************************************************** 
	 * @throws SeleniumException 
	 *************************************************************************************************************************************************************************** 
	 *************************************************************************************************************************************************************************** 
	 *************************************************************************************************************************************************************************** 
	 ***************************************************************************************************************************************************************************  
	 */
	
	public void TRIBUTEEventSetUp(WebDriver driver) throws InvalidInputException, SeleniumException {	
		performInteraction(driver, OperationType.CLICK, "Tribute", new ById("wcEventSetupWizard_rblTemplateTypes_2"),  null,  null);
		performInteraction(driver, OperationType.CLICK,"Next Button", new ById("wcEventSetupWizard_StartNavigationTemplateContainerID_btNext"),  null,  null);
			
		if(create_new_event.value().equalsIgnoreCase("yes") || create_new_event.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "I want to create a new event", new ById("wcEventSetupWizard_rblNewOrCopy_0"),  null,  null);	
			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);
		}
		else if(create_new_event.value().equalsIgnoreCase("no")){		
			performInteraction(driver, OperationType.CLICK, "I want to create a copy of an existing event", new ById("wcEventSetupWizard_rblNewOrCopy_1"),  null,  null);
			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null,  null);
		}
		//===========================================================================STEP 1======================================================================================
				
		if(language.value().equalsIgnoreCase("both") || language.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Yes, English and French pages", new ById("wcEventSetupWizard_rblLanguages_0"),  null,  null);	
		}
		else if(language.value().equalsIgnoreCase("english")){
			performInteraction(driver, OperationType.CLICK, "No, English pages only", new ById("wcEventSetupWizard_rblLanguages_1"),  null,  null);	
		}
					
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);	
				
		//==========================================================================STEP 2=======================================================================================
			
		performInteraction(driver, OperationType.TYPE, "Event Name (English)", new ById("wcEventSetupWizard_tbEventNameEn"), eventname_en ,EventSetUpGenerator.eventNameEn()); 
		performInteraction(driver, OperationType.TYPE, "Event Name (French)", new ById("wcEventSetupWizard_tbEventNameFr"), eventname_fr ,EventSetUpGenerator.eventNameFr()); 

		performInteraction(driver, OperationType.TYPE, "Event Description (English)", new ById("wcEventSetupWizard_tbEventDescription"),  description_en ,EventSetUpGenerator.descriptionEn());  
		performInteraction(driver, OperationType.TYPE, "Event Description (French)", new ById("wcEventSetupWizard_tbEventDescriptionFr"), description_fr ,EventSetUpGenerator.descriptionFr());

		performInteraction(driver, OperationType.TYPE, "Fundraising Goal", new ById("wcEventSetupWizard_tbFundraisingGoal"),fundraising_goal ,EventSetUpGenerator.fundraisingGoal());
				
		datePicker(driver, "Event Start", new ById("wcEventSetupWizard_tbEventStartDate"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
				new By.ByXPath("/html/body/div[2]/div[1]"),event_start_date ,EventSetUpGenerator.eventStartDate()); 
		performInteraction(driver, OperationType.SELECT, "Event Start Time", new By.ByXPath("//*[@id=\"eventDatePair\"]/div[1]/span/select"), event_start_time ,EventSetUpGenerator.eventStartTime()); 
		
		
		datePicker(driver, "Event End", new ById("wcEventSetupWizard_tbEventEndDate"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
				new By.ByXPath("/html/body/div[2]/div[1]"), event_end_date ,EventSetUpGenerator.eventEndDate());
		performInteraction(driver, OperationType.SELECT, "Event End Time", new By.ByXPath("//*[@id=\"eventDatePair\"]/div[2]/span/select"),  event_end_time ,EventSetUpGenerator.eventEndTime()); 

		performInteraction(driver, OperationType.TYPE, "Registration End", new ById("wcEventSetupWizard_tbExportEventID"),export_eventid ,EventSetUpGenerator.eventExportID()); 

		performInteraction(driver, OperationType.SELECT, "Payment Gateway", new ById("wcEventSetupWizard_dlPayGateways"), payment_gateway ,EventSetUpGenerator.paymentGateway()); 
	
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);	

		//==========================================================================STEP 3=======================================================================================	

		performInteraction(driver, OperationType.TYPE, "Sender Name", new ById("wcEventSetupWizard_tbSenderName"), sender_name ,EventSetUpGenerator.senderName());
		performInteraction(driver, OperationType.TYPE, "Reply to Email Address", new ById("wcEventSetupWizard_tbReplyTo"), reply_to_email ,EventSetUpGenerator.replyToEmail());
				
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);
				
		
		//==========================================================================STEP 4=======================================================================================	

		if(enforce_a_minimum_donation_amount.value().equalsIgnoreCase("yes") || enforce_a_minimum_donation_amount.value().isEmpty())
		{
			performInteraction(driver, OperationType.CLICK, "Yes, I would like to enforce a minimum donation amount", new ById("wcEventSetupWizard_rdbEnforceMinDonationAmount"),  null,  null);		
			performInteraction(driver, OperationType.TYPE, "minimum donation amount", new ById("wcEventSetupWizard_tbMinDonationAmount"), minimum_donation_amount ,EventSetUpGenerator.minimumDonationAmount());
		}
		else if(enforce_a_minimum_donation_amount.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, I don’t want to enforce a minimum donation amount", new ById("wcEventSetupWizard_rdbNoMinDonationAmount"), null,  null);	
		}
		
		if(donor_cover_the_processing_fee.value().equalsIgnoreCase("yes") || donor_cover_the_processing_fee.value().isEmpty())
		{
			performInteraction(driver, OperationType.CLICK, "Yes, I would like to ask donors if they want to cover the processing fee", new ById("wcEventSetupWizard_rbCoverProcessingFee"),  null,  null);	
			performInteraction(driver, OperationType.TYPE, "Percentage", new ById("wcEventSetupWizard_tbFeePercentage"), processing_fee_percentage ,EventSetUpGenerator.processingFeePercentage());
			performInteraction(driver, OperationType.TYPE, "Amount", new ById("wcEventSetupWizard_tbFeeAmount"), processing_fee_amount ,EventSetUpGenerator.processingFeeAmount());
		}
		else if(donor_cover_the_processing_fee.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, I don’t want to ask donors to cover the processing fee", new ById("wcEventSetupWizard_rbNoProcessingFee"),  null,  null);	
		}
		
		if(issue_tax_receipts_for_donations.value().equalsIgnoreCase("yes") || issue_tax_receipts_for_donations.value().isEmpty())
		{
			performInteraction(driver, OperationType.CLICK, "Yes, I would like to issue tax receipts", new ById("wcEventSetupWizard_rbIssueTaxReceipt"), null,  null);	
			
			performInteraction(driver, OperationType.SELECT, "English Template", new ById("wcEventSetupWizard_ddlTaxReceiptTemplates"), tax_receipts_templete ,EventSetUpGenerator.taxReceiptsTemplete());
			performInteraction(driver, OperationType.SELECT, "French Template", new ById("wcEventSetupWizard_ddlTaxReceiptTemplatesFr"), tax_receipts_french_templete ,EventSetUpGenerator.taxReceiptsTempleteFr());
		}
		else if(issue_tax_receipts_for_donations.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, I don’t want to issue tax receipts", new ById("wcEventSetupWizard_rbNoTaxReceipt"), null,  null);
		}

		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null,  null);	
		
		//==========================================================================STEP 5=======================================================================================	

		performInteraction(driver, OperationType.FILEUPLOAD, "Header English", new ById("wcEventSetupWizard_ucImageLoaderHeader_fileUpload"), header_en ,EventSetUpGenerator.headerEn());
		performInteraction(driver, OperationType.FILEUPLOAD, "Header French", new ById("wcEventSetupWizard_ucImageLoaderHeaderFr_fileUpload"), header_fr ,EventSetUpGenerator.headerFr());
		performInteraction(driver, OperationType.FILEUPLOAD, "Footer English", new ById("wcEventSetupWizard_ucImageLoaderFooter_fileUpload"),  footer_en ,EventSetUpGenerator.footerEn());
		performInteraction(driver, OperationType.FILEUPLOAD, "Footer French", new ById("wcEventSetupWizard_ucImageLoaderFooterFr_fileUpload"), footer_fr ,EventSetUpGenerator.footerFr());

		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null,  null);	
		
		//==========================================================================STEP 6=======================================================================================	

		performInteraction(driver, OperationType.CLICK, "Finish Button", new ById("wcEventSetupWizard_FinishNavigationTemplateContainerID_btnFinish"), null,  null);
	}
	
	/***************************************************************************************************************************************************************************
	 ***************************************************************************************************************************************************************************
	 *************************************************************************************************************************************************************************** 
	 *****************************************************************************PIEventSetUp**********************************************************************************
	 *************************************************************************************************************************************************************************** 
	 *************************************************************************************************************************************************************************** 
	 *************************************************************************************************************************************************************************** 
	 ***************************************************************************************************************************************************************************  
	 */
	
	public void PIEventSetUp(WebDriver driver) throws InvalidInputException, SeleniumException {	
		performInteraction(driver, OperationType.CLICK, "Purchase Items", new ById("wcEventSetupWizard_rblTemplateTypes_3"), null,  null);
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StartNavigationTemplateContainerID_btNext"), null,  null);
		
		if(create_new_event.value().equalsIgnoreCase("yes") || create_new_event.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "I want to create a new event", new ById("wcEventSetupWizard_rblNewOrCopy_0"), null,  null);
			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"),  null,  null);
		}
		else if(create_new_event.value().equalsIgnoreCase("no")){		
			performInteraction(driver, OperationType.CLICK, "I want to create a copy of an existing event", new ById("wcEventSetupWizard_rblNewOrCopy_1"), null,  null);
			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null,  null);
		}
					
		//===========================================================================STEP 1======================================================================================
				
		if(language.value().equalsIgnoreCase("both") || language.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Yes, English and French pages", new ById("wcEventSetupWizard_rblLanguages_0"),  null,  null);	
		}
		else if(language.value().equalsIgnoreCase("english")){
			performInteraction(driver, OperationType.CLICK, "No, English pages only", new ById("wcEventSetupWizard_rblLanguages_1"), null,  null);
		}
					
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null,  null);	
				
		//==========================================================================STEP 2=======================================================================================
			
		performInteraction(driver, OperationType.TYPE, "Event Name (English)", new ById("wcEventSetupWizard_tbEventNameEn"), eventname_en ,EventSetUpGenerator.eventNameEn()); 
		performInteraction(driver, OperationType.TYPE, "Event Name (French)", new ById("wcEventSetupWizard_tbEventNameFr"),  eventname_fr ,EventSetUpGenerator.eventNameFr()); 

		performInteraction(driver, OperationType.TYPE, "Event Description (English)", new ById("wcEventSetupWizard_tbEventDescription"), description_en ,EventSetUpGenerator.descriptionEn());  
		performInteraction(driver, OperationType.TYPE, "Event Description (French)", new ById("wcEventSetupWizard_tbEventDescriptionFr"),  description_fr ,EventSetUpGenerator.descriptionFr());

		performInteraction(driver, OperationType.TYPE, "Fundraising Goal", new ById("wcEventSetupWizard_tbFundraisingGoal"), fundraising_goal ,EventSetUpGenerator.fundraisingGoal());
				
		datePicker(driver, "Event Start", new ById("wcEventSetupWizard_tbEventStartDate"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
				new By.ByXPath("/html/body/div[2]/div[1]"), event_start_date ,EventSetUpGenerator.eventStartDate()); 
		performInteraction(driver, OperationType.SELECT, "Event Start Time", new By.ByXPath("//*[@id=\"eventDatePair\"]/div[1]/span/select"), event_start_time ,EventSetUpGenerator.eventStartTime()); 
		
		
		datePicker(driver, "Event End", new ById("wcEventSetupWizard_tbEventEndDate"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
				new By.ByXPath("/html/body/div[2]/div[1]"), event_end_date ,EventSetUpGenerator.eventEndDate());
		performInteraction(driver, OperationType.SELECT, "Event End Time", new By.ByXPath("//*[@id=\"eventDatePair\"]/div[2]/span/select"), event_end_time ,EventSetUpGenerator.eventEndTime()); 

		performInteraction(driver, OperationType.TYPE, "Registration End", new ById("wcEventSetupWizard_tbExportEventID"), export_eventid ,EventSetUpGenerator.eventExportID()); 

		performInteraction(driver, OperationType.SELECT, "Payment Gateway", new ById("wcEventSetupWizard_dlPayGateways"),  payment_gateway ,EventSetUpGenerator.paymentGateway()); 
	
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null,  null);	

		//==========================================================================STEP 3=======================================================================================	

		performInteraction(driver, OperationType.TYPE, "Sender Name", new ById("wcEventSetupWizard_tbSenderName"), sender_name ,EventSetUpGenerator.senderName());
		performInteraction(driver, OperationType.TYPE, "Reply to Email Address", new ById("wcEventSetupWizard_tbReplyTo"), reply_to_email ,EventSetUpGenerator.replyToEmail());
				
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null,  null);	
				
		
		//==========================================================================STEP 4=======================================================================================	

		if(enforce_a_minimum_donation_amount.value().equalsIgnoreCase("yes") || enforce_a_minimum_donation_amount.value().isEmpty())
		{
			performInteraction(driver, OperationType.CLICK, "Yes, I would like to enforce a minimum donation amount", new ById("wcEventSetupWizard_rdbEnforceMinDonationAmount"), null,  null);
			performInteraction(driver, OperationType.TYPE, "minimum donation amount", new ById("wcEventSetupWizard_tbMinDonationAmount"),  minimum_donation_amount ,EventSetUpGenerator.minimumDonationAmount());
		}
		else if(enforce_a_minimum_donation_amount.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, I don’t want to enforce a minimum donation amount", new ById("wcEventSetupWizard_rdbNoMinDonationAmount"),  null,  null);
		}
		
		if(donor_cover_the_processing_fee.value().equalsIgnoreCase("yes") || donor_cover_the_processing_fee.value().isEmpty())
		{
			performInteraction(driver, OperationType.CLICK, "Yes, I would like to ask donors if they want to cover the processing fee", new ById("wcEventSetupWizard_rbCoverProcessingFee"),  null,  null);	
			performInteraction(driver, OperationType.TYPE, "Percentage", new ById("wcEventSetupWizard_tbFeePercentage"), processing_fee_percentage ,EventSetUpGenerator.processingFeePercentage());
			performInteraction(driver, OperationType.TYPE, "Amount", new ById("wcEventSetupWizard_tbFeeAmount"), processing_fee_amount ,EventSetUpGenerator.processingFeeAmount());
		}
		else if(donor_cover_the_processing_fee.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, I don’t want to ask donors to cover the processing fee", new ById("wcEventSetupWizard_rbNoProcessingFee"), null,  null);
		}
		
		if(issue_tax_receipts_for_donations.value().equalsIgnoreCase("yes") || issue_tax_receipts_for_donations.value().isEmpty())
		{
			performInteraction(driver, OperationType.CLICK, "Yes, I would like to issue tax receipts", new ById("wcEventSetupWizard_rbIssueTaxReceipt"), null,  null);	
			
			performInteraction(driver, OperationType.SELECT, "English Template", new ById("wcEventSetupWizard_ddlTaxReceiptTemplates"), tax_receipts_templete ,EventSetUpGenerator.taxReceiptsTemplete());
			performInteraction(driver, OperationType.SELECT, "French Template", new ById("wcEventSetupWizard_ddlTaxReceiptTemplatesFr"), tax_receipts_french_templete ,EventSetUpGenerator.taxReceiptsTempleteFr());
		}
		else if(issue_tax_receipts_for_donations.value().equalsIgnoreCase("no"))
		{
			performInteraction(driver, OperationType.CLICK, "No, I don’t want to issue tax receipts", new ById("wcEventSetupWizard_rbNoTaxReceipt"), null, null);
		}

		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null, null);	
		
		//==========================================================================STEP 5=======================================================================================	

		performInteraction(driver, OperationType.FILEUPLOAD, "Header English", new ById("wcEventSetupWizard_ucImageLoaderHeader_fileUpload"),  header_en ,EventSetUpGenerator.headerEn());
		performInteraction(driver, OperationType.FILEUPLOAD, "Header French", new ById("wcEventSetupWizard_ucImageLoaderHeaderFr_fileUpload"), header_fr ,EventSetUpGenerator.headerFr());
		performInteraction(driver, OperationType.FILEUPLOAD, "Footer English", new ById("wcEventSetupWizard_ucImageLoaderFooter_fileUpload"), footer_en ,EventSetUpGenerator.footerEn());
		performInteraction(driver, OperationType.FILEUPLOAD, "Footer French", new ById("wcEventSetupWizard_ucImageLoaderFooterFr_fileUpload"),  footer_fr ,EventSetUpGenerator.footerFr());

		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null, null);	
		
		//==========================================================================STEP 6=======================================================================================	

		performInteraction(driver, OperationType.CLICK, "Finish Button", new ById("wcEventSetupWizard_FinishNavigationTemplateContainerID_btnFinish"), null, null);
	}
	
	/***************************************************************************************************************************************************************************
	 ***************************************************************************************************************************************************************************
	 *************************************************************************************************************************************************************************** 
	 *****************************************************************************PETEventSetUp*************************************************************************************** 
	 * @throws SeleniumException 
	 *************************************************************************************************************************************************************************** 
	 *************************************************************************************************************************************************************************** 
	 *************************************************************************************************************************************************************************** 
	 ***************************************************************************************************************************************************************************  
	 */
	
	public void PETEventSetUp(WebDriver driver) throws InvalidInputException, SeleniumException {	
		performInteraction(driver, OperationType.CLICK, "Personal Event/Campaign", new ById("wcEventSetupWizard_rblTemplateTypes_4"), null, null);
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StartNavigationTemplateContainerID_btNext"), null, null);
		
		if(create_new_event.value().equalsIgnoreCase("yes") || create_new_event.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "I want to create a new event", new ById("wcEventSetupWizard_rblNewOrCopy_0"),  null, null);
			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null, null);
		}
		else if(create_new_event.value().equalsIgnoreCase("no")){		
			performInteraction(driver, OperationType.CLICK, "I want to create a copy of an existing event", new ById("wcEventSetupWizard_rblNewOrCopy_1"), null, null);	
			performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null, null);
		}
		
		//===========================================================================STEP 1======================================================================================
		
		if(language.value().equalsIgnoreCase("both") || language.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Yes, English and French pages", new ById("wcEventSetupWizard_rblLanguages_0"), null, null);	
		}
		else if(language.value().equalsIgnoreCase("english")){
			performInteraction(driver, OperationType.CLICK, "No, English pages only", new ById("wcEventSetupWizard_rblLanguages_1"), null, null);	
		}
				
		if(participants_required_to_accept_a_waiver.value().equalsIgnoreCase("yes")|| participants_required_to_accept_a_waiver.value().isEmpty()){
			performInteraction(driver, OperationType.CLICK, "Yes, participants are required to accept a waiver", new ById("wcEventSetupWizard_rblWaiver_0"), null, null);	
		}
		else if(participants_required_to_accept_a_waiver.value().equalsIgnoreCase("no")){
			performInteraction(driver, OperationType.CLICK, "No, participants are not required to accept a waiver", new ById("wcEventSetupWizard_rblWaiver_1"), null, null);	
		}

		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null, null);	
				
		//==========================================================================STEP 2=======================================================================================
			
		performInteraction(driver, OperationType.TYPE, "Event Name (English)", new ById("wcEventSetupWizard_tbEventNameEn"), eventname_en ,EventSetUpGenerator.eventNameEn()); 
		performInteraction(driver, OperationType.TYPE, "Event Name (French)", new ById("wcEventSetupWizard_tbEventNameFr"), eventname_fr ,EventSetUpGenerator.eventNameFr()); 

		performInteraction(driver, OperationType.TYPE, "Event Description (English)", new ById("wcEventSetupWizard_tbEventDescription"), description_en ,EventSetUpGenerator.descriptionEn());  
		performInteraction(driver, OperationType.TYPE, "Event Description (French)", new ById("wcEventSetupWizard_tbEventDescriptionFr"),  description_fr ,EventSetUpGenerator.descriptionFr());

		performInteraction(driver, OperationType.TYPE, "Fundraising Goal", new ById("wcEventSetupWizard_tbFundraisingGoal"), fundraising_goal ,EventSetUpGenerator.fundraisingGoal());
				
		datePicker(driver, "Event Start", new ById("wcEventSetupWizard_tbEventStartDate"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
				new By.ByXPath("/html/body/div[2]/div[1]"), event_start_date ,EventSetUpGenerator.eventStartDate()); 
		performInteraction(driver, OperationType.SELECT, "Event Start Time", new By.ByXPath("//*[@id=\"eventDatePair\"]/div[1]/span/select"), event_start_time ,EventSetUpGenerator.eventStartTime()); 
		
		
		datePicker(driver, "Event End", new ById("wcEventSetupWizard_tbEventEndDate"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
				new By.ByXPath("/html/body/div[2]/div[1]"), event_end_date ,EventSetUpGenerator.eventEndDate());
		performInteraction(driver, OperationType.SELECT, "Event End Time", new By.ByXPath("//*[@id=\"eventDatePair\"]/div[2]/span/select"),event_end_time ,EventSetUpGenerator.eventEndTime()); 
		
		datePicker(driver, "Registration Start", new ById("wcEventSetupWizard_tbRegistrationStartDate"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
				new By.ByXPath("/html/body/div[2]/div[1]"), registration_start_date ,EventSetUpGenerator.regStartDate()); 
		performInteraction(driver, OperationType.SELECT, "Registration Start Time", new By.ByXPath("//*[@id=\"wcEventSetupWizard_divRegistrationStart\"]/span/select"), registration_start_time ,EventSetUpGenerator.regStartTime()); 
		
		datePicker(driver, "Registration End", new ById("wcEventSetupWizard_tbRegistrationEndDate"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]"), 
				new By.ByXPath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]"), 
				new By.ByXPath("/html/body/div[2]/div[1]"), registration_end_date ,EventSetUpGenerator.regEndDate()); 
		performInteraction(driver, OperationType.SELECT, "Registration End Time", new By.ByXPath("//*[@id=\"wcEventSetupWizard_divRegistrationEnd\"]/span/select"),registration_end_time ,EventSetUpGenerator.regEndTime()); 
		

		performInteraction(driver, OperationType.TYPE, "Registration End", new ById("wcEventSetupWizard_tbExportEventID"), export_eventid ,EventSetUpGenerator.eventExportID()); 

		performInteraction(driver, OperationType.SELECT, "Payment Gateway", new ById("wcEventSetupWizard_dlPayGateways"),payment_gateway ,EventSetUpGenerator.paymentGateway()); 
	
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null, null);	

		//==========================================================================STEP 3=======================================================================================	
		
		performInteraction(driver, OperationType.TYPE, "Sender Name", new ById("wcEventSetupWizard_tbSenderName"), sender_name ,EventSetUpGenerator.senderName());
		performInteraction(driver, OperationType.TYPE, "Reply to Email Address", new ById("wcEventSetupWizard_tbReplyTo"),  reply_to_email ,EventSetUpGenerator.replyToEmail());
				
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null, null);	
				
		
		//==========================================================================STEP 4=======================================================================================	
	
		performInteraction(driver, OperationType.TYPE, "English Waiver", new ById("wcEventSetupWizard_txtWaiverEn"), waiver_content_en ,EventSetUpGenerator.waiverContentEn());
		performInteraction(driver, OperationType.TYPE, "French Waiver", new ById("wcEventSetupWizard_txtWaiverFr"),  waiver_content_fr ,EventSetUpGenerator.waiverContentFr());
		
		performInteraction(driver, OperationType.CLICK, "Next Button", new ById("wcEventSetupWizard_StepNavigationTemplateContainerID_btNext"), null, null);	
		
		//==========================================================================STEP 5=======================================================================================	

		performInteraction(driver, OperationType.CLICK, "Finish Button", new ById("wcEventSetupWizard_FinishNavigationTemplateContainerID_btnFinish"), null, null);
				
				
	}
	
}
